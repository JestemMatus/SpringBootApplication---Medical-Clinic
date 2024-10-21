package pl.dmcs.amatuszewski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.Visits;
import pl.dmcs.amatuszewski.repository.VisitsRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service("visitsService")
public class VisitsServiceImpl implements VisitsService {

    private final VisitsRepository visitsRepository;

    @Autowired
    public VisitsServiceImpl(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    @Transactional
    @Override
    public void addVisit(Visits visit) {
        visitsRepository.save(visit);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Visits> listVisits() {
        return visitsRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Visits> listVisitsByPatient(long patientId) {
        return visitsRepository.findByPatientId(patientId);
    }

    @Transactional(readOnly = true)
    @Override
    public Visits getVisit(long id) {
        return visitsRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void updateVisit(Visits visit) {
        visitsRepository.save(visit);
    }

    @Transactional
    @Override
    public void deleteVisit(long id) {
        visitsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isDoctorAvailable(long doctorId, Date date, Time time) {
        List<Visits> visits = visitsRepository.findByDoctorIdAndDateAndTime(doctorId, date, time);
        return visits.isEmpty();
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void deleteUnpaidVisits() {
        List<Visits> visits = visitsRepository.findAll();
        for (Visits visit : visits) {
            if (!visit.getIsPayed()) {
                visitsRepository.delete(visit);
            }
        }
    }
}
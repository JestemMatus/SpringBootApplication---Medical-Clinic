package pl.dmcs.amatuszewski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.Prescription;
import pl.dmcs.amatuszewski.repository.PrescriptionRepository;

import java.util.ArrayList;
import java.util.List;

@Service("prescriptionService")
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    @Transactional
    @Override
    public void addPrescription(Prescription prescription) {
        if (prescription.getMedicines() == null) {
            prescription.setMedicines(new ArrayList<>());
        }
        prescriptionRepository.save(prescription);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Prescription> listPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        for (Prescription prescription : prescriptions) {
            prescription.getMedicines().size();
        }
        return prescriptions;
    }

    @Transactional(readOnly = true)
    @Override
    public Prescription getPrescription(long id) {
        Prescription prescription = prescriptionRepository.findById(id).orElse(null);
        if (prescription != null) {
            prescription.getMedicines().size();
        }
        return prescription;
    }

    @Transactional
    @Override
    public void updatePrescription(Prescription prescription) {
        if (prescription.getMedicines() == null) {
            prescription.setMedicines(new ArrayList<>());
        }
        prescriptionRepository.save(prescription);
    }

    @Transactional
    @Override
    public void deletePrescription(long id) {
        prescriptionRepository.deleteById(id);
    }
}

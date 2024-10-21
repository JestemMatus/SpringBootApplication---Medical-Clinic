package pl.dmcs.amatuszewski.service;

import pl.dmcs.amatuszewski.domain.Visits;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface VisitsService {
    void addVisit(Visits visit);
    List<Visits> listVisits();
    List<Visits> listVisitsByPatient(long patientId);
    Visits getVisit(long id);
    void updateVisit(Visits visit);
    void deleteVisit(long id);

    boolean isDoctorAvailable(long doctorId, Date date, Time time);

    void deleteUnpaidVisits();


}

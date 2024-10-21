package pl.dmcs.amatuszewski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.amatuszewski.domain.Visits;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface VisitsRepository extends JpaRepository<Visits, Long> {
    List<Visits> findByDoctorIdAndDateAndTime(long doctorId, Date date, Time time); // Nowa metoda
    List<Visits> findByPatientId(long patientId);

    List<Visits> findByIsPayedFalse();

}


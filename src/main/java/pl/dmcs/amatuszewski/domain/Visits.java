package pl.dmcs.amatuszewski.domain;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "visits")
public class Visits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isPayed;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private AppUser doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private AppUser patient;

    @ManyToOne
    @JoinColumn(name = "visit_type_id")
    private VisitType visitType;

    private Date date;

    private Time time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public AppUser getDoctor() {
        return doctor;
    }

    public void setDoctor(AppUser doctor) {
        this.doctor = doctor;
    }

    public AppUser getPatient() {
        return patient;
    }

    public void setPatient(AppUser patient) {
        this.patient = patient;
    }

    public VisitType getVisitType() {
        return visitType;
    }

    public void setVisitType(VisitType visitType) {
        this.visitType = visitType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Visits{" +
                "id=" + id +
                ", isPayed=" + isPayed +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", visitType=" + visitType +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}

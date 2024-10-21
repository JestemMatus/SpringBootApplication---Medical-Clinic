package pl.dmcs.amatuszewski.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "prescription_medicines", joinColumns = @JoinColumn(name = "prescription_id"))
    @Column(name = "medicine")
    private List<String> medicines = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private AppUser doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private AppUser patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<String> medicines) {
        this.medicines = medicines;
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


    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", medicines=" + medicines +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}

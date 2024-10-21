package pl.dmcs.amatuszewski.service;

import pl.dmcs.amatuszewski.domain.Prescription;

import java.util.List;

public interface PrescriptionService {
    void addPrescription(Prescription prescription);
    List<Prescription> listPrescriptions();
    Prescription getPrescription(long id);
    void updatePrescription(Prescription prescription);
    void deletePrescription(long id);
}

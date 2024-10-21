package pl.dmcs.amatuszewski.service;

import pl.dmcs.amatuszewski.domain.VisitType;

import java.util.List;

public interface VisitTypeService {
    void addVisitType(VisitType visitType);
    List<VisitType> listVisitTypes();
    VisitType getVisitType(long id);
    void updateVisitType(VisitType visitType);
    void deleteVisitType(long id);
}

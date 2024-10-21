package pl.dmcs.amatuszewski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.amatuszewski.domain.VisitType;
import pl.dmcs.amatuszewski.repository.VisitTypeRepository;

import java.util.List;

@Service
public class VisitTypeServiceImpl implements VisitTypeService {

    private final VisitTypeRepository visitTypeRepository;

    @Autowired
    public VisitTypeServiceImpl(VisitTypeRepository visitTypeRepository) {
        this.visitTypeRepository = visitTypeRepository;
    }

    @Transactional
    @Override
    public void addVisitType(VisitType visitType) {
        visitTypeRepository.save(visitType);
    }

    @Transactional(readOnly = true)
    @Override
    public List<VisitType> listVisitTypes() {
        return visitTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public VisitType getVisitType(long id) {
        return visitTypeRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void updateVisitType(VisitType visitType) {
        VisitType existingVisitType = visitTypeRepository.findById(visitType.getId()).orElseThrow(() -> new IllegalArgumentException("Visit type not found"));
        existingVisitType.setServiceName(visitType.getServiceName());
        existingVisitType.setServicePrice(visitType.getServicePrice());
        visitTypeRepository.save(existingVisitType);
    }

    @Transactional
    @Override
    public void deleteVisitType(long id) {
        visitTypeRepository.deleteById(id);
    }
}

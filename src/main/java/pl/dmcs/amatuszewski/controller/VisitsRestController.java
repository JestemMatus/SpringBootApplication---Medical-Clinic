package pl.dmcs.amatuszewski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.amatuszewski.domain.Visits;
import pl.dmcs.amatuszewski.service.VisitsService;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitsRestController {

    private final VisitsService visitsService;

    @Autowired
    public VisitsRestController(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Visits> listVisits() {
        return visitsService.listVisits();
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Visits> getVisit(@PathVariable("id") long id) {
        Visits visit = visitsService.getVisit(id);
        if (visit != null) {
            return new ResponseEntity<>(visit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Visits> addVisit(@RequestBody Visits visit) {
        visitsService.addVisit(visit);
        return new ResponseEntity<>(visit, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Visits> updateVisit(@PathVariable("id") long id, @RequestBody Visits visit) {
        Visits existingVisit = visitsService.getVisit(id);
        if (existingVisit != null) {
            visit.setId(id);
            visitsService.updateVisit(visit);
            return new ResponseEntity<>(visit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Void> deleteVisit(@PathVariable("id") long id) {
        Visits visit = visitsService.getVisit(id);
        if (visit != null) {
            visitsService.deleteVisit(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

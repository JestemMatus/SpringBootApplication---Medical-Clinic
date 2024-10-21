package pl.dmcs.amatuszewski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletResponse;
import pl.dmcs.amatuszewski.domain.Visits;
import pl.dmcs.amatuszewski.service.PdfService;
import pl.dmcs.amatuszewski.service.VisitsService;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    private final PdfService pdfService;
    private final VisitsService visitsService;

    @Autowired
    public PdfController(PdfService pdfService, VisitsService visitsService) {
        this.pdfService = pdfService;
        this.visitsService = visitsService;
    }

    @GetMapping("/download/{id}")
    public void downloadPdf(@PathVariable("id") Long id, HttpServletResponse response) {
        Visits visit = visitsService.getVisit(id);
        pdfService.generatePdf(visit, response);
    }
}

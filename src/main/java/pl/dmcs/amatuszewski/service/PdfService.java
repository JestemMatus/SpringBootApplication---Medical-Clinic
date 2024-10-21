package pl.dmcs.amatuszewski.service;

import jakarta.servlet.http.HttpServletResponse;
import pl.dmcs.amatuszewski.domain.Visits;

public interface PdfService {
    void generatePdf(Visits visit, HttpServletResponse response);
}

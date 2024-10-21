package pl.dmcs.amatuszewski.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import pl.dmcs.amatuszewski.domain.Visits;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public void generatePdf(Visits visit, HttpServletResponse response) {
        try {
            Document document = new Document();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=visit_receipt.pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Receipt", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);

            addTableHeader(table, "Field", "Value");
            addTableCell(table, "Doctor", visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName());
            addTableCell(table, "Patient", visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            addTableCell(table, "Date", dateFormat.format(visit.getDate()));
            addTableCell(table, "Time", visit.getTime().toString().substring(0, 5));
            addTableCell(table, "Service", visit.getVisitType().getServiceName());

            String priceFormatted = String.format("%.2f zlotych", visit.getVisitType().getServicePrice());
            addTableCell(table, "Price", priceFormatted);

            document.add(table);

            Paragraph downloadedOn = new Paragraph("Pobrano dnia: " + dateFormat.format(new Date()));
            downloadedOn.setAlignment(Element.ALIGN_RIGHT);
            downloadedOn.setSpacingBefore(20);
            document.add(downloadedOn);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table, String header1, String header2) {
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
        PdfPCell headerCell1 = new PdfPCell(new Phrase(header1, headFont));
        headerCell1.setBackgroundColor(BaseColor.BLUE);
        headerCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell1);

        PdfPCell headerCell2 = new PdfPCell(new Phrase(header2, headFont));
        headerCell2.setBackgroundColor(BaseColor.BLUE);
        headerCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(headerCell2);
    }

    private void addTableCell(PdfPTable table, String key, String value) {
        Font keyFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        PdfPCell keyCell = new PdfPCell(new Phrase(key, keyFont));
        keyCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        keyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(keyCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(valueCell);
    }
}

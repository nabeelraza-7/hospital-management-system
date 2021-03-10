package pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import patient.Patient;

public class CreatePDF {
    private static Font ROMAN_HEADING = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static Font ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 14);
    public static void writeToPDF(Patient patient) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("prescription.pdf"));
            document.open();
            Paragraph header = new Paragraph("Hospital Management System", ROMAN_HEADING);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            helper(document, "Medical Report", ROMAN_HEADING);
            helper(document, "Patient ID:    " + patient.getId(), ROMAN);
            helper(document, "Name:           "+ patient.getName(), ROMAN);
            helper(document, "Age:              "+ patient.getAge(), ROMAN);
            helper(document, "Phone No:     "+ patient.getPhoneNo(), ROMAN);
            helper(document, "Address:        "+ patient.getAddress(), ROMAN);
            helper(document, "Symptoms", ROMAN_HEADING);
            helper(document, patient.getSymptoms(), ROMAN);
            helper(document, "Prescription", ROMAN_HEADING);
            helper(document, patient.getPrescription(), ROMAN);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    private static void helper(Document document, String text, Font font) {
        try {
            Paragraph paragraph = new Paragraph(text, font);
            document.add(paragraph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}


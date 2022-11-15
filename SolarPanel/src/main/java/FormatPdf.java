import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class FormatPdf {

    public static void main(String[] args) {
    }

    public void PrintPdf() {
        //Set Local Directory
        String LOCAL_DIRECTORY = System.getProperty("user.dir");
        //pdf file path
        String FILE_NAME = LOCAL_DIRECTORY + "\\SolarPanel\\PdfDocs\\doc.pdf";
        //Image path
        String IMAGE_PATH = LOCAL_DIRECTORY+ "\\SolarPanel\\Images\\testimage.png";
        Document document = new Document();
        try {
            //Create a pdfwriter to the pdf file
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            //open pdf document
            document.open();

            //Create and add paragraph
            Font font = new Font();
            font.setSize(20);
            Paragraph p = new Paragraph("", font);
            p.add("Test Image");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            //Add image
            document.add(Image.getInstance(IMAGE_PATH));

            //Create paragraph
            Paragraph p2 = new Paragraph("Test Text wowowowow\n" +
                    "woooooooooow", font);

            //Add paragraph
            document.add(p2);

            //close document
            document.close();
            System.out.println("Done!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class FormatPdf {

    public static void main(String[] args) {
    }

    public void PrintPdf() {
        //Get Local Directory
        String LOCAL_DIRECTORY = System.getProperty("user.dir");
        //pdf file path
        String FILE_NAME = LOCAL_DIRECTORY + "\\SolarPanel\\PdfDocs\\doc.pdf";
        //Image path
        String IMAGE_PATH = LOCAL_DIRECTORY+ "\\SolarPanel\\Images\\testimage.png";
        //Check if image file is valid
        if(IMAGE_PATH.endsWith(".png")
                || IMAGE_PATH.endsWith(".jpeg")) {

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
                p.add("Title");
                p.setAlignment(Element.ALIGN_CENTER);
                document.add(p);

                //Add image
                Image image = Image.getInstance(IMAGE_PATH);
                image.scaleToFit(PageSize.A5);
                image.setAlignment(1);
                document.add(image);


                //Create paragraph
                font.setSize(12);
                Paragraph p2 = new Paragraph("Paragraph text\n" +
                        "New line text", font);

                //Add paragraph
                document.add(p2);

                //close document
                document.close();

                System.out.println("PDF file created!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid Image file type");
        }
    }
}

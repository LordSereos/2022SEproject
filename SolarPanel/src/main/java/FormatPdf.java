import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileOutputStream;

public class FormatPdf {

    public static void main(String[] args) {
    }

    public void PrintPdf() {
        String LOCAL_DIRECTORY = System.getProperty("user.dir");
        String FILE_NAME = LOCAL_DIRECTORY + "\\SolarPanel\\PdfDocs\\doc.pdf";
        String IMAGE_PATH = LOCAL_DIRECTORY+ "\\SolarPanel\\Images\\testimage.png";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            document.open();

            Font font = new Font();
            font.setSize(20);
            Paragraph p = new Paragraph("", font);
            p.add("Test Image");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            document.add(Image.getInstance(IMAGE_PATH));

            Paragraph p2 = new Paragraph("Test Text wowowowow\n" +
                    "woooooooooow", font);

            document.add(p2);

            document.close();
            System.out.println("Done!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

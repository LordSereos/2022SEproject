import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FormatPdf {

    public static void main(String[] args) {
    }

    public void PrintPdf() {
        //Get Local Directory
        String LOCAL_DIRECTORY = System.getProperty("user.dir");
        //pdf file path
        String FILE_NAME = LOCAL_DIRECTORY + "/SolarPanel/PdfDocs/Solar Panel Placement.pdf";
        //Image path
        String IMAGE_DIR_PATH = LOCAL_DIRECTORY+ "/SolarPanel/Images";

        File imgDirectory = new File(IMAGE_DIR_PATH);

        File[] files = imgDirectory.listFiles();

        if(files.length<1)
        {
            System.out.println("No files found in 'Images' directory");
            return;
        }

        ArrayList<File> imageFiles = new ArrayList<File>();



            for (File file:
                    files) {
                if (file.getName().endsWith(".png")){
                    imageFiles.add(file);
                }
            }
            if(imageFiles.size()<1) {
                System.out.println("No images with 'png' format were found in 'Images' directory");
                return;
            }

            Document document = new Document();
            try {
                //Create a pdfwriter to write to the pdf file
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

                //Add images with names
                Paragraph[] imagenames = new Paragraph().toArray(new Paragraph[files.length]);
                int i = 0;
                for (File file:
                        imageFiles) {
                    Image image = Image.getInstance(file.getPath());
                    image.scaleToFit(PageSize.A5);
                    imagenames[i] = new Paragraph("", font);
                    imagenames[i].add(file.getName().substring(0, file.getName().length()- 4));
                    imagenames[i].setAlignment(Element.ALIGN_LEFT);
                    document.add(imagenames[i]);
                    image.setAlignment(1);
                    document.add(image);
                    i += 1;
                }

                //close document
                document.close();

                System.out.println("PDF file created!");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}

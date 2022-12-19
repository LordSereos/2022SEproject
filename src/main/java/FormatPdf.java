import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FormatPdf {

    public static void main(String[] args) {
    }

    public String PrintPdf() {
        //Get Local Directory
        String LOCAL_DIRECTORY = System.getProperty("user.dir");
        //Set PDF file path
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssms");
        String dateTime = now.format(formatter);
        String FILE_NAME = LOCAL_DIRECTORY + "/SolarPanel/PdfDocs/Solar Panel Placement - " + dateTime + ".pdf";
        //Set Image directory path
        String IMAGE_DIR_PATH = LOCAL_DIRECTORY+ "/SolarPanel/Images";

        File imgDirectory = new File(IMAGE_DIR_PATH);

        File[] files = imgDirectory.listFiles();

        //Check if any files exist in Image directory
        if(files.length<1)
        {
            System.out.println("No files found in 'Images' directory");
            return "No files found in 'Images' directory";
        }

        ArrayList<File> imageFiles = new ArrayList<File>();


        //Check if file is an image with png format, then add it to file ArrayList
        for (File file:
                files) {
            if (file.getName().endsWith(".png")){
                imageFiles.add(file);
            }
        }
        //Check if any images with png format were found and added to ArrayList
        if(imageFiles.size()<1) {
            System.out.println("No images with 'png' format were found in 'Images' directory");
            return "No images with 'png' format were found in 'Images' directory";
        }

        Document document = new Document();
        try {
            //Create a pdfwriter to write to the pdf file
            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
            //Open pdf document
            document.open();

            //Create and add Title paragraph
            Font titleFont = new Font();
            titleFont.setSize(20);
            Paragraph p = new Paragraph("", titleFont);
            p.add("Solar Panel Placement Guidelines\n\n");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            //Add images with names, descriptions
            Paragraph[] imageNames = new Paragraph().toArray(new Paragraph[files.length]);
            Paragraph[] imageDescriptions = new Paragraph().toArray(new Paragraph[files.length]);

            //Create View Title Font
            Font viewTitleFont = new Font();
            viewTitleFont.setSize(16);

            //Create View Description Font
            Font descTitleFont = new Font();
            descTitleFont.setSize(12);

            MeanRoofHeight meanRoofHeight = new MeanRoofHeight();
            //Calculate Wind Zone width
            double windZoneWidth = meanRoofHeight.getWidthOfWindZone();

            //Add all png files with names, descriptions to pdf document
            int i = 0;
            for (File file:
                    imageFiles) {
                Image image = Image.getInstance(file.getPath());
                image.scaleToFit(PageSize.A5);
                imageNames[i] = new Paragraph("", viewTitleFont);
                String fileName = file.getName().substring(0, file.getName().length()- 4);
                imageNames[i].add(fileName);
                imageNames[i].setAlignment(Element.ALIGN_LEFT);
                imageDescriptions[i] = new Paragraph("", descTitleFont);
                document.add(imageNames[i]);
                image.setAlignment(1);
                document.add(image);
                if(fileName.contains("Layout")) {
                    imageDescriptions[i].add("This is the view of each individual roof face with Fire Ventilation Setbacks");
                }
                else {
                    imageDescriptions[i].add(
                            "This is an overview of the whole site.\n"
                    );
                }
                if(fileName.contains("Fire")) {
                    imageDescriptions[i].add(
                            "The Fire Ventilation Setbacks are portrayed by the blue lines\n"
                    );
                }
                if(fileName.contains("Wind")) {
                    imageDescriptions[i].add(
                            "The Wind Pressure Zones are portrayed by the colored and crossed rectangles.\n");
                    Paragraph windZoneDescParagraph = new Paragraph("" +
                            "   (•) The red rectangles represent High Wind Pressure Zones\n" +
                            "   (•) The yellow rectangles represent Medium Wind Pressure Zones\n" +
                            "   (•) The green rectangles represent Low Wind Pressure Zones\n",
                            descTitleFont);
                    imageDescriptions[i].add(windZoneDescParagraph);
                    imageDescriptions[i].add(
                            "The width of the Wind Pressure zone is calculated using this formula: a = 0.4 * mean roof height, but not less than 3 feet. " +
                                    "In this case the width is: " + windZoneWidth + " meters\n"
                    );
                }
                document.add(imageDescriptions[i]);
                document.newPage();
                i += 1;
            }
            //close document
            document.close();

            System.out.println("PDF file created!");
            return "PDF file created!";
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "error";
    }
}

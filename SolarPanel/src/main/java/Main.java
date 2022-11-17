public class Main {

    public static void main(String[] args) {
        FormatPdf formatPdf = new FormatPdf();
        formatPdf.PrintPdf();

        DrawLinesFromCords drawLinesFromCords = new DrawLinesFromCords();

        drawLinesFromCords.Launch(null);
    }
}

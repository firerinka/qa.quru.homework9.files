package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ArchiveParseTest {
    ClassLoader classLoader = ArchiveParseTest.class.getClassLoader();

    private static final String ZIP_NAME = "files.zip";
    private static final String PDF_NAME = "IntelliJIDEA_ReferenceCard.pdf";
    private static final String XLSX_NAME = "Menu.xlsx";
    private static final String CSV_NAME = "Menu.csv";

    @Test
    void checkPdfInZipTest() throws IOException {
        ZipFile zf = new ZipFile(classLoader.getResource(ZIP_NAME).getPath());
        try (InputStream entryStream = zf.getInputStream(zf.getEntry(PDF_NAME))) {
            PDF pdf = new PDF(entryStream);
            assertThat(pdf.text).contains("Windows & Linux keymap");
            assertThat(pdf.text).contains("Default macOS keymap");
        }
    }

    @Test
    void checkXlsxInZipTest() throws IOException {
        ZipFile zf = new ZipFile(classLoader.getResource(ZIP_NAME).getPath());
        try (InputStream entryStream = zf.getInputStream(zf.getEntry(XLSX_NAME))) {
            XLS xls = new XLS(entryStream);
            assertThat(
                    xls.excel.getSheetAt(0)
                            .getRow(0)
                            .getCell(0)
                            .getStringCellValue()
            ).isEqualTo("category");

            assertThat(
                    xls.excel.getSheetAt(0)
                            .getRow(1)
                            .getCell(0)
                            .getStringCellValue()
            ).isEqualTo("Салаты");
        }
    }

    @Test
    void checkCSVInZipTest() throws Exception {
        ZipFile zf = new ZipFile(classLoader.getResource(ZIP_NAME).getPath());
        try (InputStream entryStream = zf.getInputStream(zf.getEntry(CSV_NAME))) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(entryStream, UTF_8));
            List<String[]> csv = csvReader.readAll();
            assertThat(csv).contains(
                    new String[]{"category", "title", "price", "weight"},
                    new String[]{"Салаты", "Из моркови с сыром и чесноком", "40", "120гр"}
            );
        }
    }
}

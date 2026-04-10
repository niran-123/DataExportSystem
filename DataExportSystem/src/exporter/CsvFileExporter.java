package exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CsvFileExporter
 * ---------------
 * Saves data as a standard CSV file that can be opened in Excel or Google Sheets.
 *
 * HOW TO USE:
 *   DataExporter exporter = new CsvFileExporter("output/data.csv");
 *   exporter.export(headers, rows);
 */
public class CsvFileExporter implements DataExporter {

    private final String filePath;

    /**
     * @param filePath  Where to save the CSV (e.g. "output/data.csv")
     */
    public CsvFileExporter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(List<String> headers, List<List<String>> rows) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Write header line
            writer.write(toCsvLine(headers));
            writer.newLine();

            // Write each data row
            for (List<String> row : rows) {
                writer.write(toCsvLine(row));
                writer.newLine();
            }

            System.out.println("✔  CSV saved → " + filePath);

        } catch (IOException e) {
            System.err.println("✘  Failed to write CSV: " + e.getMessage());
        }
    }

    // ── Helper ─────────────────────────────────────────────────────────────

    /**
     * Joins values with commas.
     * Values that contain a comma or quote are wrapped in double quotes.
     */
    private String toCsvLine(List<String> values) {
        return values.stream()
                .map(this::escapeCsv)
                .collect(Collectors.joining(","));
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}

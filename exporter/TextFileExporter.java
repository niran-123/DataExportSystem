package exporter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * TextFileExporter
 * ----------------
 * Saves the data as a human-readable .txt file.
 * The file is saved to the path you provide when creating this exporter.
 *
 * HOW TO USE:
 *   DataExporter exporter = new TextFileExporter("output/report.txt");
 *   exporter.export(headers, rows);
 */
public class TextFileExporter implements DataExporter {

    private final String filePath;

    /**
     * @param filePath  Where to save the file (e.g. "output/report.txt")
     */
    public TextFileExporter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(List<String> headers, List<List<String>> rows) {
        // Calculate column widths (same logic as ConsoleExporter)
        int[] colWidths = calculateColumnWidths(headers, rows);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Timestamp header
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("Data Export Report");
            writer.newLine();
            writer.write("Generated : " + timestamp);
            writer.newLine();
            writer.write("Records   : " + rows.size());
            writer.newLine();
            writer.write("=".repeat(totalWidth(colWidths)));
            writer.newLine();
            writer.newLine();

            // Header row
            writer.write(buildRow(headers, colWidths));
            writer.newLine();
            writer.write(buildSeparator(colWidths));
            writer.newLine();

            // Data rows
            for (List<String> row : rows) {
                writer.write(buildRow(row, colWidths));
                writer.newLine();
            }

            System.out.println("✔  File saved → " + filePath);

        } catch (IOException e) {
            System.err.println("✘  Failed to write file: " + e.getMessage());
            System.err.println("   Tip: Make sure the output folder exists.");
        }
    }

    // ── Helpers ────────────────────────────────────────────────────────────

    private int[] calculateColumnWidths(List<String> headers, List<List<String>> rows) {
        int[] widths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            widths[i] = headers.get(i).length();
        }
        for (List<String> row : rows) {
            for (int i = 0; i < row.size() && i < widths.length; i++) {
                widths[i] = Math.max(widths[i], row.get(i).length());
            }
        }
        return widths;
    }

    private String buildRow(List<String> values, int[] colWidths) {
        StringBuilder sb = new StringBuilder("| ");
        for (int i = 0; i < colWidths.length; i++) {
            String val = i < values.size() ? values.get(i) : "";
            sb.append(String.format("%-" + colWidths[i] + "s", val));
            sb.append(" | ");
        }
        return sb.toString();
    }

    private String buildSeparator(int[] colWidths) {
        StringBuilder sb = new StringBuilder("+");
        for (int w : colWidths) {
            sb.append("-".repeat(w + 2)).append("+");
        }
        return sb.toString();
    }

    private int totalWidth(int[] colWidths) {
        int total = 1; // leading "+"
        for (int w : colWidths) total += w + 3; // padding + separator
        return total;
    }
}

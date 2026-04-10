package exporter;

import java.util.List;

/**
 * ConsoleExporter
 * ---------------
 * Displays data as a formatted table in the terminal.
 *
 * HOW TO USE:
 *   DataExporter exporter = new ConsoleExporter();
 *   exporter.export(headers, rows);
 */
public class ConsoleExporter implements DataExporter {

    @Override
    public void export(List<String> headers, List<List<String>> rows) {
        System.out.println("\n========== DATA EXPORT (Console) ==========\n");

        // Calculate column widths based on the longest value per column
        int[] colWidths = calculateColumnWidths(headers, rows);

        // Print header row
        printRow(headers, colWidths);
        printSeparator(colWidths);

        // Print each data row
        for (List<String> row : rows) {
            printRow(row, colWidths);
        }

        System.out.println("\nTotal records: " + rows.size());
        System.out.println("===========================================\n");
    }

    // ── Helpers ────────────────────────────────────────────────────────────

    /** Computes the max character width needed for each column. */
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

    /** Prints a single row padded to match column widths. */
    private void printRow(List<String> values, int[] colWidths) {
        StringBuilder sb = new StringBuilder("| ");
        for (int i = 0; i < values.size(); i++) {
            String val = i < values.size() ? values.get(i) : "";
            sb.append(String.format("%-" + colWidths[i] + "s", val));
            sb.append(" | ");
        }
        System.out.println(sb);
    }

    /** Prints a dashed separator line. */
    private void printSeparator(int[] colWidths) {
        StringBuilder sb = new StringBuilder("+");
        for (int w : colWidths) {
            sb.append("-".repeat(w + 2)).append("+");
        }
        System.out.println(sb);
    }
}

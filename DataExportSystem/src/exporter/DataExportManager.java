package exporter;

import java.util.List;
import java.util.Scanner;

/**
 * DataExportManager
 * -----------------
 * Presents the user with a simple menu to choose how to export data,
 * then delegates to the appropriate exporter strategy.
 *
 * Think of this as the "controller" — it wires the UI choices to the
 * correct DataExporter implementation.
 */
public class DataExportManager {

    private final List<String>       headers;
    private final List<List<String>> rows;
    private final String             outputDir;

    /**
     * @param headers    Column names
     * @param rows       Data rows
     * @param outputDir  Directory where files will be saved (e.g. "output")
     */
    public DataExportManager(List<String> headers,
                             List<List<String>> rows,
                             String outputDir) {
        this.headers   = headers;
        this.rows      = rows;
        this.outputDir = outputDir;
    }

    /** Shows the menu and handles the user's choice. */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     DATA EXPORT SYSTEM       ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println();
        System.out.println("Choose an export option:");
        System.out.println("  1 → Display in console");
        System.out.println("  2 → Save as Text file (.txt)");
        System.out.println("  3 → Save as CSV file (.csv)");
        System.out.println("  4 → All of the above");
        System.out.println("  0 → Exit");
        System.out.print("\nYour choice: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> runExporter(new ConsoleExporter());
            case "2" -> runExporter(new TextFileExporter(outputDir + "/report.txt"));
            case "3" -> runExporter(new CsvFileExporter(outputDir + "/data.csv"));
            case "4" -> {
                runExporter(new ConsoleExporter());
                runExporter(new TextFileExporter(outputDir + "/report.txt"));
                runExporter(new CsvFileExporter(outputDir + "/data.csv"));
            }
            case "0" -> System.out.println("Goodbye!");
            default  -> System.out.println("Invalid choice. Please run again and enter 0–4.");
        }

        scanner.close();
    }

    /** Runs a single exporter and catches any unexpected errors. */
    private void runExporter(DataExporter exporter) {
        try {
            exporter.export(headers, rows);
        } catch (Exception e) {
            System.err.println("Export failed: " + e.getMessage());
        }
    }
}

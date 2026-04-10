import exporter.DataExportManager;

import java.io.File;
import java.util.List;

/**
 * Main.java — Entry Point
 * ========================
 * This is where the program starts.
 *
 * TO CUSTOMISE:
 *   1. Replace the sample headers and rows below with your own data.
 *   2. Run the program — a menu will appear.
 *   3. Choose how you want to export the data.
 *
 * HOW TO COMPILE & RUN (Terminal / Command Prompt):
 *   javac -d out src/exporter/*.java src/Main.java
 *   java  -cp out Main
 */
public class Main {

    public static void main(String[] args) {

        // ── 1. Define your column headers ─────────────────────────────────
        List<String> headers = List.of("ID", "Name", "Department", "Salary", "City");

        // ── 2. Define your data rows ───────────────────────────────────────
        //       Each inner list = one row; order must match headers above.
        List<List<String>> rows = List.of(
            List.of("1001", "Arun Kumar",    "Engineering",  "72000", "Chennai"),
            List.of("1002", "Priya Nair",    "Marketing",    "65000", "Bengaluru"),
            List.of("1003", "Rohan Mehta",   "HR",           "58000", "Mumbai"),
            List.of("1004", "Sneha Pillai",  "Engineering",  "80000", "Hyderabad"),
            List.of("1005", "Vikram Singh",  "Finance",      "69000", "Delhi"),
            List.of("1006", "Divya Menon",   "Marketing",    "61000", "Kochi"),
            List.of("1007", "Karthik Raj",   "Engineering",  "75000", "Coimbatore"),
            List.of("1008", "Ananya Sharma", "HR",           "55000", "Pune")
        );

        // ── 3. Create the output directory (if it doesn't exist) ───────────
        String outputDir = "output";
        new File(outputDir).mkdirs();   // safe to call even if folder exists

        // ── 4. Launch the export menu ──────────────────────────────────────
        DataExportManager manager = new DataExportManager(headers, rows, outputDir);
        manager.run();
    }
}

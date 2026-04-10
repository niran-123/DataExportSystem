# Data Export System — Java

A simple, beginner-friendly Java project that lets users **display** or **download** data as text.

---

## Project Structure

```
DataExportSystem/
├── src/
│   ├── Main.java                        ← Entry point (start here)
│   └── exporter/
│       ├── DataExporter.java            ← Interface (the "contract")
│       ├── ConsoleExporter.java         ← Prints a table to the terminal
│       ├── TextFileExporter.java        ← Saves a .txt report file
│       ├── CsvFileExporter.java         ← Saves a .csv file (opens in Excel)
│       └── DataExportManager.java       ← Shows the menu, wires everything
└── output/                              ← Generated files appear here
```

---

## How to Compile & Run

### Option A — Terminal / Command Prompt

```bash
# 1. Navigate to the project root
cd DataExportSystem

# 2. Compile all Java files into the "out" folder
javac -d out src/exporter/*.java src/Main.java

# 3. Run the program
java -cp out Main
```

### Option B — VS Code / IntelliJ / Eclipse

1. Open the `DataExportSystem` folder as a project.
2. Set `src` as the source root.
3. Run `Main.java`.

---

## What the Menu Looks Like

```
╔══════════════════════════════╗
║     DATA EXPORT SYSTEM       ║
╚══════════════════════════════╝

Choose an export option:
  1 → Display in console
  2 → Save as Text file (.txt)
  3 → Save as CSV file (.csv)
  4 → All of the above
  0 → Exit

Your choice: _
```

---

## Sample Console Output (Option 1)

```
========== DATA EXPORT (Console) ==========

| ID   | Name           | Department  | Salary | City       |
+------+----------------+-------------+--------+------------+
| 1001 | Arun Kumar     | Engineering | 72000  | Chennai    |
| 1002 | Priya Nair     | Marketing   | 65000  | Bengaluru  |
...

Total records: 8
===========================================
```

---

## How to Use Your Own Data

Open `src/Main.java` and replace the sample data:

```java
// Your column names
List<String> headers = List.of("StudentID", "Name", "Grade", "Score");

// Your rows (each inner list must match the column order above)
List<List<String>> rows = List.of(
    List.of("S001", "Alice", "A", "95"),
    List.of("S002", "Bob",   "B", "82"),
    List.of("S003", "Carol", "A", "91")
);
```

That's all — the rest of the system adapts automatically.

---

## How to Add a New Export Format

1. Create a new class in `src/exporter/`, e.g. `HtmlExporter.java`.
2. Implement the `DataExporter` interface:

```java
public class HtmlExporter implements DataExporter {
    private final String filePath;

    public HtmlExporter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(List<String> headers, List<List<String>> rows) {
        // Write HTML table to filePath
    }
}
```

3. Add a new menu option in `DataExportManager.java`:

```java
case "5" -> runExporter(new HtmlExporter(outputDir + "/report.html"));
```

Done! The design uses the **Strategy Pattern**, so adding formats never breaks existing ones.

---

## Design Concepts Used

| Concept            | Where                        | Why                                          |
|--------------------|------------------------------|----------------------------------------------|
| Interface          | `DataExporter.java`          | Defines a common contract for all exporters  |
| Strategy Pattern   | All exporter classes         | Swap export behaviour without changing logic |
| Single Responsibility | Each class does one job   | Easy to read, test, and modify               |
| Factory-like menu  | `DataExportManager.java`     | Centralises all wiring in one place          |

---

## Requirements

- Java 11 or higher (uses `List.of()` and text blocks)
- No external libraries needed — pure standard Java

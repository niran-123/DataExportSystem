# Data Export System — Java

A clean, beginner-friendly Java project that exports tabular data to multiple formats from a simple numbered menu. Built with zero external dependencies using core Java OOP principles and the Strategy design pattern.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Usage Guide](#usage-guide)
- [Sample Output](#sample-output)
- [Key Concepts](#key-concepts)
- [Architecture](#architecture)
- [How to Add a New Export Format](#how-to-add-a-new-export-format)
- [Using Your Own Data](#using-your-own-data)
- [Running in an IDE](#running-in-an-ide)
- [Troubleshooting](#troubleshooting)
- [Project Highlights](#project-highlights)
- [License](#license)

---

## Overview

The **Data Export System** solves a common developer problem: you have a table of data and need to deliver it in multiple formats — printed to the terminal, saved as a text report, and exported as a CSV — without duplicating logic for each format.

This project demonstrates how to solve that cleanly using:
- A shared `DataExporter` **interface** as the common contract
- Three concrete **strategy** classes — one per output format
- A central `DataExportManager` that wires user input to the right strategy
- Pure Java standard library — no Maven, no Gradle, no third-party libraries

---

## Features

| Feature | Details |
|---|---|
| Console display | Auto-sized column table printed to the terminal |
| Text file export | Timestamped `.txt` report saved to `output/report.txt` |
| CSV file export | Standards-compliant `.csv` saved to `output/data.csv` — opens in Excel & Google Sheets |
| Extensible design | Add a new format by creating one class — no existing code changes |
| Zero dependencies | Pure Java 11+ standard library only |
| Cross-platform | Works on Windows, macOS, and Linux |

---

## Project Structure

```
DataExportSystem/
├── src/
│   ├── Main.java                      ← Entry point — define your data here
│   └── exporter/
│       ├── DataExporter.java          ← Interface (the common contract)
│       ├── ConsoleExporter.java       ← Prints a formatted table to the terminal
│       ├── TextFileExporter.java      ← Saves a .txt report with timestamp
│       ├── CsvFileExporter.java       ← Saves a .csv file (Excel-compatible)
│       └── DataExportManager.java     ← Shows the menu, routes user choice
├── out/                               ← Compiled .class files go here (auto-created)
├── output/                            ← Generated export files appear here
└── README.md
```

---

## Prerequisites

You need **Java Development Kit (JDK) 11 or higher** installed.

### Check if Java is already installed

Open a terminal or command prompt and run:

```bash
java -version
javac -version
```

Both commands should print a version number (e.g., `openjdk 21.0.x`).

### Install Java (if not installed)

| Platform | Download |
|---|---|
| All platforms | [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/) |
| macOS (Homebrew) | `brew install openjdk@21` |
| Ubuntu/Debian | `sudo apt install openjdk-21-jdk` |
| Windows | Download the installer from Oracle and run it |

### Add Java to your PATH (Windows only)

After installing on Windows, if `javac` is not recognised:

1. Search **"Environment Variables"** in the Start menu
2. Click **Edit the system environment variables**
3. Under **System Variables**, find `Path` → click **Edit**
4. Click **New** and add the path to your JDK's `bin` folder, e.g.:
   ```
   C:\Program Files\Java\jdk-21\bin
   ```
5. Click **OK** on all dialogs and restart your terminal

---

## Installation & Setup

### Step 1 — Get the project files

If you received a ZIP file, extract it to any folder (e.g., your Desktop):

```
DataExportSystem/
```

If you are cloning from a repository:

```bash
git clone https://github.com/your-username/DataExportSystem.git
cd DataExportSystem
```

### Step 2 — Verify the folder structure

Make sure you can see the `src/` folder containing `Main.java` and the `exporter/` subfolder with all four exporter files.

---

## How to Run

### Option A — Terminal / Command Prompt (Recommended)

Open a terminal inside the `DataExportSystem/` folder, then run these two commands:

**1. Compile:**

```bash
javac -d out src/exporter/*.java src/Main.java
```

This compiles all `.java` files and places the `.class` files in the `out/` folder. You will see no output if successful.

**2. Run:**

```bash
java -cp out Main
```

The interactive menu will appear immediately.

---

### Option B — Single command (compile + run together)

```bash
javac -d out src/exporter/*.java src/Main.java && java -cp out Main
```

---

### Option C — Windows Command Prompt (alternative syntax)

```cmd
javac -d out src\exporter\*.java src\Main.java
java -cp out Main
```

---

## Usage Guide

When you run the program, you will see this menu:

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

Type a number and press **Enter**.

| Choice | What happens |
|---|---|
| `1` | Prints the data as a formatted aligned table in the terminal |
| `2` | Creates `output/report.txt` — a human-readable report with a timestamp |
| `3` | Creates `output/data.csv` — a CSV file you can open in Excel or Google Sheets |
| `4` | Runs all three exports at once |
| `0` | Exits the program |

---

## Sample Output

### Console display (option 1)

```
========== DATA EXPORT (Console) ==========

| ID   | Name           | Department  | Salary | City       |
+------+----------------+-------------+--------+------------+
| 1001 | Arun Kumar     | Engineering | 72000  | Chennai    |
| 1002 | Priya Nair     | Marketing   | 65000  | Bengaluru  |
| 1003 | Rohan Mehta    | HR          | 58000  | Mumbai     |
| 1004 | Sneha Pillai   | Engineering | 80000  | Hyderabad  |
| 1005 | Vikram Singh   | Finance     | 69000  | Delhi      |
| 1006 | Divya Menon    | Marketing   | 61000  | Kochi      |
| 1007 | Karthik Raj    | Engineering | 75000  | Coimbatore |
| 1008 | Ananya Sharma  | HR          | 55000  | Pune       |

Total records: 8
===========================================
```

### Text file (option 2) — `output/report.txt`

```
Data Export Report
Generated : 2025-08-14 10:32:07
Records   : 8
============================================

| ID   | Name           | Department  | Salary | City       |
+------+----------------+-------------+--------+------------+
| 1001 | Arun Kumar     | Engineering | 72000  | Chennai    |
...
```

### CSV file (option 3) — `output/data.csv`

```
ID,Name,Department,Salary,City
1001,Arun Kumar,Engineering,72000,Chennai
1002,Priya Nair,Marketing,65000,Bengaluru
...
```

---

## Key Concepts

### Interface

`DataExporter` is a Java interface — it declares the method `export(headers, rows)` without implementing it. Every exporter class promises to implement this method. This is the "contract" that makes the whole system work.

```java
public interface DataExporter {
    void export(List<String> headers, List<List<String>> rows);
}
```

### Strategy Pattern

Each export format is a separate class (a "strategy") that implements `DataExporter`. The `DataExportManager` calls `export()` on whichever strategy the user chose — without knowing which one it is.

```java
// Same call regardless of which exporter was chosen
private void runExporter(DataExporter exporter) {
    exporter.export(headers, rows);
}
```

### Polymorphism

The JVM decides at runtime which `export()` method to execute based on the actual object. The calling code is always identical — the behaviour differs.

### Single Responsibility

Each class does exactly one job:

| Class | Responsibility |
|---|---|
| `DataExporter` | Declare the export contract |
| `ConsoleExporter` | Format and print to terminal |
| `TextFileExporter` | Write a `.txt` file |
| `CsvFileExporter` | Write a `.csv` file |
| `DataExportManager` | Show the menu and route the choice |
| `Main` | Define data and start the manager |

### BufferedWriter & try-with-resources

File exporters use `BufferedWriter` (efficient batched writes) inside a `try-with-resources` block (guarantees the file is closed even if an error occurs).

```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
    writer.write("some content");
    writer.newLine();
} catch (IOException e) {
    System.err.println("Failed: " + e.getMessage());
}
// writer.close() is called automatically
```

---

## Architecture

```
Main.java
    │
    │  passes headers + rows
    ▼
DataExportManager
    │
    │  shows menu, reads Scanner input, runs switch
    ▼
«interface» DataExporter
    │
    ├──────────────────┬──────────────────────┐
    ▼                  ▼                      ▼
ConsoleExporter  TextFileExporter      CsvFileExporter
    │                  │                      │
    ▼                  ▼                      ▼
Terminal         output/report.txt     output/data.csv
```

The manager never depends on a concrete exporter — only on the `DataExporter` interface. This is the key to the extensible design.

---

## How to Add a New Export Format

Because the project uses the Strategy Pattern, adding a new format requires only two small changes and zero modifications to existing code.

### Step 1 — Create a new class in `src/exporter/`

```java
package exporter;

import java.util.List;

public class HtmlExporter implements DataExporter {

    private final String filePath;

    public HtmlExporter(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(List<String> headers, List<List<String>> rows) {
        // Build and write an HTML table to filePath
        System.out.println("✔  HTML saved → " + filePath);
    }
}
```

### Step 2 — Add a case to `DataExportManager.java`

In the `run()` method, add one new case to the switch:

```java
case "5" -> runExporter(new HtmlExporter(outputDir + "/report.html"));
```

Also update the menu text:

```java
System.out.println("  5 → Save as HTML file (.html)");
```

That's everything. No other files need to be changed.

---

## Using Your Own Data

Open `src/Main.java` and replace the sample data with your own:

```java
// Step 1: Replace these column names
List<String> headers = List.of("StudentID", "Name", "Subject", "Grade");

// Step 2: Replace these rows
// Each inner list must match the column order above
List<List<String>> rows = List.of(
    List.of("S001", "Alice",   "Mathematics", "A"),
    List.of("S002", "Bob",     "Physics",     "B+"),
    List.of("S003", "Charlie", "Chemistry",   "A-")
);
```

Recompile and run — all three exporters adapt automatically to whatever headers and rows you define.

---

## Running in an IDE

If you prefer a graphical editor over the terminal, all major Java IDEs support this project out of the box.

### IntelliJ IDEA (recommended for beginners)

1. Download [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) — free
2. Open IntelliJ → **File → Open** → select the `DataExportSystem/` folder
3. Right-click `src/` in the Project panel → **Mark Directory as → Sources Root**
4. Right-click `src/Main.java` → **Run 'Main.main()'**
5. The menu appears in IntelliJ's built-in Run console

### VS Code

1. Install [VS Code](https://code.visualstudio.com/) and the **Extension Pack for Java** extension
2. Open the `DataExportSystem/` folder: **File → Open Folder**
3. Open `src/Main.java` and click the ▶ **Run** button at the top right
4. The menu appears in VS Code's integrated terminal

### Eclipse

1. Open Eclipse → **File → New → Java Project**
2. Uncheck "Use default location" and browse to your `DataExportSystem/` folder
3. Click **Finish**, then right-click `Main.java` → **Run As → Java Application**

---

## Troubleshooting

### `javac: command not found` or `'javac' is not recognized`

Java is not on your PATH. Follow the [Windows PATH instructions](#add-java-to-your-path-windows-only) above, or on macOS/Linux run:

```bash
export PATH="/usr/local/opt/openjdk/bin:$PATH"   # macOS Homebrew
export PATH="/usr/lib/jvm/java-21-openjdk/bin:$PATH"  # Ubuntu
```

Add that line to your `~/.bashrc` or `~/.zshrc` to make it permanent.

---

### `error: package exporter does not exist`

You compiled from the wrong directory. Make sure your terminal is inside the `DataExportSystem/` root folder (where `src/` is visible), then re-run:

```bash
javac -d out src/exporter/*.java src/Main.java
```

---

### `Could not find or load main class Main`

You either forgot the `-cp out` flag or ran the command from the wrong folder. Use exactly:

```bash
java -cp out Main
```

---

### `✘ Failed to write file` when choosing option 2 or 3

The `output/` folder does not exist. `Main.java` creates it automatically with `new File(outputDir).mkdirs()` — if this fails, create the folder manually:

```bash
mkdir output    # macOS / Linux
mkdir output    # Windows Command Prompt
```

---

### `error: ... requires Java 11` or `List.of() not found`

Your JDK is older than version 11. Check your version with `java -version` and install JDK 11 or higher from the link in [Prerequisites](#prerequisites).

---

## Project Highlights

| Aspect | Detail |
|---|---|
| Language | Java 11+ |
| Design pattern | Strategy Pattern |
| OOP principles | Encapsulation, Polymorphism, Single Responsibility, Open/Closed |
| File I/O | `BufferedWriter` with `try-with-resources` |
| User input | `Scanner(System.in)` with `.trim()` |
| Control flow | Arrow-switch expression (Java 14+) |
| Data structures | `List<String>`, `List<List<String>>`, `List.of()` |
| Dependencies | None — pure Java standard library |
| Lines of code | ~250 across 6 files |
| Build tool | None required — plain `javac` |

---

## License

This project is released for educational purposes. You are free to use, modify, and distribute it for learning or portfolio work.

---

> Built as a college project to demonstrate OOP design principles and the Strategy Pattern in Java.
> GitHub: [niran-123](https://github.com/niran-123)

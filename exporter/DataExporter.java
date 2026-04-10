package exporter;

import java.util.List;

/**
 * DataExporter Interface
 * ---------------------
 * All export strategies implement this interface.
 * To add a new export format, just implement this interface.
 */
public interface DataExporter {

    /**
     * Export the given rows of data.
     * @param headers  Column names (e.g. ["Name", "Age", "City"])
     * @param rows     Data rows (each row is a list of values)
     */
    void export(List<String> headers, List<List<String>> rows);
}

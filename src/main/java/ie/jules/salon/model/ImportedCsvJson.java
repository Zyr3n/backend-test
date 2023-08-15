package ie.jules.salon.model;

public class ImportedCsvJson {
    private String csvData;
    private String tableName;

    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getCsvData() {
        return csvData;
    }
    public void setCsvData(String csvData) {
        this.csvData = csvData;
    }
}

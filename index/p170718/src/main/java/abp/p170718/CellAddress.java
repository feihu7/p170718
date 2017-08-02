package abp.p170718;

public class CellAddress {
		 String sheetName;
    int rowIndex;
    int columnIndex;

    /**
     * @param rowIndex
     *            1-based
     * @param columnIndex
     *            1-based
     */
    public CellAddress(String sheetName, int rowIndex, int columnIndex) {
        this.sheetName = sheetName;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    /**
     * @param rowIndex
     *            1-based
     * @param columnIndex
     *            1-based
     */
    public CellAddress(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    /**
     * 返回Excel的单元格地址格式，比如 A3, Sheet2:G18
     */
    public String toExcelPosition() {
        String name = sheetName;
        StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append(name);
            sb.append(":");
        }

        // TODO what if column-count > 26?
        char columnName = (char) ('A' + (columnIndex - 1));
        sb.append(columnName);
        sb.append(rowIndex);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (sheetName != null) {
            sb.append(sheetName);
            sb.append(":");
        }
        sb.append(rowIndex);
        sb.append(",");
        sb.append(columnIndex);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + columnIndex;
        result = prime * result + rowIndex;
        result = prime * result + ((sheetName == null) ? 0 : sheetName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CellAddress other = (CellAddress) obj;
        if (columnIndex != other.columnIndex)
            return false;
        if (rowIndex != other.rowIndex)
            return false;
        if (sheetName == null) {
            if (other.sheetName != null)
                return false;
        } else if (!sheetName.equals(other.sheetName))
            return false;
        return true;
    }
}

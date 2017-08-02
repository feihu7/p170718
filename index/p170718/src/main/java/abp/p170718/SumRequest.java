package abp.p170718;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 汇总请求
 */
public class SumRequest {
    List<File> excelFiles;
    List<CellAddress> addresses;

    public SumRequest() {
        excelFiles = new ArrayList<File>();
        addresses = new ArrayList<CellAddress>();
    }

    public List<File> getExcelFiles() {
        return excelFiles;
    }

    public void setExcelFiles(List<File> excelFiles) {
        this.excelFiles = excelFiles;
    }

    public void addExcelFile(File excelFile) {
        if (excelFile == null)
            throw new NullPointerException("excelFile");
        excelFile = excelFile.getAbsoluteFile();
        excelFiles.add(excelFile);
    }

    public boolean removeExcelFile(File excelFile) {
        if (excelFile == null)
            return false;
        excelFile = excelFile.getAbsoluteFile();
        return excelFiles.remove(excelFile);
    }

    public List<CellAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CellAddress> addresses) {
        this.addresses = addresses;
    }
}

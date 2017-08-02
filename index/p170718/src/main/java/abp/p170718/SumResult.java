package abp.p170718;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Excel 汇总结果
 */
public class SumResult {
    String errorMessage;
    Map<CellAddress, CellSum> map;

    public SumResult() {
        map = new LinkedHashMap<CellAddress, CellSum>();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<CellAddress, CellSum> getMap() {
        return map;
    }

			public void setMap(Map<CellAddress, CellSum> map) {
				  this.map = map;
			}
    
}

package abp.p170718;

import java.math.BigDecimal;

public class CellSum {
    private BigDecimal sum = BigDecimal.ZERO;
    private int count = 0;
    private BigDecimal max = null;
    private BigDecimal min = null;

    public BigDecimal getSum() {
        return sum;
    }

    public BigDecimal getAverage() {
        return sum.divide(new BigDecimal(count), 3);  // 保留三位小数
    }

    public BigDecimal getMax() {
        return max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void add(BigDecimal number) {
        sum = sum.add(number);
        count++;
        if (max == null || max.compareTo(number) < 0)
            max = number;
        if (min == null || min.compareTo(number) > 0)
            min = number;
    }
}

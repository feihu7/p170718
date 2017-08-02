package abp.p170718;

import org.junit.Assert;
import org.junit.Test;

import abp.p170718.CellAddress;

public class CellAddressTest extends Assert{
    @Test
    public void testToString() {
        CellAddress address = new CellAddress(1, 3);
        assertEquals("1,3", address.toString());

        address = new CellAddress("sheet1", 1, 3);
        assertEquals("sheet1:1,3", address.toString());
    }

    @Test
    public void testToExcelPosition() {
        CellAddress address = new CellAddress(1, 3);
        assertEquals("C1", address.toExcelPosition());

        address = new CellAddress("sheet1", 1, 3);
        assertEquals("sheet1:C1", address.toExcelPosition());
    }
}

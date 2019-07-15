package sometest;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigintToLong {
    public static void main(String[] args) {
        System.out.println(getDoubleNumber1("1.2934E13"));
    }
    /**
     * 将科学计数法的字符串传入
     * @param str
     * @return 返回double类型
     */
    private static double getDoubleNumber(String str){
        double number = 0;
        BigDecimal bd = new BigDecimal(str);
        number =  Double.parseDouble(bd.toPlainString());

        return number;
    }
    private static BigInteger getDoubleNumber1(String str){
        BigDecimal bd = new BigDecimal(str);
        Object object = bd.toBigInteger();
        return bd.toBigInteger();
    }
}

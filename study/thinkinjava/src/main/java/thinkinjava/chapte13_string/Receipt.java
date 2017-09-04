package thinkinjava.chapte13_string;

import java.util.Formatter;

/**
 * 打印超市购物清单
 */
public class Receipt {
    private double total = 0;
    private Formatter f = new Formatter(System.out);
    public void printTitle() {
        f.format("%4$2.2s %3$2s %2$2s %1$2s\n", "a", "b", "c", "dcccc");        // 4$ 表示获取参数在参数列表中的位置：4
        f.format("%-15s %5s %10s\n", "Item", "Qty", "Price");                   // - 表示数据向左边对齐，默认情况向右对齐
        f.format("%-15s %5s %10s\n", "----", "---", "-----");                   // 15 表示表明要向输出中写入的最少字符数

    }
    public void print(String name, int qty, double price) {
        f.format("%-15.15s %5d %10.2f\n", name, qty, price);
        total += price;
    }
    public void printTotal() {
        f.format("%-15s %5s %10.2f\n", "Tax", "", total*0.06);
        f.format("%-15s %5s %10s\n", "", "", "-----");
        f.format("%-15s %5s %10.2f\n", "Total", "",
                total * 1.06);
    }
    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beansccc ", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();

        // 十六进制转换
        System.out.println( String.format("%05X",17) );  // 转换为十六进制后，显示5位，默认显示前面的0不显示
    }
}

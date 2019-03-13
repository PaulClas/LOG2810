package Tp1;
import java.util.ArrayList;
/**
 * The table printer classes takes a matrix of data and prints it.
 */
class TablePrinter {

    /**
     * The row class represents one row of data.
     * Yes, it's just a wrapper for String[], but it helps
     * keep it simple.
     */
    private static class Row {
        String[] data;
        Row(String[] v) { data = v; }
    }

    /**
     * Contains column header and max width information
     */
    private static class Col {
        String name;
        int maxWidth;
    }

    // matrix information
    Col[] cols;
    ArrayList<Row> rows;

    /**
     * Constructor - pass in columns as an array, or hard coded
     */
    public TablePrinter(String... names) {
        cols = new Col[names.length];
        for(int i = 0; i < cols.length; i++) {
            cols[i] = new Col();
            cols[i].name = names[i];
            cols[i].maxWidth = names[i].length();
        }

        rows = new ArrayList<Row>();
    }

    /**
     * Adds a row - pass in an array or hard coded
     */
    public void addRow(String... values) {
        if(values.length != cols.length) {
            throw new IllegalArgumentException("invalid number of columns in values");
        }

        Row row = new Row(values);
        rows.add(row);
        for(int i = 0; i < values.length; i++) {
            if(values[i].length() > cols[i].maxWidth) {
                cols[i].maxWidth = values[i].length();
            }
        }
    }

    /**
     * Helper method to make sure column headers and
     * row information are printed the same
     */
    private void print(String v, int w) {
        System.out.print(" ");
        System.out.print(v);
        System.out.print(spaces(w - v.length()));
        System.out.print(" |");
    }

    /**
     * Ugly, poorly documented print method.
     * All pieces of production code should have some
     * methods that you have to decipher. This fulfils that requirement.
     */
    public void print() {

        System.out.print("|");
        for(Col col : cols) {
            print(col.name, col.maxWidth);
        }
        System.out.println("");
        int numDashes = cols.length*3 + 1;
        for(Col col : cols) numDashes += col.maxWidth;
        // TODO make columns have + instead of -
        System.out.println(dashes(numDashes));
        for(Row row : rows) {
            System.out.print("|");
            int i = 0;
            for(String v : row.data) {
                print(v,cols[i++].maxWidth);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    // print a specific number of spaces for padding
    private static String spaces(int i) {
        StringBuilder sb = new StringBuilder();
        while(i  --> 0) sb.append(" ");
        return sb.toString();
    }

    // print a specific number of dashes
    private static String dashes(int i) {
        StringBuilder sb = new StringBuilder();
        while(i  --> 0) sb.append("-");
        return sb.toString();
    }

    // test driver
    public static void main(String[] args) {
        System.out.println("TablePrinter test driver");

        TablePrinter table = new TablePrinter("MyTest","OtherTest","SillyColumn");
        table.addRow("ABC","DEF","GHIJKLMNOPQRSTUVWXYZ");
        table.addRow("This is a test","of the","TablePrinter class");

        table.print();
    }

}
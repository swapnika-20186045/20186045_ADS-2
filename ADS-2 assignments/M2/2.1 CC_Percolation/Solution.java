import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
    /**
     *the array.
     */
    private boolean[] array;
    /**
     *object declaration for class.
     */
    private Graph graph;
    /**
     *array size.
     */
    private int arraySize;
    /**
     *size.
     */
    private int size;
    /**
     * initializing count.
     */
    private int count;
    /**
     *first row.
     */
    private int top;
    /**
     * last row.
     */
    private int bottom;
    /**
     * Constructs the object.
     */
    protected Percolation() {

    }
    /**
     * Constructs the object.
     *
     * @param n int
     */
    Percolation(final int n) {
        this.arraySize = n;
        this.size = n * n;
        this.top = size;
        this.bottom = size + 1;
        this.count = 0;
        graph = new Graph(size + 2);
        array = new boolean[size];
        for (int i = 0; i < arraySize; i++) {
            graph.addEdge(top, i);
            graph.addEdge(bottom, size - i - 1);
        }
    }
    /**
     * method to convert from two dimensional to one dimensional.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return  onedimensional array
     */
    public int toOneD(final int row, final int col) {
        return (arraySize * (row - 1)) + (col - 1);
    }
    /**
     * Connects open sites(== full site).
     *
     * @param      row   The row
     * @param      col   The col
     */
    private void connectOpenSites(final int row, final int col) {
        if (array[col] && !graph.hasEdge(row, col)) {
            graph.addEdge(row, col);
        }
    }
    /**
     * method that opens the blocked site.
     *
     * @param      row     The row
     * @param      col  The column
     */
    public void open(final int row, final int col) {
        int index = toOneD(row, col);
        array[index] = true;
        count++;
        int toprow = index - arraySize;
        int bottomrow = index + arraySize;
        if (arraySize == 1) {
            graph.addEdge(top, index);
            graph.addEdge(bottom, index);
        }
        if (bottomrow < size) {         //bottom
            connectOpenSites(index, bottomrow);
        }
        if (toprow >= 0) {              //top
            connectOpenSites(index, toprow);
        }
        if (col == 1) {                 //left
            if (col != arraySize) {
                connectOpenSites(index, index + 1);
            }
            return;
        }
        if (col == arraySize) {         //right
            connectOpenSites(index, index - 1);
            return;
        }
        connectOpenSites(index, index + 1);
        connectOpenSites(index, index - 1);
    }
    /**
     * Determines if open.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if open, False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        return array[toOneD(row, col)];
    }
    /**
     * return number of open sites.
     *
     * @return count
     */
    public int numberOfOpenSites() {
        return count;
    }
    /**
     * method to check whether there is a flow.
     *
     * @return boolean
     */
    public boolean percolates() {
        CC connectedComponents = new CC(graph);
        return connectedComponents.connected(top, bottom);
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {

    }
    /**
     * main method to read input.
     *
     * @param args String
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Percolation pobj = new Percolation(n);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            pobj.open(Integer.parseInt(tokens[0]),
                      Integer.parseInt(tokens[1]));
        }
        System.out.println(pobj.percolates()
                           && pobj.numberOfOpenSites() != 0);
    }
}

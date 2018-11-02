/**
 * class for Solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //empty constructor.
    }
    /**
     * Client program.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String synsetsFileName = StdIn.readLine();
        // In in = new In(str);
        // System.out.println(strsynsetsFileName + "lol");
        String hypernymsFileName = StdIn.readLine();
        // In in1 = new In(str1);
        // System.out.println(hypernymsFileName + "lollll");
        // System.out.println(wn.nouns());
        String str2 = StdIn.readLine();
        switch (str2) {
        // WordNet wn = new WordNet(synsetsFileName, hypernymsFileName);
        case "Graph":
            // System.out.println(wn.getDg().toString());
            try {
                WordNet wn = new WordNet(synsetsFileName, hypernymsFileName);
                wn.display();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        case "Queries":
            // System.out.println("lll");
            try {
                WordNet wnq = new WordNet(synsetsFileName,
                                          hypernymsFileName);
                // System.out.println(line + "ooo");
                while (StdIn.hasNextLine()) {
                    String line = StdIn.readLine();
                    String[] strarr = line.split(" ");
                    if (strarr[0].equals("null")) {
                        throw new IllegalArgumentException(
                            "IllegalArgumentException");
                    }
                    System.out.println("distance = " + wnq.distance(strarr[0],
                                       strarr[1]) + ", ancestor = " + wnq.sap(strarr[0],
                                               strarr[1]));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            break;
        default:
            break;
        }
    }
}



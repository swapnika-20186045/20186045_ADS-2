/**
 * wordnet.
 */
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * declaration of ST.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> nounST;
    /**
     * declaration of ST.
     */
    private LinearProbingHashST<Integer, String> idST;
    /**
     * declaration of digraph.
     */
    private Digraph dg;
    /**
     * declaration of SAP.
     */
    private SAP sap;

    /**
     * Gets the dg.
     *
     * @return     The dg.
     */
    public Digraph getDg() {
        return this.dg;
    }
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        nounST = new LinearProbingHashST<String, ArrayList<Integer>>();
        idST = new LinearProbingHashST<Integer, String>();
        try {
            In in = new In("./Files/" + synsets);
            int id = 0;
            while (!in.isEmpty()) {

                String line = in.readLine();

                assert !line.equals("");

                String[] tokens = line.split(",");
                id = Integer.parseInt(tokens[0]);
                String[] nouns = tokens[1].split(" ");

                ArrayList<String> nounsList = new ArrayList<String>();
                for (String noun : nouns) {
                    nounsList.add(noun);
                }
                idST.put(id, tokens[1]);

                for (String noun : nouns) {
                    if (nounST.contains(noun)) {
                        nounST.get(noun).add(id);
                    } else {
                        ArrayList<Integer> s = new ArrayList<Integer>();
                        s.add(id);
                        nounST.put(noun, s);
                    }
                }
            }

            //Hypernyms
            assert id != 1;
            this.dg = new Digraph(id + 1);

            in = new In("./Files/" + hypernyms);
            while (!in.isEmpty()) {
                String line = in.readLine();
                String[] tokens = line.split(",");
                // if (tokens.length > 2) {
                //     throw new IllegalArgumentException("Multiple roots");
                // }
                int synsetIds = Integer.parseInt(tokens[0]);

                for (int i = 1; i < tokens.length; i++) {
                    dg.addEdge(synsetIds, Integer.parseInt(tokens[i]));
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //graph built
        sap = new SAP(dg);
    }

    /**
     * display output.
     */
    public void display() {
        DirectedCycle dc = new DirectedCycle(dg);
        if (dc.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else if (dg.noOfOutdegree() > 1) {
            throw new IllegalArgumentException("Multiple roots");
        } else {
            System.out.println(dg.toString());
        }
    }
    /**
     * return nouns.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> nouns() {
        return nounST.keys();
    }
    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return nounST.contains(word);
    }
    /**
     * checks the distance.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsA = nounST.get(nounA);
        ArrayList<Integer> idsB = nounST.get(nounB);

        return sap.length(idsA, idsB);
    }
    /**
     * ancestor path.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public String sap(final String nounA, final String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsA = nounST.get(nounA);
        ArrayList<Integer> idsB = nounST.get(nounB);

        int anc = sap.ancestor(idsA, idsB);
        return idST.get(anc);
    }
}




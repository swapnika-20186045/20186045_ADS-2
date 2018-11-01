import java.util.ArrayList;
public class WordNet {
    private LinearProbingHashST<String, ArrayList<Integer>> nounST;
    private LinearProbingHashST<Integer, String> idST;
    private Digraph dg;
    private SAP sap;

    public Digraph getDg() {
        return this.dg;
    }
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

    public void display() {
        DirectedCycle dc = new DirectedCycle(dg);
        if (dc.hasCycle()) {
            System.out.println("Cycle detected");
        } else if (dg.noOfOutdegree() > 1) {
            System.out.println("Multiple roots");
        } else {
            System.out.println(dg.toString());
        }
    }
    public Iterable<String> nouns() {
        return nounST.keys();
    }

    public boolean isNoun(final String word) {
        return nounST.contains(word);
    }

    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsA = nounST.get(nounA);
        ArrayList<Integer> idsB = nounST.get(nounB);

        return sap.length(idsA, idsB);
    }

    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idsA = nounST.get(nounA);
        ArrayList<Integer> idsB = nounST.get(nounB);

        int anc = sap.ancestor(idsA, idsB);
        return idST.get(anc);
    }
}
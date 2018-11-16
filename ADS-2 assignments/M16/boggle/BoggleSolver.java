/**
 * Boggle Solver.
 */
import java.util.Set;
import java.util.TreeSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    // Initializes the data structure using the given array of strings
    // as the dictionary.
    // (You can assume each word in the dictionary contains only the
    // uppercase letters A through Z.)
    /**
    * TrieST for dictionary.
    **/
    private TrieST<Integer> trie;
    /**
     * set of valid words.
     */
    private Set<String> validWordsSet;
    /**
     * visited chars.
     */
    private boolean[][] visited1;
    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        trie = new TrieST<Integer>();
        validWordsSet = new TreeSet<String>();
        final int x = 11;
        int[] points = {0, 0, 0, 1, 1, 2, 2 + 1, 2 + 2 + 1, x};
        for (String word : dictionary) {
            if (word.length() >= 2 + 2 + 2 + 2) {
                trie.put(word, x);
            } else {
                trie.put(word, points[word.length()]);
            }
        }
    }

    //Returns the set of all valid words in the given Boggle board,
    //as an Iterable.
    /**
     * Gets all valid words.
     *
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        if (board == null) {
            throw new IllegalArgumentException("board is null");
        }
        visited1 = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                String str = appendCharacter("", board.getLetter(i, j));
                dfs(board, visited1, i, j, str);
            }
        }
        return validWordsSet;
    }

    /**
     * Appends a character.
     *
     * @param      str   The string
     * @param      c     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private String appendCharacter(final String str, final char c) {
        String str1 = str;
        if (c == 'Q') {
            str1 += "QU";
            return str1;
        } else {
            str1 += c;
            return str1;
        }
    }

    /**
     * Determines if valid word.
     *
     * @param      word  The word
     *
     * @return     True if valid word, False otherwise.
     */
    private boolean isValidWord(final String word) {
        if (word.length() < 2 + 1) {
            return false;
        }
        return trie.contains(word);
    }

    /**
     * dfs.
     *
     * @param      board    The board
     * @param      visited  The visited
     * @param      rows     The rows
     * @param      columns  The columns
     * @param      word     The word
     */
    public void dfs(final BoggleBoard board, final boolean[][] visited,
                    final int rows, final int columns, final String word) {
        if (!trie.hasPrefix(word)) {
            return;
        }
        if (isValidWord(word)) {
            validWordsSet.add(word);
        }
        visited[rows][columns] = true;
        for (int i = rows - 1; i <= rows + 1; i++) {
            for (int j = columns - 1; j <= columns + 1; j++) {
                if (isValidRowColumn(i, j, board) && !visited[i][j]) {
                    String sequence = appendCharacter(word,
                                                      board.getLetter(i, j));
                    dfs(board, visited, i, j, sequence);
                }
            }
        }
        visited[rows][columns] = false;
    }

    /**
     * Determines if valid row column.
     *
     * @param      row    The row
     * @param      col    The col
     * @param      board  The board
     *
     * @return     True if valid row column, False otherwise.
     */
    private boolean isValidRowColumn(final int row, final int col,
                                     final BoggleBoard board) {
        return (row >= 0 && col >= 0 && row < board.rows() && col
            < board.cols());
    }

    // Returns the score of the given word if it is in the dictionary,
    // zero otherwise.
    // (You can assume the word contains only the uppercase letters
    // A through Z.)
    /**
     * Score.
     *
     * @param      word  The word
     *
     * @return     { description_of_the_return_value }
     */
    public int scoreOf(final String word) {
        if (word == null) {
            return 0;
        }
        if (trie.contains(word)) {
            return trie.get(word);
        }
        return 0;
    }
}




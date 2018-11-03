import java.util.Scanner;
class PageRank {
	
}

class WebSearch {

}

/**
 * class for Solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution () {
		//empty constructor.
	}
	/**
	 * client program.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);		
		int vertices = Integer.parseInt(scan.nextLine());
		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		Digraph dg = new Digraph(vertices);
		int vcopy = vertices;
		while(vcopy > 0 ) {
			String[] varr = scan.nextLine().split(" ");
			int v = Integer.parseInt(varr[0]);
			for(int i = 1; i < varr.length; i++) {
				dg.addEdge(v, Integer.parseInt(varr[i]));
			}
			vcopy--;
		}
		System.out.println(dg.V() + " vertices, "+ dg.E() + " edges ");

		for(int j = 0; j < vertices; j++) {
			System.out.print(j + ": ");
			for(int k : dg.adj(j)) {
				System.out.print(k + " ");
			}
			System.out.println();
		}
		
		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}

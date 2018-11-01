public final class Solution {
	private Solution() {
		//empty constructor.
	}
	public static void main(final String[] args) {
		String synsetsFileName = StdIn.readString();
		// In in = new In(str);
		// System.out.println(strsynsetsFileName + "lol");
		String hypernymsFileName = StdIn.readString();
		// In in1 = new In(str1);
		// System.out.println(hypernymsFileName + "lollll");
		// System.out.println(wn.nouns());
		String str2 = StdIn.readString();
		switch (str2) {
			// WordNet wn = new WordNet(synsetsFileName, hypernymsFileName);
			case "Graph":
				// System.out.println(wn.getDg().toString());
				WordNet wn = new WordNet(synsetsFileName, hypernymsFileName);
				wn.display();
			break;
			case "Queries":
				WordNet wnq = new WordNet(synsetsFileName, hypernymsFileName);
				String line = StdIn.readLine();
				while (!line.equals("")) {
					String[] strarr = line.split(" ");
					if (strarr[0].equals("null")) {
						System.out.println("IllegalArgumentException");
					}
					System.out.println("distance = " + wnq.distance(strarr[0], strarr[1]) + "ancestor = " + wnq.sap(strarr[0], strarr[1]));
					line = StdIn.readLine();
				}
			break;
			default:
			break;
		}
	}
}
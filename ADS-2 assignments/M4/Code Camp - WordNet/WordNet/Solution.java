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
			case "Graph":
				WordNet wn = new WordNet(synsetsFileName, hypernymsFileName);
				// System.out.println(wn.getDg().toString());
				// System.out.println(wn.readHypernyms(str2));
			break;
		// 	case "Queries":
		// 	break;
			default:
			break;
		}
	}
}
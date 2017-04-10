import java.util.LinkedList;

public class LoopInspector {

	public int loopSize(Node node) {

		LinkedList<Node> li = new LinkedList<>();
		boolean b = false;
		int i = 0;
		Node n = null;
		n = node.getNext();
		while (b == false) {

			for (Node n2 : li) {
				i++;
				if (n.equals(n2)) {
					b = true;
					break;
				} else {
					b = false;
				}
			}
			if (b == false)
				i = 0;
			li.add(n);
			n = n.getNext();
		}

		return li.size() - i;
	}

}
public class Queue<Item> {

	private Node first;
	private Node last;
	private int N;

	private  class Node {
		Item item;
		Node next;
	}

	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;

		if (isEmpty()) {
			first = last;
		} else {
			oldlast.next = last;
		}

		N++;
	}

	public Item dequeue() {
		Item item = first.item;
		first = first.next;

		if (!isEmpty()) {
			N--;
		}
		return item;
	}

	public boolean isEmpty() {
		return N == 0;
	}

}
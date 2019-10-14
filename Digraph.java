public class Digraph {

	private final int V;// ����ͼ�Ķ������
	private int E;// �ߵĸ���
	private Bag<Integer> adj[];// �ڽӱ�
	
	@SuppressWarnings("unchecked")
	public Digraph(int v) {
		this.V = v;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}


	/**
	 * ��ӱ�
	 * 
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	/**
	 * �������ж���vָ��Ķ���
	 * 
	 * @param v
	 * @return
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/**
	 * ����ͼ�ķ���ͼ���ɵõ�����ָ�򶥵�ı�
	 * 
	 * @return
	 */
	public Digraph reverse() {
		Digraph d = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v))
				d.addEdge(w, v);
		}
		return d;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + "vertex" + "  and  " + E + "edge" + ";");
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj(v))
				s.append(w + " ");
			s.append(";");
		}
		return s.toString();
	}

}
import java.util.LinkedList;
import java.util.List;

 
/**
 * P387 T21 顶点v和w的共同祖先（图G无环）
 * 顶点的输入请按规定输入，在这不做判断
 * 思路：所谓的最近祖先就是最近的一个顶点能够分别到达v和w，因此我们可以使用G的反向图和广度优先搜索结合的办法找到
 * 
 * 
 * 
 * @author he
 *
 */
 
class LAC {
	private Digraph G;// 图G的反向图
	private boolean marked[];
 
	public LAC(Digraph G) {
		this.G = G.reverse();
	}
 
	/**
	 * 在逆向图G中顶点v
	 * 
	 * 
	 * 因为使用广度优先搜索，所以linkedlist保存的顺序就是顶点v指向的最近的顶点的顺序
	 * 
	 * @param v
	 */
	public Iterable<Integer> find(Digraph G, int v) {
		marked = new boolean[G.V()];// 重置
		Queue<Integer> queue = new Queue<Integer>();
		List<Integer> l = new LinkedList<Integer>();
		marked[v] = true;
		queue.enqueue(v);
		while (!queue.isEmpty()) {
			int t = queue.dequeue();
			for (int w : G.adj(t)) {
				if (!marked[w]) {
					l.add(w);
					marked[w] = true;
					queue.enqueue(w);
				}
			}
		}
		return l;
	}
 
	/**
	 * 找出顶点v和顶点w的最近共同祖先
	 * 
	 * @param v
	 * @param w
	 * @return
	 */
	public int findLAC(int v, int w) {
		List<Integer> lv = (List<Integer>) find(G, v);
		List<Integer> lw = (List<Integer>) find(G, w);
		if (lv.size() > lw.size()) {
			for (int t : lw) {
				if (lv.contains(t))
					return t;
			}
		} else {
 
			for (int t : lv) {
				if (lw.contains(t))
					return t;
			}
		}
		return -1;
	}
 
}


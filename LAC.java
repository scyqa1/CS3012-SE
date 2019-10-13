import java.util.LinkedList;
import java.util.List;

 
/**
 * P387 T21 ����v��w�Ĺ�ͬ���ȣ�ͼG�޻���
 * ����������밴�涨���룬���ⲻ���ж�
 * ˼·����ν��������Ⱦ��������һ�������ܹ��ֱ𵽴�v��w��������ǿ���ʹ��G�ķ���ͼ�͹������������ϵİ취�ҵ�
 * 
 * 
 * 
 * @author he
 *
 */
 
class LAC {
	private Digraph G;// ͼG�ķ���ͼ
	private boolean marked[];
 
	public LAC(Digraph G) {
		this.G = G.reverse();
	}
 
	/**
	 * ������ͼG�ж���v
	 * 
	 * 
	 * ��Ϊʹ�ù����������������linkedlist�����˳����Ƕ���vָ�������Ķ����˳��
	 * 
	 * @param v
	 */
	public Iterable<Integer> find(Digraph G, int v) {
		marked = new boolean[G.V()];// ����
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
	 * �ҳ�����v�Ͷ���w�������ͬ����
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


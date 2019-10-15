import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

 
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
    private Stack<Integer> cycle; // �����洢���򻷶��㡣
    private int[] edgeTo;// edgeTo[0]=1������1->0, to 0�Ķ���Ϊ1��
    private boolean[] onStack;// ����Ϊ������ֵΪ�ö����Ƿ����dfs�ݹ飬����Ϊtrue

 
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
		l.add(v);
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
		if(IsEmpty(G)) return -1;
		if(IsCycle(G)) return -2;
		if(v>=G.V()||w>=G.V()) return -3;
		
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
		return -4;
	}
	
	public boolean IsEmpty(Digraph digraph) {
		if(digraph.V()==0) return true;
		else return false;
	}
	
    public boolean IsCycle(Digraph digraph) {
        // ��ʼ����Ա����
        marked = new boolean[digraph.V()];
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        cycle = null;
        // ����Ƿ��л�
        for (int v = 0; v < digraph.V(); v++) {
            dfs(digraph, v);
        }
        
        if (hasCycle()) {
        	return true;
        } else {
            return false;
        }
    }

    private void dfs(Digraph digraph, int v) {
        onStack[v] = true;// �ݹ鿪ʼ��������ջ
        marked[v] = true;
        for (int w : digraph.adj(v)) {// ����һ���ߣ�v-> w
            // ��ֹ�������ҵ�����
            if (hasCycle()) return;
            // ʹ��onStack��־λ����¼��Ч·���ϵĵ㣬���w��ջ�ϣ�˵��w��ǰ�浱�˳����㣬
            if (!marked[w]) {
                edgeTo[w] = v;// to w�Ķ���Ϊv
                dfs(digraph, w);
            } else if (onStack[w]) {// ���ָ�����ѱ�ǵĶ��㣬�Ҹö���ݹ�ջ�ϡ���ջ�϶��ǳ����㣬���ҵ����ѱ�ǵĶ������յ㣬˵����������յ���ͬ�ˡ���
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {//����ڵ�һ��ѭ�����Ѿ�push�ˣ���Ҫ�ظ�
                    cycle.push(x);// ����v������w�����Ļ����м�Ľ�����push��cycle�С�
                }
                cycle.push(w);// push�յ�
            }
        }
        onStack[v] = false;// ���ݹ鿪ʼ�����˳�ʱ��������ջ��
    }

    public boolean hasCycle() {
        return cycle != null;
    }
 
}


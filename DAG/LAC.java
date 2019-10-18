import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

 
class LAC {
	private Digraph G;
	private boolean marked[];
    private Stack<Integer> cycle; 
    private int[] edgeTo;
    private boolean[] onStack;

 
	public LAC(Digraph G) {
		this.G = G.reverse();
	}
 
	/**
	 *  DFS
	 * @param v
	 */
	public Iterable<Integer> find(Digraph G, int v) {
		marked = new boolean[G.V()];
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
		
		if (lw.size() > lv.size()) {
			for (int t : lv) {
				if (lw.contains(t)) {
					return t;
				}
			}
		}
		else {
			for (int t : lw) {
				if (lv.contains(t)) {
					return t;
				}
			}
		}
		return -4;
	}
	
	public boolean IsEmpty(Digraph digraph) {
		if(digraph.V()==0) return true;
		else return false;
	}
	
    public boolean IsCycle(Digraph digraph) {

        marked = new boolean[digraph.V()];
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        cycle = null;

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
        onStack[v] = true;
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (hasCycle()) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }
 
}


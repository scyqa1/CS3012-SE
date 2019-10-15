import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

 
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
    private Stack<Integer> cycle; // 用来存储有向环顶点。
    private int[] edgeTo;// edgeTo[0]=1代表顶点1->0, to 0的顶点为1。
    private boolean[] onStack;// 顶点为索引，值为该顶点是否参与dfs递归，参与为true

 
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
	 * 找出顶点v和顶点w的最近共同祖先
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
        // 初始化成员变量
        marked = new boolean[digraph.V()];
        onStack = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        cycle = null;
        // 检查是否有环
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
        onStack[v] = true;// 递归开始，顶点上栈
        marked[v] = true;
        for (int w : digraph.adj(v)) {// 遍历一条边，v-> w
            // 终止条件：找到有向环
            if (hasCycle()) return;
            // 使用onStack标志位来记录有效路径上的点，如果w在栈上，说明w在前面当了出发点，
            if (!marked[w]) {
                edgeTo[w] = v;// to w的顶点为v
                dfs(digraph, w);
            } else if (onStack[w]) {// 如果指到了已标记的顶点，且该顶点递归栈上。（栈上都是出发点，而找到了已标记的顶点是终点，说明出发点和终点相同了。）
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {//起点在第一次循环中已经push了，不要重复
                    cycle.push(x);// 将由v出发，w结束的环上中间的结点遍历push到cycle中。
                }
                cycle.push(w);// push终点
            }
        }
        onStack[v] = false;// 当递归开始结算退出时，顶点下栈。
    }

    public boolean hasCycle() {
        return cycle != null;
    }
 
}


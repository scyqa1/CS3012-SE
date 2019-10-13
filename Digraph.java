import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Digraph {

	private final int V;// 有向图的顶点个数
	private int E;// 边的个数
	private Bag<Integer> adj[];// 邻接表
	
	
	public static class In{
	    private Scanner scanner;
	    // assume Unicode UTF-8 encoding
	    private static final String CHARSET_NAME = "UTF-8";

	    // assume language = English, country = US for consistency with System.out.
	    private static final Locale LOCALE = Locale.US;
		
	    public In() {
	        scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
	        scanner.useLocale(LOCALE);
	    }
	    
	    public In(String name) {
	        if (name == null) throw new IllegalArgumentException("argument is null");
	        try {
	            // first try to read file from local file system
	            File file = new File(name);
	            if (file.exists()) {
	                // for consistency with StdIn, wrap with BufferedInputStream instead of use
	                // file as argument to Scanner
	                FileInputStream fis = new FileInputStream(file);
	                scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
	                scanner.useLocale(LOCALE);
	                return;
	            }

	            // resource relative to .class file
	            URL url = getClass().getResource(name);

	            // resource relative to classloader root
	            if (url == null) {
	                url = getClass().getClassLoader().getResource(name);
	            }

	            // or URL from web
	            if (url == null) {
	                url = new URL(name);
	            }

	            URLConnection site = url.openConnection();

	            // in order to set User-Agent, replace above line with these two
	            // HttpURLConnection site = (HttpURLConnection) url.openConnection();
	            // site.addRequestProperty("User-Agent", "Mozilla/4.76");

	            InputStream is     = site.getInputStream();
	            scanner            = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
	            scanner.useLocale(LOCALE);
	        }
	        catch (IOException ioe) {
	            throw new IllegalArgumentException("Could not open " + name, ioe);
	        }
	    }
		
		public int readInt() {
	        try {
	            return scanner.nextInt();
	        }
	        catch (InputMismatchException e) {
	            String token = scanner.next();
	            throw new InputMismatchException("attempts to read an 'int' value from the input stream, "
	                                           + "but the next token is \"" + token + "\"");
	        }
	        
	        
	        catch (NoSuchElementException e) {
	            throw new NoSuchElementException("attemps to read an 'int' value from the input stream, "
	                                           + "but no more tokens are available");
	        }
	    }
	}

	@SuppressWarnings("unchecked")
	public Digraph(int v) {
		this.V = v;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}

	public Digraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	/**
	 * 添加边
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
	 * 返回所有顶点v指向的顶点
	 * 
	 * @param v
	 * @return
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/**
	 * 有向图的反向图，可得到所有指向顶点的边
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
		s.append("有" + V + "个顶点" + "   " + E + "条边" + "\n");
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj(v))
				s.append(w + " ");
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		Digraph dg = new Digraph(new In(args[0]));
		System.out.println(dg.toString());
		Digraph gd = dg.reverse();
		System.out.println(gd.toString());
	}

}
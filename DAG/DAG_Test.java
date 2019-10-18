import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class DAG_Test
{
	//~ Constructor ........................................................
    @Test
	public void testConstructor()
	{
    	Digraph d = new Digraph(0);
    	new LAC(d);
	}

    @Test
	public void testGrapgh()
	{
    	Digraph d = new Digraph(5);
    	d.addEdge(0, 1);
        d.addEdge(1, 0);
        d.addEdge(2, 3);
        d.addEdge(0, 4);
    	assertEquals(  "5vertex  and  4edge;0: 4 1 ;1: 0 ;2: 3 ;3: ;4: ;", d.toString());
    	assertEquals(  5, d.V());
    	assertEquals(  4, d.E());
    	
    	d=d.reverse();
    	assertEquals(  "5vertex  and  4edge;0: 1 ;1: 0 ;2: ;3: 2 ;4: 0 ;", d.toString());
    	assertEquals(  5, d.V());
    	assertEquals(  4, d.E());
	}
    
    
    @Test
    public void testEmpty() {
    	Digraph d = new Digraph(0);
    	LAC solu=new LAC(d);
    	assertEquals( -1, solu.findLAC(6,7));
    	assertEquals( true, solu.IsEmpty(d));
    }
    
    @Test
    public void testDirectedCycle() {
    	Digraph d = new Digraph(3);
       	d.addEdge(0, 1);
        d.addEdge(1, 2);
        d.addEdge(2, 0);
    	LAC solu=new LAC(d);
    	assertEquals( -2, solu.findLAC(6,7));
    	assertEquals( true, solu.IsCycle(d));
    }
    
    @Test
    public void testOutOfBounds() {
    	Digraph d = new Digraph(3);
       	d.addEdge(0, 1);
        d.addEdge(1, 2);
        
    	LAC solu=new LAC(d);
    	assertEquals( -3, solu.findLAC(6,1));
    	assertEquals( -3, solu.findLAC(1,6));
    }
    
    @Test
    public void testNoLCA() {
    	Digraph d = new Digraph(3);
        
    	LAC solu=new LAC(d);
    	assertEquals( -4, solu.findLAC(1,2));
    }
       
    
    @Test
    public void test1(){
		/*
		 *                          0
		 *                        /   \
		 *                       1     2
		 *                      / \      \
		 *                     /   \      3 
		 *                    /     \    / \
		 *                   4       \  /   \
		 *                    \        5     7
		 *                   	\	   | \   /
		 *                   	  \	   |   8	
		 *                   		\  |
		 *                    		  6  
		 * 
		 * */
    	Digraph d = new Digraph(9);
    	d.addEdge(0, 1);
        d.addEdge(0, 2);
        d.addEdge(1, 4);
        d.addEdge(1, 5);
        d.addEdge(2, 3);
        d.addEdge(3, 5);
        d.addEdge(3, 7);
        d.addEdge(4, 6);
        d.addEdge(5, 6);
        d.addEdge(5, 8);
        d.addEdge(7, 8);
        
    	LAC solu=new LAC(d);
    	assertEquals( 3, solu.findLAC(6,7));
    	assertEquals( 5, solu.findLAC(6,8));
    	assertEquals( 1, solu.findLAC(4,8));
    	assertEquals( 1, solu.findLAC(4,5));
    }
    
    @Test
    public void test2(){
		/*
		 *                          0
		 *                        /   \
		 *                       1     2
		 *                      /|   /   \
		 *                     / |  /     3 
		 *                    /  | /     /|\
		 *                   4   6      / | \
		 *                    \   \    5  |  7
		 *                   	\	 \ |\ | /
		 *                   	  \	   |\\|/ 	
		 *                   		\  |  8 
		 *                    		  9  
		 * 
		 * */
    	Digraph d = new Digraph(10);
    	d.addEdge(0, 1);
        d.addEdge(0, 2);
        d.addEdge(1, 4);
        d.addEdge(1, 6);
        d.addEdge(2, 3);
        d.addEdge(2, 6);
        d.addEdge(3, 5);
        d.addEdge(3, 7);
        d.addEdge(3, 8);
        d.addEdge(4, 9);
        d.addEdge(5, 8);
        d.addEdge(5, 9);
        d.addEdge(6, 8);
        d.addEdge(7, 8);
        
    	LAC solu=new LAC(d);
    	assertEquals( 5, solu.findLAC(9,8));
    	assertEquals( 5, solu.findLAC(5,8));  
    	assertEquals( 2, solu.findLAC(6,9));
    	assertEquals( 2, solu.findLAC(6,5));
    	
    	assertEquals( "[2, 6, 3, 8, 7, 5, 9]", solu.find(d,2).toString()); 
    	assertEquals( false, solu.IsCycle(d));
    	assertEquals( false, solu.IsEmpty(d));
    }
    

    
}

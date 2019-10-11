import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;



@RunWith(JUnit4.class)
public class SolutionTest
{
	//~ Constructor ........................................................
    @Test
	public void testConstructor()
	{
	  new Solution();
	}
    
    @Test
	public void testEmptyTree()
	{
	  Solution testSolu=new Solution();
	  
	    /*
		 *   root:null      
		 *                     node1:null          node2:null
		 */  
	  
	  Solution.TreeNode root=null;	  
	  Solution.TreeNode node1 = null;
      Solution.TreeNode node2 = null; 
	  assertEquals(  null, testSolu.lowestCommonAncestor(root,node1,node2) );
	  
        /*
		 *          5
		 *         / \
		 *      null  null
		 */     
	  
	  root=testSolu.new TreeNode(5);
	  node1 = root.getLeftSubtree();
      node2 = root.getRightSubtree(); 
	  assertEquals(  null, testSolu.lowestCommonAncestor(root,node1,node2) );
	  
        /*
		 *          5
		 *         / \
		 *        4  null
		 */    

	  root=testSolu.new TreeNode(5);
	  root.makeLeftSubTree(4);
	  node1 = root.getLeftSubtree();
      node2 = root.getRightSubtree(); 
	  assertEquals(  null, testSolu.lowestCommonAncestor(root,node1,node2) );
	  
        /*
		 *          5
		 *         / \
		 *        4   3        
		 * 	  
         *        node1:               node2:
		 *          2                    7
		 *         / \                  / \
		 *      null  null           null  null
		 */     
	  root=testSolu.new TreeNode(5);
	  root.makeLeftSubTree(4);
      root.makeRightSubtree(3);
      node1=testSolu.new TreeNode(2);
      node2=testSolu.new TreeNode(7);
	  assertEquals(  null, testSolu.lowestCommonAncestor(root,node1,node2) );
	}
    
    
    @Test
	public void test()
	{
	  Solution testSolu=new Solution();
	  Solution.TreeNode root=testSolu.new TreeNode(5);

	  root.makeLeftSubTree(4);
      root.makeRightSubtree(3);
      root.getLeftSubtree().makeLeftSubTree(7);
      root.getLeftSubtree().makeRightSubtree(9);
      root.getRightSubtree().makeLeftSubTree(1);
      root.getRightSubtree().makeRightSubtree(2);
      root.getLeftSubtree().getRightSubtree().makeSubtree(10, -3);
      root.getLeftSubtree().getRightSubtree().getRightSubtree().makeSubtree(0, 5);
      
        /*
		 *          5
		 *         / \
		 *      4       3
		 *     / \     / \
		 *    7   9    1  2
		 *       / \
		 *     10   -3
		 *          / \
		 *         0   5
		 */         
      
      // find lca of two nodes which is 7 and 2
      Solution.TreeNode node1 = root.getLeftSubtree().getLeftSubtree();
      Solution.TreeNode node2 = root.getRightSubtree().getRightSubtree();      
      assertEquals(  root, testSolu.lowestCommonAncestor(root,node1,node2) );
      
      List<Solution.TreeNode> path_node1 = new ArrayList<>();
      List<Solution.TreeNode> path_node2 = new ArrayList<>();
      path_node1.add(root);
      path_node2.add(root);
      testSolu.getPath(root,node1,path_node1);
      testSolu.getPath(root,node2,path_node2);

      assertEquals(  root, path_node1.get(0) );
      assertEquals(  root.getLeftSubtree(), path_node1.get(1) );
      assertEquals(  root.getLeftSubtree().getLeftSubtree(), path_node1.get(2) );
      
      assertEquals(  root, path_node2.get(0) );
      assertEquals(  root.getRightSubtree(), path_node2.get(1) );
      assertEquals(  root.getRightSubtree().getRightSubtree(), path_node2.get(2) );


      
      // find lca of two nodes which is 7 and 0
      node1 = root.getLeftSubtree().getLeftSubtree();
      node2 = root.getLeftSubtree().getRightSubtree().getRightSubtree().getLeftSubtree();      
      assertEquals(  root.getLeftSubtree(), testSolu.lowestCommonAncestor(root,node1,node2) );
      
      path_node1 = new ArrayList<>();
      path_node2 = new ArrayList<>();
      path_node1.add(root);
      path_node2.add(root);
      testSolu.getPath(root,node1,path_node1);
      testSolu.getPath(root,node2,path_node2);

      assertEquals(  root, path_node1.get(0) );
      assertEquals(  root.getLeftSubtree(), path_node1.get(1) );
      assertEquals(  root.getLeftSubtree().getLeftSubtree(), path_node1.get(2) );
      
      assertEquals(  root, path_node2.get(0) );
      assertEquals(  root.getLeftSubtree(), path_node2.get(1) );
      assertEquals(  root.getLeftSubtree().getRightSubtree(), path_node2.get(2) );
      assertEquals(  root.getLeftSubtree().getRightSubtree().getRightSubtree(), path_node2.get(3) );
      assertEquals(  root.getLeftSubtree().getRightSubtree().getRightSubtree().getLeftSubtree(), path_node2.get(4) );


	}
}

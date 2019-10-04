import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
      
      assertEquals(  root, testSolu.lowestCommonAncestor(root,root.getLeftSubtree().getLeftSubtree(),root.getRightSubtree().getRightSubtree()) );
      assertEquals(  root.getLeftSubtree(), testSolu.lowestCommonAncestor(root,root.getLeftSubtree().getLeftSubtree(),root.getLeftSubtree().getRightSubtree().getRightSubtree().getLeftSubtree()) );

	}
}

/**
 * Summing up the hours of individual tree
 *
 * @author  Mahoko Malebo
 * @version 13 September 2018
 *
 **/


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


public class SumTree extends RecursiveAction {
	/**
	 *
	 */
    private int y_corner_tree, tree_extend, ID;
    private ForkJoinPool fjPool = new ForkJoinPool();
    double total_per_row;
    // sum per tree which is basically the sum of all the total_per_row
    // But since we will be adding all sum_per_row on sum_per_tree, you need to use semaphores/mutex to lock sum_per_tree to avoid race condition
    List<String> startrow;
		/**
		 * Precondition: Given the array and the start and end index.
		 * Postcondition: Create a Thread object using devide-and-conquer
		 */
    public SumTree(List<String> pstartrow,int py_corner_tree,int ptree_extend, int pID) {
          ID =pID;
          y_corner_tree = py_corner_tree;
          tree_extend = ptree_extend;
          startrow = pstartrow;
    }

    @Override
    protected void compute() {
        System.out.println("Starting thread " + ID);
        int arrayStart = y_corner_tree;
        int arrayEnd = y_corner_tree + tree_extend;
        if(arrayEnd >= startrow.size()){
          arrayEnd = startrow.size();
        }
        total_per_row += Double.parseDouble(fjPool.invoke(new SumArray(startrow.toArray(new String[0]),Integer.toString(arrayStart),Integer.toString(arrayEnd))));
        System.out.println("Ending thread " + ID);

    }
}

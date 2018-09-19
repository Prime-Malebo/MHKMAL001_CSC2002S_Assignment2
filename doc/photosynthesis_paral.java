

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.PrintWriter;
import java.util.concurrent.ForkJoinPool;


public class photosynthesis_paral {
     public static void main(String[] args) throws Exception{
        int procID = 0;
        ForkJoinPool fjPool = new ForkJoinPool();
        System.out.println("I am running");
        File file = new File("seqData.txt");
        if(args.length >0){
          file = new File(args[0]);
        }
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){

          String xy = sc.nextLine(); //terrain section dimension
          List<List<String>> ls2d = new ArrayList<List<String>>();  //matrix of terrain span 2D
          List<String> matrix1D = new ArrayList<String>(Arrays.asList((sc.nextLine()).split(" ")));
          int x = Character.getNumericValue((xy.charAt(0))); // terrain x size
          int y = Character.getNumericValue((xy.charAt(2))); // terrain y size

          for(int i=0; i<x ;i++){ //create 2D array
              ls2d.add( matrix1D.subList( (i*y) ,y*(i+1)  ) );
          }

          int numTrees = Integer.parseInt( sc.nextLine() );

          for(int j=0; j<numTrees; j++){ //calculate total sunlight per tree
            String treeinfo =  sc.nextLine() ;
            int start_index = Character.getNumericValue(treeinfo.charAt(0)) ; //x corner of tree
            int tree_extend = Character.getNumericValue(treeinfo.charAt(4)) ; //canopy extent of tree
            int y_corner_tree = Character.getNumericValue(treeinfo.charAt(2)); //y corner of tree

            for(int x_axis=0; x_axis<tree_extend; x_axis++){
              List<String> startrow = new ArrayList<String>();
              if((start_index +x_axis)< ls2d.size()){
                startrow = ls2d.get(start_index +x_axis);
              }
              // Implement the parallel version
              fjPool.invoke(new SumTree(startrow,y_corner_tree,tree_extend,procID++));
              // Create a thread for each tree row
            }

          }//end numTrees loop
        }
        System.out.println("I am done");
     }
}

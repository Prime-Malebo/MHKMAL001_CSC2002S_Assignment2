
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.PrintWriter;


public class photosynthesisSequencial{

  public static void main(String[] args) throws Exception {
    System.out.println("I am running");
    File file = new File("doc/data.txt");
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
      double totalAll =0;
      List<Double> list_of_total = new ArrayList<Double>();


      for(int j=0; j<numTrees; j++){ //calculate total sunlight per tree

        double total_per_tree=0;

        String treeinfo =  sc.nextLine() ;
        int start_index = Character.getNumericValue(treeinfo.charAt(0)) ; //x corner of tree
        int tree_extend = Character.getNumericValue(treeinfo.charAt(4)) ; //canopy extent of tree
        int y_corner_tree = Character.getNumericValue(treeinfo.charAt(2)); //y corner of tree

        for(int k=0; k<tree_extend; k++){

          List<String> startrow = new ArrayList<String>();
          if((start_index +k)< ls2d.size()){
            startrow = ls2d.get(start_index +k);
          }

          for(int a=0; a<tree_extend; a++){
            if( ((y_corner_tree+a)< startrow.size()) && (startrow.size()!=0) ){

              total_per_tree += Double.parseDouble( startrow.get( y_corner_tree+a  ) );
              System.out.println(total_per_tree);
            }
          }
        }

        totalAll += total_per_tree;
        list_of_total.add(total_per_tree);

      }//end numTrees loop

    System.out.println("I am done");

    //writting to output file
    PrintWriter writer = new PrintWriter("test output.txt", "UTF-8");
    if(args.length >0){
      writer = new PrintWriter(args[1], "UTF-8");
    }
    writer.println(totalAll/numTrees); //avg
    writer.println(numTrees); //num of trees
    for(Double tot: list_of_total){
      writer.println(tot); //avg
    }
    writer.close();
    }//end while
  }
}

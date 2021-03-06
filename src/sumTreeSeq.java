/**
 * Summing up the sunlight hours a tree gets daily, using the sequantial approach
 *
 * @author  Mahoko Malebo
 * @version 11 September 2018
 *
 **/

import java.util.*; 
import java.io.*;

public class sumTreeSeq{

    public static void main(String args []) throws Exception{
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();  
        StringTokenizer files = new StringTokenizer(in);
        
        String inputFile = files.nextToken();
        String outputFile = files.nextToken();           
        
        //Extracting the size of the 2x2 created
        Scanner terrainSize = new Scanner(new File(inputFile));
        String[] xy = terrainSize.nextLine().split(" ");        
        String X = xy[0];
        String Y = xy[1];
        int terrainX = Integer.parseInt(X);
        int terrainY = Integer.parseInt(Y);
        float[] arrayHours = new float[terrainX*terrainY];
        float[][] hours = new float[terrainX][terrainY];
        
        //extracting the hours from the file 
        Scanner readFile = new Scanner(new File(inputFile));
        readFile.nextLine();
        while (readFile.hasNextLine()){
            System.out.println(arrayHours.length);
            
            StringTokenizer lineHours = new StringTokenizer(readFile.nextLine());
            System.out.println(lineHours.countTokens());
            for (int i = 0; i < terrainX*terrainY; i++ ){
                //System.out.println(Float.valueOf(lineHours.nextToken()));
                arrayHours[i] = Float.valueOf(lineHours.nextToken()); 
            }
        
            
            for (int i = 0; i< arrayHours.length  ; i++){
                for (int j = 0; j < arrayHours[i].length ; j++ ){
                    hours[i][j] = arrayHours[i];
                }
            } 
            
            for (int i = 0; i < terrainY ; i++){
                for (int j = 0; j < terrainX; j++){
                    String conv = Float.toString(hours[i][j]);
                    System.out.printf( conv+" ");
                }
                System.out.println();
            }
            break;
        }
    }
} 
/*CPCS 324: Algorithms and Data Structures (II)
Group Project 
Section: BAR
Team members
Sarah Hamoud Aljabri   - 1907215
Khadija Salem Balfagih - 1914895
Nouf Abdullah Alharbi  - 1906257 */
package cpcs324_project_part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author KHADIJA.B, Nouf, Sarah
 */
public class Application {

    /**
     *
     * @param args the main
     * @throws FileNotFoundException exception for fils
     */
    public static void main(String[] args) throws FileNotFoundException {
        //tow arraies to store edge and vertex value
        int n[] = {1000, 1000, 1000, 5000, 5000, 10000, 10000, 20000, 20000, 50000}; //n= number of vertices 
        int m[] = {10000, 15000, 25000, 15000, 25000, 15000, 25000, 200000, 300000, 1000000}; //m= number of edges

        //initial value for choice
        int MyC = -1;
        Scanner in = new Scanner(System.in);
        //print the header of program then ask user to choice any experiment
        do {
            System.out.println("[                            W E L C O M E                          ]");
            System.out.println("[    The application applay the minimum-spanning-tree algorithms    ]");
            System.out.println("[               Enter 1 or 2 to start your experiment..             ]\n");
            System.out.println("1- Kruskal's Algorithm & PQ Prim's Algorithm");
            System.out.println("2- MH Prim's Algorithm & PQ Prim's Algorithm");
            System.out.print("• your choice : ");
            MyC = in.nextInt();
            if (MyC != 1 && MyC != 2) { //if not 1 and 2 print wrong number and try again 
                System.out.println("Wrong input");
            }
        } while (MyC != 1 && MyC != 2);

        // Create file to print in for algorthims 
        File KrusFile = new File("Kruskal.txt"); 
        File PQ1File = new File("PQ1Prim.txt"); 
        File MHFile = new File("MHPrim.txt"); 
        File PQ2File = new File("PQ2Prim.txt"); 

        //creat opject for write in files
        PrintWriter outputKrus = new PrintWriter(KrusFile);
        PrintWriter outputPQ1 = new PrintWriter(PQ1File);
        PrintWriter outputMH = new PrintWriter(MHFile);
        PrintWriter outputPQ2 = new PrintWriter(PQ2File);

        System.out.println();
        
        //if choice was 1 
        if (MyC == 1) {

            //for loop 10 time for the 10 Cases in arraies
            for (int t = 0; t < 10; t++) {
                outputKrus.print((t + 1) + " ");
                outputPQ1.print((t + 1) + " ");
                System.out.println("【 Case " + (t + 1) + " 】");

                //for loop 10 time iteration for each case
                for (int k = 0; k < 10; k++) {
                    System.out.println("Iteration " + (k + 1) + " ➡");
                    //creat a graph object
                    Graph graph = new Graph(n[t], m[t]);
                    //generate randolly graph 
                    graph.makeGraph();

                    //craet MST object to send graph to kruskal and prim priority queue
                    MSTAlgorithm algo1 = new KruskalAlg(graph);
                    MSTAlgorithm algo2 = new PQPrimAlg(graph);
                    //call to perform and display the total cost and time 
                    algo1.displayResultingMST();
                    algo2.displayResultingMST();
                    //print the run time in files
                    outputKrus.print(algo1.duration + " ");
                    outputPQ1.print(algo2.duration + " ");
                    System.out.println();
                }
                
                System.out.println("________________");
                //print new line for next case
                outputKrus.print("\n");
                outputPQ1.print("\n");
            }
            
         //if choice was 2   
        } else if (MyC == 2) {

            //for loop 10 time for the 10 Cases in arraies
            for (int t = 0; t < 10; t++) {
                outputMH.print((t + 1) + " ");
                outputPQ2.print((t + 1) + " ");
                System.out.println("【 Case " + (t + 1) + " 】");

                //for loop 10 time iteration for each case
                for (int k = 0; k < 10; k++) {
                    System.out.println("Iteration " + (k + 1) + " ➡");
                    //creat a graph object
                    Graph graph = new Graph(n[t], m[t]);
                    //generate randolly graph 
                    graph.makeGraph();

                    //craet MST object to send graph to prim min heap and prim priority queue
                    MSTAlgorithm algo1 = new MHPrimAlg(graph);
                    MSTAlgorithm algo2 = new PQPrimAlg(graph);
                    //call to perform and display the total cost and time 
                    algo1.displayResultingMST();
                    algo2.displayResultingMST();
                    //print the run time in files
                    outputMH.print(algo1.duration + " ");
                    outputPQ2.print(algo2.duration + " ");
                    System.out.println();
                }
                System.out.println("________________");
                //print new line for next case
                outputMH.print("\n");
                outputPQ2.print("\n");
            }
        }
        //close all my files
        outputPQ1.close();
        outputPQ2.close();
        outputMH.close();
        outputKrus.close();
    }
}
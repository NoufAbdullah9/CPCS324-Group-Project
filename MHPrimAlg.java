/*CPCS 324: Algorithms and Data Structures (II)
Group Project 
Section: BAR
Team members
Sarah Hamoud Aljabri   - 1907215
Khadija Salem Balfagih - 1914895
Nouf Abdullah Alharbi  - 1906257 */
package cpcs324_project_part1;

import java.util.ArrayList;

/**
 *
 * @author KHADIJA.B, Nouf, Sarah
 */
public class MHPrimAlg extends MSTAlgorithm {

    /**
     *it take the generated graph
     * @param g it take the generated graph
     */
    public MHPrimAlg(Graph g){
        graph = g;
    }
    
    /**
     * MIN-HEAP PRIM ALGORITHM //.
     */
    @Override
    public void displayResultingMST() {
        //start the time
        double StartTime = System.currentTimeMillis();

        //create arrays and heap to use it        
        MSTresultList = new ArrayList<Edge>();
        boolean added[] = new boolean[graph.verticesNo];
        Heap<Edge> mh = new Heap<Edge>();
        int mst_cost = 0;
        
        //add edges in MST with source vertex and assign initial value in array
        for(int i = 0; i < graph.verticesNo; i++){
            MSTresultList.add(new Edge(graph.vertices.get(i).label,-1,Integer.MAX_VALUE));
            added[i] = false;
        }
               
        //add in heap the first element with weight 0
        MSTresultList.get(0).weight = 0;
        mh.add(MSTresultList.get(0));
        
        //while the heap is not empty
        while(!mh.isEmpty()){
            
            //retrieve and remove the minimum element
            Edge e = mh.removeMin();
             
            // If the node is not added to the minimum spanning tree, add it and increment the cost
            if (!added[e.source.label]) {
                //add its cost in total
                mst_cost += e.weight;
                //updated with true
                added[e.source.label] = true;
                

                //for all vertex adjacent 
                for (Edge e2 : graph.vertices.get(e.source.label).adjList) {
                    int label = e2.target.label;
                    //add only the vertex that are not in MST
                    if (added[label] == false) {
                        MSTresultList.set(label,new Edge(label,-1,e2.weight)); 
                        MSTresultList.get(label).parent = e.source;
                        mh.add(new Edge(label,-1,e2.weight));
                    }
                }
            }
        }
        //finish the time
        double FinishTime = System.currentTimeMillis();
        //print the time that the algorithm has taken
        System.out.println("Total runtime of Prim's Algorithm (MH): " + (FinishTime - StartTime) + " ms.");
        //assign the time for print in file
        duration = (int) (FinishTime - StartTime);
        //print the final total cost
        System.out.println("Total cost: " + mst_cost);
   }
}


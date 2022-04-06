/*CPCS 324: Algorithms and Data Structures (II)
Group Project 
Section: BAR
Team members
Sarah Hamoud Aljabri   - 1907215
Khadija Salem Balfagih - 1914895
Nouf Abdullah Alharbi  - 1906257 */
package cpcs324_project_part1;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author KHADIJA.B, Nouf, Sarah
 */
public class KruskalAlg extends MSTAlgorithm {

    /**
     *it take the generated graph
     * @param g it take the generated graph
     */
    public KruskalAlg(Graph g) {
        graph = g;
    }


    /**
     *make all vertices parents = -1 initialy
     * @param parent array of parent
     */
    public void makeSet(int[] parent) {
        for (int i = 0; i < graph.verticesNo; i++) {
            parent[i] = -1;
        }
    }

    /**
     *make all sets belongs to the same parent
     * @param parent integer the array of parent
     * @param x integer x 
     * @param y integer y
     */
    public void union(int[] parent, int x, int y) {
        int x_setParent = parent[x]; //find parent of x
        int y_setParent = parent[y]; //find parent of y
        //make x as parent of y
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == y_setParent) {
                parent[i] = x_setParent;
            }
        }
    }

    /**
     * THE KRUSKAL ALGORITHM //.
     */
    @Override
    public void displayResultingMST() {
        //start the time
        double StartTime = System.currentTimeMillis();
        
        //create arrays and PriorityQueue to use it
        MSTresultList = new ArrayList<Edge>();
        boolean[] vers = new boolean[graph.verticesNo];
        int[] parent = new int[graph.verticesNo];
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        
        //make initialize values for each vertex
        int j = 0;
        for (Vertex v : graph.vertices) {
            v.isVisited = false;
            vers[j] = false;
            parent[j] = -1;
            j++;
        }

        //creat new edge with same value then add to priority queue
        for (Vertex vx : graph.vertices) {
            for (Edge e : vx.adjList) {
                Edge e1 = new Edge(e.source.label, e.target.label, e.weight);
                e1.source.isVisited = false;
                e1.target.isVisited = false;
                pq.add(e1);
            }
        }
        
        // make the set 
        makeSet(parent);
        
        //while the priority queue is not empty 
        while (!pq.isEmpty()) {
            
            //retrieve and remove the head element in pq //returns null if this queue is empty
            Edge e = pq.poll();
            
            //if source and destenation of edge in a MST 
            if (vers[e.source.label] && vers[e.target.label]) {
                //if the parent is diffrent
                if (parent[e.source.label] != parent[e.target.label]) {
                    //make union in the same set
                    union(parent, e.source.label, e.target.label);
                    int prt = parent[e.source.label];
                    //make the source and destenation true and with same parent
                    vers[e.source.label] = true;
                    vers[e.target.label] = true;
                    parent[e.source.label] = prt;
                    parent[e.target.label] = prt;
                    //add in our final result MST
                    MSTresultList.add(e);
                }
            
            //if source and destenation of edge not in a MST
            } else {
                //assign a perent if it has or distination
                int prt = -1;
                if (parent[e.source.label] != -1)
                    prt = parent[e.source.label];
                else
                    prt = parent[e.target.label];

                //if distination also not include 
                if (prt == -1) {
                    prt = e.source.label;
                }

                //make the source and destenation true and with same parent                
                vers[e.source.label] = true;
                vers[e.target.label] = true;
                parent[e.source.label] = prt;
                parent[e.target.label] = prt;
                //add in our final result MST
                MSTresultList.add(e);
            }
        }

        //finish the time 
        double FinishTime = System.currentTimeMillis();
        //print the time that the algorithm has taken
        System.out.println("Total runtime of Kruskal's Algorithm: " + (FinishTime - StartTime) + " ms.");
        //assign the time for print in file
        duration = (int) (FinishTime - StartTime);
        //print the final result MST
        PrintMinSpanTree(MSTresultList);
    }

    /**
     * Print the the total cost in MST
     * @param resultSet array final result
     */
    public void PrintMinSpanTree(ArrayList<Edge> resultSet) {
        //add the wight for each edge in total 
        int total_min_weight = 0;
        for (int i = 0; i < resultSet.size(); i++) {
            total_min_weight += resultSet.get(i).weight;
        }
        //print the total cost
        System.out.println("Total cost: " + total_min_weight);
    }

}

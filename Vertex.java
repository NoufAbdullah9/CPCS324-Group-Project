/*CPCS 324: Algorithms and Data Structures (II)
Group Project 
Section: BAR
Team members
Sarah Hamoud Aljabri   - 1907215
Khadija Salem Balfagih - 1914895
Nouf Abdullah Alharbi  - 1906257 */
package cpcs324_project_part1;

import java.util.LinkedList;

/**
 *
 * @author KHADIJA.B, Nouf, Sarah
 */
public class Vertex {
     

    /**
     *the variales
     * the label vertex value
     */
    int label;

    /**
     *Is the vertex vesited or not
     */
    boolean isVisited; 

    /**
     *linked list for all adjuncy edge list of vertex 
     */
    LinkedList<Edge> adjList;
    
   

    /**
     *the constructor with int label parameter
     * @param label int label of vertex
     */
    public Vertex(int label){
        this.label = label;
        adjList = new LinkedList<Edge>();
        isVisited = false;
    }

    
}
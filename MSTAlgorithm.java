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
public abstract class MSTAlgorithm {

    /**
     * the graph that ues in algorithm
     */
    Graph graph;

    /**
     * the result MST stored here
     */
    ArrayList<Edge> MSTresultList; 

    /**
     *the time that the algorithm has taken
     */
    int duration;

    /**
     * absratct method include in all algorithm 
     * to calcuate the algorthim and display it
     */
    public abstract void displayResultingMST();
}


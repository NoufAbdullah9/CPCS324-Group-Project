/*CPCS 324: Algorithms and Data Structures (II)
Group Project 
Section: BAR
Team members
Sarah Hamoud Aljabri   - 1907215
Khadija Salem Balfagih - 1914895
Nouf Abdullah Alharbi  - 1906257 */
package cpcs324_project_part1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author KHADIJA.B, Nouf, Sarah
 */
public class Graph {

    /**
     *the variales
     * number of vertices in graph
     */
    int verticesNo; 

    /**
     *number of edge in graph
     */
    int edgeNo;

    /**
     *array list for all verteces in graph
     */
    ArrayList<Vertex> vertices;

    /**
     *Is Direct graph or not
     */
    boolean isDigraph;


    /**
     *the constructor with integer number of edge and vertices
     * @param verticesNo number of vertices in graph
     * @param edgeNo number of edge in graph
     */
    Graph(int verticesNo, int edgeNo) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.vertices = new ArrayList<Vertex>();
        //to initialize the vertices
        for (int i = 0; i < verticesNo; i++) {
            vertices.add(new Vertex(i));
        }
        isDigraph = isDigraph();

    }


    /**
     *checks if there is already existed and connected
     * @param Source the lable of vertex
     * @param Destination the label of Destination
     * @return boolean F or T
     */
    public boolean isConnected(int Source, int Destination) {
        for (Edge e : vertices.get(Source).adjList) {
            if (e.target.label == Destination) {
                return true;
            }
        }
        for (Edge e : vertices.get(Destination).adjList) {
            if (e.target.label == Source) {
                return true;
            }
        }
        return false;
    }


    /**
     *add a new edge from source to target and vice versa
     * @param source the lable of source
     * @param target the lable of target
     * @param weight int weight
     */
    public void addEdge(int source, int target, int weight) {
        Edge edge = new Edge(source, target, weight);
        vertices.get(source).adjList.add(edge);

        //for make undirect graph
        edge = new Edge(target, source, weight);
        vertices.get(target).adjList.add(edge);
    }



    /**
     *randomly generate graph by makes edges
     */
    public void makeGraph() {
        Random random = new Random(); // Random Class
        //for connect each vertex with its next
        for (int i = 0; i < verticesNo - 1; i++) {
            int randG = random.nextInt(30) + 1;
            addEdge(i, i + 1, randG);
        }
        int remaning = edgeNo - (verticesNo - 1); //for take rwmaning edge
        //generate random graph with the remaining edges
        for (int i = 0; i < remaning; i++) {
            
            //generate random for source, Destination and weight
            int source = random.nextInt(verticesNo);
            int Destination = random.nextInt(verticesNo);
            int weight = random.nextInt(30) + 1;
            
            //if it is already existed 
            if (Destination == source || isConnected(source, Destination)) {
                i--;
                continue;
            }
            
            addEdge(source, Destination, weight);
        }

        isDigraph = isDigraph();
    }
    
        


    /**
     *searchs for the edge from u to v
     * @param u vertex u
     * @param w vertex w
     * @return true or false 
     */
    private boolean EdgeExist(Vertex u, int w) {
        for (Edge e : u.adjList) {
            if (e.target.label == w) {
                return true;
            }
        }
        return false;
    }


    /**
     *checks if the graph is direct or not
     * @return true or false
     */
    public boolean isDigraph() {
        for (int i = 0; i < vertices.size(); i++) {
            for (Edge e : vertices.get(i).adjList) {
                if (EdgeExist(e.target, i) == false) {
                    return false;
                }
            }
        }
        return true;
    }
}

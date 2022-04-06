/*CPCS 324: Algorithms and Data Structures (II)
Group Project 
Section: BAR
Team members
Sarah Hamoud Aljabri   - 1907215
Khadija Salem Balfagih - 1914895
Nouf Abdullah Alharbi  - 1906257 */
package cpcs324_project_part1;


/**
 *
 * @author KHADIJA.B, Nouf, Sarah
 */
public class Edge implements Comparable<Edge> {
    //the variales 

    /**
     *source vertex
     */
    Vertex source; 

    /**
     *target vertex
     */
    Vertex target; 

    /**
     *parent vertex
     */
    Vertex parent; 

    /**
     *weigh of edge
     */
    int weight; 


    

    /**
     *the constructor with integer parameter
     * @param source int label for source
     * @param target int label for target
     * @param weight int weigh of edge
     */
    public Edge(int source, int target, int weight) {
        this.source = new Vertex(source);//craet to int label for source
        this.target = new Vertex(target);//craet to int label for target
        this.weight = weight;
        parent = new Vertex(-1);//initialize parent vertex
    }
    
    

    /**
     *the constructor with vertex and int parameter
     * @param source Vertex source
     * @param target Vertex target
     * @param weight int weight of edge
     */
    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        parent = new Vertex(-1);//initialize parent vertex
    }


    /**
     *the defulte constructor
     */
    public Edge() {
        this.source = new Vertex(0);//initialize source vertex
        this.target = new Vertex(1);//initialize target vertex
        this.weight = 0;
        parent = new Vertex(-1);
    }

    /**
     * @return source, target and weight
     */
    @Override
    public String toString() {
        return source + "-" + target + ": " + weight;
    }

    /**
     *override the comparator to do the sorting based keys
     * @param o edge that compare it
     * @return 1 o1 grater than o2, -1 o1 smaller than o2, 0 is equal.
     */
    @Override    
    public int compareTo(Edge o) {
        if(this.weight > o.weight) {
            return 1;
        } else if (this.weight < o.weight) {
            return -1;
        } else {
            return 0;
        }
    }

}

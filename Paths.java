
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;

import java.util.Iterator;
import java.util.LinkedList;

public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        System.out.println(g);

        DepthFirstSearch dfs = new DepthFirstSearch(g, 0);

        dfs.dfs(g, 0);
        for(int i=0; i<g.numberOfVertices(); i++){
            if(!dfs.visited[i])System.out.println("didnt visit " + i);
        }
        System.out.println("total visits " + dfs.count);
        DepthFirstSearch dfs2 = new DepthFirstSearch(g, 0);
        int components = dfs2.noOfComponents(g, 999);
        System.out.println(components);
        Dijkstra d = new Dijkstra();
        LinkedList<Vertex> l = d.dijkstra(g, "Renyn", "Parses", false);
        System.out.println(l.size());
        System.out.println("Dijkstra");
        System.out.println(l);
    }
}

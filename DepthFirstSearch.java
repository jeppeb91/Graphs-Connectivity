import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.Iterator;
import java.util.LinkedList;

public class DepthFirstSearch{
    public boolean [] visited;
    public int count;
    public DepthFirstSearch(Graph g, int v){
        visited=new boolean[g.numberOfVertices()];
    }
    public void dfs(Graph g, int v){
        count++;
        Iterator<Edge> iter=g.adj(v).iterator();

        visited[v]=true;
        while(iter.hasNext()){
            Edge e=iter.next();
            int a=e.to;
            if(!visited[a]) dfs(g, a);
        }
    }
    public int noOfComponents(Graph g, int v){
        int components=1;
        dfs(g, v);
        for(int i=0; i<visited.length; i++){
            if(!visited[i]){
                dfs(g, i);
                components++;
            }
        }
        return components;
    }

}

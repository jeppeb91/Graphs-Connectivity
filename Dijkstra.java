import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.*;

/**
 * Created by jeppe on 2016-12-08.
 */
public class Dijkstra {
    public LinkedList<Vertex> dijkstra(Graph g, String s, String t, boolean w){

        int target=0;
        int source=0;
        Vertex [] vertices=new Vertex[g.numberOfVertices()];
        boolean[] visited=new boolean[g.numberOfVertices()];
        double [] dist=new double[g.numberOfVertices()];
        int [] prev=new int[g.numberOfVertices()];
        for(int i=0; i<prev.length; i++)prev[i]=-1;

        Iterator<Vertex> vertice=g.vertices().iterator();
        Vertex u=new Vertex();
        for(int i=0; i<dist.length; i++)dist[i]=Double.MAX_VALUE;
        //visited[source]=true;
        int count=0;
        double alt=Double.MAX_VALUE;
        double mintemp=Double.MAX_VALUE;
        for(int i=0; vertice.hasNext(); i++) {
            Vertex v=vertice.next();
            if(v.label.equals(s)) source=i;
            if(v.label.equals(t)) target=i;
            vertices[i] = v;
            //System.out.println("Added" + vertices[i]);
        }

        dist[source]=0.0;
        for(int i=0; i<g.numberOfVertices(); i++){
            mintemp=Double.MAX_VALUE;
            count=FindSmallest(dist, visited);
            mintemp=dist[count];
            u=vertices[count];
            //System.out.println("u: " + u.id + "dist: " + dist[count]);
            visited[count]=true;
            if(count==target) return path(prev, target, vertices);
            dist[count]=mintemp;

            Iterator<Edge> adjs=g.adj(count).iterator();
            while(adjs.hasNext()){
                Edge e=adjs.next();
                Vertex v=vertices[e.to];
                if(!w)alt=dist[count]+1;
                else alt=dist[count]+e.weight;
                if(alt<dist[e.to]){
                    dist[e.to]=alt;
                    prev[e.to]=e.from;
                }
            }
        }
        return path(prev, target, vertices);
    }

    public LinkedList<Vertex> path (int [] prev, int u, Vertex [] vertices){
        LinkedList<Vertex> l=new LinkedList<Vertex>();
        while(prev[u]!=-1){
            l.addFirst(vertices[u]);
            u=vertices[prev[u]].id;
        }
        return l;
    }

    public static int FindSmallest (double [] arr1, boolean [] b){//start method
        int start=0;
        for(int i=0; i<b.length; i++){
            if(!b[i]){
                start=i;
                break;
            }
        }
        int index =start;

        for (int i=start; i<arr1.length; i++){
            if (arr1[i] < arr1[index] && !b[i]){
                index = i;
            }
        }
        return index;
    }
}

/* File name: Graph
 * TA: Gabe
 * Name: Alex Mai
 * Partner: None
 * Lab Session: MW
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class Graph {

	private int vertexCount, edgeCount;
	static int numVertices = 0;
	// Map with String intersectionID as the key, and Vertexes as the value
	static Map<String, Vertex> hashVertex = new HashMap<String, Vertex>();
	static Map<String, Edge> hashEdge = new HashMap<String, Edge>();
	static double minimumLat = 0.0;
	static double maximumLat = 0.0;
	static double minimumLong = 0.0;
	static double maximumLong = 0.0;
	static ArrayList latitudeList = new ArrayList();
	static ArrayList longitudeList = new ArrayList();
	ArrayList<Vertex> pathKeeper;
	ArrayList<Edge> edgeKeeper;

	public Graph(int numVerticies) {
		this.vertexCount = numVerticies;
		this.pathKeeper = new ArrayList<Vertex>();
		this.edgeKeeper = new ArrayList<Edge>();
	}
	
	void printPrim(String vertex1) {
		
		prim(vertex1);
		int counter = 1;
		//System.out.println(edgeKeeper.size());
		for(int i=edgeKeeper.size()-1; i>=0; i--) {
			System.out.println(counter + ". " + edgeKeeper.get(i).roadID);
			counter++;
		}
	}
	
	void prim(String vertex) {
		PriorityQueue<Edge> values = new PriorityQueue<Edge>();
		Vertex vertex1 = hashVertex.get(vertex);
		vertex1.known = true;

		for(Edge e: vertex1.edgeList) {
			values.add(e);
		}

		while(!values.isEmpty()) {
			Edge smallest = values.poll();
			if(!smallest.vertex1.known && !smallest.vertex2.known) {
				//Change destination vertex to known
				smallest.vertex2.known = true;
			}
			//Prevents duplicate of same edge - if the destination of the edge is unknown (meaning the two vertices are unknown)
			if(!smallest.vertex2.known) {
				for(Edge v: smallest.vertex2.edgeList) {
					if(!v.vertex2.known) {
						values.add(v);
					}
					smallest.vertex2.known = true;
				}
				edgeKeeper.add(smallest);
			}
		}
		
	}

	void dijkstra(Vertex vertex1) {
		//Priority queue
		PriorityQueue<Vertex> values = new PriorityQueue<Vertex>();
		
		vertex1.distance = 0.0;
		vertex1.path = null;
		
		//System.out.println("The hash size is: " + hashVertex.size());
		for (Vertex v: hashVertex.values()) {
			if(v != vertex1) {
			v.path = null;
			v.known = false;
			v.distance = Double.POSITIVE_INFINITY;
			}
			values.add(v);
		}
		
		while(!values.isEmpty()) {
			Vertex u = values.poll();
			u.known = true;
			//System.out.println("This is u ID: " + u.intersectionID);
			
			//Finding neighbors of u
			for(Vertex v: u.linkedList) {
				if(!v.known) {
				double cvw = weight(u,v);
				//System.out.println(increment + " Weight is: " + cvw);
				if(u.distance + cvw < v.distance) {
					values.remove(v);
					v.distance = u.distance + cvw;
					v.path = u;
					values.add(v);
					
					}
				}
			}
		}//end of while loop
		
	}//end of dijkstra
	
	public void prints(Vertex v, Vertex v2) {
		dijkstra(v);
		int counter = 1;
		
		while(v2 != null) {
			pathKeeper.add(v2);
			v2 = v2.path;
		}
		
		//Printing backwards
		for(int i=pathKeeper.size()-1; i>=0; i--) {
			System.out.println(counter + ". " + pathKeeper.get(i).intersectionID);
			counter++;
		}
	}
	
	// Helper method
	public double weight(Vertex vertex1, Vertex vertex2) {
		double weight = Math.sqrt(Math.pow(vertex1.latitude - vertex2.latitude,
				2) + Math.pow(vertex1.longitude - vertex2.longitude, 2));
		return weight;
	}

	@SuppressWarnings({ "unchecked", "resource", "unused" })
	public static Graph createFromFile(String filename) {
		File inFile = new File(filename);
		Graph newGraph = new Graph(0);
		try {
			Scanner scan = new Scanner(inFile);

			while (scan.hasNext()) {
				String temp = scan.nextLine();
				String[] info = temp.split("\\t", 4);
				String interRoad = info[0];

				// If it is an intersection
				if (info[0].equalsIgnoreCase("i")) {
					String vertexID = info[1];
					double x = Double.parseDouble(info[2]);
					double y = Double.parseDouble(info[3]);
					// System.out.println(interRoad + " " + vertexID + " " + x +
					// " " + y);
					// Insert to vertex here
					Vertex tempVertex = new Vertex(vertexID, numVertices, x, y);
					hashVertex.put(vertexID, tempVertex);
					numVertices++;
					latitudeList.add(x);
					longitudeList.add(y);
					// System.out.println(longitudeList);
				}// end of intersection
					// System.out.println(numVertices);

				// Update graph to have the number of vertices
				newGraph = new Graph(numVertices);

				// If it is a road
				if (info[0].equalsIgnoreCase("r")) {
					String roadID = info[1];
					String vertexID1 = info[2];
					String vertexID2 = info[3];

					Vertex vertex1 = hashVertex.get(vertexID1);
					Vertex vertex2 = hashVertex.get(vertexID2);
					// System.out.println(interRoad + " " + roadID + " " +
					// vertexID1 + " " + vertexID2);
					Edge newEdge = new Edge(roadID, vertex1, vertex2);
					Edge newEdge2 = new Edge(roadID, vertex2, vertex1);
					hashEdge.put(roadID, newEdge);
					newEdge.vertex1.edgeList.add(newEdge);
					newEdge.vertex2.edgeList.add(newEdge2);

					// Essentially saying linkedList(v1).add(v2) because if it's
					// a road, it's inherently connected
					vertex1.linkedList.add(vertex2);
					vertex2.linkedList.add(vertex1);
				}
			}// end of while loop
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Sort to return the minimum and maximum lat/long
		Collections.sort(latitudeList);
		minimumLat = (Double) latitudeList.get(0);
		maximumLat = (Double) latitudeList.get(latitudeList.size() - 1);
		//System.out.println(minimumLat + " " + maximumLat);

		Collections.sort(longitudeList);
		minimumLong = (Double) longitudeList.get(0);
		maximumLong = (Double) longitudeList.get(longitudeList.size() - 1);
		//System.out.println(minimumLong + " " + maximumLong);

		return newGraph;
	}

	public int vertices() {
		return vertexCount;
	}

	public int edges() {
		return edgeCount;
	}
}
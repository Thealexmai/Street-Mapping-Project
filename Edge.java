/* File name: Edge
 * TA: Gabe
 * Name: Alex Mai
 * Partner: None
 * Lab Session: MW
 */
public class Edge implements Comparable<Edge>{
	public Vertex vertex1, vertex2;
	String roadID;
	double weight;
	Edge parent;
	
	public Edge(String roadID, Vertex vertex1, Vertex vertex2) {
		this.roadID = roadID;
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		//Gives all the edges a weight
		this.weight = weightRoad(vertex1, vertex2);
		this.parent = null;
	}
	
	//Weight of the road method here
	public double weightRoad(Vertex vertex1, Vertex vertex2) {
		double weight = Math.sqrt(Math.pow(vertex1.latitude-vertex2.latitude, 2) + Math.pow(vertex1.longitude-vertex2.longitude, 2));
		return weight;
	}
	
	public int compareTo(Edge e) {
		if(this.weight < e.weight)
			return -1;
		else if(this.weight == e.weight) {
			return 0;
		}
		
		return 1;
	}
}
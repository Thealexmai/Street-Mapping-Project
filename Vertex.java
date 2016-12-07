/* File name: Vertex
 * TA: Gabe
 * Name: Alex Mai
 * Partner: None
 * Lab Session: MW
 */
import java.util.LinkedList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
	
	//To link the neighbor vertexes
	List<Vertex> linkedList = new LinkedList<Vertex>();
	List<Edge> edgeList = new LinkedList<Edge>();
	Vertex path;
	int vertexNumber;
	boolean known;
	double distance;
	double longitude;
	double latitude;
	String intersectionID;
	double weight = 0.0;

	//Remember latitude = x coord, longitude = y coor
	public Vertex(String intersectionID, int vertexNumber, double latitude, double longitude) {
		this.vertexNumber = vertexNumber;
		this.distance = Double.POSITIVE_INFINITY;
		this.known = false;
		this.intersectionID = intersectionID;
		this.latitude = latitude;
		this.longitude = longitude;
		this.path = null;
	}

	//Be sure to check. If the compareTo function is doing it out of order, then flip
	public int compareTo(Vertex vertex) {
		if(this.distance < vertex.distance)
			return -1;
		else if(this.distance == vertex.distance) {
			return 0;
		}
		
		return 1;
	}
	
}

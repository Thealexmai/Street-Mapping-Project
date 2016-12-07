/* File name: DrawPanel
 * TA: Gabe
 * Name: Alex Mai
 * Partner: None
 * Lab Session: MW
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	Graph graph;
	Vertex v;
	Edge e;
	int height;
	int width;
	
	public DrawPanel(Graph graph) {
		this.graph = graph;
		setPreferredSize(new Dimension(1000,1000));
		setBackground(Color.black);
		setVisible(true);
	}
	
	@SuppressWarnings("static-access")
	@Override
	protected void paintComponent(Graphics page) {
		super.paintComponent(page);
		width = this.getWidth();
		height = this.getHeight();
		Graphics2D page2 = (Graphics2D) page;
//		page2.setColor(Color.black);
		
		int xscale = (int) (width/(graph.maximumLong - graph.minimumLong));
		int yscale = (int) (height/(graph.maximumLat - graph.minimumLat));
		
//		System.out.println("The max long is: " + graph.maximumLong);
//		System.out.println("The min Long is: " + graph.minimumLong);
//		System.out.println("The max lat is: " + graph.maximumLat);
		
		
		Map<String, Edge> edge = graph.hashEdge;
		page.setColor(Color.white);
		for(Entry<String, Edge> pair : edge.entrySet()) {
			int x1 = (int)((pair.getValue().vertex1.longitude - graph.minimumLong) * xscale);
			int y1 = (int) (height - (pair.getValue().vertex1.latitude - graph.minimumLat) * yscale);
			int x2 = (int)((pair.getValue().vertex2.longitude - graph.minimumLong) * xscale);
			int y2 = (int) (height - (pair.getValue().vertex2.latitude - graph.minimumLat) * yscale);
			//System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			
			page.drawLine(x1, y1, x2, y2);
		}

		//To draw the lines in between vertices  connecting path
		page.setColor(Color.blue);
		ArrayList<Vertex> path = graph.pathKeeper;
		//System.out.println("Path size is: " + path.size());
		if(path.size() > 1) {
			for(int i=0; i< path.size()-1; i++) {
				int x1 = (int) ((path.get(i).longitude - graph.minimumLong) * xscale);
				int y1 = (int) (height - (path.get(i).latitude - graph.minimumLat) * yscale);
				int x2 = (int) ((path.get(i+1).longitude - graph.minimumLong) * xscale);
				int y2 = (int) (height - (path.get(i+1).latitude - graph.minimumLat) * yscale);
				//System.out.println("\t" + x1 + " " + y1 + " " + x2 + " " + y2);
				page2.setStroke(new BasicStroke(3));
				page2.drawLine(x1, y1, x2, y2);
			}
		}
		
		//To draw the roads of MWST
		page.setColor(Color.orange);
		ArrayList<Edge> theEdges = graph.edgeKeeper;
		if(theEdges.size() > 1) {
			for(int i=0; i<theEdges.size()-1; i++) {
				int x1 = (int) ((theEdges.get(i).vertex1.longitude - graph.minimumLong) * xscale);
				int y1 = (int) (height - (theEdges.get(i).vertex1.latitude - graph.minimumLat) * yscale);
				int x2 = (int) ((theEdges.get(i).vertex2.longitude - graph.minimumLong) * xscale);
				int y2 = (int) (height - (theEdges.get(i).vertex2.latitude - graph.minimumLat) * yscale);
				//System.out.println("\t" + x1 + " " + y1 + " " + x2 + " " + y2);
				page2.setStroke(new BasicStroke(3));
				page2.drawLine(x1, y1, x2, y2);
			}
		}
	}
	
}

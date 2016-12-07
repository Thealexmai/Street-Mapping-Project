/* File name: Project4Test
 * TA: Gabe
 * Name: Alex Mai
 * Partner: None
 * Lab Session: MW
 */
import java.awt.Frame;
import javax.swing.JFrame;

public class Project4Test {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Graph graph = Graph.createFromFile(args[0]);
		boolean show = false;
		Vertex startVertex = null;
		Vertex endVertex = null;
		
		for(int i=1; i <args.length; i++) {
			if(args[i].equalsIgnoreCase("-show")) {
				show = true;
			}
			if(args[i].equalsIgnoreCase("-directions")) {
				startVertex = graph.hashVertex.get(args[i+1]);
				endVertex = graph.hashVertex.get(args[i+2]);
				graph.prints(startVertex, endVertex);
				System.out.println();
				System.out.println("Distance from " + args[i+1] + " to " + args[i+2] + " is " + endVertex.distance + " units");
			}
			if(args[i].equalsIgnoreCase("-meridianmap")){
				String start = "i1";
				graph.printPrim(start);
			}
		}
		
		if(show) {
		JFrame frame = new JFrame("Mapping");
		DrawPanel drawpanel = new DrawPanel(graph);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.getContentPane().add(drawpanel);
		frame.setVisible(true);
		}

	} //end of main
	
	
}

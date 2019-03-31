package editors.quest;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

public class GraphView {
	
	private Graph graph;
	private Component component;
	ViewerPipe pipe;
	private boolean running;
	
	public GraphView() {

		System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		graph = new SingleGraph("Quest Graph");
		graph.addAttribute("ui.stylesheet", StylesheetFactory.makeStylesheet());
		
		running = true;

		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();

		pipe = viewer.newViewerPipe();
		pipe.addSink(graph);
		
		component = viewer.addDefaultView(false);
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (running) {
					pipe.pump();
				}
			}
			
		});
		thread.start();

	}
	
	public void reset() {
		graph.clear();
		graph.addAttribute("ui.stylesheet", StylesheetFactory.makeStylesheet());
	}
	
	public void register(ViewerListener listener) {
		pipe.addViewerListener(listener);
	}
	
	public Node addNode(String id) {
		
		Node n;
		if(!nodeExists(id)) {
			n = graph.addNode(id);
		} else {
			n = graph.getNode(id);
		}
		n.setAttribute("ui.label", id);
		return n;
	}
	
	public boolean nodeExists(String id) {
		return graph.getNode(id) != null;
	}
	
	public void addEdge(String id, String u, String v) {
		if (!u.isEmpty()) {
			graph.addEdge(id, u, v, true);
		}
	}
	
	public void removeEnteringEdges(String id) {
		Node n = graph.getNode(id);
		while (n.getEachEnteringEdge().iterator().hasNext()) {
			Edge e = n.getEachEnteringEdge().iterator().next();
			graph.removeEdge(e);
		}
	}
	
	public void removeLeavingEdges(String id) {
		Node n = graph.getNode(id);
		while (n.getEachLeavingEdge().iterator().hasNext()) {
			Edge e = n.getEachLeavingEdge().iterator().next();
			graph.removeEdge(e);
		}
	}
	
	public Iterator<Node> getNodes() {
		return graph.getNodeIterator();
	}
	
	public Component getComponent(int width, int height) {
		component.setPreferredSize(new Dimension(width, height));
		return component;
	}
	
	public void removeNode(String id) {
		graph.removeNode(id);
	}

}

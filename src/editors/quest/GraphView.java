package editors.quest;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Iterator;

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

		graph = new SingleGraph("Quest Graph");
		graph.setStrict(false);
		graph.setAutoCreate( true );
		
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
	
	public void register(ViewerListener listener) {
		pipe.addViewerListener(listener);
	}
	
	public Node addNode(String id) {
		return graph.addNode(id);
	}
	
	public void addEdge(String id, String u, String v) {
		graph.addEdge(id, u, v, true);
	}
	
	public Iterator<Node> getNodes() {
		return graph.getNodeIterator();
	}
	
	public Component getComponent(int width, int height) {
		component.setPreferredSize(new Dimension(width, height));
		return component;
	}

}

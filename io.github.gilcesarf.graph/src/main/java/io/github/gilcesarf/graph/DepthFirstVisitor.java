package io.github.gilcesarf.graph;

import java.util.HashSet;
import java.util.NoSuchElementException;

public class DepthFirstVisitor implements GraphVisitor {

	private HashSet<Node> toVisit = null;

	@Override
	public void visit(Graph graph, GraphListener listener) throws GraphException {
		visit(graph, listener, null);
	}

	@Override
	public void visit(Graph graph, GraphListener listener, Node startNode) throws GraphException {
		// Do basic validation
		if (graph == null) {
			throw new GraphException("Graph cannot be null");
		}
		if (listener == null) {
			throw new GraphException("Listener cannot be null");
		}
		// lookup for starting node
		Node start = startNode;
		if (start == null) {
			// no startNode was given. Get any node.
			try {
				start = graph.getNodeSet().iterator().next();
			} catch (NoSuchElementException e) {
				start = null;
			}
		} else {
			// startNode was given. Check if it is contained within the given graph.
			if (!graph.equals(start.getGraph())) {
				throw new GraphException("Given startNode must be contained within the same given graph.");
			}
		}
		// do the visit
		toVisit = new HashSet<Node>(graph.getNodeSet());
		boolean keepGoing = true;
		if (listener.beforeGraph(graph)) {
			while (!this.toVisit.isEmpty() && keepGoing) {
				if (!(keepGoing = listener.beforeIsolatedSubGraph(graph))) {
					break;
				}
				doDFVisit(graph, listener, start);
				if (!(keepGoing = listener.afterIsolatedSubGraph(graph))) {
					break;
				}
				try {
					start = graph.getNodeSet().iterator().next();
				} catch (NoSuchElementException e) {
					start = null;
				}
			}
			if (keepGoing) {
				listener.afterGraph(graph);
			}
		}
	}

	private void doDFVisit(Graph graph, GraphListener listener, Node node) {
		if (this.toVisit.contains(node)) {
			this.toVisit.remove(node);
			if (listener.beforeNode(node)) {
				for (Edge e : node.getSucessors()) {
					if (listener.beforeEdge(e)) { // if listener returns false abort edge processing
						doDFVisit(graph, listener, e.getSuccessor());
						if (!listener.afterEdge(e)) { // if listener returns false abort edge processing
							break;
						}
					} else {
						break;
					}
				}
				listener.afterNode(node);
			}
		}
	}
}

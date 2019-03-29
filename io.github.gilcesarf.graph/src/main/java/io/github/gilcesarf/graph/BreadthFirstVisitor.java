package io.github.gilcesarf.graph;

import java.util.Deque;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BreadthFirstVisitor implements GraphVisitor {

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
		if (listener.beforeGraph(graph)) {
			doBFVisit(graph, listener, start);
			listener.afterGraph(graph);
		}
	}

	private void doBFVisit(Graph graph, GraphListener listener, Node start) {
		Deque<Node> discovered = new ConcurrentLinkedDeque<>();
		HashSet<Node> toVisit = new HashSet<Node>(graph.getNodeSet());
		while (!toVisit.isEmpty()) { // to process disconnected subgraphs
			if (!listener.beforeIsolatedSubGraph(graph)) {
				break;
			}
			discovered.offerFirst(start);
			while (!discovered.isEmpty()) {
				Node currentNode = discovered.pollLast();
				if (currentNode != null && toVisit.contains(currentNode)) {
					toVisit.remove(currentNode); // must be done before node processing to avoid infinity loops
					// if listener.beforeNode returns false abort node processing
					if (listener.beforeNode(currentNode)) {
						for (Edge e : currentNode.getSucessors()) {
							if (listener.beforeEdge(e)) { // if listener returns false abort edge processing
								discovered.offerFirst(e.getSuccessor());
								if (!listener.afterEdge(e)) { // if listener returns false abort edge processing
									break;
								}
							} else {
								break;
							}
						}
						listener.afterNode(currentNode);
					}
				}
			}
			// if exists disconnect subgraphs, process them
			try {
				start = toVisit.iterator().next();
			} catch (NoSuchElementException e) {
				start = null;
			}
			if (!listener.afterIsolatedSubGraph(graph)) {
				break;
			}

		}
	}
}

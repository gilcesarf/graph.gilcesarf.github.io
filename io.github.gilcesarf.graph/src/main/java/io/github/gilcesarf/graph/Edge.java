package io.github.gilcesarf.graph;

public class Edge {
	private Graph graph = null;
	private Node predecessor = null;
	private Node successor = null;
	private EdgeData data = null;

	public Edge(Graph graph, Node predecessor, Node successor) throws GraphException {
		if (graph == null) {
			throw new GraphException("Edge requires a non null graph");
		}
		if (predecessor == null) {
			throw new GraphException("Edge requires a non null predecessor node");
		}
		if (successor == null) {
			throw new GraphException("Edge requires a non null successor node");
		}
		// both predecessor and successor must belongs to this graph
		if (!graph.equals(predecessor.getGraph())) {
			throw new GraphException("Predecessor is not contained in the given graph.");
		}
		if (!graph.equals(successor.getGraph())) {
			throw new GraphException("Succesor is not contained in the given graph.");
		}

		if (!graph.isDuplicatedEdgesAllowed()) {
			// lookup for duplicated edges
			for (Edge l : graph.getEdgeSet()) {
				if (predecessor.equals(l.getPredecessor()) && successor.equals(l.getSuccessor())) {
					throw new GraphException("Duplicated Edges not allowed.");
				}
			}
		}
		this.graph = graph;
		this.predecessor = predecessor;
		this.successor = successor;
		predecessor.addSucessor(this);

		if (!this.graph.isDirected()) {
			successor.addPredecessor(this);
		}

		graph.addEdge(this);
	}

	public Graph getGraph() {
		return this.graph;
	}

	public Node getPredecessor() {
		return predecessor;
	}

	public Node getSuccessor() {
		return successor;
	}

	public EdgeData getData() {
		return data;
	}

	public void setData(EdgeData d) {
		EdgeData old = this.data;
		this.data = d;
		if (this.data != null) {
			if (this.data.getEdge() != this) {
				this.data.setEdge(this);
			}
		} else {
			if (old != null) {
				old.setEdge(null);
			}
		}
	}

}

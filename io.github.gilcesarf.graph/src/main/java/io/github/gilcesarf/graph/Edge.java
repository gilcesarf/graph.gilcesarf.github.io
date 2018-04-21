package io.github.gilcesarf.graph;

public class Edge {
	private Node predecessor = null;
	private Node sucessor = null;
	private EdgeData data = null;

	public Edge(Node predecessor, Node sucessor) {
		this.predecessor = predecessor;
		this.sucessor = sucessor;
		predecessor.addSucessor(this);
		sucessor.addPredecessor(this);
	}

	public Node getPredecessor() {
		return predecessor;
	}

	public Node getSucessor() {
		return sucessor;
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

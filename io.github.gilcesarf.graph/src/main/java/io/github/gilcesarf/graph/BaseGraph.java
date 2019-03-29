package io.github.gilcesarf.graph;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class BaseGraph implements Graph {

	protected LinkedHashSet<Node> nodeSet = null;
	protected LinkedHashSet<Edge> edgeSet = null;
	protected boolean duplicatedEdgesAllowed = true;
	protected boolean directed = false;

	public BaseGraph() {
		this(false);
	}

	public BaseGraph(boolean directed) {
		this.directed = directed;
		this.nodeSet = new LinkedHashSet<>();
		this.edgeSet = new LinkedHashSet<>();
	}

	public BaseGraph(int initialNodeCapacity) {
		this(false, initialNodeCapacity);
	}

	public BaseGraph(boolean directed, int initialNodeCapacity) {
		this.directed = directed;
		this.nodeSet = new LinkedHashSet<>(initialNodeCapacity);
		this.edgeSet = new LinkedHashSet<>();
	}

	public BaseGraph(int initialNodeCapacity, int initialEdgeCapacity) {
		this(false, initialNodeCapacity, initialEdgeCapacity);
	}

	public BaseGraph(boolean directed, int initialNodeCapacity, int initialEdgeCapacity) {
		this.directed = directed;
		this.nodeSet = new LinkedHashSet<>(initialNodeCapacity);
		this.edgeSet = new LinkedHashSet<>(initialEdgeCapacity);
	}

	public BaseGraph(int initialNodeCapacity, float initialNodeLoadFactor, int initialEdgeCapacity,
			float initialEdgeLoadFactor) {
		this(false, initialNodeCapacity, initialNodeLoadFactor, initialEdgeCapacity, initialEdgeLoadFactor);
	}

	public BaseGraph(boolean directed, int initialNodeCapacity, float initialNodeLoadFactor, int initialEdgeCapacity,
			float initialEdgeLoadFactor) {
		this.directed = directed;
		this.nodeSet = new LinkedHashSet<>(initialNodeCapacity, initialNodeLoadFactor);
		this.edgeSet = new LinkedHashSet<>(initialEdgeCapacity, initialEdgeLoadFactor);
	}

	@Override
	public boolean isDirected() {
		return directed;
	}

	@Override
	public boolean addNode(Node n) throws GraphException {
		if (n == null) {
			throw new GraphException("The given Node cannot be null.");
		} else if (!this.equals(n.getGraph())) {
			throw new GraphException("The given node does not belong to this graph.");
		} else {
			return this.nodeSet.add(n);
		}
	}

	@Override
	public boolean addEdge(Edge e) throws GraphException {
		if (e == null) {
			throw new GraphException("The given Edge cannot be null.");
		} else if (!this.equals(e.getGraph())) {
			throw new GraphException("The given edge does not belong to this graph.");
		} else {
			return this.edgeSet.add(e);
		}
	}

	@Override
	public Node createNode(String type, String name, NodeData data) throws GraphException {
		Node n = new Node(this, type, name);
		n.setData(data);
		return n;
	}

	@Override
	public Edge connect(Node predecessor, Node successor) throws GraphException {
		// avoid null origin and destiny
		if (predecessor == null) {
			throw new GraphException("Cannot connect null predecesor");
		}
		if (successor == null) {
			throw new GraphException("Cannot connect null successor");
		}

		// both origin and destiny must belongs to this graph
		if (!this.equals(predecessor.getGraph())) {
			throw new GraphException("Predecessor is not contained in this graph.");
		}
		if (!this.equals(successor.getGraph())) {
			throw new GraphException("Successor is not contained in this graph.");
		}

		// proceeds to edge creation
		Edge e = new Edge(this, predecessor, successor);
		this.edgeSet.add(e);
		return e;
	}

	@Override
	public Set<Node> getNodeSet() {
		return Collections.unmodifiableSet(this.nodeSet);
	}

	@Override
	public Set<Edge> getEdgeSet() {
		return Collections.unmodifiableSet(this.edgeSet);
	}

	@Override
	public boolean isDuplicatedEdgesAllowed() {
		return duplicatedEdgesAllowed;
	}

	@Override
	public void allowDuplicatedEdges() {
		this.duplicatedEdgesAllowed = true;
	}

	@Override
	public void disallowDuplicatedEdges() throws GraphException {
		if (this.edgeSet.size() > 0) {
			throw new GraphException("Cannot change duplicated option because the graph already contains edges");
		}
		this.duplicatedEdgesAllowed = false;
	}

	@Override
	public int getNodeSetSize() {
		return this.nodeSet.size();
	}

	@Override
	public int getEdgeSetSize() {
		return this.edgeSet.size();
	}

}

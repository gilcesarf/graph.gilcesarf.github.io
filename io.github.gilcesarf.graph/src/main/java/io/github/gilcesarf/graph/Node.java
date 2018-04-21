package io.github.gilcesarf.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Node {
	private String type = null;
	private String name = null;
	private Set<Edge> sucessors = new HashSet<Edge>();
	private Set<Edge> predecessors = new HashSet<Edge>();
	private NodeData data = null;

	/**
	 * Create a new node of arbitrary type and name
	 * 
	 * @param type
	 *            an arbitrary String to type the Node being created
	 * @param name
	 *            an arbitrary String to name the Node being created
	 */
	public Node(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Edge> getSucessors() {
		return Collections.unmodifiableSet(sucessors);
	}

	public Set<Edge> getPredecessors() {
		return Collections.unmodifiableSet(predecessors);
	}

	public void addSucessor(Edge edge) throws GraphException {
		if (edge.getPredecessor() == this) {
			this.sucessors.add(edge);
		} else {
			throw new GraphException("Invalid Edge Given");
		}
	}

	public void addPredecessor(Edge edge) throws GraphException {
		if (edge.getSucessor() == this) {
			this.predecessors.add(edge);
		} else {
			throw new GraphException("Invalid Edge Given");
		}
	}

	public void setData(NodeData d) {
		NodeData old = this.data;
		this.data = d;
		if (this.data != null) {
			if (this.data.getNode() != this) {
				this.data.setNode(this);
			}
		} else {
			if (old != null) {
				old.setNode(null);
			}
		}
	}

	public NodeData getData() {
		return data;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + this.name + "," + this.type + "]";
	}

}
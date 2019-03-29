package io.github.gilcesarf.graph;

import java.util.Set;

public interface Graph {

	public boolean isDirected();
	
	public boolean addNode(Node n) throws GraphException;

	public boolean addEdge(Edge e) throws GraphException;

	public Node createNode(String type, String name, NodeData data) throws GraphException;

	public Edge connect(Node predecessor, Node successor) throws GraphException;

	public Set<Node> getNodeSet();

	public Set<Edge> getEdgeSet();
	
	public int getNodeSetSize();

	public int getEdgeSetSize();

	public boolean isDuplicatedEdgesAllowed();

	public void allowDuplicatedEdges();

	public void disallowDuplicatedEdges() throws GraphException;

}

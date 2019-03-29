package io.github.gilcesarf.graph;

public interface GraphVisitor {
	public void visit(Graph graph, GraphListener listener) throws GraphException;

	public void visit(Graph graph, GraphListener listener, Node startNode) throws GraphException;
}

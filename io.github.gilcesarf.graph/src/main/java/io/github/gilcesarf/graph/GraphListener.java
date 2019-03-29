package io.github.gilcesarf.graph;

public interface GraphListener {

	public boolean beforeGraph(Graph graph);

	public boolean afterGraph(Graph graph);

	public boolean beforeIsolatedSubGraph(Graph graph);

	public boolean afterIsolatedSubGraph(Graph graph);

	public boolean beforeNode(Node node);

	public boolean afterNode(Node node);

	public boolean beforeEdge(Edge edge);

	public boolean afterEdge(Edge edge);

}

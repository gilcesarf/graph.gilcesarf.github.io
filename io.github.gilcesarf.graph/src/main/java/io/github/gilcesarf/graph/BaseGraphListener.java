package io.github.gilcesarf.graph;

public class BaseGraphListener implements GraphListener {

	@Override
	public boolean beforeGraph(Graph graph) {
		// empty implementation for further extension
		return true;
	}

	@Override
	public boolean afterGraph(Graph graph) {
		// empty implementation for further extension
		return true;
	}

	@Override
	public boolean beforeIsolatedSubGraph(Graph graph) {
		// empty implementation for further extension
		return true;
	}

	@Override
	public boolean afterIsolatedSubGraph(Graph graph) {
		// empty implementation for further extension
		return true;
	}

	@Override
	public boolean beforeNode(Node node) {
		// empty implementation for further extension
		return true;
	}

	@Override
	public boolean afterNode(Node node) {
		// empty implementation for further extension
		return true;
	}

	@Override
	public boolean beforeEdge(Edge edge) {
		// empty implementation for further extension
		return true;
	}

	@Override
	public boolean afterEdge(Edge edge) {
		// empty implementation for further extension
		return true;
	}

}

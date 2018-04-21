package io.github.gilcesarf.graph;

public class BaseEdgeData implements EdgeData {
	protected Edge edge = null;

	@Override
	public void setEdge(Edge edge) {
		Edge old = this.edge;
		this.edge = edge;
		if (this.edge != null) {
			if (this.edge.getData() != this) {
				this.edge.setData(this);
			}
		} else {
			if (old != null) {
				old.setData(null);
			}
		}
	}

	@Override
	public Edge getEdge() {
		return this.edge;
	}

}

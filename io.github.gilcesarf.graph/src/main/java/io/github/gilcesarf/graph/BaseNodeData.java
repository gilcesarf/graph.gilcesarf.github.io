package io.github.gilcesarf.graph;

public class BaseNodeData implements NodeData {
	protected Node node = null;

	@Override
	public void setNode(Node node) {
		Node old = this.node;
		this.node = node;
		if (this.node != null) {
			if (this.node.getData() != this) {
				this.node.setData(this);
			}
		} else {
			if (old != null) {
				old.setData(null);
			}
		}
	}

	@Override
	public Node getNode() {
		return this.node;
	}

}

package io.github.gilcesarf.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestGraphListener extends BaseGraphListener implements GraphListener {
	public static Integer DEFAULT_EDGE_SIZE = 20;

	private boolean beforeGraphReturnValue = true;
	private boolean afterGraphReturnValue = true;
	private boolean beforeIsolatedSubGraphReturnValue = true;
	private boolean afterIsolatedSubGraphReturnValue = true;
	private boolean beforeNodeReturnValue = true;
	private boolean afterNodeReturnValue = true;
	private boolean beforeEdgeReturnValue = true;
	private boolean afterEdgeReturnValue = true;

	private ArrayList<Graph> beforeGraphVisited = null;
	private ArrayList<Graph> afterGraphVisited = null;
	private ArrayList<Graph> beforeIsolatedSubGraphVisited = null;
	private ArrayList<Graph> afterIsolatedSubGraphVisited = null;
	private ArrayList<Node> beforeNodeVisited = null;
	private ArrayList<Node> afterNodeVisited = null;
	private ArrayList<Edge> beforeEdgeVisited = null;
	private ArrayList<Edge> afterEdgeVisited = null;

	public TestGraphListener() {
		resetAttributes();
	}

	public TestGraphListener(int nodeSize) {
		resetAttributes(nodeSize);
	}

	public void resetAttributes() {
		this.resetAttributes(DEFAULT_EDGE_SIZE);
	}

	public void resetAttributes(int nodeSize) {
		this.beforeGraphReturnValue = true;
		this.afterGraphReturnValue = true;
		this.beforeIsolatedSubGraphReturnValue = true;
		this.afterIsolatedSubGraphReturnValue = true;
		this.beforeNodeReturnValue = true;
		this.afterNodeReturnValue = true;
		this.beforeEdgeReturnValue = true;
		this.afterEdgeReturnValue = true;
		this.beforeGraphVisited = new ArrayList<>(nodeSize);
		this.afterGraphVisited = new ArrayList<>(nodeSize);
		this.beforeIsolatedSubGraphVisited = new ArrayList<>(nodeSize);
		this.afterIsolatedSubGraphVisited = new ArrayList<>(nodeSize);
		this.beforeNodeVisited = new ArrayList<>(nodeSize);
		this.afterNodeVisited = new ArrayList<>(nodeSize);
		this.beforeEdgeVisited = new ArrayList<>(nodeSize);
		this.afterEdgeVisited = new ArrayList<>(nodeSize);
	}

	@Override
	public boolean beforeGraph(Graph graph) {
		this.beforeGraphVisited.add(graph);
		return this.beforeGraphReturnValue;
	}

	@Override
	public boolean afterGraph(Graph graph) {
		this.afterGraphVisited.add(graph);
		return this.afterGraphReturnValue;
	}

	@Override
	public boolean beforeIsolatedSubGraph(Graph graph) {
		this.beforeIsolatedSubGraphVisited.add(graph);
		return this.beforeIsolatedSubGraphReturnValue;
	}

	@Override
	public boolean afterIsolatedSubGraph(Graph graph) {
		this.afterIsolatedSubGraphVisited.add(graph);
		return this.afterIsolatedSubGraphReturnValue;
	}

	@Override
	public boolean beforeNode(Node node) {
		this.beforeNodeVisited.add(node);
		return this.beforeNodeReturnValue;
	}

	@Override
	public boolean afterNode(Node node) {
		this.afterNodeVisited.add(node);
		return this.afterNodeReturnValue;
	}

	@Override
	public boolean beforeEdge(Edge edge) {
		this.beforeEdgeVisited.add(edge);
		return this.beforeEdgeReturnValue;
	}

	@Override
	public boolean afterEdge(Edge edge) {
		this.afterEdgeVisited.add(edge);
		return this.afterEdgeReturnValue;
	}

	////////////////////////////

	public boolean isBeforeGraphReturnValue() {
		return beforeGraphReturnValue;
	}

	public void setBeforeGraphReturnValue(boolean beforeGraphReturnValue) {
		this.beforeGraphReturnValue = beforeGraphReturnValue;
	}

	public boolean isAfterGraphReturnValue() {
		return afterGraphReturnValue;
	}

	public void setAfterGraphReturnValue(boolean afterGraphReturnValue) {
		this.afterGraphReturnValue = afterGraphReturnValue;
	}

	public boolean isBeforeIsolatedSubGraphReturnValue() {
		return beforeIsolatedSubGraphReturnValue;
	}

	public void setBeforeIsolatedSubGraphReturnValue(boolean beforeIsolatedSubGraphReturnValue) {
		this.beforeIsolatedSubGraphReturnValue = beforeIsolatedSubGraphReturnValue;
	}

	public boolean isAfterIsolatedSubGraphReturnValue() {
		return afterIsolatedSubGraphReturnValue;
	}

	public void setAfterIsolatedSubGraphReturnValue(boolean afterIsolatedSubGraphReturnValue) {
		this.afterIsolatedSubGraphReturnValue = afterIsolatedSubGraphReturnValue;
	}

	public boolean isBeforeNodeReturnValue() {
		return beforeNodeReturnValue;
	}

	public void setBeforeNodeReturnValue(boolean beforeNodeReturnValue) {
		this.beforeNodeReturnValue = beforeNodeReturnValue;
	}

	public boolean isAfterNodeReturnValue() {
		return afterNodeReturnValue;
	}

	public void setAfterNodeReturnValue(boolean afterNodeReturnValue) {
		this.afterNodeReturnValue = afterNodeReturnValue;
	}

	public boolean isBeforeEdgeReturnValue() {
		return beforeEdgeReturnValue;
	}

	public void setBeforeEdgeReturnValue(boolean beforeEdgeReturnValue) {
		this.beforeEdgeReturnValue = beforeEdgeReturnValue;
	}

	public boolean isAfterEdgeReturnValue() {
		return afterEdgeReturnValue;
	}

	public void setAfterEdgeReturnValue(boolean afterEdgeReturnValue) {
		this.afterEdgeReturnValue = afterEdgeReturnValue;
	}

	public List<Graph> getBeforeGraphVisited() {
		return Collections.unmodifiableList(beforeGraphVisited);
	}

	public List<Graph> getAfterGraphVisited() {
		return Collections.unmodifiableList(afterGraphVisited);
	}

	public List<Graph> getBeforeIsolatedSubGraphVisited() {
		return Collections.unmodifiableList(beforeIsolatedSubGraphVisited);
	}

	public List<Graph> getAfterIsolatedSubGraphVisited() {
		return Collections.unmodifiableList(afterIsolatedSubGraphVisited);
	}

	public List<Node> getBeforeNodeVisited() {
		return Collections.unmodifiableList(beforeNodeVisited);
	}

	public List<Node> getAfterNodeVisited() {
		return Collections.unmodifiableList(afterNodeVisited);
	}

	public List<Edge> getBeforeEdgeVisited() {
		return Collections.unmodifiableList(beforeEdgeVisited);
	}

	public List<Edge> getAfterEdgeVisited() {
		return Collections.unmodifiableList(afterEdgeVisited);
	}

}
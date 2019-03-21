package io.github.gilcesarf.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeEdgeHandlingTest {
	private static final String[] NODE_TYPES = new String[] { "Node Type 1", "Node Type 2", "Node Type 3" };
	private static final String[] NODE_NAMES = new String[] { "Node Name 1", "Node Name 2", "Node Name 3" };

	private Node[] nodes = new Node[3];
	private Edge[] edges = new Edge[2];

	private Graph graph = null;

	@Before
	public void setUp() throws Exception {
		this.graph = new BaseGraph();
		for (int i = 0; i < NODE_NAMES.length; i++) {
			this.nodes[i] = new Node(this.graph, NODE_TYPES[i], NODE_NAMES[i]);
		}
	}

	@After
	public void tearDown() throws Exception {
		this.nodes = null;
		this.edges = null;
		this.graph = null;
	}

	@Test
	public void test() {
		for (int i = 0; i < nodes.length; i++) {
			checkNode(nodes[i], i);
		}
		edges[0] = this.graph.connect(nodes[0], nodes[1]);
		edges[1] = this.graph.connect(nodes[1], nodes[2]);
		checkAllEdgeSets();
		try {
			nodes[0].addSucessor(edges[1]);
			fail("Expected GraphException not raised.");
		} catch (GraphException e) {
			checkAllEdgeSets();
		}
		try {
			nodes[2].addPredecessor(edges[0]);
			fail("Expected GraphException not raised.");
		} catch (GraphException e) {
			checkAllEdgeSets();
		}
		EdgeData data = new BaseEdgeData();
		assertNull(edges[0].getData());
		edges[0].setData(data);
		assertEquals(data, edges[0].getData());
		assertEquals(edges[0], data.getEdge());
		edges[0].setData(null);
		assertNull(edges[0].getData());
	}

	private void checkAllEdgeSets() {
		checkEdgeSet(nodes[0].getPredecessors(), 0);
		checkEdgeSet(nodes[0].getSucessors(), 1);
		checkEdgeSet(nodes[1].getPredecessors(), 1);
		checkEdgeSet(nodes[1].getSucessors(), 1);
		checkEdgeSet(nodes[2].getPredecessors(), 1);
		checkEdgeSet(nodes[2].getSucessors(), 0);
	}

	private void checkNode(Node node, int i) {
		assertEquals(NODE_TYPES[i], node.getType());
		assertEquals(NODE_NAMES[i], node.getName());
		assertEquals(Node.class.getSimpleName() + "[" + NODE_NAMES[i] + "," + NODE_TYPES[i] + "]", node.toString());
		node.setType(null);
		assertNull(node.getType());
		node.setType(NODE_TYPES[i]);
		assertEquals(NODE_TYPES[i], node.getType());
		assertEquals(Node.class.getSimpleName() + "[" + NODE_NAMES[i] + "," + NODE_TYPES[i] + "]", node.toString());
		checkEdgeSet(node.getPredecessors(), 0);
		checkEdgeSet(node.getSucessors(), 0);
		BaseNodeData data = new BaseNodeData();
		assertNull(node.getData());
		node.setData(data);
		assertEquals(data, node.getData());
		assertEquals(node, data.getNode());
		node.setData(null);
		assertNull(node.getData());
	}

	private void checkEdgeSet(Set<Edge> set, int expectedSize) {
		assertEquals(expectedSize, set.size());
		try {
			set.add(null);
			fail("Set must be immutable");
		} catch (UnsupportedOperationException e) {
			assertEquals(expectedSize, set.size());
		}
	}

}

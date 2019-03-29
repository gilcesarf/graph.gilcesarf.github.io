package io.github.gilcesarf.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NodeConnectionGraphTest {

	private static final String NODE_TYPE = "JUnit Test";
	private static final String[] NODE_NAMES = new String[] { "Node 0", "Node 1", "Node 2", "Node 3", "Node 4" };

	private boolean[] directed = new boolean[] { true, false };
	private Graph[] graph = new BaseGraph[directed.length];
	private Node[][] nodes = new Node[directed.length][NODE_NAMES.length];

	@Before
	public void setUp() throws Exception {
		for (int j = 0; j < this.directed.length; j++) {
			this.graph[j] = new BaseGraph(this.directed[j]);
			for (int i = 0; i < NODE_NAMES.length; i++) {
				this.nodes[j][i] = this.graph[j].createNode(NODE_TYPE, NODE_NAMES[i], null);
			}
			assertEquals(0, this.graph[j].getEdgeSetSize());
			assertEquals(NODE_NAMES.length, this.graph[j].getNodeSetSize());
			for (int i = 0; i < this.nodes[j].length; i++) {
				this.graph[j].connect(this.nodes[j][i], this.nodes[j][i + 1 < this.nodes[j].length ? i + 1 : 0]);
			}
			assertEquals(NODE_NAMES.length, this.graph[j].getEdgeSetSize());
		}
	}

	@After
	public void tearDown() throws Exception {
		this.nodes = null;
		this.graph = null;
	}

	@Test
	public void test() {
		for (int j = 0; j < this.directed.length; j++) {
			for (int i = 0; i < this.nodes.length; i++) {
				assertEquals(1, this.nodes[j][i].getSucessors().size());
				assertEquals(this.directed[j] ? 0 : 1, this.nodes[j][i].getPredecessors().size());
			}
		}

	}

}

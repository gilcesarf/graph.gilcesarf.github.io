package io.github.gilcesarf.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BreadthFirstVisitorTest {

	private static final String NODE_TYPE = "JUnit Test";
	private static final String[] NODE_NAMES = new String[] { "Node 0", "Node 1", "Node 2", "Node 3", "Node 4" };

	private boolean[] directed = new boolean[] { true, false };
	private Graph[] graph = new BaseGraph[directed.length];
	private Node[][] nodes = new Node[directed.length][NODE_NAMES.length];
	private BreadthFirstVisitor visitor = null;
	private TestGraphListener listener = null;

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
		this.visitor = new BreadthFirstVisitor();
		this.listener = new TestGraphListener();
	}

	@After
	public void tearDown() throws Exception {
		this.nodes = null;
		this.graph = null;
	}

	@Test
	public void testNulls() {
		try {
			this.visitor.visit(null, null);
			fail("Expected GraphException not thrown");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("cannot be null"));
		}
		try {
			this.visitor.visit(this.graph[0], null);
			fail("Expected GraphException not thrown");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("cannot be null"));
		}
		try {
			this.visitor.visit(null, this.listener);
			fail("Expected GraphException not thrown");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("cannot be null"));
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < this.graph.length; i++) {
			System.out.println("Processing graph " + i + " without starting node.");
			this.visitor.visit(this.graph[i], this.listener);

			this.listener = new TestGraphListener();
			System.out.println("Processing graph " + i + " with starting node.");
			this.visitor.visit(this.graph[i], this.listener, this.nodes[i][2]);
		}
	}

}

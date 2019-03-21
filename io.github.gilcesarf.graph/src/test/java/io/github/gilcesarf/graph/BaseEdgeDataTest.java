package io.github.gilcesarf.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseEdgeDataTest {

	private Graph graph = null;
	private Node predecessor = null;
	private Node successor = null;
	private Edge edge = null;
	private BaseEdgeData edgeData = null;

	@Before
	public void setUp() {
		this.edgeData = new BaseEdgeData();
		this.graph = new BaseGraph();
		this.predecessor = this.graph.createNode("JUnit", "Node 1", null);
		this.successor = this.graph.createNode("JUnit", "Node 2", null);
		this.edge = this.graph.connect(this.predecessor, this.successor);
	}

	@After
	public void tearDown() {
		this.edgeData = null;
		this.graph = null;
		this.predecessor = null;
		this.successor = null;
		this.edge = null;
	}

	@Test
	public void test() {
		assertTrue(this.edgeData.getEdge() == null);
		this.edgeData.setEdge(this.edge);
		assertEquals(this.edge, this.edgeData.getEdge());
		assertEquals(this.edge.getData(), this.edgeData);
		this.edgeData.setEdge(null);
		assertNull(this.edgeData.getEdge());
		assertNull(this.edge.getData());
	}

}

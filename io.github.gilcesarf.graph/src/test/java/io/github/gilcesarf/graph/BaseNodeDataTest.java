package io.github.gilcesarf.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseNodeDataTest {

	private BaseNodeData nodeData = null;
	private Graph graph = null;
	private Node node = null;

	@Before
	public void setUp() {
		this.nodeData = new BaseNodeData();
		this.graph = new BaseGraph();
		this.node = this.graph.createNode("JUnit", "Node 1", null);
	}

	@After
	public void tearDown() {
		this.nodeData = null;
		this.graph = null;
		this.node = null;
	}

	@Test
	public void test() {
		assertTrue(this.nodeData.getNode() == null);
		this.nodeData.setNode(this.node);
		assertEquals(this.node, this.nodeData.getNode());
		assertEquals(this.node.getData(), this.nodeData);
		this.nodeData.setNode(null);
		assertNull(this.nodeData.getNode());
		assertNull(this.node.getData());
	}

}

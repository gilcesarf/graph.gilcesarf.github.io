package io.github.gilcesarf.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseNodeDataTest {

	private BaseNodeData nodeData = null;

	@Before
	public void setUp() {
		this.nodeData = new BaseNodeData();
	}

	@After
	public void tearDown() {
		this.nodeData = null;
	}

	@Test
	public void test() {
		assertTrue(this.nodeData.getNode() == null);
		Node n = new Node("JUnit Test", "Simple Node");
		this.nodeData.setNode(n);
		assertEquals(n, this.nodeData.getNode());
		assertEquals(n.getData(), this.nodeData);
		this.nodeData.setNode(null);
		assertNull(this.nodeData.getNode());
		assertNull(n.getData());
	}

}

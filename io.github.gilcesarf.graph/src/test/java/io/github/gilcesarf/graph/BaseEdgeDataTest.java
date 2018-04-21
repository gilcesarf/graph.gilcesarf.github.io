package io.github.gilcesarf.graph;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseEdgeDataTest {

	private BaseEdgeData edgeData = null;

	@Before
	public void setUp() {
		this.edgeData = new BaseEdgeData();
	}

	@After
	public void tearDown() {
		this.edgeData = null;
	}

	@Test
	public void test() {
		assertTrue(this.edgeData.getEdge() == null);
		Node pred = new Node("JUnit Test", "Predecessor");
		Node suc = new Node("JUnit Test", "Sucessor");
		Edge e = new Edge(pred, suc);
		this.edgeData.setEdge(e);
		assertEquals(e, this.edgeData.getEdge());
		assertEquals(e.getData(), this.edgeData);
		this.edgeData.setEdge(null);
		assertNull(this.edgeData.getEdge());
		assertNull(e.getData());
	}

}

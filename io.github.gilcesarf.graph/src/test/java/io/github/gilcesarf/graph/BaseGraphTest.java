package io.github.gilcesarf.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseGraphTest {

	private BaseGraph graph = null;
	private Node predecessor = null;
	private Node successor = null;

	@Before
	public void setup() {
		this.graph = new BaseGraph();
		assertFalse(this.graph.isDirected());
		Set<Node> nodeSet = this.graph.getNodeSet();
		assertNotNull(nodeSet);
		assertEquals(0, nodeSet.size());
		Set<Edge> edgeSet = this.graph.getEdgeSet();
		assertNotNull(edgeSet);
		assertEquals(0, edgeSet.size());
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		this.graph.connect(this.predecessor, this.successor);
	}

	@After
	public void tearDown() {
		this.graph = null;
		this.predecessor = null;
		this.successor = null;
	}

	@Test
	public void testConstructors() {
		//
		this.graph = new BaseGraph(20);
		assertFalse(this.graph.isDirected());
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		this.graph.connect(this.predecessor, this.successor);
		testGetEdgeSet();
		testGetNodeSet();
		//
		this.graph = new BaseGraph(30, 40);
		assertFalse(this.graph.isDirected());
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		this.graph.connect(this.predecessor, this.successor);
		testGetEdgeSet();
		testGetNodeSet();
		//
		this.graph = new BaseGraph(50, 0.9F, 60, 0.5F);
		assertFalse(this.graph.isDirected());
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		this.graph.connect(this.predecessor, this.successor);
		testGetEdgeSet();
		testGetNodeSet();
		//
		this.graph = new BaseGraph(true, 20);
		assertTrue(this.graph.isDirected());
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		this.graph.connect(this.predecessor, this.successor);
		testGetEdgeSet();
		testGetNodeSet();
		//
		this.graph = new BaseGraph(true, 30, 40);
		assertTrue(this.graph.isDirected());
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		this.graph.connect(this.predecessor, this.successor);
		testGetEdgeSet();
		testGetNodeSet();
		//
		this.graph = new BaseGraph(true, 50, 0.9F, 60, 0.5F);
		assertTrue(this.graph.isDirected());
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		this.graph.connect(this.predecessor, this.successor);
		testGetEdgeSet();
		testGetNodeSet();
	}

	@Test
	public void testDuplicatedEdgesAllowed() {
		assertTrue(this.graph.isDuplicatedEdgesAllowed());
		try {
			this.graph.disallowDuplicatedEdges();
			fail("Expected an GraphException instance");
		} catch (GraphException e) {
			assertTrue(e.getMessage().startsWith("Cannot change duplicated option"));
		}
		assertTrue(this.graph.isDuplicatedEdgesAllowed());
		this.graph.allowDuplicatedEdges();
		assertTrue(this.graph.isDuplicatedEdgesAllowed());

		this.graph = new BaseGraph(20);
		this.predecessor = null;
		this.successor = null;
		assertTrue(this.graph.isDuplicatedEdgesAllowed());
		this.graph.disallowDuplicatedEdges();
		assertFalse(this.graph.isDuplicatedEdgesAllowed());
		this.graph.allowDuplicatedEdges();
		assertTrue(this.graph.isDuplicatedEdgesAllowed());

	}

	@Test
	public void testGetEdgeSet() {
		Set<Edge> edgeSet = this.graph.getEdgeSet();
		assertNotNull(edgeSet);
		assertEquals(1, edgeSet.size());
		try {
			edgeSet.add(new Edge(this.graph, this.predecessor, this.successor));
			fail("Expected an UnmodifiableSet instance");
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
	}

	@Test
	public void testGetNodeSet() {
		Set<Node> nodeSet = this.graph.getNodeSet();
		assertNotNull(nodeSet);
		assertEquals(2, nodeSet.size());
		//
		assertTrue(nodeSet.contains(predecessor));
		assertTrue(nodeSet.contains(successor));
		//
		try {
			nodeSet.add(predecessor);
			fail("Expected an UnmodifiableSet instance");
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}

	}

	@Test
	public void testConnectNullPredecessor() {
		try {
			this.graph.connect(null, this.successor);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("Cannot connect"));
			assertTrue(e.getMessage().contains("null"));
			assertTrue(e.getMessage().contains("predecesor"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception");
		}
	}

	@Test
	public void testConnectNullSuccessor() {
		try {
			this.graph.connect(this.predecessor, null);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("Cannot connect"));
			assertTrue(e.getMessage().contains("null"));
			assertTrue(e.getMessage().contains("successor"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception");
		}
	}

	@Test
	public void testConnectWrongPredecessorGraph() {
		Graph other = new BaseGraph();
		Node otherPredecessor = other.createNode("JUnit", "Other Predecessor", null);
		try {
			this.graph.connect(otherPredecessor, this.successor);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("Predecessor"));
			assertTrue(e.getMessage().contains("is not contained in this graph"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception");
		}
	}

	@Test
	public void testConnectWrongSuccessorGraph() {
		Graph other = new BaseGraph();
		Node otherSuccessor = other.createNode("JUnit", "Other Successor", null);
		try {
			this.graph.connect(this.predecessor, otherSuccessor);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("Successor"));
			assertTrue(e.getMessage().contains("is not contained in this graph"));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected Exception");
		}
	}

	@Test
	public void testAddNode() {
		try {
			assertEquals(2, this.graph.getNodeSet().size());
			this.graph.addNode(null);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertEquals(2, this.graph.getNodeSet().size());
			assertTrue(e.getMessage().contains("The given Node cannot be null"));
		}

		Graph otherGraph = new BaseGraph();
		Node otherNode = new Node(otherGraph, "Other Graph", "Other Type");
		try {
			this.graph.addNode(otherNode);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("The given node does not belong to this graph"));
		}
	}

	@Test
	public void testAddEdge() {
		try {
			assertEquals(1, this.graph.getEdgeSet().size());
			this.graph.addEdge(null);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertEquals(1, this.graph.getEdgeSet().size());
			assertTrue(e.getMessage().contains("The given Edge cannot be null"));
		}

		Graph otherGraph = new BaseGraph();
		Node otherNode1 = new Node(otherGraph, "Other Graph", "Other Type 1");
		try {
			this.graph.addNode(otherNode1);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("The given node does not belong to this graph"));
		}
		Node otherNode2 = new Node(otherGraph, "Other Graph", "Other Type 2");
		try {
			this.graph.addNode(otherNode2);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("The given node does not belong to this graph"));
		}
		Edge e1 = null;
		Edge e2 = null;
		try {
			e1 = new Edge(this.graph, otherNode1, otherNode2);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException ge) {
			assertTrue(ge.getMessage().contains("Predecessor is not contained in the given graph"));
		}
		try {
			e1 = new Edge(this.graph, otherNode1, this.successor);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException ge) {
			assertTrue(ge.getMessage().contains("Predecessor is not contained in the given graph"));
		}
		try {
			e1 = new Edge(this.graph, this.predecessor, otherNode2);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException ge) {
			assertTrue(ge.getMessage().contains("Succesor is not contained in the given graph"));
		}
		try {
			e1 = new Edge(this.graph, this.predecessor, this.successor);
		} catch (GraphException ge) {
			ge.printStackTrace();
			fail("Unexpected GraphException");
		}
		try {
			otherGraph.addEdge(e1);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException ge) {
			assertTrue(ge.getMessage().contains("The given edge does not belong to this graph"));
		}
		try {
			assertEquals(2, this.graph.getEdgeSet().size());
			assertFalse(this.graph.addEdge(e1));
			assertEquals(2, this.graph.getEdgeSet().size());
		} catch (GraphException ge) {
			fail("Unexpected GraphException");
		}
		try {
			assertEquals(0, otherGraph.getEdgeSet().size());
			e2 = new Edge(otherGraph, otherNode1, otherNode2);
			assertEquals(1, otherGraph.getEdgeSet().size());
			Edge e3 = otherGraph.connect(otherNode1, otherNode2);
			assertEquals(2, otherGraph.getEdgeSet().size());
			assertTrue(!e2.equals(e3));
		} catch (GraphException ge) {
			fail("Unexpected GraphException");
		}
	}

	@Test
	public void testConnectDisallowDuplicatedEdges() {
		this.graph = new BaseGraph();
		this.graph.disallowDuplicatedEdges();
		this.predecessor = this.graph.createNode("JUnit", "Predecessor", null);
		this.successor = this.graph.createNode("JUnit", "Successor", null);
		Edge e1 = this.graph.connect(this.predecessor, this.successor);
		assertNotNull(e1);
		// try a duplicated edge
		Edge e2 = null;
		try {
			this.graph.connect(this.predecessor, this.successor);
			fail("Expected a GraphException to be raised.");
		} catch (GraphException e) {
			assertTrue(e.getMessage().contains("Duplicated Edges not allowed"));
		}
		assertNull(e2);

		// try only same predecessor
		Node otherSuccessor = this.graph.createNode("JUnit", "Other Successor", null);
		Edge e3 = null;
		try {
			e3 = this.graph.connect(this.predecessor, otherSuccessor);
		} catch (GraphException e) {
			e.printStackTrace();
			fail("Unexpected Exception");
		}
		assertNotNull(e3);

		// try only same successor
		Node otherPredecessor = this.graph.createNode("JUnit", "Other Predecessor", null);
		Edge e4 = null;
		try {
			e4 = this.graph.connect(otherPredecessor, this.successor);
		} catch (GraphException e) {
			e.printStackTrace();
			fail("Unexpected Exception");
		}
		assertNotNull(e4);

		// everything different
		Edge e5 = null;
		try {
			e5 = this.graph.connect(this.graph.createNode("JUnit", "Predecessor 3", null),
					this.graph.createNode("JUnit", "Successor 3", null));
		} catch (GraphException e) {
			e.printStackTrace();
			fail("Unexpected Exception");
		}
		assertNotNull(e5);

		// check EdgeSet and NodeSet sizes
		assertEquals(4, this.graph.getEdgeSet().size());
		assertEquals(6, this.graph.getNodeSet().size());

	}

}

package io.github.gilcesarf.graph;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BaseEdgeDataTest.class, BaseNodeDataTest.class, NodeEdgeHandlingTest.class, BaseGraphTest.class,
		NodeConnectionGraphTest.class })
public class AllTests {

}

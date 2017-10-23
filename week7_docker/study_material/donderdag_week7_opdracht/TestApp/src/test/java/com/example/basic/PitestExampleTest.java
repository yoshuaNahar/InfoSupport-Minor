package com.example.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PitestExampleTest {

	private PitestExample example;
	
	@Before
	public void setUp() {
		example = new PitestExample();
	}
	
	@Test
	public void testGetValueOrBoundary() {
		assertTrue(example.getValueOrBoundary(20, 100) < 101);
	}
}

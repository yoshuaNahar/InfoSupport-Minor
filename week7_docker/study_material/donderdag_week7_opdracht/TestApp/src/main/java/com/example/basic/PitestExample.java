package com.example.basic;

public class PitestExample {	
	
	public int getValueOrBoundary(int value, int boundary) {
		if (value < boundary) {
			return value;
		}
		return boundary;
	}
}

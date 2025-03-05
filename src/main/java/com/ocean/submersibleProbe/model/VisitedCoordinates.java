package com.ocean.submersibleProbe.model;

import java.util.ArrayList;
import java.util.List;

public class VisitedCoordinates {
	 private List<Point> visited;

	    public VisitedCoordinates() {
	        this.visited = new ArrayList<>();
	    }

	    public List<Point> getVisited() {
	        return visited;
	    }

	    public void setVisited(List<Point> visited) {
	        this.visited = visited;
	    }
}

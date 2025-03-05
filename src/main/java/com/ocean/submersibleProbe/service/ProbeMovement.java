package com.ocean.submersibleProbe.service;

import com.ocean.submersibleProbe.model.*;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProbeMovement {
	 private Probe probe;
	    private Grid grid;
	    private VisitedCoordinates visitedCoordinates;

	    public void initialize(Probe initialProbe, Grid initialGrid) {
	        this.probe = initialProbe;
	        this.grid = initialGrid;
	        this.visitedCoordinates = new VisitedCoordinates();
	        this.visitedCoordinates.getVisited().add(new Point(probe.getX(), probe.getY()));
	    }

	    public Probe processCommands(List<Command> commands) {
	        for (Command command : commands) {
	            executeCommand(command);
	        }
	        return probe;
	    }

	    private void executeCommand(Command command) {
	        int newX = probe.getX();
	        int newY = probe.getY();

	        switch (command) {
	            case FORWARD:
	                switch (probe.getDirection()) {
	                    case NORTH: newY++; break;
	                    case EAST: newX++; break;
	                    case SOUTH: newY--; break;
	                    case WEST: newX--; break;
	                }
	                break;
	            case BACKWARD:
	                switch (probe.getDirection()) {
	                    case NORTH: newY--; break;
	                    case EAST: newX--; break;
	                    case SOUTH: newY++; break;
	                    case WEST: newX++; break;
	                }
	                break;
	            case LEFT:
	                probe.setDirection(Direction.values()[(probe.getDirection().ordinal() - 1 + Direction.values().length) % Direction.values().length]);
	                return;
	            case RIGHT:
	                probe.setDirection(Direction.values()[(probe.getDirection().ordinal() + 1) % Direction.values().length]);
	                return;
	        }

	        if (isValidMove(newX, newY)) {
	            probe.setX(newX);
	            probe.setY(newY);
	            visitedCoordinates.getVisited().add(new Point(newX, newY));
	        }
	    }

	    private boolean isValidMove(int x, int y) {
	        if (x < 0 || x >= grid.getWidth() || y < 0 || y >= grid.getHeight()) {
	        	
	            return false;
	        }
	        for (Point obstacle : grid.getObstacles()) {
	            if (obstacle.getX() == x && obstacle.getY() == y) {
	                return false;
	            }
	        }
	        return true;
	    }

	    public VisitedCoordinates getVisitedCoordinates() {
	        return visitedCoordinates;
	    }

}

package com.ocean.submersibleProbe.service;

import com.ocean.submersibleProbe.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbeServiceTest {

    private ProbeMovement probeMovement;
    private Probe initialProbe;
    private Grid initialGrid;

    @BeforeEach
    void setUp() {
    	probeMovement = new ProbeMovement();
        initialProbe = new Probe(0, 0, Direction.NORTH);
        initialGrid = new Grid(5, 5, Collections.singletonList(new Point(2, 2))); // Obstacle at (2, 2)
        probeMovement.initialize(initialProbe, initialGrid);
    }

    @Test
    void testInitialize() {
        assertEquals(0, probeMovement.getVisitedCoordinates().getVisited().get(0).getX());
        assertEquals(0, probeMovement.getVisitedCoordinates().getVisited().get(0).getY());
    }

    @Test
    void testProcessCommands_Forward() {
        List<Command> commands = Collections.singletonList(Command.FORWARD);
        probeMovement.processCommands(commands);
        assertEquals(0, probeMovement.processCommands(commands).getX());
        assertEquals(1, probeMovement.processCommands(commands).getY());
    }

    @Test
    void testProcessCommands_Backward() {
        List<Command> commands = Collections.singletonList(Command.BACKWARD);
        probeMovement.processCommands(commands);
        assertEquals(0, probeMovement.processCommands(commands).getX());
        assertEquals(0, probeMovement.processCommands(commands).getY()); //stayed on the same place, because it can not go behind 0
    }

    @Test
    void testProcessCommands_Left() {
        List<Command> commands = Collections.singletonList(Command.LEFT);
        probeMovement.processCommands(commands);
        assertEquals(Direction.WEST, probeMovement.processCommands(commands).getDirection());
    }

    @Test
    void testProcessCommands_Right() {
        List<Command> commands = Collections.singletonList(Command.RIGHT);
        probeMovement.processCommands(commands);
        assertEquals(Direction.EAST, probeMovement.processCommands(commands).getDirection());
    }

    @Test
    void testProcessCommands_MultipleCommands() {
        List<Command> commands = Arrays.asList(Command.FORWARD, Command.RIGHT, Command.FORWARD);
        probeMovement.processCommands(commands);
        assertEquals(1, probeMovement.processCommands(commands).getX());
        assertEquals(1, probeMovement.processCommands(commands).getY());
        assertEquals(Direction.EAST, probeMovement.processCommands(commands).getDirection());
    }

    @Test
    void testProcessCommands_ObstacleAvoidance() {
        List<Command> commands = Arrays.asList(Command.FORWARD, Command.RIGHT, Command.FORWARD, Command.FORWARD, Command.FORWARD);
        probeMovement.processCommands(commands);
        assertEquals(1, probeMovement.processCommands(commands).getX());
        assertEquals(2, probeMovement.processCommands(commands).getY()); //avoided obstacle
    }

    @Test
    void testProcessCommands_GridBoundary() {
        List<Command> commands = Arrays.asList(Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD);
        probeMovement.processCommands(commands);
        assertEquals(0, probeMovement.processCommands(commands).getX());
        assertEquals(4, probeMovement.processCommands(commands).getY());
    }

    @Test
    void testVisitedCoordinates() {
        List<Command> commands = Arrays.asList(Command.FORWARD, Command.RIGHT, Command.FORWARD);
        probeMovement.processCommands(commands);
        List<Point> visited = probeMovement.getVisitedCoordinates().getVisited();
        assertEquals(4, visited.size());
        assertEquals(new Point(0, 0), visited.get(0));
        assertEquals(new Point(0, 1), visited.get(1));
        assertEquals(new Point(1, 1), visited.get(3));
    }
}
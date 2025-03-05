package com.ocean.submersibleProbe.model;

import java.util.List;

public class Grid {

	private int width;
    private int height;
    private List<Point> obstacles;

    public Grid() {}

    public Grid(int width, int height, List<Point> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Point> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Point> obstacles) {
        this.obstacles = obstacles;
    }
}

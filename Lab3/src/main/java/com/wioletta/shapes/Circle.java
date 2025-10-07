package com.wioletta.shapes;

public class Circle extends Shape {
    private final double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle\t\t| color: " + shapeColor + ", radius: " + radius + ", area: " + calcArea();
    }
}

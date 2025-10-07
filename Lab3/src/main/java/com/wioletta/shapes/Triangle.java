package com.wioletta.shapes;

public class Triangle extends Shape {
    private final double sideA;
    private final double sideB;
    private final double sideC;

    public Triangle(String shapeColor, double sideA, double sideB, double sideC) {
        super(shapeColor);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calcArea() {
        double s = (sideA + sideB + sideC) / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public String toString() {
        return "Triangle\t| color: " + shapeColor + ", sideA = " + sideA + ", sideB = " + sideB + ", sideC = " + sideC + ", area = " + calcArea();
    }
}

package com.wioletta.model;

import com.wioletta.shapes.Circle;
import com.wioletta.shapes.Rectangle;
import com.wioletta.shapes.Shape;
import com.wioletta.shapes.Triangle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ShapeModel {
    private final Shape[] shapes;

    public ShapeModel(int numberOfShapes) {
        this.shapes = new Shape[numberOfShapes];
        generateShapes();
    }

    private void generateShapes() {
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Black"};
        Random rand = new Random();

        for (int i = 0; i < shapes.length; i++) {
            switch (rand.nextInt(3)) {
                case 0:
                    shapes[i] = new Rectangle(
                            colors[rand.nextInt(colors.length)],
                            rand.nextDouble() * 10,
                            rand.nextDouble() * 10);
                    break;
                case 1:
                    double sideA, sideB, sideC;
                    do {
                        sideA = rand.nextDouble() * 10;
                        sideB = rand.nextDouble() * 10;
                        sideC = rand.nextDouble() * 10;
                    } while (!isValidTriangle(sideA, sideB, sideC));
                    shapes[i] = new Triangle(
                            colors[rand.nextInt(colors.length)],
                            sideA, sideB, sideC);
                    break;
                case 2:
                    shapes[i] = new Circle(
                            colors[rand.nextInt(colors.length)],
                            rand.nextDouble() * 10);
                    break;
            }
        }
    }

    public boolean isValidTriangle(double sideA, double sideB, double sideC) {
        return (sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA);
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public double getTotalArea() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }
        return totalArea;
    }

    public double getTotalAreaByType(Class<? extends Shape> shapeType) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            if (shapeType.isInstance(shape)) {
                totalArea += shape.calcArea();
            }
        }
        return totalArea;
    }

    public void sortByArea() {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
    }

    public void sortByColor() {
        Arrays.sort(shapes, Comparator.comparing(shape -> shape.shapeColor));
    }

    public Class<? extends Shape> getShapeType(String shapeTypeInput) {
        return switch (shapeTypeInput.toLowerCase()) {
            case "triangle" -> Triangle.class;
            case "circle" -> Circle.class;
            case "rectangle" -> Rectangle.class;
            default -> null;
        };
    }
}

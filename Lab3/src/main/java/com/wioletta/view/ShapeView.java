package com.wioletta.view;

import com.wioletta.shapes.Shape;

public class ShapeView {
    public final String DISPLAYING_SHAPES = "Displaying all shapes:";
    public final String EXIT_PROGRAM = "Exiting program.";
    public final String INVALID_COMMAND = "Invalid command: ";
    public final String AVAILABLE_COMMANDS = "Available commands: display, load, save, sort area, sort color, total area, exit";

    public void displayShapes(Shape[] shapes) {
        for (Shape shape : shapes) {
            System.out.println(shape);
        }
    }

    public void displayTotalArea(double totalArea) {
        System.out.println("Total area of all shapes: " + totalArea);
    }

    public void displayTotalAreaByType(double totalArea, String shapeType) {
        System.out.println("Total area of " + shapeType + "s: " + totalArea);
    }

    public void displaySortedShapes(String sortingCriteria) {
        System.out.println("\nSorting by " + sortingCriteria + ":");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}

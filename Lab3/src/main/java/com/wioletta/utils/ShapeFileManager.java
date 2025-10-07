package com.wioletta.utils;

import com.wioletta.shapes.Shape;

import java.io.*;

public class ShapeFileManager {
    public void saveShapesToFile(Shape[] shapes, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(shapes);
        } catch (IOException e) {
            System.err.println("Error saving shapes: " + e.getMessage());
        }
    }

    public Shape[] loadShapesFromFile(String fileName) {
        Shape[] shapes = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            shapes = (Shape[]) ois.readObject();
            System.out.println("Shapes loaded successfully from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading shapes: " + e.getMessage());
        }
        return shapes;
    }
}

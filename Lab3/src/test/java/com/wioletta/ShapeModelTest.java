package com.wioletta;

import com.wioletta.model.ShapeModel;
import com.wioletta.shapes.Circle;
import com.wioletta.shapes.Rectangle;
import com.wioletta.shapes.Shape;
import com.wioletta.shapes.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeModelTest {
    private ShapeModel model;

    @BeforeEach
    public void setUp() {
        model = new ShapeModel(10);
    }

    @Test
    public void testGenerateShapes() {
        Shape[] shapes = model.getShapes();

        assertNotNull(shapes);
        assertEquals(10, shapes.length);

        for (Shape shape : shapes) {
            assertNotNull(shape);
        }
    }

    @Test
    public void testGetTotalArea() {
        double totalArea = model.getTotalArea();

        double expectedArea = 0;
        for (Shape shape : model.getShapes()) {
            expectedArea += shape.calcArea();
        }

        assertEquals(expectedArea, totalArea, 0.001);
    }

    @Test
    public void testGetTotalAreaByType() {
        double totalTriangleArea = model.getTotalAreaByType(Triangle.class);
        double totalCircleArea = model.getTotalAreaByType(Circle.class);
        double totalRectangleArea = model.getTotalAreaByType(Rectangle.class);

        double expectedTriangleArea = 0;
        double expectedCircleArea = 0;
        double expectedRectangleArea = 0;

        for (Shape shape : model.getShapes()) {
            if (shape instanceof Triangle) {
                expectedTriangleArea += shape.calcArea();
            } else if (shape instanceof Circle) {
                expectedCircleArea += shape.calcArea();
            } else if (shape instanceof Rectangle) {
                expectedRectangleArea += shape.calcArea();
            }
        }

        assertEquals(expectedTriangleArea, totalTriangleArea, 0.001);
        assertEquals(expectedCircleArea, totalCircleArea, 0.001);
        assertEquals(expectedRectangleArea, totalRectangleArea, 0.001);
    }

    @Test
    public void testSortByArea() {
        Shape[] shapesBeforeSort = model.getShapes().clone();
        model.sortByArea();

        Shape[] shapesAfterSort = model.getShapes();

        assertFalse(Arrays.equals(shapesBeforeSort, shapesAfterSort), "Shapes should be sorted, thus the arrays should be different.");

        for (int i = 1; i < shapesAfterSort.length; i++) {
            assertTrue(shapesAfterSort[i - 1].calcArea() <= shapesAfterSort[i].calcArea());
        }
    }

    @Test
    public void testSortByColor() {
        Shape[] shapesBeforeSort = model.getShapes().clone();
        model.sortByColor();

        Shape[] shapesAfterSort = model.getShapes();

        assertFalse(Arrays.equals(shapesBeforeSort, shapesAfterSort), "Shapes should be sorted by color, thus the arrays should be different.");

        for (int i = 1; i < shapesAfterSort.length; i++) {
            assertTrue(shapesAfterSort[i - 1].shapeColor.compareTo(shapesAfterSort[i].shapeColor) <= 0);
        }
    }

    @Test
    public void testIsValidTriangle() {
        assertTrue(model.isValidTriangle(3, 4, 5), "3, 4, 5 should form a valid triangle.");
        assertFalse(model.isValidTriangle(1, 2, 10), "1, 2, 10 should not form a valid triangle.");
    }

    @Test
    public void testGetShapeType() {
        assertEquals(Triangle.class, model.getShapeType("triangle"));
        assertEquals(Circle.class, model.getShapeType("circle"));
        assertEquals(Rectangle.class, model.getShapeType("rectangle"));
        assertNull(model.getShapeType("invalidShape"));
    }
}

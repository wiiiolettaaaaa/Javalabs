package com.wioletta.shapes;

import com.wioletta.drawable.Drawable;

import java.io.Serializable;

public abstract class Shape implements Drawable, Serializable {
    public String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public abstract double calcArea();

    @Override
    public void draw() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
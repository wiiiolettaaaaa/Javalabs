package com.wioletta;

import com.wioletta.controller.ShapeController;

public class Main {
    public static void main(String[] args) {
        ShapeController controller = new ShapeController(10);
        controller.startInteraction();
    }
}
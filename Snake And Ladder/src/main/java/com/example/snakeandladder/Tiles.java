package com.example.snakeandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
// rectangle is a class having height and width
public class Tiles extends Rectangle {
    // setting the parameters for each tile
    public Tiles(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        setFill(Color.ALICEBLUE);
        setStroke(Color.BLACK);
    }
}

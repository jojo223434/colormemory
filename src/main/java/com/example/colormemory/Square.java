package com.example.colormemory;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Square extends Rectangle

{   // Constructor for the Square class
    public Square(Pane pane, int x, int y, Color color)
    {
        super(x,y, 100, 100);
        pane.getChildren().add(this);
        this.setFill(color);

        // Add a mouse click event to scale the square
        this.setOnMouseClicked(mouseEvent -> scaleSquare(this));
    }

    //Method to crate and return and animation that scales the square
    public ScaleTransition scaleSquare(Node node)
    {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setNode(node);
        scaleTransition.setInterpolator(Interpolator.EASE_OUT);
        scaleTransition.setDuration(Duration.seconds(1.0));
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.setByX(0.2);
        scaleTransition.setByY(0.2);
        return scaleTransition;
    }
}

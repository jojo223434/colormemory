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
    }

    // Method for scaling the cube and reversing to the original size
    public ScaleTransition scaleSquare(Node node, boolean mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        scaleTransition.setDuration(Duration.seconds(0.8));
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.setNode(node);

        if (mouseEvent) {
            disablePress();
            scaleTransition.setOnFinished(event -> {
                enablePress();
            });
            scaleTransition.play();
        }
        return scaleTransition;
    }

    // Enable being able to press the cubes with the mouse
    public void enablePress() {
        this.setDisable(false);
    }

    // Disable being able to press the cubes with the mouse
    public void disablePress() {
        this.setDisable(true);
    }
}
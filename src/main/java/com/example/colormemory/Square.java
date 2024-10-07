package com.example.colormemory;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Square extends Rectangle
{
    // Constructor for the Square class
    // Initializes a square object with specified position and color, then adds it to the pane
    public Square(Pane pane, int x, int y, Color color)
    {
        // Call the superclass constuctor (Rectangle) to set the square's position and size
        super(x,y, 100, 100);

        // Add the square to the Pane so it is visible in the game
        pane.getChildren().add(this);

        // Set the color of the square
        this.setFill(color);
    }

    // Method for scaling the cube and reversing to the original size
    // 'mouseEvent' is used to determine if the scale is triggered by user interaction (click)
    public ScaleTransition scaleSquare(Node node, boolean mouseEvent)
    {
        // Create a new ScaleTransition animation
        ScaleTransition scaleTransition = new ScaleTransition();
        // Set animation interpolator for smoother scaling
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        // Set the duration of the animation
        scaleTransition.setDuration(Duration.seconds(0.8));
        // Define the scaling size (increase by 20%)
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        // Enable auto-reverse so the square returns to its original size
        scaleTransition.setAutoReverse(true);
        // Set the cycle count to 2 so the square scales up and down
        scaleTransition.setCycleCount(2);
        // Attach the animation to the square (node)
        scaleTransition.setNode(node);

        // If the scaling is triggered by a mouse click
        if (mouseEvent)
        {
            // Disable the square to prevent additional clicks during animation
            disablePress();

            // re-enable the square after the scaling animation finishes
            scaleTransition.setOnFinished(event ->
            {
                enablePress();
            });
            // Start the scaling animation
            scaleTransition.play();
        }

        // Return the ScaleTransition object
        return scaleTransition;
    }

    // Method to enable mouse interaction with the square
    public void enablePress()
    {
        this.setDisable(false); // Make the Square clickable again
    }

    // Method to disable mouse interaction with the square
    public void disablePress()
    {
        this.setDisable(true); // Prevent the square from being clicked
    }
}
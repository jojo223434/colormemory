package com.example.colormemory;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Sequence
{
    // Stores the squares as a list of integers
    ArrayList<Integer> sequenceArray = new ArrayList<>();

    // List to store the available squares in the game
    ArrayList<Square> squaresArray = new ArrayList<>();

    // Random selection of a square for the sequence
    Random randomSquare = new Random();

    // Reference to the main GameMemmory application, used to control game flow ( enable/disable clicks)
    GameMemory gameMemory;

    // Constructor that takes four squares and add them to the squaresArray list
    public Sequence(GameMemory mainApp, Square square1, Square square2, Square square3, Square square4)
    {
        // Store the reference to the main game application
        gameMemory = mainApp;

        // Add the four squares to the list of available squares
        squaresArray.add(square1);
        squaresArray.add(square2);
        squaresArray.add(square3);
        squaresArray.add(square4);
    }

    // Method to add a new randomly chosen square to the sequenceArray
    public void addNewSquareToSequence()
    {
        // Generate a random number between 0 and 3 to select one of the four squares
        int randomNumber = randomSquare.nextInt(4);

        // Add the random number to the sequence
        sequenceArray.add(randomNumber);

        // Print the selected random number
        System.out.println("randomNumber: " + randomNumber);
    }

    // Method to play back the entire sequence of squares with animations
    public void playAllSquares()
    {

        // Create a SequentialTransition object tp play animations one after another
        SequentialTransition sequentialTransition = new SequentialTransition();

        // Loop through the sequenceArray to access each square's index
        for (int i = 0; i < sequenceArray.size(); i++)
        {
            // Get the index of the square from the sequenceArray
            int x = sequenceArray.get(i);
            // Retrieve the corresponding square from SquaresArray based on the index
            Square square = squaresArray.get(x);

            // Create a ScaleTransition for the selected square (false means it's not triggered by a mouse event)
            ScaleTransition scaleTransition = square.scaleSquare(square, false);

            // Create a pause between each square's animation
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));

            // Add both the scale transition and the pause to the sequentialTransition
            sequentialTransition.getChildren().addAll(scaleTransition, pause);
        }

        // Play the entire sequence of animations
        sequentialTransition.play();

        // Disable clicks on squares while the sequence is playing
        gameMemory.disableClicks();

        // After the sequence is finished playing, enable clicks again
        sequentialTransition.setOnFinished(event ->
        {
            gameMemory.enableClicks();
            System.out.println("Finished"); // indicate the sequence is finished so u can press again
        });
    }
}

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

    // Store the available squares in and array
    ArrayList<Square> squaresArray = new ArrayList<>();

    // Random selection of a square
    Random randomSquare = new Random();

    GameMemory gameMemory;

    // Constructor that takes four squares and add them to the squaresArray list
    public Sequence(GameMemory mainApp, Square square1, Square square2, Square square3, Square square4)
    {
        gameMemory = mainApp;
        squaresArray.add(square1);
        squaresArray.add(square2);
        squaresArray.add(square3);
        squaresArray.add(square4);
    }


    // Method to add a new randomly chosen square to the order
    public void addNewSquareToSequence()
    {
        int randomNumber = randomSquare.nextInt(4);
        sequenceArray.add(randomNumber);
        System.out.println("randomNumber: " + randomNumber);
    }

    public void playAllSquares() {
        SequentialTransition sequentialTransition = new SequentialTransition();

        // Loop through the sequence list
        for (int i = 0; i < sequenceArray.size(); i++) {
            int x = sequenceArray.get(i);
            Square square = squaresArray.get(x);

            // Create a ScaleTransition for each square
            ScaleTransition scaleTransition = square.scaleSquare(square, false);

            // Add a pause between each transition
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));

            // Add the transition and pause to the sequentialTransition
            sequentialTransition.getChildren().addAll(scaleTransition, pause);
        }

        // Play the entire sequence of animations
        sequentialTransition.play();

        gameMemory.disableClicks();

        sequentialTransition.setOnFinished(event -> {
            gameMemory.enableClicks();
            System.out.println("Finished");
        });
    }
}

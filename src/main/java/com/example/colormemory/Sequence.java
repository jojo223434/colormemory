package com.example.colormemory;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
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

    // Constructor that takes four squares and add them to the squaresArray list
    public Sequence(Square square1, Square square2, Square square3, Square square4)
    {
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

    // Method to play back all squares in the current sequence
    public void playAllSquares()
    {
        SequentialTransition sequentialTransition = new SequentialTransition();

        // Loop through the sequence list
        for(int i = 0; i < sequenceArray.size(); i++)
        {
            int x = sequenceArray.get(i);
            Square square = squaresArray.get(x);

            ScaleTransition scaleTransition = square.scaleSquare(square);

            sequentialTransition.getChildren().add(scaleTransition);
        }
        sequentialTransition.play();
    }
}

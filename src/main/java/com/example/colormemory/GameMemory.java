package com.example.colormemory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameMemory extends Application
{
    Sequence sequence; // The sequence of squares that the player must follow
    Square square1, square2, square3, square4; // Four squares used in the game
    Pane pane; // The Pane layout for the game
    int count = 0; // Keeps track of the player's progress through the current sequence

    // To track and display the player's score
    ScoreCounter scoreCounter;

    // A boolean that tracks if the player selects the correct sequence
    boolean allSquareCorrect;

    // A Start/Restart button for the game
    Button startButton = new Button("Start");

    @Override
    public void start(Stage stage) throws Exception
    {
        // Create a Pane to hold the squares
        pane = new Pane();

        // Create scene, add background color, and show it on stage
        Scene scene = new Scene(pane, 400, 400);
        scene.setFill(Color.GREY);
        stage.setScene(scene);
        stage.setTitle("Memory Game");
        stage.show();

        // The position of the button and add it to the pane
        startButton.setLayoutX(175);
        startButton.setLayoutY(300);
        pane.getChildren().add(startButton);
        // Press the start button to start the game
        startButton.setOnAction(event -> {startGame();});

        scoreCounter = new ScoreCounter(pane);

        // Create four squares with specified positions and colors
        square1 = new Square(pane, 85, 50, Color.RED);
        square2 = new Square(pane, 210, 50, Color.YELLOW);
        square3 = new Square(pane, 85, 180, Color.GREEN);
        square4 = new Square(pane, 210, 180, Color.BLUE);

        // Initialize the sequence with the squares and the game reference
        sequence = new Sequence(this, square1, square2, square3, square4);

        // Set up a mouse click for each square
        square1.setOnMouseClicked(mouseEvent -> pressedSquare(0, square1));
        square2.setOnMouseClicked(mouseEvent -> pressedSquare(1, square2));
        square3.setOnMouseClicked(mouseEvent -> pressedSquare(2, square3));
        square4.setOnMouseClicked(mouseEvent -> pressedSquare(3, square4));
    }

    public void startGame()
    {

        // Add the first square to the sequence and play it
        sequence.addNewSquareToSequence();
        sequence.playAllSquares();

        startButton.setVisible(false);
        startButton.setText("Start");
    }

    // Method called when a square is clicked
    public void pressedSquare(int squareIndex, Square square)
    {

        // Play the scaling animation when the square is clicked
        square.scaleSquare(square, true);

        // schedule a new thread pool to handle sequence timing
        ScheduledExecutorService order = Executors.newScheduledThreadPool(1);

        // Check if the clicked square matches the current position is the sequence
        if (sequence.sequenceArray.get(count) == squareIndex)
        {
            count++; // Move to the next square in the sequence

            // If the player has successfully matched the full sequence
            if (count == sequence.sequenceArray.size())
            {
                count = 0; // reset count for the next round
                allSquareCorrect = true;
                sequence.addNewSquareToSequence(); // Add a new square to the sequence

                scoreCounter.addScore(); // Update the score

                // Schedule the sequence to play after a 3-second delay
                order.schedule(() ->
                {
                    sequence.playAllSquares();
                }, 3, TimeUnit.SECONDS);
            }
        } else
        {
            // If the sequence pressed is incorrect reset the game
            tryAgain();
        }
    }

    // Resets the game
    public void tryAgain()
    {
        allSquareCorrect = false; // Player did not follow the sequence

        startButton.setText("Restart Game"); // changes the button text
        startButton.setVisible(true); // make the button visible
        scoreCounter.setScore(0); // Reset the score

        sequence.sequenceArray.clear(); // Clears the current sequence
    }


    // Method to disable square clicks during sequence playback
    public void disableClicks()
    {
        square1.setDisable(true);
        square2.setDisable(true);
        square3.setDisable(true);
        square4.setDisable(true);
    }

    // Method to re-enable square clicks
    public void enableClicks()
    {
        square1.setDisable(false);
        square2.setDisable(false);
        square3.setDisable(false);
        square4.setDisable(false);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
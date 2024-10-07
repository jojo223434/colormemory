package com.example.colormemory;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class GameMemory extends Application
{
    Sequence sequence;
    Square square1, square2, square3, square4;
    int count = 0;
    boolean isTransitioning = false;

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane pane = new Pane();

        square1 = new Square(pane, 85, 50, Color.RED);
        square2 = new Square(pane, 210, 50, Color.YELLOW);
        square3 = new Square(pane, 85, 180, Color.GREEN);
        square4 = new Square(pane, 210, 180, Color.BLUE);

        sequence = new Sequence (square1, square2, square3, square4);

        sequence.addNewSquareToSequence();
        playSequence();

        square1.setOnMouseClicked(mouseEvent -> pressedSquare(0, square1));
        square2.setOnMouseClicked(mouseEvent -> pressedSquare(1, square2));
        square3.setOnMouseClicked(mouseEvent -> pressedSquare(2, square3));
        square4.setOnMouseClicked(mouseEvent -> pressedSquare(3, square4));

        Scene scene = new Scene(pane, 400, 400);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Memory Game");
        stage.show();
    }

    public void pressedSquare(int squareIndex, Square square)
        {
            if (isTransitioning) return;

            square.scaleSquare();

            if (sequence.sequenceArray.get(count) == squareIndex)
            { count++;
                if (count == sequence.sequenceArray.size())
                {
                    count = 0;
                    sequence.addNewSquareToSequence();
                    playSequence();
                }
            }
            else
            {
                System.out.println("Lose");
                count = 0;
            }
        }

    public void playSequence()
    {
        isTransitioning = true;
        sequence.playAllSquares(() -> {
            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1.0));
            pauseTransition.setOnFinished(event ->
            {
                isTransitioning = false;
            });
            pauseTransition.play();
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

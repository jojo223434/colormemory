package com.example.colormemory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class GameMemory extends Application
{
    Sequence sequence;
    Square square1, square2, square3, square4;

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

        sequence.playAllSquares();

        square1.setOnMouseClicked(mouseEvent -> pressedSquare1());
        square2.setOnMouseClicked(mouseEvent -> pressedSquare2());
        square3.setOnMouseClicked(mouseEvent -> pressedSquare3());
        square4.setOnMouseClicked(mouseEvent -> pressedSquare4());

        Scene scene = new Scene(pane, 400, 400);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Memory Game");
        stage.show();
    }

    public void pressedSquare1()
        {
            square1.scaleSquare();

            if (sequence.sequenceArray.getLast() == 0){
                sequence.playAllSquares();
                sequence.addNewSquareToSequence();
            }
            else {
                System.out.println("Lose");
            }
        }

    public void pressedSquare2()
    {
        if (sequence.sequenceArray.getLast() == 1){
            sequence.playAllSquares();
            sequence.addNewSquareToSequence();
        }
        else {
            System.out.println("Lose");
        }
    }

    public void pressedSquare3()
    {
        if (sequence.sequenceArray.getLast() == 2){
            sequence.playAllSquares();
            sequence.addNewSquareToSequence();
        }
        else {
            System.out.println("Lose");
        }

    }

    public void pressedSquare4()
    {
        if (sequence.sequenceArray.getLast() == 3){
            sequence.playAllSquares();
            sequence.addNewSquareToSequence();
        }
        else {
            System.out.println("Lose");
        }
    }
    public void check()
    {
        
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

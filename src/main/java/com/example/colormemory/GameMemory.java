package com.example.colormemory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class GameMemory extends Application
{
    Sequence sequence;

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane pane = new Pane();

        Square square1 = new Square(pane, 85, 50, Color.RED);
        Square square2 = new Square(pane, 210, 50, Color.YELLOW);
        Square square3 = new Square(pane, 85, 180, Color.GREEN);
        Square square4 = new Square(pane, 210, 180, Color.BLUE);

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
            check();
        }

    public void pressedSquare2()
    {

    }

    public void pressedSquare3()
    {

    }

    public void pressedSquare4()
    {

    }
    public void check()
    {
        
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

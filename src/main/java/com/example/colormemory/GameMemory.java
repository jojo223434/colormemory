package com.example.colormemory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class GameMemory extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane pane = new Pane();

        Square square1 = new Square(pane, 85, 50, Color.RED);
        Square square2 = new Square(pane, 210, 50, Color.YELLOW);
        Square square3 = new Square(pane, 85, 180, Color.GREEN);
        Square square4 = new Square(pane, 210, 180, Color.BLUE);

        Sequence sequence = new Sequence(square1, square2, square3, square4);

        sequence.addNewSquareToSequence();

        sequence.playAllSquares();

        Scene scene = new Scene(pane, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Memory Game");
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

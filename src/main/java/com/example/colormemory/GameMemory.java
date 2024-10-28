package com.example.colormemory;

import com.example.colormemory.ScoreCounter;
import com.example.colormemory.Sequence;
import com.example.colormemory.Square;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameMemory extends Application {
    Sequence sequence; // Sekvens af firkanter, som spilleren skal følge
    Square square1, square2, square3, square4, square5, square6, square7, square8, square9; // Ni firkanter til 3x3 layout
    Pane pane; // Pane layout til spillet
    int count = 0; // Holder styr på spillerens fremskridt i den nuværende sekvens

    ScoreCounter scoreCounter; // Til at holde styr på og vise spillerens score

    boolean allSquareCorrect; // Boolean der holder styr på om spilleren vælger rigtigt i sekvensen

    Button startButton = new Button("Start"); // Start/Restart knap

    @Override
    public void start(Stage stage) throws Exception {
        // Opret pane og scene
        pane = new Pane();
        Scene scene = new Scene(pane, 600, 600); // For at tilpasse 3x3 gitter, øg størrelsen på scenen
        scene.setFill(Color.GREEN);
        stage.setScene(scene);
        stage.setTitle("Memory Game");
        stage.show();

        // Tilføj startknappen
        startButton.setLayoutX(250); // X-placering
        startButton.setLayoutY(500); // Y-placering
        pane.getChildren().add(startButton);
        startButton.setOnAction(event -> startGame()); // Start spillet på klik

        scoreCounter = new ScoreCounter(pane);

        // Opret ni firkanter med specifikke positioner og farver
        square1 = new Square(pane, 85, 50, Color.BLACK);
        square2 = new Square(pane, 210, 50, Color.YELLOW);
        square3 = new Square(pane, 335, 50, Color.GREEN);
        square4 = new Square(pane, 85, 180, Color.BLUE);
        square5 = new Square(pane, 210, 180, Color.RED);
        square6 = new Square(pane, 335, 180, Color.WHITE);
        square7 = new Square(pane, 85, 310, Color.ORANGE);
        square8 = new Square(pane, 210, 310, Color.PINK);
        square9 = new Square(pane, 335, 310, Color.CYAN);

        // Initialiser sekvensen med de ni firkanter
        sequence = new Sequence(this, square1, square2, square3, square4, square5, square6, square7, square8, square9);

        // Tilføj klik-handlinger for alle ni firkanter
        square1.setOnMouseClicked(mouseEvent -> pressedSquare(0, square1));
        square2.setOnMouseClicked(mouseEvent -> pressedSquare(1, square2));
        square3.setOnMouseClicked(mouseEvent -> pressedSquare(2, square3));
        square4.setOnMouseClicked(mouseEvent -> pressedSquare(3, square4));
        square5.setOnMouseClicked(mouseEvent -> pressedSquare(4, square5));
        square6.setOnMouseClicked(mouseEvent -> pressedSquare(5, square6));
        square7.setOnMouseClicked(mouseEvent -> pressedSquare(6, square7));
        square8.setOnMouseClicked(mouseEvent -> pressedSquare(7, square8));
        square9.setOnMouseClicked(mouseEvent -> pressedSquare(8, square9));
    }

    public void startGame() {
        sequence.addNewSquareToSequence(); // Tilføj første firkant til sekvensen
        sequence.playAllSquares(); // Afspil sekvensen

        startButton.setVisible(false); // Skjul knappen under spillet
        startButton.setText("Start"); // For at sætte knaptekst til 'Start'

    }

    public void pressedSquare(int squareIndex, Square square) {
        square.scaleSquare(square, true);

        ScheduledExecutorService order = Executors.newScheduledThreadPool(1);

        // Kontrollér om den rigtige firkant blev klikket på
        if (sequence.sequenceArray.get(count) == squareIndex) {
            count++;

            if (count == sequence.sequenceArray.size()) {
                count = 0;
                allSquareCorrect = true;
                sequence.addNewSquareToSequence();
                scoreCounter.addScore();

                order.schedule(() -> {
                    sequence.playAllSquares();
                }, 3, TimeUnit.SECONDS);
            }
        } else {
            tryAgain(); // Hvis forkert firkant klikkes på
        }
    }

    public void tryAgain() {
        allSquareCorrect = false;
        startButton.setText("Restart Game"); // For at sætte knaptekst til 'Restart Game'
        startButton.setVisible(true);
        scoreCounter.setScore(0);

        sequence.sequenceArray.clear(); // Nulstil sekvensen
    }

    public void disableClicks() {
        square1.setDisable(true);
        square2.setDisable(true);
        square3.setDisable(true);
        square4.setDisable(true);
        square5.setDisable(true);
        square6.setDisable(true);
        square7.setDisable(true);
        square8.setDisable(true);
        square9.setDisable(true);
    }

    public void enableClicks() {
        square1.setDisable(false);
        square2.setDisable(false);
        square3.setDisable(false);
        square4.setDisable(false);
        square5.setDisable(false);
        square6.setDisable(false);
        square7.setDisable(false);
        square8.setDisable(false);
        square9.setDisable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

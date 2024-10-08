package com.example.colormemory;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

// Class that manage and displays the player's score
public class ScoreCounter extends Label
{
    private int score; // store the player's score
    private Label scoreLabel; // A label to display the score

    public ScoreCounter(Pane pane)
    {
        this.score = 0; // Score starts at zero

        // Gives a layout, font and text to the label
        scoreLabel = new Label();
        scoreLabel.setLayoutX(160);
        scoreLabel.setLayoutY(20);
        scoreLabel.setFont(new Font("Arial", 20));
        scoreLabel.setText("Score: " + score);
        pane.setStyle("-fx-background-color: grey;"); // Set background color
        pane.getChildren().add(scoreLabel); // Label added to the pane

    }

    public void addScore()
    {
        this.score++;
        System.out.println("Score: " + score);
        scoreLabel.setText("Score: " + score); // Updates the label text
    }

    public int getScore() {
        return this.score; // return current score
    }

    // Method to display and set a specific score
    public void setScore(int score)
    {
        this.score = score;
        scoreLabel.setText("Score: " + score); // Updates label with new score
    }

}

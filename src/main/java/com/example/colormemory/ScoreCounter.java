package com.example.colormemory;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreCounter extends Label
{
    private int score;
    private Label scoreLabel;

    public ScoreCounter(Pane pane)
    {
        this.score = 0;

        scoreLabel = new Label();
        scoreLabel.setLayoutX(200);
        scoreLabel.setLayoutY(20);
        scoreLabel.setFont(new Font("Arial", 20));
        scoreLabel.setText("Score: " + score);
        pane.getChildren().add(scoreLabel);
    }

    public void addScore()
    {
        this.score++;
        System.out.println("Score: " + score);
        scoreLabel.setText("Score: " + score);
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score)
    {
        this.score = score;
        scoreLabel.setText("Score: " + score);
    }

}

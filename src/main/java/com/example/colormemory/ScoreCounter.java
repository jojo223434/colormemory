package com.example.colormemory;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

// Class der administrerer og viser spillerens score
public class ScoreCounter extends Label
{
    private int score;// gemme spillerens score
    private Label scoreLabel; // En etiket til at vise partitur

    public ScoreCounter(Pane pane)
    {
        this.score = 0; // Score starts FOR zero

        // Giver et layout, skrifttype og tekst til etiketten
        scoreLabel = new Label();
        scoreLabel.setLayoutX(160);
        scoreLabel.setLayoutY(20);
        scoreLabel.setFont(new Font("Arial", 20));
        scoreLabel.setText("Score: " + score);
        pane.setStyle("-fx-background-color: grey;"); // Indstil baggrundsfarve
        pane.getChildren().add(scoreLabel); // Etiket tilføjet til ruden

    }

    public void addScore()
    {
        this.score++;
        System.out.println("Score: " + score);
        scoreLabel.setText("Score: " + score); // Updates the label text
    }

    // Metode til at vise og indstille en specifik score
    public void setScore(int score)
    {
        this.score = score;
        scoreLabel.setText("Score: " + score); // Updates label with new score
    }

}
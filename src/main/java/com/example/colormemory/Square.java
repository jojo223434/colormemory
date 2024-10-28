package com.example.colormemory;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Class der repræsenterer hver firkant i spillet
public class Square extends Rectangle
{
    // Konstruktør for Square-klassen
    public Square(Pane pane, int x, int y, Color color)
    {
        // Kald superklassekonstruktøren (Rektangel) for at indstille firkantens position og størrelse
        super(x,y, 100, 100);

        // Tilføj firkanten til ruden, så den er synlig i spillet
        pane.getChildren().add(this);

        // Indstil firkantens farve
        this.setFill(color);
    }

    public Square(Pane pane, int x, int y, java.awt.Color black) {
    }

    // Metode til at skalere terningen og vende tilbage til den oprindelige størrelse
    // // 'mouseEvent' bruges til at bestemme, om skalaen udløses af brugerinteraktion (klik)
    public ScaleTransition scaleSquare(Node node, boolean mouseEvent)
    {
        // Opret en ny ScaleTransition-animation
        ScaleTransition scaleTransition = new ScaleTransition();
        // Indstil animationsinterpolator for jævnere skalering
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        // Indstil varigheden animationen
        scaleTransition.setDuration(Duration.seconds(0.8));
        // Definer skaleringsstørrelsen (forøg med 20 %)
        scaleTransition.setByY(0.2);
        scaleTransition.setByX(0.2);
        // Aktiver auto-reversering, så firkanten vender tilbage til sin oprindelige størrelse
        scaleTransition.setAutoReverse(true);
        // Indstil cyklustællingen til 2, så firkanten skaleres op og ned
        scaleTransition.setCycleCount(2);
        // Vedhæft animationen til firkanten (knudepunktet)
        scaleTransition.setNode(node);

        // Hvis skaleringen udløses af et museklik
        if (mouseEvent)
        {
            // Deaktiver firkanten for at forhindre yderligere klik under animation
            disablePress();

            // genaktiver firkanten, når skaleringsanimationen er færdig
            scaleTransition.setOnFinished(event ->
            {
                enablePress();
            });
            // Start skaleringsanimationen
            scaleTransition.play();
        }

        // Returner SceTransition-objektet
        return scaleTransition;
    }

    // Metode til at aktivere musens interaktion med firkanten
    public void enablePress()
    {
        this.setDisable(false); // Gør firkanten klikbar igen
    }

    // Metode til at deaktivere musens interaktion med firkanten
    public void disablePress()
    {
        this.setDisable(true); // Undgå, at firkanten bliver klikket
    }
}
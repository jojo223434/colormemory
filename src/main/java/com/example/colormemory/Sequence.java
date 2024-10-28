package com.example.colormemory;

import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Sequence
{
    // Gemmer firkanterne som en liste over heltal
    ArrayList<Integer> sequenceArray = new ArrayList<>();

    // Liste for at gemme de tilgængelige firkanter i spillet
    ArrayList<Square> squaresArray = new ArrayList<>();

    // Tilfældig udvælgelse af en firkant til rækkefølgen
    Random randomSquare = new Random();

    // Reference til hovedapplikationen GameMemmory, der bruges til at styre spilflowet (aktiver/deaktiver klik)
    GameMemory gameMemory;

    // Konstruktør, der tager fire firkanter og tilføjer dem til squaresArray-listen
    public Sequence(GameMemory mainApp, Square square1, Square square2, Square square3, Square square4,Square square5,Square square6,Square square7,Square square8,Square square9)
    {
        // Gem referencen til hovedspilapplikationen
        gameMemory = mainApp;

        // Tilføj de fire firkanter til listen over tilgængelige firkanter
        squaresArray.add(square1);
        squaresArray.add(square2);
        squaresArray.add(square3);
        squaresArray.add(square4);
        squaresArray.add(square5);
        squaresArray.add(square6);
        squaresArray.add(square7);
        squaresArray.add(square8);
        squaresArray.add(square9);

    }

    // Metode til at tilføje en ny tilfældigt valgt firkant til sequenceArray
    public void addNewSquareToSequence()
    {
        // Generer et tilfældigt tal mellem 0 og 3 for at vælge en af ​​de fire firkanter
        int randomNumber = randomSquare.nextInt(9);

        // Tilføj det tilfældige tal til sekvensen
        sequenceArray.add(randomNumber);

        // Udskriv det valgte tilfældige tal
        System.out.println("randomNumber: " + randomNumber);
    }

    // Metode til at afspille hele sekvensen afirkanter med animationer
    public void playAllSquares()
    {

        // Opret et sekventielt overgangsobjekt for at afspille animationer efter hinanden
        SequentialTransition sequentialTransition = new SequentialTransition();

        // Gå gennem sequenceArray'et for at få adgang til hver firkants indeks
        for (int i = 0; i < sequenceArray.size(); i++)
        {
            // Få indekset for kvadratet fra sequenceArray
            int x = sequenceArray.get(i);
            // Hent den tilsvarende firkant fra SquaresArray baseret på indekset
            Square square = squaresArray.get(x);

            // Opret en ScaleTransition for den valgte firkant (falsk betyder, at den ikke udløses af en musehændelse)
            ScaleTransition scaleTransition = square.scaleSquare(square, false);

            // Lav en pause mellem hver firkants animation
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));

            // Tilføj både skalaovergangen og pausen til den sekventielle overgang
            sequentialTransition.getChildren().addAll(scaleTransition, pause);
        }

        // Spil hele sekvensen af animationer
        sequentialTransition.play();

        // Deaktiver klik på firkanter, mens sekvensen afspilles
        gameMemory.disableClicks();

        // Når sekvensen er færdig med at spille, skal du aktivere klik igen
        sequentialTransition.setOnFinished(event ->
        {
            gameMemory.enableClicks();
            System.out.println("Finished"); // indikere, at sekvensen er afsluttet, så du kan trykke igen
        });
    }
}
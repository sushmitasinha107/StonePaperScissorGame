package com.sushmitasinha;

public class MyRockPaperScissorsHelper {

    public static void showCurrentScore(int playerCurrentScore, int computerCurrentScore) {
        System.out.println("Player1: "+ playerCurrentScore + "\t Computer: " + computerCurrentScore+ "\n");
    }

    public static void viewGameScores(int playerTotalWins, int computerTotalWins) {
        System.out.println("Total Player Wins : "+ playerTotalWins);
        System.out.println("Total Computer Wins : "+ computerTotalWins);
    }
}

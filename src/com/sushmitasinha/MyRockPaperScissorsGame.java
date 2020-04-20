package com.sushmitasinha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MyRockPaperScissorsGame {
    public enum GameMoves {
        ROCK,
        PAPER,
        SCISSORS
    }
    public enum GameResult {
        WIN,
        LOSS,
        TIE
    }

    private static final int MAX_TURNS = 10;

    private static int playerCurrentScore;
    private static int computerCurrentScore;

    private static int playerTotalWins;
    private static int computerTotalWins;

    private static Random random;
    private BufferedReader bufferedReader;

    public MyRockPaperScissorsGame() {
        playerCurrentScore = 0;
        computerCurrentScore = 0;
        playerTotalWins = 0;
        computerTotalWins = 0;
        random = new Random();
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Starts a new game of MAX_TURNS number of turns. And determines winner based on results from playturn()
     * @throws IOException
     */
    private void startNewGame() throws IOException {
        for(int i=0; i<MAX_TURNS; i++) {
            System.out.print("Turn: " + (i + 1)
                    + "\nRock, paper, scissors, shoot! \t 1. Rock\t 2. Paper\t 3. Scissors\nEnter your choice: ");
            GameResult turnResult = playTurn();
            switch (turnResult) {
                case WIN: System.out.println("You win!");
                    playerCurrentScore++;
                    break;
                case LOSS:
                    System.out.println("You lose!");
                    computerCurrentScore++;
                    break;
                 case TIE:
                     System.out.println("It's a tie!");
                    break;
            }
            MyRockPaperScissorsHelper.showCurrentScore(playerCurrentScore, computerCurrentScore);
        }
        determineGameWinner();
    }

    /**
     * Accept input from the user, validates it.
     * @return maps the option to Enum value in GameMoves
     */
    private GameMoves getPlayerMove() throws IOException {
        int selectedOption = 0;
        do {
            String option = bufferedReader.readLine();
            try {
                selectedOption = Integer.parseInt(option);
            } catch (NumberFormatException ex) {}

            if(!validateOption(selectedOption)) {
                System.out.println("Enter a valid number between 1 and 3");
            }
        } while (!validateOption(selectedOption));

        return GameMoves.values()[selectedOption-1];
    }

    private void resetGame() {
        computerCurrentScore = 0;
        playerCurrentScore = 0;
    }

    /**
     * Determines the winner of the game based on the choise made by player and random move by the computer
     * @return winner of the game based on choice of player and random move made by computer
     */
    private GameResult playTurn() throws IOException {
        GameMoves computerMove = getRandomMove();
        GameMoves playerMove = getPlayerMove();

        System.out.println("Your move: "+ playerMove.toString() + " Computer move: " + computerMove.toString());
        if(computerMove == playerMove)
            return GameResult.TIE;

        switch (playerMove) {
            case ROCK:
                if(computerMove == GameMoves.PAPER) return GameResult.LOSS;
                else return GameResult.WIN;
            case PAPER:
                if(computerMove == GameMoves.SCISSORS) return GameResult.LOSS;
                else return GameResult.WIN;
            case SCISSORS:
                if(computerMove == GameMoves.ROCK) return GameResult.LOSS;
                else return GameResult.WIN;
        }
        return null;
    }

    private boolean validateOption(int option) {
        return option == 1 || option == 2 || option == 3;
    }

    /**
     * @return a valid member of enum GameMoves chosen randomly
     */
    private GameMoves getRandomMove() {
        int selection = random.nextInt(3);
        return GameMoves.values()[selection];
    }

    /**
     * This method determines the game winner after MAX_TURNS turn
     */
    private void determineGameWinner() {
        if (playerCurrentScore > computerCurrentScore) {
            playerTotalWins+=1;
            System.out.println("You Win!");
        } else if (playerCurrentScore < computerCurrentScore) {
            computerTotalWins+=1;
            System.out.println("You Lose!");
        } else {
            System.out.println("It's a Tie!");
        }
    }

    /**
     *
     * @throws IOException
     */
    private void playGame() throws IOException {
        String option;
        System.out.println("Rock Paper Scissors Game");
        do {
            System.out.println("\n*****MENU*****" +
                    "\n1. Start New Game \n2. View Scores \n3. Exit \nEnter your choice(1/2/3): ");
            option = bufferedReader.readLine();
            switch (option) {
                case "1":
                    resetGame();
                    startNewGame();
                    break;
                case "2":
                    MyRockPaperScissorsHelper.viewGameScores(playerTotalWins, computerTotalWins);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Invalid Option Selected. Please select a valid option from the Menu!");
                    break;
            }
        } while(!option.equals("3"));
    }

    public static void main(String[] args) throws IOException {
        MyRockPaperScissorsGame game = new MyRockPaperScissorsGame();
        game.playGame();
    }
}

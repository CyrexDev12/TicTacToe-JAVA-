import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Initalize a new game of tictactoe
        Tictactoe game = new Tictactoe();
        // Initialize a scanner
        Scanner scnr = new Scanner(System.in);

        // Make a while loop that goes until a winning player is found or the game resolves in a tie



        System.out.println("Welcome to Tictactoe, player 1 is X and player 2 is O");
        System.out.println("The game is now starting!");

        int move = 0;
        int currentPlayer = 1;
        int plrChoice = -1;

        while (move == 0) {
        game.printSheet();
        System.out.println("Player " + currentPlayer + " it is your move!\nChoose index (1-9)");
        plrChoice = scnr.nextInt();
        
        game.move(currentPlayer, plrChoice);
        // Get move
        move = game.getMove();

        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }

    }

    switch (move) {
        case 1:
            System.out.println("Congratulations player 1! You have won Tictactoe ");
            break;
        case 2:
            System.out.println("Congratulations player 2! You have won Tictactoe ");
        break;
        default:
            System.out.println("This game has resulted in a tie! ");
        break;
    }

    }


}
import java.util.Scanner;
// V1 - Basic game logic
// V1.1 - Now implemented interface
// V1.2 - Game logic fixed, player can now choose to play against AI, or they can choose to play against a player. 
// V1.3 - The player can now play as 'X' or 'O', AI logic is smarter. 

// Upcoming future: Implement AI player logic 

public class main {
    public static void main(String[] args) {
    // Initalize a new game of tictactoe
      Scanner scnr = new Scanner(System.in);
      System.out.println("Would you like to play AI or multiplayer? \n1. Ai\n2. Multiplayer");

      int plrModeChoice = scnr.nextInt();
      boolean AIplrmode = false;

      switch (plrModeChoice) {
        case 1:
        AIplrmode = true;
          break;
        
          case 2: 
          AIplrmode = false;
      
        default:
        System.out.println("Please try again! Select a choice!!!!!!!! Bruh");
          break;
      }


      new Tictactoe(AIplrmode);
    }

}
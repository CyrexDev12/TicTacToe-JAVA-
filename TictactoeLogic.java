public class TictactoeLogic {
    private int move; 
    private int[][] board;
    private int totalMoves;


    public TictactoeLogic() {
        this.board = new int[3][3]; // Initalizes 3x3 tictactoe board
        this.move = 0; 
        this.totalMoves = 0;
    }

   public void printSheet() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            char symbol;
            if (board[i][j] == 1) {
                symbol = 'X';
            } else if (board[i][j] == 2) {
                symbol = 'O';
            } else {
                symbol = ' ';
            }

            System.out.print(" " + symbol + " ");
            if (j < 2) System.out.print("|");
        }
        System.out.println();
        if (i < 2) System.out.println("---+---+---");
    }
}

    // We will return an int in this method 
    // If we return 0 there is no winner or loser
    // If we return 1 player1 has won the game
    // If we return 2 player 2 has won the game
    // If we return 3 the game is a tie

    public void move(int player, int choice) {
    int row = (choice - 1) / 3;
    int col = (choice - 1) % 3;


    if (choice < 1 || choice > 9) {
        System.out.println("Invalid choice! Please select a number between 1 and 9.");
    }

    if (board[row][col] == 1 || board[row][col] == 2) {
        System.out.println("Position already taken! Try again.");
    }

    board[row][col] = player;
    totalMoves++;
    System.out.println("DEBUG // : Total moves : " + totalMoves);
    // Now check the game status and return
    move = checkGameStatus();
}

    // We need to check Diagonally, horizontally, and vertically in the 2D array 
    public int checkGameStatus() {
        // First check to see if its even possible to win the game currently

        if (totalMoves <= 4) {
            return 0;
        }

        int vertical = checkVertically();
        int horizontal = checkHorizontally();
        int Diagonal = checkDiagonally();


        if (vertical == 0 && horizontal == 0 && Diagonal == 0) {
            return 0;
        }

        if (vertical == 1 || horizontal == 1 || Diagonal == 1) {
            return 1;
        }

      

        if (vertical == 2 || horizontal == 2 || Diagonal == 2) {
            return 2;
        }

        // Else we can assume that it is a tie
        return 3; 

    }


    public int checkHorizontally() {
        for (int i = 0; i < 3; i++) {
         if (board[i][0] == 1 || board[i][0] == 2) {
             if (board[i][0] == 1 && board[i][1] == 1 && board[i][2] == 1) {
                // DEBUG
                
                return 1;
             } else if (board[i][0] == 2 && board[i][1] == 2 && board[i][2] == 1) {
                // DEBUG
                
                return 2;
             }
            }
        }
        return 0;
    }
    
    public int checkVertically() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == 1 || board[0][i] == 2) {
                if (board[0][i] == 1 && board[1][i] == 1 && board[2][i] == 1) {
                // DEBUG
              
                    return 1;
                } else if (board[0][i] == 2 && board[1][i] == 2 && board[2][i] == 2) {
                    // DEBUG
           
                    return 2; 
                }
            }
        }
        return 0; 
    }

    public int checkDiagonally() {
        if (board[1][1] == 1 || board[1][1] == 2 ) {
            if (board[1][1] == 1 && (board[0][0] == 1 && board[2][2] == 1) || (board[0][2] == 1 && board[2][0] == 1)) {
                // DEBUG
               
                return 1;
            } else if (board[1][1] == 2 && (board[0][0] == 2 && board[2][2] == 2) || (board[0][2] == 2 && board[2][0] == 2)) {
                // DEBUG
               
                return 2;
            }
        }
        return 0;
    }

    public void resetBoard() {
        totalMoves = 0; 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }



    // AI Logic 
    // We will use probability for the AI, so sometimes it may mess up. 
    // We implement checkers, and then the AI will choose wether to attack or to defend
    public int AImove() {
        if (totalMoves <= 3) {
            return (int) (Math.random() * 9) + 1;
        }
        int defendVertically = AIcheckDefendVertically();
        int defendHorizontally = AIcheckDefendHorizontally();
        int defendDiagonally = AICheckDefendDiagonally();


        // If attack is true the AI attempts to find positions where it can win
        if (defendVertically == -1 && defendHorizontally == -1 && defendDiagonally == -1) {
             return (int) (Math.random() * 9) + 1;
        }

        else if (defendVertically > defendDiagonally && defendVertically > defendHorizontally) {
            return defendVertically;
        }

        else if (defendDiagonally > defendHorizontally && defendDiagonally > defendVertically) {
            return defendDiagonally;
        }
        else {
            return defendHorizontally;
        }


    }



    // These methods will return -1 if no position if found, else it will return the index on the board they need to defend
    public int AIcheckDefendVertically() {
        for (int i = 0; i < 3; i++) {
            // Go through all the rows and check all the possible ways to win vertically
          if (board[0][i] == 1 && board[2][i] == 1 && board[1][i] != 2) {
                return (1 * 3) + (i + 1);
            }
            if (board[0][i] == 1 && board[1][i] == 1 && board[2][i] != 2) {
                return (2 * 3) + (i + 1);
            }
            if (board[2][i] == 1 && board[1][i] == 1 && board[0][i] != 2) {
                return (i + 1);
            }
        }
        return -1;
    }

    public int AIcheckDefendHorizontally() {
        for (int i = 0; i < 3; i++) {
        if (board[i][0] == 1 && board[i][2] == 1 && board[i][1] != 2) {
            return (i * 3) + 2;
        }
        if (board[i][0] == 1 && board[i][1] == 1 && board[i][2] != 2) {
            return (i * 3) + 1;
        }
        if (board[i][2] == 1 && board[1][i] == 1 && board[i][0] != 2) {
            return (i * 3) + 2;
        }
    }
    return -1;
    }

    public int AICheckDefendDiagonally() {
        if (board[1][1] == 1 && board[0][0] == 1 && board[2][2] != 2) {
            return 9;
        }
        if (((board[0][0] == 1 && board[2][2] == 1) || (board[2][0] == 1 && board[0][2] == 1)) && board[1][1] != 2) {
            return 5;
        }
        if (board[1][1] == 1 && board[2][2] == 1 && board[0][0] != 2) {
            return 1;
        }
        if (board[2][0] == 1 && board[1][1] == 1 && board[0][2] != 2) {
            return 3;
        }
        if (board[0][2] == 1 && board[1][1] == 1 && board[2][0] != 2) {
            return 7;
        }
        return -1;
    }


    // Getters

    public int getMove() {
        return move;
    }






}

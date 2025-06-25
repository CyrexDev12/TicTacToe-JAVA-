public class Tictactoe {
    private int move; 
    private int[][] board;
    private int player1; 
    private int player2; 
    private int totalMoves;


    public Tictactoe() {
        this.board = new int[3][3]; // Initalizes 3x3 tictactoe board
        this.move = 0; 
        this.player1 = 1; 
        this.player2 = 2; 
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

    //TODO: We need to handle invalid choices or taken positions

    if (choice < 1 || choice > 9) {
        System.out.println("Invalid choice! Please select a number between 1 and 9.");
    }

    if (board[row][col] == 1 || board[row][col] == 2) {
        System.out.println("Position already taken! Try again.");
    }

    board[row][col] = player;
    totalMoves++;
    // Now check the game status and return
    move = checkGameStatus();
}

    // We need to check Diagonally, horizontally, and vertically in the 2D array 
    public int checkGameStatus() {
        // First check to see if its even possible to win the game currently

        if (totalMoves < 5) {
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

      

        if (vertical == 1 || horizontal == 1 || Diagonal == 1) {
            return 2;
        }

        // Else we can assume that it is a tie
        return 3; 

    }


    public int checkVertically() {
        for (int i = 0; i < 3; i++) {
         if (board[i][0] == 1 || board[i][0] == 2) {
             if (board[i][1] == 1 && board[i][2] == 1) {
                return 1;
             } else if (board[i][1] == 2 && board[i][2] == 2) {
                return 2;
             }
            }
        }
        return 0;
    }

    public int checkHorizontally() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == 1 || board[0][i] == 2) {
                if (board[1][i] == 1 && board[2][i] == 1) {
                    return 1;
                } else if (board[1][i] == 2 && board[2][i] == 2) {
                    return 2; 
                }
            }
        }
        return 0; 
    }

    public int checkDiagonally() {
        if (board[1][1] == 1 || board[1][1] == 2 ) {
            if ((board[0][0] == 1 && board[2][2] == 1) || (board[0][2] == 1 && board[2][0] == 1)) {
                return 1;
            } else if ((board[0][0] == 2 && board[2][2] == 2) || (board[0][2] == 2 && board[2][0] == 2)) {
                return 2;
            }
        }
        return 0;
    }

    // Getters

    public int getMove() {
        return move;
    }






}

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tictactoe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private TictactoeLogic logic = new TictactoeLogic();
    private int currentPlayer = 1; // Initalize current player as 1 to start off
    private boolean AIplrEnabled = false; 

    public Tictactoe(boolean AIplrEnabled) {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
        this.AIplrEnabled = AIplrEnabled;
        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial" , Font.PLAIN, 60));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }


    // Main setup 
    // Player will be able to choose wether they want to play as 'X' or 'O', and they can either play a friend or they can play AI
    public void mainSetup() {
        return;
    }


    // Override method 
   public void actionPerformed(ActionEvent e) {
    // Get the clicked button
    JButton clicked = (JButton) e.getSource();

    // Loop through the board to find which button was clicked
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (buttons[i][j] == clicked && clicked.getText().equals("")) {
                int choice = i * 3 + j + 1;
                logic.move(currentPlayer, choice);
                clicked.setText(currentPlayer == 1 ? "X" : "O");

                int status = logic.getMove();
                if (status == 1 || status == 2) {
                    JOptionPane.showMessageDialog(this, "Player " + status + " wins!");
                    resetBoard();
                    return;
                } else if (status == 3) {
                    JOptionPane.showMessageDialog(this, "It's a tie!");
                    resetBoard();
                    return;
                }

                // Switch player
                currentPlayer = (currentPlayer == 1) ? 2 : 1;

                // If AI is enabled and it's now AI's turn
                if (AIplrEnabled && currentPlayer == 2) {
                    int aiChoice = logic.AImove();

                    int row = (aiChoice - 1) / 3;
                    int col = (aiChoice - 1) % 3;
                    
                    while (buttons[row][col].equals("X")) {
                        aiChoice = logic.AImove();
                        row = (aiChoice - 1) / 3;
                        col = (aiChoice - 1) % 3;
                    }
                    System.out.println("DEBUG //: Ai move : " + aiChoice);

                    logic.move(currentPlayer, aiChoice);

                    buttons[row][col].setText("O");

                    status = logic.getMove();
                    // DEBUG
                    if (status == 2) {
                        JOptionPane.showMessageDialog(this, "Player 2 (AI) wins!");
                        resetBoard();
                    } else if (status == 3) {
                        JOptionPane.showMessageDialog(this, "It's a tie!");
                        resetBoard();
                    } else {
                        currentPlayer = 1; // Back to human
                    }
                }
                
                System.out.println("DEBUG :// Current player : " + currentPlayer);
                return;
            }
        }
    }
}


    private void resetBoard() {
        logic.resetBoard();
        currentPlayer = 1; 
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
            }
        }
    }








}

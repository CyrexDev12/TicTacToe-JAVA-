import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tictactoe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private TictactoeLogic logic = new TictactoeLogic();
    private int currentPlayer = 1; // Initalize current player as 1 to start off

    public Tictactoe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();

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


    // Override method 
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

         // System.out.println("DEBUG // : Current Player: " + currentPlayer);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clicked) {
                    int choice = i * 3 + j + 1; 
                    // System.out.println("DEBUG || Choice : " + choice);
                    logic.move(currentPlayer, choice);

                    if (clicked.getText().equals("")) {
                        clicked.setText(currentPlayer == 1 ? "X" : "O"); // If the current player is 1 it will be an X, else it will be O
                    } 
                    int status = logic.getMove(); // Get the current state of the game

                    if (status == 1 || status == 2) {
                        JOptionPane.showMessageDialog(this, "Player " + status + " wins!");
                        resetBoard();
                    } else if (status == 3) {
                        JOptionPane.showMessageDialog(this, "It's a tie!");
                    } else {
                        currentPlayer = (currentPlayer == 1) ? 2 : 1;
                    }

                    // Debug print board
                   // logic.printSheet();
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

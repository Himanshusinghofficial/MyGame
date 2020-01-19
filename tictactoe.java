package Asummerprojests;

import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class tictactoe extends JFrame implements ActionListener {
	public static int Board_size = 3;

	public static enum GameStatus {
		INCOMPLETE, XWIN, ZWIN, TIE
	}

	private JButton[][] Buttons = new JButton[Board_size][Board_size];
	boolean crossturn = true;

	tictactoe() {
		super.setTitle("TicTacToe");
		super.setSize(800, 800);
		GridLayout Grid = new GridLayout(Board_size, Board_size);
		super.setLayout(Grid);
		Font font = new Font("Comic Sans", 1, 150);
		for (int row = 0; row < Board_size; row++) {
			for (int col = 0; col < Board_size; col++) {
				JButton button = new JButton("");
				Buttons[row][col] = button;
				button.setFont(font);
				super.getColorModel();
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedButton = (JButton) e.getSource();
		makeMove(clickedButton);
		GameStatus gs = this.getGameStatus();
		if (gs == GameStatus.INCOMPLETE) {
			return;
		}
		declarewinner(gs);
		int choice=JOptionPane.showConfirmDialog(this, "Do you want to restart the game:");
       if(choice==JOptionPane.YES_OPTION)
       {
    	   for(int row=0;row<Board_size;row++)
    	   {
    		   for(int col=0;col<Board_size;col++)
    		   {
    			   Buttons[row][col].setText("");
    		   }
    			   
    	   }
    	   crossturn=true;
       }
       else
       {
    	   super.dispose();
       }
    	   
	}

	private void makeMove(JButton clickedButton) {
		String btntext = clickedButton.getText();
		if (btntext.length() > 0) {
			JOptionPane.showMessageDialog(this, "Invalid Move");

		} else {
			if (crossturn) {
				clickedButton.setText("X");
			} else {
				clickedButton.setText("0");
			}
			crossturn = !crossturn;

		}
	}

	private GameStatus getGameStatus() {
		String text1 = "", text2 = "";
		int row = 0, col = 0;
		// text inside row
		row = 0;
		while (row < Board_size) {
			col = 0;
			while (col < Board_size - 1) {
				text1 = Buttons[row][col].getText();
				text2 = Buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				col++;
			}
			if (col == Board_size - 1) {
				if (text1.equals("X")) {
					return GameStatus.XWIN;
				} else {
					return GameStatus.ZWIN;
				}
			}
			row++;
		}
		// text inside col
		col = 0;
		while (col < Board_size) {
			row = 0;
			while (row < Board_size - 1) {
				text1 = Buttons[row][col].getText();
				text2 = Buttons[row + 1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				row++;
			}
			if (row == Board_size - 1) {
				if (text1.equals("X")) {
					return GameStatus.XWIN;
				} else {
					return GameStatus.ZWIN;
				}
			}
			col++;
		}

		// test in diagonal 1
		row = 0;
		col = 0;
		while (row < Board_size - 1) {
			text1 = Buttons[row][col].getText();
			text2 = Buttons[row + 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row++;
			col++;
		}
		if (row == Board_size - 1) {
			if (text1.equals("X")) {
				return GameStatus.XWIN;
			} else {
				return GameStatus.ZWIN;
			}

		}
		// test for diagnol2
		row = Board_size - 1;
		col = 0;
		while (row > 0) {
			text1 = Buttons[row][col].getText();
			text2 = Buttons[row - 1][col + 1].getText();
			if (!text1.equals(text2) || text1.length() == 0) {
				break;
			}
			row--;
			col++;
		}
		if (row == 0) {
			if (text1.equals("X")) {
				return GameStatus.XWIN;
			} else {
				return GameStatus.ZWIN;
			}

		}

		String txt = "";
		for (row = 0; row < Board_size; row++) {
			for (col = 0; col < Board_size; col++) {
				txt = Buttons[row][col].getText();
				if (txt.length() == 0) {
					return GameStatus.INCOMPLETE;
				}
			}
		}
		return GameStatus.TIE;
	}

	private void declarewinner(GameStatus gs) {
		if (gs == GameStatus.XWIN) {
			JOptionPane.showMessageDialog(this, "X WIN");
		} else if (gs == GameStatus.ZWIN) {
			JOptionPane.showMessageDialog(this, "Z WIN");
		} else {
			JOptionPane.showMessageDialog(this, "It is a tie");
		}

	}

} 
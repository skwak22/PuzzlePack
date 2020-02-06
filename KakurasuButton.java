import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KakurasuButton extends JButton implements ActionListener {
    private int value;
    private int x;
    private int y;
    private int[][] theGrid;
    public KakurasuButton(int x, int y, int[][] theGrid) {
        this.addActionListener(this);
        this.value = 0;
        this.x = x;
        this.y = y;
        this.theGrid = theGrid;
        this.setBackground(Color.GRAY);
        this.setOpaque(true);
    }

    public void set() {
        if (theGrid[x][y] == 0) {
            this.setText("");
            this.setBackground(Color.GRAY);
        } else if (theGrid[x][y] == 1) {
            this.setText("X");
            this.setBackground(Color.BLACK);
        }
        else {
            this.setText("");
            this.setBackground(Color.white);
        }
    }

    public void actionPerformed(ActionEvent e) {
        value++;
        value = value%3;
        theGrid[x][y] = value;
        if (value == 1) {
            this.setText("X");
            this.setBackground(Color.BLACK);
        }
        else if (value == 2){
            this.setText("");
            this.setBackground(Color.WHITE);
        }
        else {
            this.setText("");
            this.setBackground(Color.GRAY);
        }

        gridPrint(theGrid);
    }

    public static void gridPrint(int[][] grid) {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid.length; ++j) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


    }
}

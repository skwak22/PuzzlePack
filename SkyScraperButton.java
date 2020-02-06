import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkyScraperButton extends JButton implements ActionListener {
    private int value;
    private int x;
    private int y;
    private int[][] theGrid;

    public SkyScraperButton(int x, int y, int[][] theGrid) {
        this.addActionListener(this);
        this.value = 0;
        this.x = x;
        this.y = y;
        this.theGrid = theGrid;
        this.setBackground(Color.PINK);
    }

    public void set() {
        if (theGrid[x][y] == 0) {
            this.setText("");
        } else if (theGrid[x][y] > 0) {
            this.setText("" + theGrid[x][y]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        value++;
        value = value%6;
        theGrid[x][y] = value;
        if (value != 0)
            this.setText("" + value);
        else this.setText("");

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

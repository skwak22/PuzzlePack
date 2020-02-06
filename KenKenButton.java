import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KenKenButton extends JButton implements ActionListener {
    private int value;
    private int x;
    private int y;
    private int[][] theGrid;
    private int color;
    private String ogText;
    public KenKenButton(String s, int color, int x, int y, int[][] theGrid) {
        this.color = color;
        ogText = s;
        this.setText(s);
        this.addActionListener(this);
        this.value = 1;
        this.x = x;
        this.y = y;
        this.theGrid = theGrid;
        this.setBackground(Color.cyan);
        setColors();
        setText(ogText + "   1");
        theGrid[x][y] = 1;

    }

    private void setColors() {
        if (color == 1)
            this.setBackground(Color.PINK);
        else if (color == 2)
            this.setBackground(Color.cyan);
        else if (color == 3)
            this.setBackground(Color.yellow);
        else if (color == 4)
            this.setBackground(Color.orange);
        else if (color == 5)
            this.setBackground(Color.green);
        else if (color == 6)
            this.setBackground(Color.lightGray);
        else if (color == 7)
            this.setBackground(Color.magenta);
        else if (color == 8)
            this.setBackground(Color.BLUE);
        else if (color == 9)
            this.setBackground(Color.white);
        else if (color == 10)
            this.setBackground(Color.cyan);
        else if (color == 11)
            this.setBackground(Color.yellow);
        else if (color == 12)
            this.setBackground(Color.PINK);
    }

    public void set() {
            this.setText(ogText +"  "+theGrid[x][y]);

    }

    public void actionPerformed(ActionEvent e) {
        value++;
        value = value%6;
        if (value == 0)
            value = 1;
        theGrid[x][y] = value;
        this.setText(ogText + "  " + value);

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

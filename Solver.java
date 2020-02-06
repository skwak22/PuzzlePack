import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Solver extends JButton implements ActionListener {
    private Color c;
    private TheGame game;
    private int[] vals;
    private int[][] theGrid;
    public Solver (TheGame game) {
        this.setText("SOLVE");
        this.game = game;
        this.theGrid = game.getGrid();
        this.vals = game.getPossibles();
        addActionListener(this);
        this.setBackground(Color.cyan);
    }

    public boolean label(int[][] theGrid ,TheGame game, int row, int col, int[] vals ) {
        if (row == theGrid.length) {
            return game.checkAllConstraints();
        }
        for (int v: vals) {
            theGrid[row][col] = v;
            boolean check =game.checkConstraints();
            if (check) {
                int newColumn = col + 1;
                int newRow = row;
                if (newColumn == theGrid[row].length) {
                    newColumn = 0;
                    newRow = row + 1;
                }
                System.out.print(theGrid[row][col]);
                if (label(theGrid,game,newRow,newColumn,vals)) {
                    return true;
                }
            }
        }
    theGrid[row][col] = 0;
    return false;
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
    public void actionPerformed(ActionEvent e) {
        game.clearGrid();
        boolean result = label(theGrid, game, 0, 0, vals);
        gridPrint(theGrid);

        if (result) {
            game.setGrid();
        }
    }
}


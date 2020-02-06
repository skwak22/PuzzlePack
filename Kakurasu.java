import javax.swing.*;
import java.awt.*;

public class Kakurasu extends JFrame implements TheGame{

    private MainFrame an;
    private int[] topRow = {1,2,3,4,5};
    private int[] firstCol = {1,2,3,4,5};
    private int[] bottomRow = {5,3,1,2,2};
    private int[] lastCol = {2,2,1,2,3};
    private int[] rowSums = {0,0,0,0,0};
    private int[] colSums = {0,0,0,0,0};
    private int[][] theGrid = new int[5][5];
    private KakurasuButton[][] buttons;

    public Kakurasu (MainFrame an) {this.an = an;}
    public void createGame() {
    // Exit when window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container ct = getContentPane();

        // set the layout manager to a grid 0 rows by 3 columns
        // (0 means however many you need)
            ct.setLayout(new BoxLayout(ct, BoxLayout.Y_AXIS));
        JLabel message = new JLabel("Kakurasu");
            message.setAlignmentX(Component.CENTER_ALIGNMENT);
            ct.add(message);
        JPanel grid = new JPanel(new GridLayout(getRow()+2,getColumn()+2));
            grid.setAlignmentX(Component.CENTER_ALIGNMENT);
        int buttonCount = ((getRow() + 2) * (getColumn() + 2));

        buttons = new KakurasuButton[getRow()][getColumn()];
        int[] count = {0,0,0,0};
            for (int i = 0; (i*i)< buttonCount; i++) {
            for (int j = 0; (j*j)< buttonCount; j++) {
                if (i == 0 || j == 0 || getRow()+1 == i || getColumn()+1 == j ) {
                    if ((i == 0 && j == 0) || (i == getRow()+1 && j == getColumn()+1)
                            || (i == 0 && j == getColumn()+1) || (i == getRow()+1 && j == 0)) {
                        message = new JLabel("");
                        grid.add(message);
                    }
                    else{
                        if (i == 0) {
                            message = new JLabel("           "+topRow[count[0]]);
                            grid.add(message);
                            count[0]++;
                        }
                        else if (j == 0) {
                            message = new JLabel("           "+firstCol[count[1]]);
                            grid.add(message);
                            count[1]++;
                        }
                        else if (i == 6) {
                            message = new JLabel("           "+bottomRow[count[2]]);
                            grid.add(message);
                            count[2]++;
                        }
                        else if (j == 6) {
                            message = new JLabel("           "+lastCol[count[3]]);
                            grid.add(message);
                            count[3]++;
                        }

                    }
                }
                else {
                    buttons[i-1][j-1] = new KakurasuButton(i-1,j-1,theGrid);
                    grid.add(buttons[i-1][j-1]);

                }

            }
        }
            ct.add(grid);
        Solver button = new Solver( this);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            ct.add(button);





    }

    private int getRow() {
        return 5;
    }

    private int getColumn() {
        return 5;
    }


    public int[][] getGrid() {
        return theGrid;
    }

    public int[] getPossibles() {
        return new int[]{1,2};
    }

    public void clearGrid(){
        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid.length;j++){
                theGrid[i][j] = 0;
            }
        }
    }



    public boolean checkAllConstraints() {return checkConstraints();}


    private boolean checkRow() {
        for(int i=0;i<theGrid.length;i++) {
            int sumSoFar =0;
            for (int j=0;j<theGrid.length;j++) {
                if (theGrid[i][j] == 1) {
                    sumSoFar += (j+1);
                }
            }
            if (sumSoFar != lastCol[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCol() {
        for(int i=0;i<theGrid.length;i++) {
            int sumSoFar =0;
            for (int j=0;j<theGrid.length;j++) {
                if (theGrid[j][i] == 1) {
                    sumSoFar += (j+1);
                }
            }
            if (sumSoFar != bottomRow[i]) {
                return false;
            }
        }
        return true;
    }


    public boolean checkConstraints() {return checkRow() && checkCol();}

    public void setGrid() {
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                buttons[i][j].set();
            }
        }
    }
}

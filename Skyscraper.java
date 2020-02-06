import java.awt.*;
import javax.swing.*;


public class Skyscraper extends JFrame implements TheGame{

    private MainFrame an;
    private int[] topRow = {2,3,3,3,1};
    private int[] bottomRow = {3,2,1,3,3};
    private int[] firstCol = {2,1,2,2,3};
    private int[] lastCol = {1,3,2,4,2};
    private int[][] theGrid = new int[5][5];
    private SkyScraperButton[][] buttons;

    public Skyscraper (MainFrame an) {this.an = an;}
    public void createGame() {
        // Exit when window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container ct = getContentPane();

        // set the layout manager to a grid 0 rows by 3 columns
        // (0 means however many you need)
        ct.setLayout(new BoxLayout(ct, BoxLayout.Y_AXIS));
        JLabel message = new JLabel("SkyScraper");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        ct.add(message);
        JPanel grid = new JPanel(new GridLayout(getRow()+2,getColumn()+2));
        grid.setAlignmentX(Component.CENTER_ALIGNMENT);
        int buttonCount = ((getRow() + 2) * (getColumn() + 2));

        buttons = new SkyScraperButton[getRow()][getColumn()];
        int count[] = {0,0,0,0};
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
                    buttons[i-1][j-1] = new SkyScraperButton(i-1,j-1,theGrid);
                    grid.add(buttons[i-1][j-1]);

                }

            }
        }
        ct.add(grid);
        Solver button = new Solver( this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        ct.add(button);



    }


    public int[][] getGrid() {
        return theGrid;
    }

    public boolean checkSolution() {
        int[][] solutionGrid = new int[5][5];
        Solver s = new Solver(this);
        boolean result = s.label(solutionGrid, this, 0, 0, getPossibles());
        if (compareGrids(solutionGrid,theGrid)) return true;
        else return false;
        }



    private boolean compareGrids(int[][] g, int[][] z) {
        for (int i=0;i<g.length;i++){
            for (int j=0;j<g.length;j++) {
                System.out.print(g[i][j]);
                if (g[i][j] != z[i][j]) return false;
            }
        }
        return true;
    }

    public int[] getPossibles() {
        return new int[]{1, 2, 3, 4, 5};
    }

    public boolean checkAllConstraints() {/*
        if (!checkRepeats()) return false;
        int[][] solutionGrid = new int[5][5];
        Solver s = new Solver("",this);
        boolean result = s.label(solutionGrid, this, 0, 0, getPossibles());
        if (result) {
            if (compareGrids(solutionGrid,theGrid)) return true;
            else return false;
        }
        return false;
    }

    private boolean compareGrids(int[][] g, int[][] z) {
        for (int i=0;i<g.length;i++){
            for (int j=0;j<g.length;j++) {
                if (g[i][j] != z[i][j]) return false;
            }
        }
        return true;*/
        return checkConstraints();
    }

    public boolean checkConstraints() {
        return checkFirstView() && checkTopView() && checkRepeats() && checkLastView() && checkBottomView();
    }

    private boolean checkFirstView () {
        for (int i = 0; i < theGrid.length; i++) {
            int tallestSoFar = 0;
            int count = 0;
            for (int j = 0; j < theGrid.length; j++) {
                if (theGrid[i][j] > tallestSoFar) {
                    count++;
                    tallestSoFar = theGrid[i][j];
                }
            }
            if (count > firstCol[i]) {
                return false;
            }
        }
        return true;

        //iterate through the grid and makes sure that the seen buildings are less than the targets.

    }

    private boolean checkLastView () {
        for (int i = 0; i < theGrid.length; i++) {
            int tallestSoFar = 0;
            int count = 0;
            for (int j = theGrid.length-1; j >= 0; j--) {
                if (theGrid[i][j] > tallestSoFar) {
                    count++;
                    tallestSoFar = theGrid[i][j];
                }
            }
            if (count > lastCol[i] && theGrid[i][theGrid[i].length-1] != 0) {
                return false;
            }
        }
        return true;

        //iterate through the grid and makes sure that the seen buildings are less than the targets.

    }
    private boolean checkTopView () {
        for (int i = 0; i < theGrid.length; i++) {
            int tallestSoFar = 0;
            int count = 0;
            for (int j = 0; j < theGrid[i].length; j++) {
                if (theGrid[j][i] > tallestSoFar) {
                    count++;
                    tallestSoFar = theGrid[j][i];
                }
            }
            if (count > topRow[i]) {
                return false;
            }
        }
        return true;

        //iterate through the grid and makes sure that the seen buildings are less than the targets.

    }
    private boolean checkBottomView () {
        for (int i = 0; i < theGrid[0].length; i++) {
            int tallestSoFar = 0;
            int count = 0;
            for (int j = theGrid.length-1; j >= 0; j--) {
                if (theGrid[j][i] > tallestSoFar) {
                    count++;
                    tallestSoFar = theGrid[j][i];
                }
            }
            if (count > bottomRow[i] && theGrid[theGrid.length-1][i] != 0) {
                return false;
            }
        }
        return true;

        //iterate through the grid and makes sure that the seen buildings are less than the targets.

    }


    private boolean checkRepeats() {
        for (int i = 0; i < theGrid.length; i++) {
            int[] num = new int[6];
            for (int j = 0; j < theGrid.length; j++) {
                if (theGrid[i][j] != -1) {
                    num[theGrid[i][j]]++;
                }
            }
            for (int z = 1; z < num.length; z++) {
                if (num[z] > 1) {
                    return false;
                }
            }
        }
        for (int j = 0; j < theGrid.length; j++) {
            int[] num = new int[6];
            for (int i = 0; i < theGrid.length; i++) {
                if (theGrid[i][j] != -1) {
                    num[theGrid[i][j]]++;
                }
            }
            for (int z = 1; z < num.length; z++) {
                if (num[z] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFilled() {
        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid.length;j++){
                if (theGrid[i][j] >= 1 && theGrid[i][j] <= 5);
                else return false;
            }
        }
        return true;
    }

    public void clearGrid(){
        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid.length;j++){
                theGrid[i][j] = 0;
            }
        }
    }

    public void setGrid() {
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                buttons[i][j].set();
            }
        }
    }

    private int getRow() {
        return 5;
    }

    private int getColumn() {
        return 5;
    }


}

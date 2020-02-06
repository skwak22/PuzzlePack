import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class KenKen extends JFrame implements TheGame{

    private MainFrame an;
    private char[][] operatorGrid = new char[5][5];
    private int[][] puzzleGrid = new int[5][5];
    private int[][] colorGrid = new int[5][5];
    private int[][] theGrid = new int[5][5];


    private KenKenButton[][] buttons;

    public KenKen (MainFrame an) throws FileNotFoundException {
        File text = new File("C:\\Users\\sungh\\Desktop\\ProjectScratch\\src\\KenKen1.txt");
        Scanner scanner = new Scanner(text);
        for (int i=0;i<puzzleGrid.length;i++) {
            for(int j=0; j<puzzleGrid.length; j++){
                puzzleGrid[i][j] = scanner.nextInt();
            }
        }

        for (int i=0;i<colorGrid.length;i++) {
            for(int j=0; j<colorGrid.length; j++){
                colorGrid[i][j] = scanner.nextInt();
            }
        }

        for (int i=0;i<colorGrid.length;i++) {
            for (int j=0;j<colorGrid.length;j++) {
                String s = scanner.next();
                operatorGrid[i][j] = s.charAt(0);
            }
        }

        for (int i = 0; i < puzzleGrid.length; ++i) {
            for (int j = 0; j < puzzleGrid.length; ++j) {
                if (puzzleGrid[i][j] != 0) {
                    System.out.print(puzzleGrid[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();




        this.an = an;

    }
    public void createGame() {
        // Exit when window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container ct = getContentPane();

        // set the layout manager to a grid 0 rows by 3 columns
        // (0 means however many you need)
        ct.setLayout(new BoxLayout(ct, BoxLayout.Y_AXIS));
        JLabel message = new JLabel("KenKen");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        ct.add(message);
        JPanel grid = new JPanel(new GridLayout(getRow()+2,getColumn()+2));
        grid.setAlignmentX(Component.CENTER_ALIGNMENT);
        int buttonCount = ((getRow() + 2) * (getColumn() + 2));

        buttons = new KenKenButton[getRow()][getColumn()];
        for (int i = 0; (i*i)< buttonCount; i++) {
            for (int j = 0; (j*j)< buttonCount; j++) {
                if (i == 0 || j == 0 || getRow()+1 == i || getColumn()+1 == j ) {
                    if ((i == 0 && j == 0) || (i == getRow()+1 && j == getColumn()+1)
                            || (i == 0 && j == getColumn()+1) || (i == getRow()+1 && j == 0)) {
                        message = new JLabel("");
                        grid.add(message);
                    }
                    else{
                            message = new JLabel("           ");
                            grid.add(message);

                    }
                }
                else {
                    if (puzzleGrid[i-1][j-1]==0)
                        buttons[i-1][j-1] = new KenKenButton("",colorGrid[i-1][j-1],i-1,j-1,theGrid);
                    else
                        buttons[i-1][j-1] = new KenKenButton("["+puzzleGrid[i-1][j-1]+"]*",colorGrid[i-1][j-1],i-1,j-1,theGrid);
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

    public boolean checkSolution() {
        return false;
    }

    private boolean checkColorMult(int val, int color) {
        int mult = 1;
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                if (colorGrid[i][j] == color) {
                    mult *= theGrid[i][j];
                }
            }
        }
        System.out.println(mult);
        if (val<mult) return false;
        return true;
    }
    private boolean checkMult() {
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                if (operatorGrid[i][j] == '*') {
                    System.out.println(puzzleGrid[i][j]);
                    if (!checkColorMult(puzzleGrid[i][j],colorGrid[i][j]))
                        return false;
                }
            }
        }
        return true;
    }

    private boolean checkColorDiv(int val, int color) {
        int[] values = new int[2];
        double mult = 1;
        int count =0;
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                if (colorGrid[i][j] == color) {
                    mult = theGrid[i][j];
                    values[count] = theGrid[i][j];
                    count++;
                }
            }
        }
        System.out.println(mult);
        if (values[0]/values[1] != val && values[1]/values[0] != val) return false;
        return true;
    }

    private boolean checkAddition() {
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                if (operatorGrid[i][j] == '+') {
                    System.out.println(puzzleGrid[i][j]);
                    if (!checkColorAdd(puzzleGrid[i][j],colorGrid[i][j]))
                        return false;
                }
            }
        }
        return true;

    }

    private boolean checkColorAdd(int val, int color) {
        int sum = 1;
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                if (colorGrid[i][j] == color) {
                    sum += theGrid[i][j];
                }
            }
        }
        System.out.println(sum);
        if (val>sum) return false;
        return true;
    }

    private boolean checkDiv() {
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                if (operatorGrid[i][j] == '÷') {
                    System.out.println(puzzleGrid[i][j]);
                    if (!checkColorDiv(puzzleGrid[i][j],colorGrid[i][j]))
                        return false;
                }
            }
        }
        return true;
    }
    private boolean checkRepeats() {
        for (int i = 0; i < theGrid.length; i++) {
            int[] c = new int[6];
            for (int j = 0; j < theGrid.length; j++) {
                if (theGrid[i][j] != -1) {
                    c[theGrid[i][j]]++;
                }
            }
            for (int z = 1; z < c.length; z++) {
                if (c[z] > 1) {
                    return false;
                }
            }
        }
        for (int j = 0; j < theGrid.length; j++) {
            int[] c = new int[6];
            for (int i = 0; i < theGrid.length; i++) {
                if (theGrid[i][j] != -1) {
                    c[theGrid[i][j]]++;
                }
            }
            for (int z = 1; z < c.length; z++) {
                if (c[z] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getGrid() {
        return theGrid;
    }

    public int[] getPossibles() {
        return new int[] {1,2,3,4,5};
    }

    public void clearGrid(){
        for (int i=0;i<theGrid.length;i++) {
            for (int j=0;j<theGrid.length;j++){
                theGrid[i][j] = 0;
            }
        }
    }

    public boolean checkAllConstraints() {return checkRepeats();}

    public boolean checkConstraints() {return checkRepeats() && checkMult();}

    public void setGrid() {
        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid.length; j++) {
                buttons[i][j].set();
            }
        }
    }
}

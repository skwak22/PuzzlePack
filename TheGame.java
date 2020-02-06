public interface TheGame {

    void createGame();

    int[][] getGrid();

    int[] getPossibles();

    void clearGrid();

    boolean checkAllConstraints();

    boolean checkConstraints();

    void setGrid();

}

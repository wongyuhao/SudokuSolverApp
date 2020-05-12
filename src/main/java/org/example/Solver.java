package org.example;

import java.util.HashSet;
import java.util.Set;

public class Solver {
   private int[][]grid = new int[9][9];
   private int[][] initialGrid= new int[9][9];;


   public Solver(int[][]grid){
       cloneMatrix(this.grid,grid);
       cloneMatrix(this.initialGrid,grid);
   }


    public  int[][] getGrid(){
        return this.grid;
    }

    public boolean solve(){
       if(valid_board(this.grid)){
           solveAlgorithm(this.grid);
           System.out.println("solve():Is valid");
           printGrid();
           return true;

       }else{
           System.out.println("solve():Not valid");
           printGrid();
           return false;
       }


    }

    public  boolean solveAlgorithm(int[][]grid){
        for(int row =0; row<9;row++){
            for(int col =0;col<9;col++){

                if(grid[row][col]==0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(row,col,num)) {
                            grid[row][col] = num;
                            if (solveAlgorithm(grid)) {
                                return true;
                            } else {
                                grid[row][col] = 0;
                            }

                        }
                    }return false;
                }

            }
        }
        return true;
    }

    public boolean containedInRow(int y, int n){
        for(int i =0; i<9;i++){

                if (grid[y][i] == n) {
                    return true;
                }

        }
        return false;

    }
    private  boolean containedInCol(int x, int n){
        for(int i =0; i<9;i++){

                if (grid[i][x] == n) {
                    return true;
                }
                    }
        return false;

    }

    private  boolean containedInSquare(int y, int x, int n){
        int startRow = y-y%3;
        int startCol = x-x%3;

        for(int i=startRow;i<startRow+3;i++){
            for(int j = startCol;j<startCol+3;j++){

                    if (grid[i][j] == n) {
                        return true;
                    }


            }
        }

        return false;
    }
    private  boolean isValid(int y,int x, int num){
        return(!(containedInSquare(y,x,num)||containedInCol(x,num)||containedInRow(y,num)));
    }

    public void printGrid(){
        for(int row =0; row<9;row++){
            for(int col =0;col<9;col++) {
                System.out.print(grid[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public  int valid_row(int row, int [][] grid){
        int temp[] = grid[row];
        Set<Integer>set = new HashSet<Integer>();
        for (int value : temp) {
            // Checking for values outside 0 and 9;
            // 0 is considered valid because it
            // denotes an empty cell.
            // Removing zeros and the checking for values and
            // outside 1 and 9 is another way of doing
            // the same thing.
            if (value < 0 || value > 9){
                System.out.println( "Invalid value" );
                return -1;
            }
            //Checking for repeated values.
            else if (value != 0){
                if (set.add(value) == false) {
                    return 0;
                }
            }
        }
        return 1;
    }
    // Function to check if a given column is valid. It will return:
    // -1 if the column contains an invalid value
    // 0 if the column contains repeated values
    // 1 is the column is valid.
    public  int valid_col(int col, int [][] grid){
        Set<Integer>set = new HashSet<Integer>();
        for (int i =0 ; i< 9; i++) {
            // Checking for values outside 0 and 9;
            // 0 is considered valid because it
            // denotes an empty cell.
            // Removing zeros and the checking for values and
            // outside 1 and 9 is another way of doing
            // the same thing.
            if (grid[i][col] < 0 || grid[i][col] > 9){
                System.out.println( "Invalid value" );
                return -1;
            }
            // Checking for repeated values.
            else if (grid[i][col] != 0){
                if (set.add(grid[i][col]) == false) {
                    return 0;
                }
            }
        }
        return 1;
    }
    // Function to check if all the subsquares are valid. It will return:
// -1 if a subsquare contains an invalid value
// 0 if a subsquare contains repeated values
// 1 if the subsquares are valid.
    public  int valid_subsquares(int [][] grid){
        for (int row = 0 ; row < 9; row = row + 3) {
            for (int col = 0; col < 9; col = col + 3) {
                Set<Integer> set = new HashSet<Integer>();
                for(int r = row; r < row+3; r++) {
                    for(int c= col; c < col+3; c++){
                        // Checking for values outside 0 and 9;
                        // 0 is considered valid because it
                        // denotes an empty cell.
                        // Removing zeros and the checking for values and
                        // outside 1 and 9 is another way of doing
                        // the same thing.
                        if (grid[r][c] < 0 || grid[r][c] > 9){
                            System.out.println( "Invalid value" );
                            return -1;
                        }
                        // Checking for repeated values.
                        else if (grid[r][c] != 0){
                            if (set.add(grid[r][c]) == false) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }
    //Function to check if the board invalid.
    public  boolean valid_board(int [][] grid){
        // Checking the rows and columns.
        for (int i =0 ; i< 9; i++) {
            int res1 = valid_row(i, grid);
            int res2 = valid_col(i, grid);
            // if a row or column is invalid, then the board is invalid.
            if (res1 < 1 || res2 < 1) {
                System.out.println( "The board is invalid." );
                return false;
            }
        }
        int res3 = valid_subsquares(grid);
        // if any one the subsquares is invalid, then the board is invalid.
        if (res3 < 1) {
                        System.out.println( "The board is invalid." );
            return false;
        }
        else {
            System.out.println( "The board is valid." );
            return true;
        }
    }

    public void reset(){
       cloneMatrix(grid,initialGrid);
    }

    public void cloneMatrix(int[][]target,int[][]cloned){
       for(int row = 0; row<9;row++){
           for(int col = 0; col <9;col++){
               target[row][col] = cloned[row][col];
           }
       }
    }



}

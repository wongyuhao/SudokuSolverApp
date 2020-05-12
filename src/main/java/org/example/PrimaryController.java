package org.example;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;

public class PrimaryController implements Initializable {

     int[][] testGrid =
            {
                    {0,0,0,0,0,0,6,8,0},
                    {0,0,0,0,7,3,0,0,9},
                    {3,0,9,0,0,0,0,4,5},
                    {4,9,0,0,0,0,0,0,0},
                    {8,0,3,0,5,0,9,0,2},
                    {0,0,0,0,0,0,0,3,6},
                    {9,6,0,0,0,0,3,0,8},
                    {7,0,0,6,8,0,0,0,0},
                    {0,2,0,0,0,0,0,0,0},

            };
    final int[][] blankGrid =
            {
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0}

            };

    Solver solver = new Solver(blankGrid);
    @FXML
    Button solveButton = new Button();
    @FXML
    Button resetButton = new Button();
    @FXML
    Label status = new Label();
    @FXML
    GridPane gp = new GridPane();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGp(gp,solver);
    }
    public void solveSudoku(){


            if(this.solver.solve()){
                status.setText("Solved");
            }else{
                status.setText("Sudoku Board is Not Valid!");
            };


        updateGp(gp,solver);
        solveButton.setDisable(true);
        resetButton.setDisable(false);

    }


    public void updateGp(GridPane gp,Solver solver){
        for (int row = 0; row<9;row++){
            for (int col = 0; col< 9; col++){

                TextField tf = new TextField(String.valueOf(solver.getGrid()[row][col]));
                try {


                    tf.setTextFormatter(new TextFormatter<>(change ->
                            (change.getControlNewText().matches("([0-9])?")) ? change : null));
                    tf.textProperty().addListener((observable, oldValue, newValue) -> {

                            updateValue(
                                    solver,
                                    GridPane.getRowIndex(tf),
                                    GridPane.getColumnIndex(tf),
                                    (newValue.isEmpty() ?0:Integer.valueOf(newValue))


                            );

                    });

                gp.setRowIndex(tf,row);
                gp.setColumnIndex(tf,col);
                gp.getChildren().add(tf);
                }catch(Exception e){System.out.println("Error: "+ e.getMessage());}
            }

        }
    }




    public void updateValue(Solver solver, int row, int col , int newVal){
        solver.getGrid()[row][col]=newVal;
        System.out.println("Changed grid["+row+"]["+col+"] to "+ solver.getGrid()[row][col]);
        solver.printGrid();
    }

    public void reset(){
        solver.reset();
        updateGp(gp,solver);

        System.out.println("reset");
        solveButton.setDisable(false);
        resetButton.setDisable(true);
        solver.printGrid();
    }

    
}

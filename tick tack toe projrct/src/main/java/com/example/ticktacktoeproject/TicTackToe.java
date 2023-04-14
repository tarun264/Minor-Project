package com.example.ticktacktoeproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTackToe extends Application {
    // buttons for marking
    private Button buttons[][]= new Button[3][3];
    private Label  PlayerXScoreLabel,PlayerOScoreLabel;

    private int playerXscore=0 , playerOscore=0;

    private boolean playerXTurn=true;
    private BorderPane createContent(){
        BorderPane root= new BorderPane(); // pane consisting borders
        // title making
        Label titleLabel = new Label("Tic Tac Toe");
        // setting the style of the font
        titleLabel.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold;");
        titleLabel.setTextFill(Color.DARKGREEN);
        // add the label to the root
        root.setTop(titleLabel);
        // to set the alignment
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        // Game Board
        GridPane gridPane= new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button( );
                // setting the button size
                button.setPrefSize(100,100);
                // setting the font size
                button.setStyle("-fx-font-size: 35pt; -fx-font-weight: bold;");
                // this method will help to put events on the button ie when the button is clicked
                // that functionality will be implemented
                button.setOnAction(event-> buttonClicked(button));
                buttons [i][j]=button;
                gridPane.add(button,j,i); //doubt why j i
            }
        }
        root.setCenter(gridPane);
        BorderPane.setAlignment(gridPane,Pos.CENTER);

        //Score
        HBox scoreBoard= new HBox(20);    // hbox create box coloumn in horizontal
        scoreBoard.setAlignment(Pos.CENTER);
        PlayerXScoreLabel= new Label("Player X: 0");
        PlayerXScoreLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold;");
        PlayerXScoreLabel.setTextFill(Color.MAROON);
        PlayerOScoreLabel = new Label("Player O: 0");
        PlayerOScoreLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold;");
        PlayerOScoreLabel.setTextFill(Color.BLUEVIOLET);

        // add them to the score board
        scoreBoard.getChildren().addAll(PlayerXScoreLabel,PlayerOScoreLabel);
        // setting scoreboard in bottom
        root.setBottom(scoreBoard);


        return root;
    }

    // setting the function when the button is clicked
    private void buttonClicked(Button button){
        // if the button is empty
        if(button.getText().equals("")) {
            // if its player x turn then set the text X on the button
            if (playerXTurn) {
                button.setText("X");
                button.setTextFill(Color.MAROON);
            }
            //else set O on the button
            else {
                button.setText("0");
                button.setTextFill(Color.BLUEVIOLET);
            }
            // setting it to the opp for next turn
            playerXTurn = !playerXTurn;

            checkWinner();
        }
        return;
    }
    // function to check winners
    private void checkWinner(){
        // all values in row, adjacent col value should be same and iff the all the buttons are marked
        for (int row = 0; row < 3; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
                && buttons[row][1].getText().equals(buttons[row][2].getText())
                && !buttons[row][0].getText().isEmpty()
            ){
                //we will have a winner
                String winner = buttons[row][0].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;

            }
        }
        // all values in col should be same
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(buttons[1][col].getText())
                    && buttons[1][col].getText().equals(buttons[2][col].getText())
                    && !buttons[0][col].getText().isEmpty()
            ) {
                //we will have a winner
                String winner = buttons[0][col].getText();
                showWinnerDialog(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        // all values in diagonal should be same
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())
                && !buttons[0][0].getText().isEmpty()
        ) {
            //we will have a winner
            String winner = buttons[0][0].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }
        if (buttons[2][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[0][2].getText())
                && !buttons[0][2].getText().isEmpty()
        ) {
            //we will have a winner
            String winner = buttons[0][2].getText();
            showWinnerDialog(winner);
            updateScore(winner);
            resetBoard();
            return;
        }

        // for tie case
        boolean tie= true;
        for(Button row[]: buttons){
            for(Button button: row){
                if(button.getText().isEmpty()){
                    tie=false;
                    break;
                }
            }
        }
        if(tie){
            showTieDialog();
            resetBoard();
        }
    }

    // to show the winner
    private void showWinnerDialog(String winner){
        // Alert is subclass of Dialog class which shows pre-built dialog types
        Alert alert= new Alert(Alert.AlertType.INFORMATION); //can set confirmation tag also
        alert.setTitle("Winner");
        alert.setContentText("Congratulations " + winner + " !You won the Game");
        alert.setHeaderText(null);
        // method to show the alert
        alert.showAndWait();

    }
    // to show tie
    private void showTieDialog(){
        // Alert is subclass of Dialog class which shows pre-built dialog types
        Alert alert= new Alert(Alert.AlertType.INFORMATION); //can set confirmation tag also
        alert.setTitle("Winner");
        alert.setContentText("Game Over! Its a tie.");
        alert.setHeaderText(null);
        // method to show the alert
        alert.showAndWait();

    }
    private void updateScore(String winner){
        if(winner.equals("X")){
            playerXscore++;
            PlayerXScoreLabel.setText("Player X: "+ playerXscore);
        }
        else{
            playerOscore++;
            PlayerOScoreLabel.setText("Player O: " + playerOscore);
        }
        // updated this score in winner

    }
    // it will reset all the buttons to nothing
    private void resetBoard(){
        for (Button row[]: buttons){
            for(Button button: row){
                button.setText("");
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Tick Tack Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
         launch();
    }
}
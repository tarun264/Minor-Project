package com.example.snakeandladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SnakeLadder extends Application {
    // size of each block
    public static final int tileSize=40,width=10, height=10;
    public static final int buttonLine= height*tileSize+50, infoLine=buttonLine-30;
    private static Dice dice= new Dice();
    private Player player1, player2;
    private boolean gameStarted=false, player1Turn=true, player2Turn=false;
    private Pane createContent()  {
        Pane root = new Pane();
        // size of pane we want
        root.setPrefSize(width * tileSize, height * tileSize + 100);
// for creating n no of tiles for game
        for (int i = 0; i  < height; i++) {
            for (int j = 0; j < width; j++) {
                Tiles tile = new Tiles(tileSize);
                // it will add all the content as a child of pane
                root.getChildren().add(tile);
                //to set the cordinates of a tile
                tile.setTranslateX(j * tileSize);
                tile.setTranslateY(i * tileSize);


            }
        }
        FileInputStream file = null;
        try {
            file = new FileInputStream("C:\\Users\\Tarun\\IdeaProjects\\Snake And Ladder\\src\\main\\java\\com\\example\\snakeandladder\\depositphotos_123523528-stock-illustration-dragon-and-ladder-game-yellow.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image img = new  Image(file);
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);
        root.getChildren().add(board);

//        to create button
        Button player1Button= new Button("Player One");
        Button player2Button= new Button("Player Two");
        Button startButton= new Button("Start");

        player1Button.setTranslateY(buttonLine);
        player1Button.setTranslateX(20);
        player1Button.setDisable(true);
        player2Button.setTranslateY(buttonLine);
        player2Button.setTranslateX(300);
        player2Button.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(175);

//        information Label for button
        Label player1Label= new Label("");
        Label player2Label = new Label("");
        Label diceLabel= new Label("Start the Game");

        player1Label.setTranslateY(infoLine);
        player1Label.setTranslateX(20);
        player2Label.setTranslateY(infoLine);
        player2Label.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

// create players
        player1= new Player(tileSize,Color.BLACK," Tarun ");
        player2 = new Player(tileSize-5,Color.DARKBLUE," Vishal");

//Player 1 Action
        //set on action will help to move dice
        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(player1Turn) {
                        // rolling dice
                        int diceValue = dice.getRolledDiceValue();
                        // showing the dive value
                        diceLabel.setText("Dice Value : " + diceValue);
                        // moving player 1
                        player1.movePlayer(diceValue);
                        //wining condition
                        if (player1.isWinner()) {
                            diceLabel.setText("WINNER IS " + player1.getName());
                            //disabling every button after winning
                            player1Turn = false;
                            player1Button.setDisable(true);
                            player1Label.setText("");
                            player2Turn = false;
                            player2Button.setDisable(true);
                            player2Label.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                            gameStarted=false;
                        }
                        else {
                            // disabling the player 1 from playing
                            player1Turn = false;
                            player1Button.setDisable(true);
                            player2Label.setText("");

//enabling 2 player to play
                            player2Turn = true;
                            player2Button.setDisable(false);
                            player2Label.setText("Your Turn " + player2.getName());
                        }
                    }
                }
            }
        });

        //player 2 action
        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(player2Turn) {
                        // rolling dice
                        int diceValue = dice.getRolledDiceValue();
                        // showing the dive value
                        diceLabel.setText("Dice Value :" + diceValue);
                        // moving player 1
                        player2.movePlayer(diceValue);
                        //winning condition
                        if (player2.isWinner()) {
                            diceLabel.setText("WINNER IS " + player2.getName());
                     //disabling every button after winning
                            player1Turn = false;
                            player1Button.setDisable(true);
                            player1Label.setText("");
                            player2Turn = false;
                            player2Button.setDisable(true);
                            player2Label.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game");
                        }
                        // disabling the player 2 from playing
                        else {
                            player1Turn = true;
                            player1Button.setDisable(false);
                            player1Label.setText("Your Turn" + player1.getName());

//disabling 2 player to play
                            player2Turn = false;
                            player2Button.setDisable(true);
                            player2Label.setText("");
                        }
                    }
                }
            }
        });


        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                player1Label.setText("Your Turn"+ player1.getName());
                player1Button.setDisable(false);
                player1.backtoStart();

                player2Turn=false;
                player2Label.setText("");
                player2Button.setDisable(true);
                player2.backtoStart();
            }
        });



       root.getChildren().addAll(player1Button,player2Button,startButton,player1Label,
               player2Label,diceLabel,player1.getCoin(),player2.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
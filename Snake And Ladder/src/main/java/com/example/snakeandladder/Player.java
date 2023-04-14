package com.example.snakeandladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
   private Circle coin;
   private int currentPosition;
    private String name;
    //since board will be the same for all the players so we will create object of board & keep it static
    private static board gameBoard = new board();

    public Player(int tileSize, Color coinColor,String PlayerName) {
       // radius of circle
        coin= new Circle(tileSize/2);
        coin.setFill(coinColor);
        name = PlayerName;
        currentPosition=0;
        movePlayer(1); //at 1st posn of the board

    }
    public void movePlayer(int diceValue){
        if(currentPosition+diceValue<=100)
            currentPosition += diceValue;

  // for pausing the transition
        TranslateTransition secondMove=null, firstMove=translateAnimation(diceValue);
// movement in case of ladder or snakes comes
        int newPosition= gameBoard.getNewPosition(currentPosition);
        if(newPosition!=currentPosition && newPosition!=-1) {
            currentPosition = newPosition;
        }
            secondMove=translateAnimation(6);
        // it is possible that there might be snake or ladder
        if(secondMove==null){
            firstMove.play();
        }else{
            SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                    new PauseTransition(Duration.millis(1000)),secondMove);
            sequentialTransition.play();
        }
    }

        // to get the cordinates we will take the help of postn coordinate
        // from board class
//        int x= gameBoard.getXCordinates(currentPosition);
//        int y=gameBoard.getYCordinates(currentPosition);
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

// to add animation to the coin movement
    // used translate transition as return type because we want animation
    // to take place in steps ie reach to ladder or snake and then move particular index not directly move that place
    private TranslateTransition translateAnimation(int diceValue){
         TranslateTransition animate= new TranslateTransition(Duration.millis(200*diceValue),coin);
         animate.setToX(gameBoard.getXCordinates(currentPosition));
         animate.setToY(gameBoard.getYCordinates(currentPosition));
         //it will prevent to auto reverse
         animate.setAutoReverse(true);
         return animate;
}
   boolean isWinner(){
        if(currentPosition==100) {
            return true;
        }
            return false;
   }
   public void backtoStart(){
        currentPosition=0;
        movePlayer(1);
   }
///getting getters for coin, postn, name alt+insert
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}


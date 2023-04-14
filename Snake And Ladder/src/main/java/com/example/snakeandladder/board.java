package com.example.snakeandladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class board {
   //for position cordinates in board
    ArrayList<Pair<Integer,Integer>> postionCordinates;
    //creating arraylist for ladders and snake
    ArrayList<Integer> snakeLadderPosition;

    public board(){
        postionCordinates= new ArrayList<>();
        populatePostionCordinates();
        populateSnakeLadder();
    }
    private void populatePostionCordinates(){
        // key will represent x cordinate and y represent value
        postionCordinates.add(new Pair<>(0,0)); //dummy value
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                //x cordinates
                int xCord = 0;
                if (i % 2 == 0) { // if i is at even pstn,no will inc
                    xCord = j * SnakeLadder.tileSize + SnakeLadder.tileSize / 2;
                } else { //if i is at odd postn it will dec
                    xCord = (SnakeLadder.tileSize * SnakeLadder.height) -(j*SnakeLadder.tileSize)-SnakeLadder.tileSize/2;

                }
                //for y cordinates
                //total size-tilesize-1/2tile size to get the center of tile
                int yCord= (SnakeLadder.tileSize*SnakeLadder.height)-(i*SnakeLadder.tileSize)-SnakeLadder.tileSize/2; // ie 380
               postionCordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }
    int getXCordinates(int position){
        if(position>=1 && position<=100)
            return postionCordinates.get(position).getKey();
            return -1;
        }
    int getYCordinates(int position) {
        if (position >= 1 && position <= 100)
            return postionCordinates.get(position).getValue();
            return -1;
    }

    //code for ladders and snake
    private void populateSnakeLadder(){
        snakeLadderPosition= new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.add(2,23);
        snakeLadderPosition.add(6,45);
        snakeLadderPosition.add(20,59);
        snakeLadderPosition.add(30,16);
        snakeLadderPosition.add(37,3);
        snakeLadderPosition.add(52,72);
        snakeLadderPosition.add(56,8);
        snakeLadderPosition.add(57,96);
        snakeLadderPosition.add(74,94);
        snakeLadderPosition.add(78,98);
        snakeLadderPosition.add(84,64);
        snakeLadderPosition.add(87,31);
        snakeLadderPosition.add(99,41);

    }
    public int getNewPosition(int currentPosition){
        if(currentPosition>0 && currentPosition<=100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }
// to check whether the function is working
//    public static void main(String[] args) {
//        board Board = new board();
//        for (int i = 0; i < Board.postionCordinates.size(); i++) {
//            System.out.println(i + " $ x:" + Board.postionCordinates.get(i).getKey() +
//                    " y:" + Board.postionCordinates.get(i).getValue());
//        }
//    }
   }


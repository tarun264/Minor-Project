package com.example.snakeandladder;

public class Dice {
    public int getRolledDiceValue(){
        //upperbound-lowerbound+1 since random does not take upperbound
        return (int)(Math.random()*6+1);
    }
}

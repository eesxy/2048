package com.example.myapplication;

public interface Kernel {
    void left();
    void right();
    void up();
    void down();
    boolean isEnd();
    int[][] board();
    int score();
}

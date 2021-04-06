package com.example.assignment3.model;

public class Mines {
    private int num;
    private int col;
    private int row;

    //Singleton Support
    private static Mines instance;
    private Mines(){
        this.num = 6;
        this.row = 4;
        this.col = 6;
        //private to prevent anyone else from instantiating
    }

    public static Mines getInstance(){
        if(instance == null){
            instance = new Mines();
        }
        return instance;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void minus(){
        num =- 1;
    }

}

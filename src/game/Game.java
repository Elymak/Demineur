package game;

import constantes.Constantes;

import java.util.Random;

public class Game {

    private int[][] field;
    private boolean[][] clicks;

    private int xLength;
    private int yLength;

    private boolean gameOver;

    public Game(){
        this(Constantes.DEFAULT_xLENGTH, Constantes.DEFAULT_yLENGTH);
    }

    public Game(int xLength, int yLength){
        this.field = new int[xLength][yLength];
        this.clicks = new boolean[xLength][yLength];
        this.yLength = yLength;
        this.xLength = xLength;
        this.gameOver = false;

        //init clicks array
        for(int x = 0; x < this.xLength; x++){
            for(int y = 0; y < this.yLength; y++){
                this.clicks[x][y] = false;
            }
        }
    }

    public void addMines(int mineProportion){
        int nbMines = this.yLength * this.xLength * mineProportion / 100;

        for(int i = 0; i < nbMines; i++){
            boolean mineOk = false;
            while (!mineOk){
                int x = new Random().nextInt(this.xLength);
                int y = new Random().nextInt(this.yLength);
                if(this.field[x][y] != Constantes.MINE){
                    this.field[x][y] =  Constantes.MINE;
                    mineOk = true;
                }
            }
        }
        this.calculateNeighbours();
    }

    private void calculateNeighbours() {
        for(int x = 0; x < this.xLength; x++){
            for(int y = 0; y < this.yLength; y++){
                if(this.field[x][y] == Constantes.MINE){

                    if(x > 0){
                        incrementNeighbours(x-1,y);
                        if(y > 0){
                            incrementNeighbours(x-1, y-1);
                        }
                        if(y < this.yLength - 1){
                            incrementNeighbours(x-1, y+1);
                        }
                    }
                    if(x < this.xLength-1){
                        incrementNeighbours(x+1,y);
                        if(y > 0){
                            incrementNeighbours(x+1, y-1);
                        }
                        if(y < this.yLength - 1){
                            incrementNeighbours(x+1, y+1);
                        }
                    }
                    if(y > 0){
                        incrementNeighbours(x, y-1);
                    }
                    if(y < yLength-1){
                        incrementNeighbours(x, y+1);
                    }
                }
            }
        }
    }

    private void incrementNeighbours(int x, int y) {
        if(this.field[x][y] != Constantes.MINE){
            this.field[x][y]++;
        }
    }

    public void click(int x, int y){
        this.clicks[x][y] = true;
        if(this.field[x][y] == Constantes.MINE){
            setGameOver(true);
        } else if(this.field[x][y] == 0){
            if(x > 0){
                if(!this.clicks[x-1][y]){
                    click(x-1,y);
                }
                if(y > 0){
                    if(!this.clicks[x-1][y-1]) {
                        click(x - 1, y - 1);
                    }
                }
                if(y < this.yLength - 1){
                    if(!this.clicks[x-1][y+1]) {
                        click(x - 1, y + 1);
                    }
                }
            }
            if(x < this.xLength-1){
                if(!this.clicks[x+1][y]) {
                    click(x + 1, y);
                }
                if(y > 0){
                    if(!this.clicks[x+1][y-1]) {
                        click(x + 1, y - 1);
                    }
                }
                if(y < this.yLength - 1){
                    if(!this.clicks[x+1][y+1]) {
                        click(x + 1, y + 1);
                    }
                }
            }
            if(y > 0){
                if(!this.clicks[x][y-1]) {
                    click(x, y - 1);
                }
            }
            if(y < yLength-1){
                if(!this.clicks[x][y+1]) {
                    click(x, y + 1);
                }
            }
        }
    }

    // *****************
    //
    // GETTERS AND SETTERS
    //
    // *****************


    public int[][] getField() {
        return field;
    }

    public void setField(int[][] field) {
        this.field = field;
    }

    public boolean[][] getClicks() {
        return clicks;
    }

    public void setClicks(boolean[][] clicks) {
        this.clicks = clicks;
    }

    public int getXLength() {
        return xLength;
    }

    public void setxLength(int xLength) {
        this.xLength = xLength;
    }

    public int getYLength() {
        return yLength;
    }

    public void setyLength(int yLength) {
        this.yLength = yLength;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public String toString() {
        String s = "";
        for (int x = 0; x < this.xLength; x++){
            for(int y = 0; y < this.yLength; y++){
                s += "[" + (this.field[x][y] == Constantes.MINE ? "X" : this.field[x][y]) + "]";
            }
            s += "  ";
            for(int y = 0; y < this.yLength; y++){
                s += "[" + (this.clicks[x][y] ? "1" : "0") + "]";
            }
            s += "\n";
        }
        return s;
    }


}

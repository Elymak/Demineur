package view;

import constantes.Constantes;
import game.Game;
import observer.FieldObservable;
import observer.FieldObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldView extends JPanel implements MouseListener {

    private static final int OFFSET = Constantes.DEFAULT_MINE_SIZE;
    private Game game;
    private FieldObservable observable;

    public FieldView(Game game, FieldObserver observer){
        this.game = game;
        this.observable = new FieldObservable();
        this.observable.setObserver(observer);
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        for(int x = 0; x < this.game.getXLength(); x++){
            for(int y = 0; y < this.game.getYLength(); y++){
                this.paintField(x, y, g2);
            }
        }
        drawGrid(g2);
    }

    private void paintField(int x, int y, Graphics2D g2) {
        int s = Constantes.DEFAULT_MINE_SIZE;
        int xPos = (x * s) + OFFSET;
        int yPos = (y * s) + OFFSET;

        //drapeaux
        if(this.game.getFlags()[x][y]){
            g2.setColor(Color.BLUE); //couleur de base du drapeau
            if(this.game.isOver()){
                if(this.game.getField()[x][y] == Constantes.MINE){
                    g2.setColor(Color.GREEN); //drapeau bien placé
                } else {
                    g2.setColor(Color.ORANGE); //drapeau mal placé
                }
            }
            g2.fillRect(yPos, xPos, s, s);
        }

        //endroit non découvert
        else if(!this.game.getClicks()[x][y]){
            g2.setColor(Color.GRAY);
            if(this.game.isOver() && this.game.getField()[x][y] == Constantes.MINE){
                g2.setColor(Color.BLACK); //jeu fini = on révèle les mines non découvertes
            }
            g2.fillRect(yPos, xPos, s, s);
        }

        //endroit découvert
        else if (this.game.getField()[x][y] != Constantes.MINE) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(yPos, xPos, s, s);
            if(this.game.getField()[x][y] != 0) {
                g2.setColor(getTextColor(this.game.getField()[x][y]));
                g2.drawString(this.game.getField()[x][y] + "", (yPos + (s / 2) - 3), (xPos + s - 4));
            }
        }

        //mine explosée
        else {
            g2.setColor(Color.RED);
            g2.fillRect(yPos, xPos, s, s);
        }
    }

    private Color getTextColor(int nbNeighbours) {
        return switch (nbNeighbours) {
            case 1 -> Color.BLUE;
            case 2 -> Color.GREEN;
            case 3 -> Color.RED;
            case 4 -> Color.MAGENTA;
            case 5 -> Color.YELLOW;
            case 6 -> Color.CYAN;
            case 7 -> Color.ORANGE;
            case 8 -> Color.PINK;
            default -> Color.BLACK;
        };
    }

    private void drawGrid(Graphics2D g2){
        int s = Constantes.DEFAULT_MINE_SIZE;
        int xEdge = (this.game.getXLength() * s) + OFFSET;
        int yEdge = (this.game.getYLength() * s) + OFFSET;
        g2.setColor(Color.BLACK);

        for(int x = 0; x < this.game.getXLength() + 1; x++){
            for(int y = 0; y < this.game.getYLength() + 1; y++){
                g2.drawLine(OFFSET, y * s + OFFSET, xEdge, y * s + OFFSET);
                g2.drawLine(x * s + OFFSET, OFFSET, x * s + OFFSET, yEdge);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xClick = e.getX();
        int yClick = e.getY();

        int x = (xClick - OFFSET) / Constantes.DEFAULT_MINE_SIZE;
        int y = (yClick - OFFSET) / Constantes.DEFAULT_MINE_SIZE;
        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);
        boolean isRightClick = SwingUtilities.isRightMouseButton(e);
        boolean isInBounds = x < Constantes.DEFAULT_xLENGTH && y < Constantes.DEFAULT_yLENGTH && x > -1 && y > -1;

        if(isInBounds) {
            if(isLeftClick) {
                this.game.click(y, x);
                this.observable.setChanged();
                this.observable.notifyObserver();
            } else if (isRightClick){
                this.game.plantFlag(y, x);
                this.observable.setChanged();
                this.observable.notifyObserver();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void removeMouseListener() {
        this.removeMouseListener(this);
    }
    
    public void reactivateListener(){
        this.addMouseListener(this);
    }
}

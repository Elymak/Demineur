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
        for(int x = 0; x < this.game.getXLength(); x++){
            for(int y = 0; y < this.game.getYLength(); y++){
                this.paintField(x, y, g2);
            }
        }
    }

    private void paintField(int x, int y, Graphics2D g2) {
        int s = Constantes.DEFAULT_MINE_SIZE;
        int xPos = x * s;
        int yPos = y * s;

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
            g2.setColor(Color.WHITE);
            g2.drawString(this.game.getField()[x][y]+"", (yPos), (xPos + s));
        }

        //mine explosée
        else {
            g2.setColor(Color.RED);
            g2.fillRect(yPos, xPos, s, s);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xClick = e.getX();
        int yClick = e.getY();

        int x = xClick / Constantes.DEFAULT_MINE_SIZE;
        int y = yClick / Constantes.DEFAULT_MINE_SIZE;
        boolean isLeftClick = SwingUtilities.isLeftMouseButton(e);
        boolean isRightClick = SwingUtilities.isRightMouseButton(e);
        boolean isInBounds = x < Constantes.DEFAULT_xLENGTH && y < Constantes.DEFAULT_yLENGTH;

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

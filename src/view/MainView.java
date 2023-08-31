package view;

import constantes.Constantes;
import game.Game;
import observer.FieldObserver;
import view.menu.Menu;

import javax.swing.*;

public class MainView extends JFrame implements FieldObserver {

    private Game game;
    private FieldView fieldView;
    private Menu menu;

    public MainView() {
        this.setTitle("Demineur");
        menu = new Menu(this);
        this.setJMenuBar(menu);

        this.game = new Game(Constantes.DEFAULT_xLENGTH, Constantes.DEFAULT_yLENGTH);
        createField();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void createField() {
        int frameWidth = (game.getXLength() + 3) * Constantes.DEFAULT_MINE_SIZE;
        int frameHeight = (game.getYLength() + 4) * Constantes.DEFAULT_MINE_SIZE;
        this.setSize(frameWidth,frameHeight);

        this.fieldView = new FieldView(game, this);
        this.setContentPane(fieldView);

        this.setLocationRelativeTo(null);
    }


    @Override
    public void update() {
        if(this.fieldView.getGame().isGameOver() || this.fieldView.getGame().isGameWin()){
            this.fieldView.removeMouseListener();
        }
        this.repaint();
    }
    
    public void newGame(int xLength, int yLength) {
        this.game = new Game(xLength, yLength);
        createField();
        this.update();
        this.fieldView.reactivateListener();
    }

    public FieldView getFieldView() {
        return this.fieldView;
    }
}

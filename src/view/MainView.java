package view;

import constantes.Constantes;
import game.Game;
import observer.FieldObserver;

import javax.swing.*;

public class MainView extends JFrame implements FieldObserver {

    private Game game;
    private FieldView fieldView;

    public MainView(){

        this.game = new Game();
        this.game.addMines(Constantes.DEFAULT_MINE_PROPORTION);

        this.setTitle("Demineur");
        this.setJMenuBar(new Menu(this));

        int frameWidth = (Constantes.DEFAULT_xLENGTH + 3) * Constantes.DEFAULT_MINE_SIZE;
        int frameHeight = (Constantes.DEFAULT_yLENGTH + 5) * Constantes.DEFAULT_MINE_SIZE;
        this.setSize(frameWidth,frameHeight);


        this.fieldView = new FieldView(game, this);
        this.setContentPane(fieldView);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    @Override
    public void update() {
        if(this.fieldView.getGame().isGameOver() || this.fieldView.getGame().isGameWin()){
            this.fieldView.removeMouseListener();
        }
        this.repaint();
    }
    
    public void newGame(){
        Game newGame = new Game();
        newGame.addMines(Constantes.DEFAULT_MINE_PROPORTION);
        this.fieldView.setGame(newGame);
        this.fieldView.reactivateListener();
        this.update();
    }
}

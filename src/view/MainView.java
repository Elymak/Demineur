package view;

import game.Game;
import observer.FieldObserver;

import javax.swing.*;

public class MainView extends JFrame implements FieldObserver {

    private FieldView fieldView;

    public MainView(Game game){

        this.setTitle("Demineur");
        this.setSize(405,455);
        
        this.setJMenuBar(new Menu(this));
        
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
        this.getContentPane().repaint();
    }
    
    public void newGame(){
        Game newGame = new Game();
        newGame.addMines(20);
        this.fieldView.setGame(newGame);
        this.fieldView.reactivateListener();
        this.update();
    }
}

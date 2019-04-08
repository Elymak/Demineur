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
        this.getContentPane().repaint();
        if(this.fieldView.getGame().isGameOver()){
            System.out.println("PERDU");
            this.fieldView.removeMouseListener();
        }
    }
    
    public void newGame(){
        Game newGame = new Game();
        newGame.addMines(20);
        this.fieldView.setGame(newGame);
        this.fieldView.reactivateListener();
        this.update();
    }
}

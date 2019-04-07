package view;

import game.Game;
import observer.FieldObserver;

import javax.swing.*;

public class MainView extends JFrame implements FieldObserver {

    private FieldView fieldView;

    public MainView(Game game){

        this.setTitle("Demineur");
        this.setSize(420,440);
        this.fieldView = new FieldView(game, this);

        this.setContentPane(fieldView);

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
}

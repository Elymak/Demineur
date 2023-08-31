package view.frame;

import constantes.Constantes;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultyChoiceFrame extends JFrame {
    private MainView mainView;
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton expertButton;

    public DifficultyChoiceFrame(MainView mainView) {
        this.mainView = mainView;

        easyButton = new JButton("EASY");
        easyButton.setBounds(5,5,100,40);
        mediumButton = new JButton("MEDIUM");
        mediumButton.setBounds(5,50,100,40);
        hardButton = new JButton("HARD");
        hardButton.setBounds(5,95,100,40);
        expertButton = new JButton("EXPERT");
        expertButton.setBounds(5,140,100,40);

        easyButton.addActionListener(new DifficultyChoiceListner());
        mediumButton.addActionListener(new DifficultyChoiceListner());
        hardButton.addActionListener(new DifficultyChoiceListner());
        expertButton.addActionListener(new DifficultyChoiceListner());

        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);
        this.add(expertButton);

        this.setSize(100,225);
        this.setLocationRelativeTo(mainView);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setUndecorated(false);
        this.setLayout(null);
        this.setVisible(true);

    }

    class DifficultyChoiceListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            switch (source.getText()) {
                case "EASY" -> mainView.newGame(Constantes.EASY_xLENGTH, Constantes.EASY_yLENGTH);
                case "MEDIUM" -> mainView.newGame(Constantes.MEDIUM_xLENGTH, Constantes.MEDIUM_yLENGTH);
                case "HARD" -> mainView.newGame(Constantes.HARD_xLENGTH, Constantes.HARD_yLENGTH);
                case "EXPERT" -> mainView.newGame(Constantes.EXPERT_xLENGTH, Constantes.EXPERT_yLENGTH);
                default -> {
                }
            }
            closeDifficultyChoicesFrame();
        }
    }

    protected void closeDifficultyChoicesFrame() {
        dispose();
    }

}

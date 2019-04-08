package view

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar implements ActionListnener {

  private static final String FILE_LABEL = "Fichier";
  private static final String NEW_GAME_LABEL = "Nouvelle Partie";
  private static final String EXIT_LABEL = "Quitter";
  
  private MainView mainView;
  
  public Menu(MainView mainView){
    this.mainView = mainView;
    
    JMenu menu = new JMenu(FILE_LABEL);
    this.add(menu);
    
    JMenuItem newGameMenuItem = new JMenuItem(NEW_GAME_LABEL);
    newGameMenuItem.addActionListener(this);
    menu.add(newGameMenuItem);
    
    JMenuItem quitterMenuItem = new JMenuItem(EXIT_LABEL);
    quitterMenuItem.addActionListener(this);
    menu.add(quitterMenuItem);
  }
  
  @Override
  public void actionPerformed(ActionEvent e){
    JMenuItem source = (JMenuItem) (e.getSource());
    if(NEW_GAME_LABEL.equals(source.getText())){
      this.mainView.newGame();
    } else if (EXIT_LABEL.equals(source.getText())){
      System.exit(0);
    }
  }

}

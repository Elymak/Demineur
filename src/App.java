import game.Game;
import view.MainView;

public class App {
    public static void main(String[] args) {
        Game game = new Game();
        game.addMines(20);
        System.out.println(game);
        MainView mainView = new MainView(game);
    }
}

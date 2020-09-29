import config.Config;
import view.MainView;

public class App {
    public static void main(String[] args) {
        Config.loadUserConfig();
        MainView mainView = new MainView();
    }
}

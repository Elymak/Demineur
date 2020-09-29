package config;

import java.io.*;
import java.util.Properties;

public class Config {

    private int xLength;
    private int yLength;
    private int nbMines;


    private static Config INSTANCE;

    public static Config getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    public Config() {

    }

//    public Config(int xLength, int yLength, int nbMines) {
//        this.xLength = xLength;
//        this.yLength = yLength;
//        this.nbMines = nbMines;
//    }

    public static void loadDefaultConfig() {
        Properties properties = new Properties();
        BufferedInputStream stream;
        try {
            stream = new BufferedInputStream(new FileInputStream("config/config.properties"));
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            //TODO exit system cleanly
            e.printStackTrace();
            System.exit(1);
        }

        String xLengthString = properties.getProperty("xLength");
        String yLengthString = properties.getProperty("yLength");
        String minesString = properties.getProperty("mines");
        System.out.println(xLengthString + ":" + yLengthString + ":" + minesString);

        setConfig(Integer.parseInt(xLengthString), Integer.parseInt(yLengthString), Integer.parseInt(minesString));
    }

    public static void loadUserConfig() {
        Properties properties = new Properties();
        BufferedInputStream stream;
        try {
            stream = new BufferedInputStream(new FileInputStream("config/userConfig.properties"));
            properties.load(stream);
            stream.close();

            String xLengthString = properties.getProperty("xLength");
            String yLengthString = properties.getProperty("yLength");
            String minesString = properties.getProperty("mines");
            System.out.println(xLengthString + ":" + yLengthString + ":" + minesString);

            setConfig(Integer.parseInt(xLengthString), Integer.parseInt(yLengthString), Integer.parseInt(minesString));

        } catch (IOException e) {
            loadDefaultConfig();
        }
    }

    public static void writeUserConfig() {

        try (OutputStream output = new FileOutputStream("config/userConfig.properties")) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("xLength", String.valueOf(getInstance().getxLength()));
            prop.setProperty("yLength", String.valueOf(getInstance().getyLength()));
            prop.setProperty("mines", String.valueOf(getInstance().getNbMines()));

            // save properties to project root folder
            prop.store(output, null);
        } catch (IOException io) {

        }
    }

    private static void setConfig(int xLength, int yLength, int nbMines) {
        Config instance = getInstance();
        instance.setxLength(xLength);
        instance.setyLength(yLength);
        instance.setNbMines(nbMines);
    }

    public int getxLength() {
        return xLength;
    }

    public void setxLength(int xLength) {
        this.xLength = xLength;
    }

    public int getyLength() {
        return yLength;
    }

    public void setyLength(int yLength) {
        this.yLength = yLength;
    }

    public int getNbMines() {
        return nbMines;
    }

    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }
}

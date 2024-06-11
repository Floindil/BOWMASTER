package app.src.scenes;

import java.awt.Color;

import app.src.resources.StaticValues;
import app.src.resources.components.Button;

/**
 * Creates the menu screen.
 * Extends the Scene class.
 * 
 * @param start     Button object to start the game
 * @param exit      Button obkect to exit the game
 * @see             Button
 * @see             Scene
 */
public class Menu extends Scene {
    private Button start, exit;

    /**
     * Constructor.
     * Sets up the Menu scene.
     */
    public Menu() {
        setTAG("menu");
        int width = StaticValues.CANVAS_WIDTH;
        int height = StaticValues.CANVAS_HEIGHT;
        start = new Button(width/2, height/2 - 100, 100, 50, "START", Color.GRAY);
        start.setAction(() -> {
            Level1 l = new Level1();
            setNewScene(l);
        });
        exit = new Button(width/2, height/2 + 100, 100, 50, "EXIT", Color.GRAY);
        exit.setAction(() -> {
            System.exit(0);
        });
        //setBG("HuntersHouse.png");
        registerButton(start);
        registerButton(exit);
    }
}

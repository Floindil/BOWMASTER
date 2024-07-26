package app.src.scenes;

import java.awt.Color;

import app.src.StaticValues;
import app.src.resources.components.Button;

/**
 * Creates the menu screen.
 * Extends the Scene class.
 * @see Button
 * @see Scene
 */
public class Menu extends Scene {
    /**
     * Sets up the Menu scene.
     */
    public Menu() {
        super(true);
        setTAG("menu");
        setBG("bg.png");
        int width = StaticValues.CANVAS_WIDTH;
        int height = StaticValues.CANVAS_HEIGHT;
        Button start = new Button(100, 50, width/2, height/2 - 100, "START", Color.GRAY);
        start.setAction(() -> {
            Level1 l = new Level1();
            setNewScene(l);
        });
        Button exit = new Button(100, 50, width/2, height/2 + 100, "EXIT", Color.GRAY);
        exit.setAction(() -> {
            System.exit(0);
        });
        //setBG("HuntersHouse.png");
        registerButton(start);
        registerButton(exit);
    }
}

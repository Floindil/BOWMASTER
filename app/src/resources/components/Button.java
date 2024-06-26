package app.src.resources.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.resources.assets.Loader;

/**
 * Extends the Component class with a Textfield and a Runnable action
 * @see Textfield
 * @see Component
 */
public class Button extends Component{

    private Textfield label;
    private String text;
    private Runnable action;
    private BufferedImage image;

    /**
     * Creates a Button with size and location.
     * @param x         x coordinate for the location
     * @param y         y coordinate for the location
     * @param width     width of the Button
     * @param height    height of the Button
     */
    public Button(int width, int height, int x, int y) {
        super(width, height, x, y);
        setUpStandardButton(width, height);
    }

    /**
     * Creates a Button with size, location and text.
     * @param x         x coordinate for the location
     * @param y         y coordinate for the location
     * @param width     width of the Button
     * @param height    height of the Button
     * @param text      text displayed on the Button
     */
    public Button(int width, int height, int x, int y, String text) {
        super(width, height, x, y);
        setUpStandardButton(width, height);
        updateLabel(text);
    }

    /**
     * Creates a Button with size, location and color.
     * @param x         x coordinate for the location
     * @param y         y coordinate for the location
     * @param width     width of the Button
     * @param height    height of the Button
     * @param color     color of the Button
     */
    public Button(int width, int height, int x, int y, Color color) {
        super(width, height, x, y);
        setUpStandardButton(width, height);
        fill(color);
    }

    /**
     * Creates a Button with size, location, text and color.
     * @param x         x coordinate for the location
     * @param y         y coordinate for the location
     * @param width     width of the Button
     * @param height    height of the Button
     * @param text      text displayed on the Button
     * @param color     color of the Button
     */
    public Button(int width, int height, int x, int y, String text, Color color) {
        super(width, height, x, y);
        setUpStandardButton(width, height);
        fill(color);
        updateLabel(text);
    }

    /**
     * Creates a Button with location and image.
     * Loads the image from app/src/resources/assets.
     * Size is taken from the image.
     * @param x         x coordinate for the location
     * @param y         y coordinate for the location
     * @param imageName     image of the Button
     */
    public Button(int x, int y, String imageName) {
        super(1, 1, x, y);
        image = Loader.loadImage(imageName);
        int width = image.getWidth();
        int height = image.getHeight();
        setSize(width, height);
        setImage(image);
    }

    /**
     * Creates a Button with location, text and image.
     * Loads the image from app/src/resources/assets.
     * Size is taken from the image.
     * @param x         x coordinate for the location
     * @param y         y coordinate for the location
     * @param imageName image of the Button
     * @param text      text displayed on the Button
     */
    public Button(int x, int y, String imageName, String text) {
        super(1, 1, x, y);
        image = Loader.loadImage(imageName);
        int width = image.getWidth();
        int height = image.getHeight();
        setSize(width, height);
        setImage(image);
        updateLabel(text);
    }

    /**
     * Takes a Runnable and stores it as action variable
     * @param newAction    new action to perform on button click
     */
    public void setAction(Runnable newAction) {
        action = newAction;
    }

    /**
     * Runs the defined action
     */
    public void action() {
        if (action != null) {
            action.run();
        }
    }

    /**
     * Takes a color and fills the Button with it.
     * @param color new color for the Button
     */
    public void fill(Color color) {
        Graphics g = getImage().getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
    }

    /**
     * Takes a String and puts in on the Button
     * @param newText text to be displayed on the Button
     */
    public void updateLabel(String newText) {
        if (text != "") {
            text = newText;
        }
        label = new Textfield(getLocation().x, getLocation().y,  text);
        int labelWidth =  label.getWidth();
        int labelHeight =  label.getHeight();
        Graphics g = getImage().getGraphics();
        int drawPosX = (getWidth() - labelWidth)/2;
        int drawPosY = (getHeight() - labelHeight)/2;
        g.drawImage(label.getImage(), drawPosX, drawPosY, null);
        g.dispose();
    }

    /**
     * Takes the mouse location and compares it to the buttons corners.
     * If the mouse location is inside the Button, the Buttons action will be perormed.
     * @param mousePosition position to compare to the Button
     */
    public void actionCheck(Point mousePosition) {
        if (
            mousePosition.x >= getDrawPosition().x &&
            mousePosition.x <= getDrawPosition().x + getWidth() &&
            mousePosition.y >= getDrawPosition().y &&
            mousePosition.y <= getDrawPosition().y + getHeight()
        ) {
            action();
        }
    }

    /**
     * Basic set up for the different Button constructors.
     * @param width     width of the Button
     * @param height    height of the Button
     */
    private void setUpStandardButton(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        setImage(image);
    }
}

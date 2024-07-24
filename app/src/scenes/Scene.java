package app.src.scenes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.src.resources.Entity;
import app.src.resources.components.Button;
import app.src.resources.components.Component;

/**
 * Baseclass for any Scene.
 * @see Entity
 * @see Component
 * @see Button
 */
public class Scene {
    private List<Entity> entities;
    private List<Component> components;
    private List<Button> buttons;
    private String TAG;
    private Scene newScene;
    private Point mousepoint;
    private int counter;

    /**
     * Initialises the lists Entities, Components and Buttons.
     * Properties from these lists will be drawn in the Renderer.
     */
    public Scene() {
        counter = 0;
        entities = new ArrayList<>();
        components = new ArrayList<>();
        buttons = new ArrayList<>();
        // Fills the newScene Variable with itself to indicate, that the scene does not have to be changed
        setNewScene(this);
    }

    /**
     * Calls the update method of all Entities and Components.
     * Takes the playerlocation and updates the Entity list with all active Entities.
     * @param playerLocation to update all in all Entities.
     */
    public void update(Point playerLocation) {
        counter += 1;
        List<Entity> entitiesUpdate = new ArrayList<>();
        for (Entity entity: entities) {
            entity.update();
            entity.setPlayerLocation(playerLocation.x, playerLocation.y);
            if (entity.getState()) {
                entitiesUpdate.add(entity);
            }
        }
        entities = entitiesUpdate;
        for (Component component: components) {
            component.update();
        }
    }

    /**
     * Returns the counter of the Scene.
     * The counter is used for time based events.
     * @return Scene counter.
     */
    public int counter() {
        return counter;
    }

    /**
     * Set the Background image for your Scene object.
     * If not set the Background will be black.
     * Creates a new Component object to hold the image.
     * Registers the image to the components list of your scene.
     * @param bgName the name like "image.png" of your image in src/resources/assets/
     * @see Component
     */
    public void setBG(String bgName) {
        Component bg = new Component(bgName, 0, 0);
        bg.setLocation(bg.getWidth()/2, bg.getHeight()/2);
        registerComponent(bg);
    }

    /**
     * Takes x and y coordinates and stores them in the mousepoint variable
     * @param x x coordinate a new location
     * @param y y coordinate a new location
     */
    public void updateMouseLocation(int x, int y) {
        mousepoint = new Point(x, y);
    }

    /**
     * returns the private mousepoint variable
     * @return  the private Point object mousepoint
     * @see     Point
     */
    public Point getMousePoint() {
        return mousepoint;
    }

    /**
     * set the private newScene variable to a new scene
     * @param scene     a Scene object to set a new scene
     * @see             Scene
     */
    public void setNewScene(Scene scene) {
        newScene = scene;
    }

    /**
     * returns the private newScene variable
     * @return  the private Scene object newScene
     * @see     Scene
     */
    public Scene getNewScene() {
        return newScene;
    }

    /**
     * set the private TAG variable to a string
     * @param tag a String object to set a new location
     * @see String
     */
    public void setTAG(String tag) {
        TAG = tag;
    }

    /**
     * returns the private TAG variable
     * @return  the private String object TAG
     * @see String
     */
    public String getTAG() {
        return TAG;
    }

    /**
     * adds an Entity Object to the private list entities
     * @param entity a Entity object to add to the List entities
     * @see Entity
     */
    public void registerEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * removes an Entity object from the private list entities
     * @param entity a Entity object to remove from the List entities
     * @see Entity
     */
    public void unregisterEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * returns the private list of Entities
     * @return the private List object entities
     * @see Entity
     */
    public List<Entity> getEnties() {
        return entities;
    }

    /**
     * take a TAG  and returns all Entities with the provided TAG.
     * @param TAG Name of the TAG
     * @return all Entities with the provided TAG
     */
    public List<Entity> getEntitiesByTag(String TAG) {
        List<Entity> taggedEntities = new ArrayList<>();
        for (Entity entity: entities) {
            String entitiyTAG = entity.getTAG();
            if (entitiyTAG == TAG) {
                taggedEntities.add(entity);
            }
        }
        return taggedEntities;
    }

    /**
     * adds an Component object to the private list components
     * @param component a Component object to add to the List components
     * @see Component
     */
    public void registerComponent(Component component) {
        components.add(component);
    }

    /**
     * removes an Component Object from the private list components
     * @param component a Component object to remove from the List components
     * @see Component
     */
    public void unregisterComponent(Component component) {
        components.remove(component);
    }

    /**
     * returns the private list of Components
     * @return the private List object components
     * @see Component
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * adds an Button Object to the private list buttons
     * @param button a Button object to add to the List buttons
     * @see Button
     */
    public void registerButton(Button button) {
        buttons.add(button);
    }

    /**
     * removes an Button Object from the private list buttons
     * @param button a Button object to remove from the List buttons
     * @see Button
     */
    public void unregisterButton(Button button) {
        buttons.remove(button);
    }

    /**
     * returns the private List of Buttons
     * @return the private List object buttons
     * @see Button
     */
    public List<Button> getButtons() {
        return buttons;
    }
}
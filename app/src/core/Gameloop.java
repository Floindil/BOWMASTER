package app.src.core;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import app.src.StaticValues;
import app.src.StaticValues.SceneTag;
import app.src.resources.Arrow;
import app.src.resources.Entity;
import app.src.resources.monsters.Monster;
import app.src.scenes.Menu;
import app.src.scenes.Scene;
import app.src.scenes.SceneHandler;

/**
 * Handles Events regarding the gameloop.
 * @see Renderer
 * @see Controller
 * @see SceneHandler
 */
public class Gameloop {
    private Renderer bobRoss = new Renderer();
    private Controller controller = new Controller();
    private Menu menu = new Menu();
    private SceneHandler sceneHandler = new SceneHandler(menu);

    /**
     * Empty Constructor.
     */
    public Gameloop() {

    }

    /**
     * Starts the gameloop. Sets up the Renderer, SceneHandler and Controller
     * and reacts to Game Events
     */
    public void start() {
        bobRoss.setScene(menu);
        sceneHandler.setScene(menu, SceneTag.NEW);
        controller.setButtonList(menu.getButtons());
        controller.setupListeners(bobRoss.canvas);

        ActionListener updateTask = updateEvent -> {
            Scene activeScene = sceneHandler.getActive();
            updateScene(activeScene);
            hitCalculation(activeScene);
            bobRoss.repaint();
        };

        new Timer(60, updateTask).start();
    }

    private void updateScene(Scene activeScene) {
        Scene newScene = activeScene.getNewScene();
        if (sceneHandler.sceneCheck(newScene)) {
            sceneHandler.setScene(newScene, SceneTag.NEW);
            sceneHandler.startNew();
            bobRoss.setScene(sceneHandler.getActive());
            controller.setButtonList(newScene.getButtons());
        }
        Point mouseLocation = controller.getMousePos();
        activeScene.updateMouseLocation(mouseLocation.x, mouseLocation.y);
        activeScene.update();
    }

    private void hitCalculation(Scene activeScene) {

        String tag = activeScene.getTAG();
        if (tag == "level") {
            List<Entity> monsters = activeScene.getEntitiesByTag("monster");
            List<Entity> arrows = activeScene.getEntitiesByTag("arrow");

            for (Entity monsterEntity: monsters) {
                Monster monster = (Monster) monsterEntity;
                int monsterDistance = monster.getDistance();

                for (Entity entity: arrows) {
                    Arrow arrow = (Arrow) entity;
                    int arrowDistance1 = arrow.getDistance();
                    int arrowDistance2 = arrowDistance1 - arrow.getSpeed();
                    if (arrow.getShot()) {
                        if (monsterDistance >= arrowDistance1 && monsterDistance <= arrowDistance2) {
                            Point arrowhead = arrow.getHead();
                            int multiplier = monster.getMultiplier(arrowhead);
                            if (multiplier > 0) {
                                monster.updateHealth(- multiplier * StaticValues.BASEDAMAGE);
                                arrow.setState();
                                System.out.println("Hit detected! Remaining health " + monster.getHealth());
                            }
                        }
                    }
                }
            }
        }
    }
}
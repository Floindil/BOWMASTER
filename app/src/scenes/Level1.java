package app.src.scenes;

import java.awt.Point;

import app.src.resources.Bow;
import app.src.resources.Monster;

public class Level1 extends Scene {
    private Bow _bow;
    
    public Level1() {
        setTAG("level1");
        Monster gobclops = new Monster("gobclops.png", 100, 1);
        registerEntity(gobclops);
        gobclops.setMainHitbox(20, 20, 0, 0);

        _bow = new Bow();
        registerEntity(_bow);
    }

    @Override
    public void updateMousePosition(Point mousePoint) {
        super.updateMousePosition(mousePoint);
        _bow.updateMousePosition(mousePoint);
    }
}

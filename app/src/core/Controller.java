package app.src.core;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JPanel;

import app.src.resources.StaticValues;
import app.src.resources.components.Button;

/**
 * Handles Player input
 * @see MouseHandler
 * @see MouseTracker
 */
public class Controller extends JPanel{
    public final MouseHandler handler;
    private final MouseTracker tracker;
    private List<Button> _buttonlist;
    private Point _mousePosition;

    /**
     * Updates and returns the mouse position.
     * @return mouse position
     */
    public Point getMousePos() {
        update();
        return _mousePosition;
    }

    /**
     * Takes a list of Buttons and stores it in the buttonList.
     * @param buttonList
     */
    public void setButtonList(List<Button> buttonList) {
        _buttonlist = buttonList;
    }

    /**
     * Adds the required listeners to a JPanel
     * @param panel panel to add listeners
     */
    public void setupListeners(JPanel panel) {
        panel.addMouseListener(handler);
        panel.addMouseMotionListener(tracker);
    }

    /**
     * Updates the mouse position.
     */
    public void update() {
        _mousePosition = tracker.getLocation();
    }

    /**
     * Extends the MouseAdapter to add the actioncheck for the buttons.
     * @see MouseAdapter
     * @see Button
     */
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            for (Button b:_buttonlist) {
                b.actionCheck(_mousePosition);
            };
        }
    }

    /**
     * Extends the MouseMotionAdapter class to extract the current mouse location
     * @see MouseMotionAdapter
     */
    private class MouseTracker extends MouseMotionAdapter {

        private Point _location = new Point(0, 0);

        @Override
        public void mouseMoved(final MouseEvent e) {
            _location = e.getPoint();
            if (_location == null) {
                _location = new Point(0, 0);
            }
        }

        public Point getLocation() {
            return _location;
        }
    }

    /**
     * Constructor. Creates a Controller object.
     * Sets up a mouse handler and tracker.
     */
    public Controller() {
        setSize(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT);
        handler = new MouseHandler();
        tracker = new MouseTracker();
        update();
    }
}
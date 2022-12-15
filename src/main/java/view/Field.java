package view;

import controller.EventListener;
import model.Direction;
import model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler handler = new KeyHandler();
        addKeyListener(handler);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(), getHeight());
        Set<GameObject> gameObjects = view.getGameObjects().getAll();
        if (gameObjects == null) throw new RuntimeException("GameObjects is null!");
        for (GameObject object : gameObjects) {
            object.draw(g);
        }

    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case VK_RIGHT: eventListener.move(Direction.RIGHT); break;
                case VK_LEFT: eventListener.move(Direction.LEFT); break;
                case VK_UP: eventListener.move(Direction.UP); break;
                case VK_DOWN: eventListener.move(Direction.DOWN); break;
                case VK_R: eventListener.restart(); break;
            }
        }
    }
}

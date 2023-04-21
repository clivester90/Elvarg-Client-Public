package com.runescape.engine.impl;

import com.runescape.Client;
import com.runescape.cache.graphics.Slider;
import com.runescape.engine.GameEngine;

import javax.swing.*;
import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, FocusListener {

    public int clickType;
    public final int LEFT = 0;
    public final int RIGHT = 1;
    public final int DRAG = 2;
    public final int RELEASED = 3;
    public final int MOVE = 4;

    public static MouseHandler instance;
    public static volatile int idleCycles;
    public static volatile int currentButton;
    public static volatile long lastMoved;
    public static int mouseX;
    public static int mouseY;
    public static volatile int clickMode3;
    public static volatile int saveClickX;
    public static volatile int saveClickY;
    public static volatile long lastPressed;
    public static int lastButton;

    private int isInEvent;
    public int clickMode2;
    public int clickMode1;


    static {
        instance = new MouseHandler();
        idleCycles = 0;
        currentButton = 0;
        mouseX = -1;
        mouseY = -1;
        lastMoved = -1L;
        clickMode3 = 0;
        lastPressed = 0L;
        lastButton = 0;
        saveClickX = 0;
        saveClickY = 0;
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (instance != null) {
            currentButton = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!e.isConsumed()) {
            if (e.isPopupTrigger()) {
                e.consume();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (instance != null) {
            isInEvent++;
            try {
                idleCycles = 0;
                saveClickX = e.getX();
                saveClickY = e.getY();
                lastPressed = GameEngine.method2692();
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    MouseWheelHandler.mouseWheelDown = true;
                    MouseWheelHandler.mouseWheelX = e.getX();
                    MouseWheelHandler.mouseWheelY = e.getY();
                    return;
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    clickType = RIGHT;
                    clickMode1 = 2;
                    clickMode2 = 2;
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    clickType = LEFT;
                    clickMode1 = 1;
                    clickMode2 = 1;
                }
                Slider.handleSlider(saveClickX, saveClickY);
                if (e.isPopupTrigger()) {
                    e.consume();
                }

            } finally {
                isInEvent--;
            }
        }

        if (e.isPopupTrigger()) {
            e.consume();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseEvent mouseEvent = e;
        if (instance != null) {
            idleCycles = 0;
            currentButton = 0;

            if (!mouseEvent.isConsumed()) {
                isInEvent++;
                try {
                    MouseWheelHandler.mouseWheelDown = false;
                    clickMode2 = 0;
                    clickType = RELEASED;
                    if (mouseEvent.isPopupTrigger()) {
                        mouseEvent.consume();
                    }
                } finally {
                    isInEvent--;
                }
            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MouseEvent mouseEvent = e;

        if (!mouseEvent.isConsumed()) {
            isInEvent++;
            try {
                this.mouseMoved(mouseEvent);
            } finally {
                isInEvent--;
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (instance != null) {
            MouseEvent mouseEvent = e;

            if (!mouseEvent.isConsumed()) {
                isInEvent++;
                try {
                    idleCycles = 0;
                    mouseX = -1;
                    mouseY = -1;
                    lastMoved = mouseEvent.getWhen();
                } finally {
                    isInEvent--;
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MouseEvent mouseEvent = e;

        if (!mouseEvent.isConsumed()) {
            isInEvent++;
            try {
                if (MouseWheelHandler.mouseWheelDown) {
                    int y = MouseWheelHandler.mouseWheelX - mouseEvent.getX();
                    int k = MouseWheelHandler.mouseWheelY - mouseEvent.getY();
                    Client.instance.mouseWheelDragged(y, -k);
                    MouseWheelHandler.mouseWheelX = mouseEvent.getX();
                    MouseWheelHandler.mouseWheelY = mouseEvent.getY();
                    return;
                }
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
                clickType = DRAG;
                Slider.handleSlider(mouseX, mouseY);
            } finally {
                isInEvent--;
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MouseEvent mouseEvent = e;

        if (!mouseEvent.isConsumed()) {
            isInEvent++;
            try {
                mouseX = mouseEvent.getX();
                mouseY = mouseEvent.getY();
                clickType = MOVE;
            } finally {
                isInEvent--;
            }
        }

    }

}

package com.runescape.engine;

import java.awt.*;

public final class Canvas extends java.awt.Canvas {

    Component component;

    public Canvas(Component var1) {
        this.component = var1;
    }

    public void update(Graphics var1) {
        this.component.update(var1);
    }

    public void paint(Graphics var1) {
        this.component.paint(var1);
    }

}

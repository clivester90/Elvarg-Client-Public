package com.runescape.draw;

import com.runescape.Client;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.util.Hashtable;

public class RasterProvider extends AbstractRasterProvider {
    private Component component;
	private Image image;

    public RasterProvider(int width, int height, Component component) {
        super.width = width;
        super.height = height;
        super.pixels = new int[height * width + 1];
        DataBufferInt var4 = new DataBufferInt(super.pixels, super.pixels.length);
        DirectColorModel var5 = new DirectColorModel(32, 16711680, 65280, 255);
        WritableRaster var6 = Raster.createWritableRaster(var5.createCompatibleSampleModel(super.width, super.height), var4, (Point) null);
        this.image = new BufferedImage(var5, var6, false, new Hashtable());
        this.setComponent(component);
        this.apply();
    }

    public final void setComponent(Component var1) {
		this.component = var1;
	}

    public void drawFull(int var1, int var2) {
        this.drawFull0(this.component.getGraphics(), var1, var2);
    }

    public final void draw(int var1, int var2, int var3, int var4) {
        this.draw0(this.component.getGraphics(), var1, var2, var3, var4); // L: 45
    }

    private final void drawFull0(Graphics var1, int var2, int var3) {
        try {
            var1.drawImage(this.image, var2, var3, this.component);
        } catch (Exception var5) {
            this.component.repaint();
        }
    }

    final void draw0(Graphics var1, int var2, int var3, int var4, int var5) {
        try {
            Shape var6 = var1.getClip();
            var1.clipRect(var2, var3, var4, var5);
            var1.drawImage(this.image, 0, 0, this.component);
            var1.setClip(var6);
        } catch (Exception var7) {
            this.component.repaint();
        }

    }

}

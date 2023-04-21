package com.runescape.entity;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import com.runescape.Client;
import com.runescape.cache.def.ObjectDefinition;

import com.runescape.util.ObjectKeyUtil;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.Angle;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

/**
 * ObjectGenre = 2
 */
public final class GameObject {

    public int zLoc;
    public int tileHeight;
    public int xPos;
    public int yPos;
    public Renderable renderable;
    public int turnValue;
    public int xLocLow;
    public int xLocHigh;
    public int yLocHigh;
    public int yLocLow;
    public int anInt527;
    public int anInt528;
    public long uid;
    /**
     * mask = (byte)((objectRotation << 6) + objectType);
     */
    public byte mask;

}

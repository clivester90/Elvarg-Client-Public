package com.runescape.scene.object;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;

import com.runescape.Client;
import com.runescape.cache.def.ObjectDefinition;
import com.runescape.entity.Renderable;
import com.runescape.entity.model.Model;

import com.runescape.util.ObjectKeyUtil;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.geometry.Shapes;

public final class WallObject  {

	public int zLoc;
	public int tileHeights;
	public int xPos;
	public int yPos;
	public int orientation1;
	public int orientation2;
	public Renderable renderable1;
	public Renderable renderable2;
	public long uid;
	public byte mask;

}

package minigame;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class Actor {
	protected int number;
	private Image image;
	protected int posX, posY;
	protected boolean isMovable;
	protected boolean isEdible;
	static ArrayList<Actor> actors = new ArrayList<Actor>();
	
	public Actor(Image image, int posX, int posY, int number) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.isMovable = false;
		this.isEdible = false;
		this.number = number;
		Actor.actors.add(this);
	}
	
	public Actor(Image image, int posX, int posY, boolean isMovable, boolean isEdible) {
		this.image = image;
		this.posX = posX;
		this.posY = posY;
		this.isMovable = isMovable;
		this.isEdible = isEdible;
		Actor.actors.add(this);
	}
	
	static public void paintActors(Graphics g, ImageObserver io) {
		synchronized(Actor.actors) {
			for(Actor actor : Actor.actors) {
				g.drawImage(actor.image, actor.posX * Game.TILESIZE, actor.posY * Game.TILESIZE,
							Game.TILESIZE, Game.TILESIZE, io);
			}
		}
	}
	
	static public Actor existsAt(int x, int y) {
//		if(x == -1 || y == -1 || x == Game.NUMTILE_X || y == Game.NUMTILE_Y) return true;
		synchronized(Actor.actors) {
			for(Actor actor : Actor.actors) {
				if(actor.posX == x && actor.posY == y) {
					return actor;
				}
			}
			return null;
		}
	}
	
	static public boolean tileOut(int x, int y) {
		return (x == -1 || y == -1 || x == Game.NUMTILE_X || y == Game.NUMTILE_Y);
	}
	
	static public boolean frameOut(int x, int y) {
		return (x == (-1 * Stage.baseX) || y == (-1 * Stage.baseY) || x >= Game.NUMTILE_X - Stage.baseX - 1|| y >= Game.NUMTILE_Y - Stage.baseY - 1);
	}
	
	public boolean pushed(int x, int y) {
		if(this.isMovable) {
			if(Actor.existsAt(this.posX + x, this.posY + y) == null) {
				synchronized (Actor.actors) {
					this.posX += x; this.posY += y;
				}
				return true;
			}
		}
		return false;
	}
	
	public void eaten() { 
		if(this.isEdible) {
			synchronized(Actor.actors) {
				Actor.actors.remove(this);
			}
		}
	}
}

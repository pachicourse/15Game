package minigame;

import javax.swing.*;
import java.awt.*;

public class Stage extends JPanel {
	
	JFrame frame;
	static int baseX = 0, baseY = 0;
	static final int PANEL_W = Game.TILESIZE * Game.NUMTILE_X ;
	static final int PANEL_H = Game.TILESIZE * Game.NUMTILE_Y ;
	static Stage stage;
	
	public Stage() {
		this.frame = new JFrame("NE26-0111B 大橋拓馬");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		Insets is = this.frame.getInsets();
		frame.setSize(PANEL_W + is.left + is.right + 3, PANEL_H + is.top + is.bottom + 3);
		this.frame.setResizable(false);
		(this.frame.getContentPane()).add(this);
		Stage.stage = this;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.translate(this.baseX * Game.TILESIZE, this.baseY * Game.TILESIZE);
		g.setColor(Color.GRAY);
		for(int i = 0; i < Game.NUMTILE_X + 1; i++) {
			g.drawLine(i * Game.TILESIZE + 1, 1, i * Game.TILESIZE + 1, Game.TILESIZE * Game.NUMTILE_Y + 1);
		}
		for(int i =0; i < Game.NUMTILE_Y + 1; i++) {
			g.drawLine(1, i * Game.TILESIZE + 1, Game.TILESIZE * Game.NUMTILE_X + 1, i * Game.TILESIZE + 1);
		}
		
		//Actorの描画
		Actor.paintActors(g, this);
	}
}

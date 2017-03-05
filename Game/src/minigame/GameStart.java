package minigame;

import javax.swing.*;

import java.net.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GameStart {

	public GameStart() {
		JFrame frame = new JFrame();
		URL url = this.getClass().getResource("img/start1.png");
		Image image;
		
		try {
			image = frame.createImage((ImageProducer) url.getContent());
		} catch (Exception ex) {
			System.out.println("Error");
			image = null;
		}
		ImageIcon i = new ImageIcon(image);
		JLabel l = new JLabel(i);
		Insets is = frame.getInsets();
		frame.add(l);
		frame.pack();
		//frame.setSize(Stage.PANEL_W + is.left + is.right + 3, Stage.PANEL_H + is.top + is.bottom + 3);
		frame.setVisible(true);
		synchronized(this) {
			try {
				this.wait(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.setVisible(false);
		}
	}
	
}

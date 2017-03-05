package minigame;

import java.awt.Image;

public class Character extends Actor implements Runnable {
	private long speed;
	
	public Character(Image image, int posX, int posY, long speed) {
		super(image, posX, posY, 0);
		this.speed = speed;
		(new Thread(this)).start();
	}
	public Character(Image image, int posX, int posY, long speed, boolean isMovable, boolean isEdible) {
		super(image, posX, posY, isMovable, isEdible);
		this.speed = speed;
		(new Thread(this)).start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			//一定時間待ち
			try {
				Thread.sleep(this.speed);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			//Chara動かす
			int x = 0, y = 0;
			synchronized(Actor.actors) {
				if((int)(Math.random() * 2) == 0) { //0 = X軸移動 1 = Y軸移動
					x = (int)(Math.random() * 3) - 1; //-1,0,1のいずれか
				}
				else {
					y = (int)(Math.random() * 3) - 1;
				}
				//System.out.println(Actor.existsAt(this.posX + x, this.posY + y));
				if(Actor.existsAt(this.posX + x, this.posY + y) == null) {
					this.posX += x;
					this.posY += y;
				}
			}
			//再描画
			Stage.stage.repaint();
		}
		
	}

}

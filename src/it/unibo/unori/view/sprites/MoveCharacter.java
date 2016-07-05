package sprite;

import sprite.Character;
import sprite.CharacterView;
import sprite.CharacterSprite;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.Point;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class MoveCharacter extends JPanel implements KeyListener
{
	private Point position;
	private CharacterSprite sprite;

	private BufferedImage image;

	public MoveCharacter()
	{
		position = new Point(0, 0);
		sprite = new CharacterSprite(Character.CLOWN);

		image = sprite.getImage(CharacterView.FRONT);

		this.addKeyListener(this);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(image, position.x, position.y,
		            image.getWidth(), image.getHeight(), null);
	}

	private BufferedImage flip(BufferedImage image)
	{
		AffineTransform tx;
		tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(- image.getWidth(null), 0);

		AffineTransformOp op;
		op = new AffineTransformOp(tx,
								   AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

		return op.filter(image, null);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				BufferedImage[] frame = new BufferedImage[2];

				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					frame[0] = flip(sprite.getImage(CharacterView.LEFT));
					frame[1] = flip(sprite.getImage(CharacterView.LEFT2));

					position.translate(frame[0].getWidth(), 0);
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					frame[0] = sprite.getImage(CharacterView.LEFT);
					frame[1] = sprite.getImage(CharacterView.LEFT2);

					position.translate(- frame[0].getWidth(), 0);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					frame[0] = sprite.getImage(CharacterView.FRONT);
					frame[1] = sprite.getImage(CharacterView.FRONT2);

					position.translate(0, frame[0].getHeight());
				}
				if (e.getKeyCode() == KeyEvent.VK_UP)
				{
					frame[0] = sprite.getImage(CharacterView.BACK);
					frame[1] = sprite.getImage(CharacterView.BACK2);

					position.translate(0, - frame[0].getHeight());
				}

				image = frame[1];
				repaint();

				try { sleep(50); } catch (InterruptedException e) { }

				image = frame[0];
				repaint();
			}
		}.start();
	}

	@Override public void keyTyped(KeyEvent e) { }
	@Override public void keyReleased(KeyEvent e) { }

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		JPanel panel = new MoveSprite();

		frame.add(panel);
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

		panel.requestFocus();
	}
}
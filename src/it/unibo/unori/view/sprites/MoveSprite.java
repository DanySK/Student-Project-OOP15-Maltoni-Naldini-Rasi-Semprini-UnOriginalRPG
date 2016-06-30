package sprite;

import sprite.CharacterView;
import sprite.CharacterSprite;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MoveSprite extends JPanel implements KeyListener
{
	private Point position;
	private CharacterView view;
	private CharacterSprite sprite;

	public MoveSprite()
	{
		position = new Point(0, 0);
		view = CharacterView.FRONT1;
		sprite = new CharacterSprite(LoadImage());

		this.addKeyListener(this);
	}

	private BufferedImage LoadImage()
	{
		BufferedImage clown;

		try { clown = ImageIO.read(new File("res/clown.png"));
		} catch (IOException e) { clown = null; }

		return clown;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(sprite.getView(view),
					position.x, position.y,
		            sprite.getView(view).getWidth(),
					sprite.getView(view).getHeight(), null);
	}

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

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			view = CharacterView.LEFT1;
			position.translate(sprite.getView(view).getWidth(), 0);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			view = CharacterView.LEFT1;
			position.translate(- sprite.getView(view).getWidth(), 0);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			view = CharacterView.FRONT1;
			position.translate(0, sprite.getView(view).getHeight());
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			view = CharacterView.BACK1;
			position.translate(0, - sprite.getView(view).getHeight());
		}

		this.repaint();
	}

	@Override public void keyTyped(KeyEvent e) { }
	@Override public void keyReleased(KeyEvent e) { }
}

package sprite;

import sprite.Character;
import sprite.CharacterSprite;

import java.util.List;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterSelection extends JPanel
{
	private static int PARTY = 5;

	private Character character;
	private CharacterSprite sprite;
	private List<CharacterSprite> party;

	public CharacterSelection()
	{
		character = Character.values()[0];
		sprite = new CharacterSprite(character);
		party = new LinkedList<CharacterSprite>();

		Dimension size;
		this.setLayout(null);
		Insets insets = this.getInsets();

		JTextField textField = new JTextField(10);

		size = textField.getPreferredSize();
		textField.setBounds(25 + insets.left,
							25 + insets.top,
							size.width, size.height);

		textField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (party.size() <= PARTY) party.add(sprite);

				textField.setText("");
				repaint();
			}
		});

		this.add(textField);

		BasicArrowButton leftButton;
		leftButton = new BasicArrowButton(SwingConstants.WEST);

		size = leftButton.getPreferredSize();
		leftButton.setBounds(25 + insets.left,
							 75 + insets.top,
							 size.width, size.height);

		leftButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				character = character.previous();

				sprite = new CharacterSprite(character);
				repaint();
			}
		});

		this.add(leftButton);

		BasicArrowButton rightButton;
		rightButton = new BasicArrowButton(SwingConstants.EAST);

		size = rightButton.getPreferredSize();
		rightButton.setBounds(110 + insets.left,
							  75 + insets.top,
							  size.width, size.height);

		rightButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				character = character.next();

				sprite = new CharacterSprite(character);
				repaint();
			}
		});

		this.add(rightButton);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		BufferedImage image;

		image = sprite.getImage(CharacterView.BATTLE);

		g.drawImage(image, 60, 75,
					image.getWidth(), image.getHeight(), null);

		int i = 0;
		for (CharacterSprite sprite : party)
		{
			image = sprite.getImage(CharacterView.BATTLE);

			g.drawImage(image, 50 + i * 50, 150,
						image.getWidth(), image.getHeight(), null);
				
			i++;
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		JPanel panel = new CharacterSelection();

		frame.add(panel);
		frame.setSize(500, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

		panel.requestFocus();
	}
}
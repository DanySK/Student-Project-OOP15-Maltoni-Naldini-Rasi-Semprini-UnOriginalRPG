package sprite;

import sprite.Character;
import sprite.CharacterView;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.EnumMap;
import java.awt.image.BufferedImage;

public class CharacterSprite
{
	private Character character;
	private EnumMap<CharacterView, BufferedImage> sprite;

	public CharacterSprite(Character character)
	{
		this.character = character;
		sprite = new EnumMap<>(CharacterView.class);

		BufferedImage image;

		try { image = ImageIO.read(new File(character.getPath())); }
		catch (IOException e) { image = null; }

		for (CharacterView characterView : CharacterView.values())
		{
			sprite.put(characterView,
			           image.getSubimage(characterView.getPosition().x,
										 characterView.getPosition().y,
										 characterView.getDimension().width,
										 characterView.getDimension().height));
		}
	}

	public Character getCharacter()
	{
		return character;
	}

	public BufferedImage getImage(CharacterView characterView)
	{
		return sprite.get(characterView);
	}
}
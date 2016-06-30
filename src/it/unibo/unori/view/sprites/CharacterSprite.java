package sprite;

import sprite.CharacterView;

import java.util.EnumMap;
import java.awt.image.BufferedImage;

public class CharacterSprite
{
	private EnumMap<CharacterView, BufferedImage> sprite;

	public CharacterSprite(BufferedImage spriteSheet)
	{
		sprite = new EnumMap<>(CharacterView.class);

		for (CharacterView characterView : CharacterView.values())
		{
			sprite.put(characterView,
			           spriteSheet.getSubimage(characterView.getPosition().x,
			                                   characterView.getPosition().y,
			                                   characterView.getDimension().width,
			                                   characterView.getDimension().height));
		}
	}

	public BufferedImage getView(CharacterView characterView)
	{
		return sprite.get(characterView);
	}
}

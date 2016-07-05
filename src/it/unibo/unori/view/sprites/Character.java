package sprite;

public enum Character
{
	CLOWN(new String("res/clown.png")),
	COOK(new String("res/cook.png")),
	MAGE(new String("res/mage.png")),
	PALADIN(new String("res/paladin.png")),
	RANGER(new String("res/ranger.png")),
	WARRIOR(new String("res/warrior.png"));

	private final String path;

	private Character(String path)
	{
		this.path = path;
	}

	public Character next()
	{
		return values()[(ordinal() + 1) % values().length];
	}

	public Character previous()
	{
		if (ordinal() - 1 < 0) return values()[values().length - 1];
		else return values()[ordinal() - 1];
	}

	public String getPath() { return path; }
}

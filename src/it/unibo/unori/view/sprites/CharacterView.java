package sprite;

import java.awt.Point;
import java.awt.Dimension;

public enum CharacterView
{
	FRONT(new Point(0, 0), new Dimension(32, 32)),
	BACK(new Point(32, 0), new Dimension(32, 32)),
	LEFT(new Point(64, 0), new Dimension(32, 32)),

	FRONT2(new Point(0, 32), new Dimension(32, 32)),
	BACK2(new Point(32, 32), new Dimension(32, 32)),
	LEFT2(new Point(64, 32), new Dimension(32, 32)),

	BATTLE(new Point(0, 64), new Dimension(32, 48));

	private final Point position;
	private final Dimension dimension;

	private CharacterView(Point p, Dimension d)
	{
		this.position = p;
		this.dimension = d;
	}

	Point getPosition() { return position; }
	Dimension getDimension() { return dimension; }
}
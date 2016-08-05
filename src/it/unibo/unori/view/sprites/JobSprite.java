package it.unibo.unori.view.sprites;

import java.awt.Point;
import java.awt.Dimension;

/**
 *
 * This enum represents the various views of a character.
 *
 */
public enum JobSprite {
    FRONT(new Point(0, 0), new Dimension(32, 32)),
    BACK(new Point(32, 0), new Dimension(32, 32)),
    LEFT(new Point(64, 0), new Dimension(32, 32)),
    FRONT2(new Point(0, 32), new Dimension(32, 32)),
    BACK2(new Point(32, 32), new Dimension(32, 32)),
    LEFT2(new Point(64, 32), new Dimension(32, 32)),
    BATTLE(new Point(0, 64), new Dimension(32, 48));

    private final Point point;
    private final Dimension dimension;

    JobSprite(final Point point, final Dimension dimension) {
        this.point = point; this.dimension = dimension;
    }

    public Point getPosition() {
        return point;
    }
    public Dimension getDimension() {
        return dimension;
    }
}
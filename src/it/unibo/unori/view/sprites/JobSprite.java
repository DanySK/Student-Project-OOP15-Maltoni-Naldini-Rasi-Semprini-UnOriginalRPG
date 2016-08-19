package it.unibo.unori.view.sprites;

import java.awt.Point;
import java.awt.Dimension;

/**
 *
 * This enum represents the various views of a character.
 *
 */
public enum JobSprite {
    /**
     * Front view.
     */
    FRONT(new Point(0, 0), new Dimension(32, 32)),
    /**
     * Back view.
     */
    BACK(new Point(32, 0), new Dimension(32, 32)),
    /**
     * Left view.
     */
    LEFT(new Point(64, 0), new Dimension(32, 32)),
    /**
     * Second left view.
     */
    FRONT2(new Point(0, 32), new Dimension(32, 32)),
    /**
     * Second back view.
     */
    BACK2(new Point(32, 32), new Dimension(32, 32)),
    /**
     * Second left view.
     */
    LEFT2(new Point(64, 32), new Dimension(32, 32)),
    /**
     * Second battle view.
     */
    BATTLE(new Point(0, 64), new Dimension(32, 48));

    private final Point point;
    private final Dimension dimension;

    JobSprite(final Point point, final Dimension dimension) {
        this.point = point;
        this.dimension = dimension;
    }

    /**
     * @return the position of this sub-image
     */
    public Point getPosition() {
        return point;
    }

    /**
     * @return the dimension of this sub-image
     */
    public Dimension getDimension() {
        return dimension;
    }
}

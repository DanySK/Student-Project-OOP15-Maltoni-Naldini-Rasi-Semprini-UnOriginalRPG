package it.unibo.unori.model.menu.utility;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented. 
 */
public class Pair<X,Y> {
	
	private final X x;
	private final Y y;
	
	/**
	 * Standard constructor for a Pair of Objects.
	 * @param x the Object in position X.
	 * @param y the Object in position Y.
	 */
	public Pair(X x, Y y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor that builds a Pair from an existing Pair.
	 * @param other the other Pair.
	 */
	public Pair(final Pair<X, Y> other) {
	    this.x = other.getX();
	    this.y = other.getY();
	}
	
	/**
	 * Getter method that returns the X Object.
	 * @return the X Object.
	 */
	public X getX() {
		return x;
	}
	
	/**
         * Getter method that returns the Y Object.
         * @return the Y Object.
         */
	public Y getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
		    return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair other = (Pair) obj;
		if (x == null) {
			if (other.x != null) {
				return false;
			}
		} else if (!x.equals(other.x)) {
			return false;
		}
		if (y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!y.equals(other.y)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
	

}

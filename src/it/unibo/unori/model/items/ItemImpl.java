package it.unibo.unori.model.items;

/**
 * Class to implement a key object.
 *
 */
public class ItemImpl implements Item {

    /**
     * 
     */
    private static final long serialVersionUID = 9211034710548463840L;
    private final String name;
    private final String desc;
    private static final int PRIME = 31;
    /**
     * Basic item for the game, a key.
     */
    public static final Item KEY = new ItemImpl("Chiave", "Una piccola chiave " + "per ogni evenienza");

    /**
     * Constructor with two input string, one for the name and the other for the
     * description.
     * 
     * @param name
     *            name of the item
     * @param desc
     *            description of the item
     */
    public ItemImpl(final String name, final String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }

    @Override
    public int hashCode() {

        int result = 1;
        result = PRIME * result + ((desc == null) ? 0 : desc.hashCode());
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final ItemImpl tmp = (ItemImpl) o;
        return this.name.equals(tmp.getName()) 
            && this.desc.equals(tmp.getDescription());
    }

    @Override
    public String toString() {
        return this.name;
    }

}

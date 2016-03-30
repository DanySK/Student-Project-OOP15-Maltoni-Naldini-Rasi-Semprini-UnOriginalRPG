package it.unibo.unori.model.items;

/**
 * A Generic Class that implements Item.
 * Created to model an inheritance tree of Items, as the root of the tree.
 */
public class ObjectItem implements Item {

    /**
     * 
     */
    private static final long serialVersionUID = 3157546793105498392L;
    private String name = "Generic Item";
    private String description = "A generic Item, not useful";
    private int quantity = 1;
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}

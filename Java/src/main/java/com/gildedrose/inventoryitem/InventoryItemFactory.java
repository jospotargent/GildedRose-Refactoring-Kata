package com.gildedrose.inventoryitem;

import com.gildedrose.Item;
import com.gildedrose.inventoryitem.strategy.AgedBrieStrategy;
import com.gildedrose.inventoryitem.strategy.BackstagePassStrategy;
import com.gildedrose.inventoryitem.strategy.ConjuredStrategy;
import com.gildedrose.inventoryitem.strategy.RegularItemStrategy;
import com.gildedrose.inventoryitem.strategy.SulfurasStrategy;

/**
 * Factory class responsible for creating and instantiating InventoryItem objects
 * with appropriate behavior strategies based on the item type.
 * <p/>
 * The InventoryItemFactory provides utility methods to handle item creation
 * based on the items of the Gilded Rose inventory.
 */
public class InventoryItemFactory {

    private InventoryItemFactory() {
    }

    public static InventoryItem createItem(String name, int sellIn, int quality) {
        return createItem(new Item(name, sellIn, quality));
    }

    public static InventoryItem createItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        ItemType itemType = ItemType.fromString(item.name);

        switch (itemType) {
            case AGED_BRIE:
                return new InventoryItem(item, new AgedBrieStrategy());
            case BACKSTAGE_PASS:
                return new InventoryItem(item, new BackstagePassStrategy());
            case SULFURAS:
                return new InventoryItem(item, new SulfurasStrategy());
            case CONJURED:
                return new InventoryItem(item, new ConjuredStrategy());
            case REGULAR:
            default:
                return new InventoryItem(item, new RegularItemStrategy());
        }
    }
}

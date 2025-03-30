package com.gildedrose.inventoryitem;

import com.gildedrose.Item;
import com.gildedrose.inventoryitem.strategy.QualityUpdateStrategy;

/**
 * Implements an Inventory item from the Gilded Rose inventory.
 */
public class InventoryItem {

    private final Item item;
    private final QualityUpdateStrategy qualityUpdateStrategy;

    protected InventoryItem(Item item, QualityUpdateStrategy qualityUpdateStrategy) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (qualityUpdateStrategy == null) {
            throw new IllegalArgumentException("QualityUpdateStrategy cannot be null.");
        }
        this.item = item;
        this.qualityUpdateStrategy = qualityUpdateStrategy;
    }

    public String getName() {
        return item.name;
    }

    public int getQuality() {
        return item.quality;
    }

    public int getSellIn() {
        return item.sellIn;
    }

    public void updateQuality() {
        qualityUpdateStrategy.updateQuality(item);
    }

}

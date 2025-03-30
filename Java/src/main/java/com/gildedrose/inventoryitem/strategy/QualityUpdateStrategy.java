package com.gildedrose.inventoryitem.strategy;

import com.gildedrose.Item;

/**
 * Interface for QualityUpdate strategy for InventoryItem.
 */
public interface QualityUpdateStrategy {
    void updateQuality(Item item);
}

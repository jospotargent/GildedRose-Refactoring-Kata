package com.gildedrose.inventoryitem.strategy;

import com.gildedrose.Item;

/**
 * QualityUpdateStrategy for Regular items.
 */
public class RegularItemStrategy extends BaseQualityUpdateStrategy
    implements QualityUpdateStrategy {

    @Override
    public void updateQuality(Item item) {
        decreaseQuality(item);
        decreaseSellIn(item);

        if (sellInExpired(item)) {
            decreaseQuality(item);
        }
    }
}

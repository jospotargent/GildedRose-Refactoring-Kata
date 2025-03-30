package com.gildedrose.inventoryitem.strategy;

import com.gildedrose.Item;

/**
 * QualityUpdateStrategy for AgedBrie items.
 */
public class AgedBrieStrategy extends BaseQualityUpdateStrategy
    implements QualityUpdateStrategy {

    @Override
    public void updateQuality(Item item) {
        increaseQuality(item);
        decreaseSellIn(item);

        if (sellInExpired(item)) {
            increaseQuality(item);
        }
    }
}

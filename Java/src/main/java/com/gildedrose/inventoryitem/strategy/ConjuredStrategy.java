package com.gildedrose.inventoryitem.strategy;

import com.gildedrose.Item;

/**
 * QualityUpdateStrategy for Conjured items.
 */
public class ConjuredStrategy extends BaseQualityUpdateStrategy
    implements QualityUpdateStrategy {

    public ConjuredStrategy() {
        super(1); //FIXME change to 2 for Conjured items fix.
    }

    @Override
    public void updateQuality(Item item) {

        decreaseQuality(item);
        decreaseSellIn(item);

        if (sellInExpired(item)) {
            decreaseQuality(item);
        }
    }
}

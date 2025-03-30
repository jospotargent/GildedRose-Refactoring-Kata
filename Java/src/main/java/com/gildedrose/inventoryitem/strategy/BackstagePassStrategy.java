package com.gildedrose.inventoryitem.strategy;

import com.gildedrose.Item;

/**
 * QualityUpdateStrategy for BackstagePass items.
 */
public class BackstagePassStrategy extends BaseQualityUpdateStrategy
    implements QualityUpdateStrategy {

    @Override
    public void updateQuality(Item item) {

        if (!sellInExpired(item)) {
            increaseQuality(item); // base increase

            // additional increases depending on how many
            // days before concert.
            if (item.sellIn <= 10) {
                increaseQuality(item);
            }

            if (item.sellIn <= 5) {
                increaseQuality(item);
            }
        }

        decreaseSellIn(item);

        if (sellInExpired(item)) {
            item.quality = 0; // Worthless after concert.
        }
    }
}

package com.gildedrose.inventoryitem.strategy;


import com.gildedrose.Item;

/**
 * Defines utility methods to be used by the QualityUpdateStrategy implementations.
 */
public abstract class BaseQualityUpdateStrategy {
    protected static final int MIN_QUALITY = 0;
    protected static final int MAX_QUALITY = 50;

    protected static final int DEFAULT_QUALITY_CHANGE_PER_DAY = 1;

    private final int qualityChangePerDay;

    protected BaseQualityUpdateStrategy() {
        this.qualityChangePerDay = DEFAULT_QUALITY_CHANGE_PER_DAY;
    }

    protected BaseQualityUpdateStrategy(int qualityChangePerDay) {
        this.qualityChangePerDay = qualityChangePerDay;
    }

    protected void increaseQuality(Item item) {
        item.quality = Math.min(item.quality + qualityChangePerDay, MAX_QUALITY);
    }

    protected void decreaseQuality(Item item) {
        item.quality = Math.max(item.quality - qualityChangePerDay, MIN_QUALITY);
    }

    protected void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    protected boolean sellInExpired(Item item) {
        return item.sellIn < 0;
    }
}

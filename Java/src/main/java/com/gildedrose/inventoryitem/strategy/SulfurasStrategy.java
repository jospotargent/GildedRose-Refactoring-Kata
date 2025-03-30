package com.gildedrose.inventoryitem.strategy;

import com.gildedrose.Item;

/**
 * QualityUpdateStrategy for Sulfuras items.
 */
public class SulfurasStrategy extends BaseQualityUpdateStrategy
    implements QualityUpdateStrategy {

    @Override
    public void updateQuality(Item item) {
        // nothing changes for Sulfuras.
    }
}

package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gildedrose.inventoryitem.InventoryItem;
import com.gildedrose.inventoryitem.InventoryItemFactory;
import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void checkName() {
        InventoryItem item = InventoryItemFactory.createItem("foo", 0, 0);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "foo", -1, 0);
    }

    @Test
    void regularItem_decreasesQualityAndSellIn() {
        InventoryItem item = InventoryItemFactory.createItem("regular", 5, 10);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "regular", 4, 9);

    }



    @Test
    void regularItem_decreasesQuality_by_2_when_sellIn_lessThen_0() {
        InventoryItem item = InventoryItemFactory.createItem("regular", 0, 10);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "regular", -1, 8);
    }

    @Test
    void regularItem_quality_neverNegative() {
        InventoryItem item = InventoryItemFactory.createItem("regular", 5, 0);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "regular", 4, 0);
    }

    @Test
    void regularItem_quality_lowersTwiceAsFastAfterSellIn_not_negative() {
        InventoryItem item = InventoryItemFactory.createItem("regular", 0, 1);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "regular", -1, 0);
    }

    @Test
    void agedBrie_quality_increases_by_1_when_sellIn_greaterThen_0() {
        InventoryItem item = InventoryItemFactory.createItem("Aged Brie", 5, 10);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "Aged Brie", 4, 11);
    }

    @Test
    void agedBrie_quality_increases_by_2_when_sellIn_lessThen_0() {
        InventoryItem item = InventoryItemFactory.createItem("Aged Brie", 0, 10);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "Aged Brie", -1, 12);
    }



    @Test
    void agedBrie_quality_neverExceeds50() {
        InventoryItem item = InventoryItemFactory.createItem("Aged Brie", 10, 50);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "Aged Brie", 9, 50);
    }

    @Test
    void agedBrie_qualityIncrease_overMultipleDays() {
        final int initialSellIn = 12;
        final int initialQuality = 2;

        InventoryItem item = InventoryItemFactory.createItem("Aged Brie", initialSellIn, initialQuality);
        GildedRose app = new GildedRose(item);

        int numberOfDays = 10;
        for(int i = 0; i < numberOfDays; i++) {
            app.updateQuality();
        }

        assertInventoryItem(item, "Aged Brie",
            initialSellIn - numberOfDays, initialQuality + numberOfDays);
    }

    @Test
    void agedBrie_qualityIncrease_overMultipleDays_0_sellIn_case() {
        final int initialSellIn = 5;
        final int sellInDaysNegative = 3;
        final int initialQuality = 2;

        InventoryItem item = InventoryItemFactory.createItem("Aged Brie", initialSellIn, initialQuality);
        GildedRose app = new GildedRose(item);

        int numberOfDays = initialSellIn + sellInDaysNegative;
        for(int i = 0; i < numberOfDays; i++) {
            app.updateQuality();
        }

        // Quality goes up by 1 when above 0, by 2 when 0 or smaller.
        assertInventoryItem(item, "Aged Brie",
            initialSellIn - numberOfDays,
            initialQuality + initialSellIn + 2 * sellInDaysNegative);
    }


    @Test
    void sulfuras_neverChangesQualityOrSellIn() {
        InventoryItem item = InventoryItemFactory.createItem("Sulfuras, Hand of Ragnaros", 5, 80);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "Sulfuras, Hand of Ragnaros", 5, 80);
    }

    @Test
    void sulfuras_neverChangesQualityOrSellIn_evenSellInZero() {
        InventoryItem item = InventoryItemFactory.createItem("Sulfuras, Hand of Ragnaros", 0, 80);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "Sulfuras, Hand of Ragnaros", 0, 80);
    }

    @Test
    void backstagePasses_qualityBehavior_overMultipleDays() {
        InventoryItem item = InventoryItemFactory.createItem(
            "Backstage passes to a TAFKAL80ETC concert", 13, 25);
        GildedRose app = new GildedRose(item);

        // Expected quality values for 12 days,
        // including concert day (day 13) and day after.
        int[] expectedQualities = new int[] {
            26, // day 1,  sellIn = 12 (> 10: +1)
            27, // day 2,  sellIn = 11 (> 10: +1)
            28, // day 3,  sellIn = 10 (<= 10: + 2)
            30, // day 4,  sellIn = 9  (<= 10: + 2)
            32, // day 5,  sellIn = 8  (<= 10: + 2)
            34, // day 6,  sellIn = 7  (<= 10: + 2)
            36, // day 7,  sellIn = 6  (<= 10: + 2)
            38, // day 8,  sellIn = 5  (<= 5 : + 3)
            41, // day 9,  sellIn = 4  (<= 5 : + 3)
            44, // day 10, sellIn = 3  (<= 5 : + 3)
            47, // day 11, sellIn = 2  (<= 5 : + 3)
            50, // day 12, sellIn = 1  (<= 5 : + 3, limited to max 50)
            50, // day 13, sellIn = 0  (<= 5 : + 3, limited to max 50)
            0,  // day 14, sellIn = -1 (after event date: quality = 0)
            0   // day 15, sellIn = -2 (after event date: quality = 0)
        };

        for (int expectedQuality : expectedQualities) {
            app.updateQuality();
            assertEquals(expectedQuality, item.getQuality());
        }

        assertEquals(-2, item.getSellIn());
    }

    @Test
    void conjuredItem_qualityDecreasesTwiceAsFast() {
        InventoryItem item = InventoryItemFactory.createItem("Conjured Mana Cake", 3, 6);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        //FIXME change from 5 to 6 for Conjured Items fix.
        assertInventoryItem(item, "Conjured Mana Cake", 2, 5);
    }

    @Test
    void conjuredItem_decreasesQuality_by_2_when_sellIn_lessThen_0() {
        InventoryItem item = InventoryItemFactory.createItem("Conjured Mana Cake", 0, 10);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        //FIXME change from 8 to 6 for Conjured Items fix.
        assertInventoryItem(item, "Conjured Mana Cake", -1, 8);
    }

    @Test
    void conjuredItem_quality_neverNegative() {
        InventoryItem item = InventoryItemFactory.createItem("Conjured Mana Cake", 5, 1);
        GildedRose app = new GildedRose(item);

        app.updateQuality();

        assertInventoryItem(item, "Conjured Mana Cake", 4, 0);
    }


    private void assertInventoryItem(InventoryItem item, String name, int sellIn, int quality) {
        assertEquals(name, item.getName());
        assertEquals(sellIn, item.getSellIn());
        assertEquals(quality, item.getQuality());
    }

}

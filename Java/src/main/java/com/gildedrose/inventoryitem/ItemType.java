package com.gildedrose.inventoryitem;

public enum ItemType {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    CONJURED("Conjured Mana Cake"),
    REGULAR("");

    private final String itemName;

    ItemType(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Converts a string to the corresponding ItemType enum value based on its associated item name.
     * If no match is found, the default REGULAR type is returned.
     */
    public static ItemType fromString(String name) {
        for (ItemType type : values()) {
            if (name.equals(type.itemName)) {
                return type;
            }
        }
        return REGULAR;
    }
}

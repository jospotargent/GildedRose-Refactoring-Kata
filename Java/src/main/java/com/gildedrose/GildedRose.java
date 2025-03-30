package com.gildedrose;

import com.gildedrose.inventoryitem.InventoryItem;
import com.gildedrose.inventoryitem.InventoryItemFactory;
import java.util.ArrayList;
import java.util.List;

class GildedRose {
    List<InventoryItem> itemList = new ArrayList<>();

    public GildedRose(Item[] items) {
        for (Item value : items) {
            InventoryItem item = InventoryItemFactory.createItem(value);
            itemList.add(item);
        }
    }

    public GildedRose(InventoryItem... items) {
        for (InventoryItem item : items) {
            if (item != null) {
                itemList.add(item);
            }
        }
    }

    public void updateQuality() {
        for (InventoryItem item : itemList) {
            item.updateQuality();
        }
    }
}

package xyz.muricans.itemsauce;

import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ItemSauceRarity {
    LEGENDARY()
    ,EPIC()
    ,RARE()
    ,UNCOMMON()
    ,COMMON();

    private final String tooltipName;
    private final Formatting color;
    private final List<String> items;
    private static final Map<String, ItemSauceRarity> RARITY_ITEMS = new HashMap<>();

    ItemSauceRarity() {
        this.tooltipName = this.name().charAt(0) + this.name().toLowerCase().substring(1);
        this.color = ItemSauce.CONFIG.get(this).getColor();
        this.items = ItemSauce.CONFIG.get(this).getItems();
    }

    public static void populate(String id, ItemSauceRarity rarity) {
        RARITY_ITEMS.put(id, rarity);
    }

    public static ItemSauceRarity of(String id) {
        if(LEGENDARY.getItems().stream().anyMatch(id::contains)) return LEGENDARY;
        else if(EPIC.getItems().stream().anyMatch(id::contains)) return EPIC;
        else if(RARE.getItems().stream().anyMatch(id::contains)) return RARE;
        else if(UNCOMMON.getItems().stream().anyMatch(id::contains)) return UNCOMMON;
        else if(COMMON.getItems().stream().anyMatch(id::contains)) return COMMON;
        return null;
    }

    public static ItemSauceRarity get(String id) {
        return RARITY_ITEMS.get(id);
    }

    public Formatting getColor() {
        return color;
    }

    public List<String> getItems() {
        return items;
    }

    public String getTooltipName() {
        return tooltipName;
    }
}

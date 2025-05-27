package xyz.muricans.itemsauce;

import net.minecraft.util.Formatting;

import java.util.List;

public enum ItemSauceRarity {
    LEGENDARY()
    ,EPIC()
    ,RARE()
    ,UNCOMMON()
    ,COMMON();

    private final String tooltipName;
    private final Formatting color;
    private final List<String> items;

    ItemSauceRarity() {
        this.tooltipName = this.name().toLowerCase().substring(0, 1).toUpperCase() + this.name().toLowerCase().substring(1);
        this.color = ItemSauce.config.get(this).getColor();
        this.items = ItemSauce.config.get(this).getItems();
    }

    public static ItemSauceRarity of(String id) {
        if(LEGENDARY.getItems().stream().anyMatch(id::contains)) return LEGENDARY;
        else if(EPIC.getItems().stream().anyMatch(id::contains)) return EPIC;
        else if(RARE.getItems().stream().anyMatch(id::contains)) return RARE;
        else if(UNCOMMON.getItems().stream().anyMatch(id::contains)) return UNCOMMON;
        else if(COMMON.getItems().stream().anyMatch(id::contains)) return COMMON;
        return null;
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

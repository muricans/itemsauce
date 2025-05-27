package xyz.muricans.itemsauce.config;

import net.minecraft.util.Formatting;

import java.util.List;

public class ItemSauceConfigType {
    private final Formatting color;
    private final List<String> items;

    protected ItemSauceConfigType(Formatting color, List<String> items) {
        this.color = color;
        this.items = items;
    }

    public Formatting getColor() {
        return color;
    }

    public List<String> getItems() {
        return items;
    }
}

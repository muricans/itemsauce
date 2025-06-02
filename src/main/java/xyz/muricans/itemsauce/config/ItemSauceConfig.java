package xyz.muricans.itemsauce.config;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import net.minecraft.util.Formatting;
import xyz.muricans.itemsauce.ItemSauce;
import xyz.muricans.itemsauce.ItemSauceRarity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ItemSauceConfig {
    private boolean useBakedInRarity = true;
    private Map<String, Object> legendary = makeRarity(Formatting.LIGHT_PURPLE, "nether_star", "enchanted_golden_apple", "silence");
    private Map<String, Object> epic = makeRarity(Formatting.GOLD, "netherite", "elytra", "ancient_debris");
    private Map<String, Object> rare = makeRarity(Formatting.RED, "emerald", "diamond", "amethyst", "breeze", "golden_apple");
    private Map<String, Object> uncommon = makeRarity(Formatting.DARK_GREEN, "gold", "ender", "blaze", "trim", "potion");
    private Map<String, Object> common = makeRarity(Formatting.GRAY, "put_item_names_here");

    public static ItemSauceConfig initialize(Path path) {
        ItemSauceConfig config;
        if(Files.exists(path)) config = new Toml().read(path.toFile()).to(ItemSauceConfig.class);
        else config = new ItemSauceConfig();
        try {
            new TomlWriter().write(config, path.toFile());
        } catch(IOException e) {
            ItemSauce.LOGGER.error("[itemsauce] There was an error generating the config", e);
        }
        return config;
    }

    private Map<String, Object> makeRarity(Formatting color, String... items) {
        return Map.of("color", color.name(), "items", Arrays.asList(items));
    }

    public boolean usesBakedInRarity() {
        return useBakedInRarity;
    }

    public Map<String, Object> getRaw(String name) {
        switch (name) {
            case "legendary" -> {
                return legendary;
            }
            case "epic" -> {
                return epic;
            }
            case "rare" -> {
                return rare;
            }
            case "uncommon" -> {
                return uncommon;
            }
            case "common" -> {
                return common;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public ItemSauceConfigType get(String name) {
        Map<String, Object> configValue = this.getRaw(name);
        if(configValue == null) return null;
        Formatting color = Formatting.AQUA;
        try {
            color = Formatting.valueOf(((String) configValue.get("color")).toUpperCase());
        } catch (IllegalArgumentException e) {
            ItemSauce.LOGGER.error("[itemsauce] Invalid color type in config", e);
        }
        return new ItemSauceConfigType(color, (List<String>) configValue.get("items"));
    }

    public ItemSauceConfigType get(ItemSauceRarity rarity) {
        return this.get(rarity.name().toLowerCase());
    }

    public record ItemSauceConfigType(Formatting color, List<String> items) {
        public List<String> getItems() {
            return items;
        }

        public Formatting getColor() {
            return color;
        }
    }
}
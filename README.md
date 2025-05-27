# itemsauce

Fabric mod for Minecraft 1.21.1

Generates a configuration file upon first launch.

```toml
#Use minecraft's default rarity as a fallback
useBakedInRarity = true

[legendary]
color = "LIGHT_PURPLE"
items = ["nether_star", "enchanted_golden_apple", "silence"]

[epic]
color = "GOLD"
items = ["netherite", "elytra", "ancient_debris"]

[rare]
color = "RED"
items = ["emerald", "diamond", "amethyst", "breeze", "golden_apple"]

[uncommon]
color = "DARK_GREEN"
items = ["gold", "ender", "blaze", "trim", "potion"]

[common]
color = "GRAY"
items = ["put_item_names_here"]
```

Colors should follow the name of the color code in the [wiki](https://minecraft.wiki/w/Formatting_codes#Color_codes). (JE Edition codes only)  
Item names put in the items tab should follow their minecraft id. You can see an item's id in-game by using F3+H.  
Items are simply checked to see if they contain the keyword. E.g. if you put 'oak' in legendary, all items with 'oak' in their minecraft id will be legendary.
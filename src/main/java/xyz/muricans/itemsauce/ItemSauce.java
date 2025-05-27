package xyz.muricans.itemsauce;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.muricans.itemsauce.config.ItemSauceConfig;

import java.util.List;

public class ItemSauce implements ClientModInitializer {
	public static final String MOD_ID = "itemsauce";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ItemSauceConfig CONFIG = ItemSauceConfig.initialize(FabricLoader.getInstance().getConfigDir().resolve("itemsauce.toml"));

	@Override
	public void onInitializeClient() {
		LOGGER.info("[itemsauce] Initialized");
	}

	public static void addText(List<Text> list, String rarity, Formatting color) {
		list.add(1, Text.of(color + rarity));
	}
}
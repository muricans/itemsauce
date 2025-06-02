package xyz.muricans.itemsauce;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.muricans.itemsauce.config.ItemSauceConfig;

public class ItemSauce implements ClientModInitializer {
	public static final String MOD_ID = "itemsauce";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ItemSauceConfig CONFIG = ItemSauceConfig.initialize(FabricLoader.getInstance().getConfigDir().resolve("itemsauce.toml"));

	@Override
	public void onInitializeClient() {
		LOGGER.info("[itemsauce] Initialized");
		this.initializeEvents();
	}

	private void initializeEvents() {
		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, list) -> {
			String id = stack.getRegistryEntry().getIdAsString();
			ItemSauceRarity rarity = ItemSauceRarity.get(id);
			if(rarity != null) list.add(1, Text.of(rarity.getColor() + rarity.getTooltipName()));
		});
	}
}
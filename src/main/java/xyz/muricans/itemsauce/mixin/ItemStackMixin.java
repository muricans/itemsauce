package xyz.muricans.itemsauce.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.muricans.itemsauce.ItemSauce;
import xyz.muricans.itemsauce.ItemSauceRarity;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(ItemStack.class)
public class ItemStackMixin {
	@Inject(at = @At("TAIL"), method = "getTooltip", locals = LocalCapture.CAPTURE_FAILSOFT)
	private void itemsauce_afterGetTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> info, List<Text> list) {
		ItemStack self = (ItemStack) (Object) this;
		if(self == null || player == null) return;
		String id = self.getRegistryEntry().getIdAsString();
		ItemSauceRarity rarity = ItemSauceRarity.of(id);
		if(rarity != null) ItemSauce.addText(list, rarity.getTooltipName(), rarity.getColor());
		else if(ItemSauce.config.usesBakedInRarity()) {
			rarity = ItemSauceRarity.valueOf(self.getRarity().name());
			if(rarity != null) ItemSauce.addText(list, rarity.getTooltipName(), rarity.getColor());
		}
	}
}
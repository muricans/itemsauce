package xyz.muricans.itemsauce.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.muricans.itemsauce.ItemSauce;
import xyz.muricans.itemsauce.ItemSauceRarity;

@Environment(EnvType.CLIENT)
@Mixin(Registry.class)
public interface RegistryMixin {
    @Inject(at = @At("TAIL"), method = "register(Lnet/minecraft/registry/Registry;Lnet/minecraft/registry/RegistryKey;Ljava/lang/Object;)Ljava/lang/Object;")
    private static <V, T extends V> void itemsauce_afterRegister(Registry<V> registry, RegistryKey<V> key, T entry, CallbackInfoReturnable<T> info) {
        if(registry != Registries.ITEM) return;
        Item item = ((Item) entry);
        if(item == null) return;
        ItemStack stack = item.getDefaultStack();
        String id = stack.getRegistryEntry().getIdAsString();
        ItemSauceRarity rarity = ItemSauceRarity.of(id);
        if(rarity != null) ItemSauceRarity.populate(id, rarity);
        else if(ItemSauce.CONFIG.usesBakedInRarity()) {
            try {
                rarity = ItemSauceRarity.valueOf(stack.getRarity().name());
                ItemSauceRarity.populate(id, rarity);
            } catch (IllegalArgumentException e) {
                ItemSauce.LOGGER.error("[itemsauce] Invalid rarity type", e);
            }
        }
    }
}

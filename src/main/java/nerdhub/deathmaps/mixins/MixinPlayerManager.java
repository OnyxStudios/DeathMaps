package nerdhub.deathmaps.mixins;

import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TextFormat;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ServerPlayerEntity.class, priority = 5000)
public class MixinPlayerManager {

    @Inject(method = "copyFrom", at = @At("RETURN"))
    private void onRespawn(ServerPlayerEntity player, boolean alive, CallbackInfo ci){
        if (!alive){
            ItemStack itemStack = FilledMapItem.createMap(player.getServerWorld(), player.getBlockPos().getX(), player.getBlockPos().getZ(), (byte) 2,true,true);
            MapState.addDecorationsTag(itemStack, player.getBlockPos(),"Death", MapIcon.Type.TARGET_X);
            itemStack.setDisplayName(new StringTextComponent("Grave Digger's Map").applyFormat(TextFormat.RED));
            CompoundTag displayTag = itemStack.getOrCreateSubCompoundTag("display");
            TextComponent dimensionInfo = new StringTextComponent("Dimension: " + Registry.DIMENSION.getId(player.getServerWorld().dimension.getType())).applyFormat(TextFormat.GRAY);
            ListTag lore = new ListTag();
            lore.add(new StringTag(TextComponent.Serializer.toJsonString(dimensionInfo)));
            displayTag.put("Lore", lore);
            ((ServerPlayerEntity) (Object) this).inventory.insertStack(itemStack);
        }
    }
}

package com.github.glasspane.deathmaps.core.mixin;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.storage.MapData;
import net.minecraft.world.storage.MapDecoration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerList.class)
public class MixinPlayerList {

    @Inject(method = "recreatePlayerEntity", at = @At("RETURN"))
    public void onPlayerClone(EntityPlayerMP original, int respawnDimension, boolean returnsFromEnd, CallbackInfoReturnable<EntityPlayerMP> ci) {
        if(!returnsFromEnd) {
            BlockPos pos = original.getPosition();
            ItemStack stack = ItemMap.setupNewMap(original.getEntityWorld(), pos.getX(), pos.getZ(), (byte)2, true, true);
            ItemMap.renderBiomePreviewMap(original.getEntityWorld(), stack);
            MapData.addTargetDecoration(stack, pos, "Death", MapDecoration.Type.TARGET_X);
            stack.setDisplayName(new TextComponentTranslation("filled_map.deathmaps.player"));
            EntityPlayerMP player = ci.getReturnValue();
            if(!player.addItemStackToInventory(stack)) {
                EntityItem item = new EntityItem(player.getEntityWorld(), player.posX, player.posY, player.posZ, stack);
                item.setNoPickupDelay();
                item.setOwnerId(player.getUniqueID());
                player.getEntityWorld().spawnEntity(item);
            }
        }
    }
}

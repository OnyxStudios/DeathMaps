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
    public void onPlayerClone(EntityPlayerMP original, int respawnDimension, boolean wasDead, CallbackInfoReturnable<EntityPlayerMP> ci, EntityPlayerMP lvt_7_1_) {
        if(wasDead) {
            BlockPos pos = original.getPosition();
            ItemStack stack = ItemMap.setupNewMap(original.getEntityWorld(), pos.getX(), pos.getZ(), (byte)2, true, true);
            ItemMap.renderBiomePreviewMap(original.getEntityWorld(), stack);
            MapData.addTargetDecoration(stack, pos, "Death", MapDecoration.Type.TARGET_X);
            stack.setDisplayName(new TextComponentTranslation("filled_map.deathmaps.player"));
            if(!lvt_7_1_.addItemStackToInventory(stack)) {
                EntityItem item = new EntityItem(lvt_7_1_.getEntityWorld(), lvt_7_1_.posX, lvt_7_1_.posY, lvt_7_1_.posZ, stack);
                item.setNoPickupDelay();
                item.setOwnerId(lvt_7_1_.getUniqueID());
                lvt_7_1_.getEntityWorld().spawnEntity(item);
            }
        }
    }
}

package com.github.glasspane.deathmaps.event;

import com.github.glasspane.deathmaps.DeathMaps;
import com.github.glasspane.deathmaps.config.DeathMapsConfig;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber(modid = DeathMaps.MODID)
public class DeathHandler {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            ItemStack itemstack = ItemMap.setupNewMap(event.getOriginal().getEntityWorld(), event.getOriginal().posX, event.getOriginal().posZ, (byte)2, true, true);
            ItemMap.renderBiomePreviewMap(event.getOriginal().world, itemstack);
            MapData.addTargetDecoration(itemstack, event.getOriginal().getPosition(), "Death", DeathMapsConfig.markerType);
            if(!DeathMapsConfig.serversideOnly) itemstack.setTranslatableName("filled_map.deathmaps.player");
            else itemstack.setStackDisplayName("Gravedigger's Map");
            ItemHandlerHelper.giveItemToPlayer(event.getEntityPlayer(), itemstack);
        }
    }
}

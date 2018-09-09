package com.github.glasspane.deathmaps.config;

import com.github.glasspane.deathmaps.DeathMaps;
import com.github.upcraftlp.glasspane.GlassPane;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = DeathMaps.MODID, name = "glasspanemods/DeathMaps") //--> /config/glasspanemods/DeathMaps.cfg
public class DeathMapsConfig {

    @Config.Name("Serverside Only")
    @Config.Comment("if set to true, will replace the translatable name of death maps so they can be used without the client having the mod installed.")
    public static boolean serversideOnly = false;

    @Config.Name("Death Marker Type")
    @Config.Comment("what kind of marker will be shown on the map")
    public static MapDecoration.Type markerType =  MapDecoration.Type.TARGET_X;

    @Mod.EventBusSubscriber(modid = GlassPane.MODID)
    public static class Handler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent event) {
            if(DeathMaps.MODID.equals(event.getModID())) ConfigManager.sync(event.getModID(), Config.Type.INSTANCE);
        }
    }
}

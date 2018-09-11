package com.github.glasspane.deathmaps.core;

import com.github.glasspane.deathmaps.DeathMaps;
import net.insomniakitten.pylon.annotation.rift.Listener;
import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.mixin.Mixins;

@Listener
public class DeathMapsCore implements InitializationListener {

    @Override
    public void onInitialization() {
        Mixins.addConfiguration("mixins." + DeathMaps.MODID + ".json");
    }
}

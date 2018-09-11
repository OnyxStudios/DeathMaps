package com.github.glasspane.deathmaps.core;

import net.insomniakitten.pylon.annotation.rift.Listener;
import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.mixin.Mixins;

@Listener
public class DeathMapsCore implements InitializationListener {

    @Override
    public void onInitialization() {
        Mixins.addConfiguration("mixins.deathmaps.json");
    }
}

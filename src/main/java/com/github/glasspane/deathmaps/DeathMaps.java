package com.github.glasspane.deathmaps;

import net.insomniakitten.pylon.annotation.rift.Listener;
import net.insomniakitten.pylon.annotation.rift.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dimdev.rift.listener.MinecraftStartListener;

import static com.github.glasspane.deathmaps.DeathMaps.*;

@SuppressWarnings("WeakerAccess")
@Mod(name = MODNAME, id = MODID, version = VERSION, authors = "UpcraftLP")
@Listener
public class DeathMaps implements MinecraftStartListener {

    public static final String VERSION = "@VERSION@";
    public static final String MODNAME = "DeathMaps";
    public static final String MODID = "deathmaps";

    //unused
    //public static final String UPDATE_JSON = "@UPDATE_JSON@";
    //public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";

    private static final Logger log = LogManager.getLogger(MODID);

    @Override
    public void onMinecraftStart() {
        log.info("DeathMaps loaded!");
    }
}

package com.github.glasspane.deathmaps;

import net.insomniakitten.pylon.annotation.rift.Listener;
import net.insomniakitten.pylon.annotation.rift.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dimdev.riftloader.listener.InitializationListener;

import static com.github.glasspane.deathmaps.DeathMaps.*;

@SuppressWarnings("WeakerAccess")
@Mod(name = MODNAME, id = MODID, version = VERSION, authors = "UpcraftLP")
@Listener
public class DeathMaps implements InitializationListener {

    public static final String VERSION = "@VERSION@";
    public static final String MODNAME = "DeathMaps";
    public static final String MODID = "deathmaps";

    //unused
    //public static final String UPDATE_JSON = "@UPDATE_JSON@";
    //public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";

    public static final Logger log = LogManager.getLogger(MODID);

    @Override
    public void onInitialization() {
        log.info("DeathMaps is fully loaded!");
    }
}

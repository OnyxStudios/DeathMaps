package com.github.glasspane.deathmaps;

import com.github.upcraftlp.glasspane.util.ModUpdateHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.glasspane.deathmaps.DeathMaps.*;

@SuppressWarnings("WeakerAccess")
@Mod(
        certificateFingerprint = FINGERPRINT_KEY,
        name = MODNAME,
        version = VERSION,
        acceptedMinecraftVersions = MCVERSIONS,
        acceptableRemoteVersions = "*",
        modid = MODID,
        dependencies = DEPENDENCIES,
        updateJSON = UPDATE_JSON
)
public class DeathMaps {

    //Version
    public static final String MCVERSIONS = "[1.12,1.13)";
    public static final String VERSION = "@VERSION@";

    //Meta Information
    public static final String MODNAME = "DeathMaps";
    public static final String MODID = "deathmaps";
    public static final String DEPENDENCIES = "after:glasspane";
    public static final String UPDATE_JSON = "@UPDATE_JSON@";

    public static final String FINGERPRINT_KEY = "@FINGERPRINTKEY@";

    private static final Logger log = LogManager.getLogger(MODID);

    public static Logger getLogger() {
        return log;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if(Loader.isModLoaded("glasspane")) ModUpdateHandler.registerMod(MODID);
    }
}

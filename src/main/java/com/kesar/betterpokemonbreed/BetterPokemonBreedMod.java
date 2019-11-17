package com.kesar.betterpokemonbreed;

import com.kesar.betterpokemonbreed.config.ModConfig;
import com.kesar.betterpokemonbreed.event.ModEventHandler;
import com.kesar.betterpokemonbreed.key.ModKeys;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.BaseStats;
import com.pixelmonmod.pixelmon.enums.EnumEggGroup;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = BetterPokemonBreedMod.MODID, name = BetterPokemonBreedMod.NAME, version = BetterPokemonBreedMod.VERSION)
public class BetterPokemonBreedMod {
    public static final String MODID = "betterpokemonbreed";
    public static final String NAME = "Better Pokemon Breed Mod";
    public static final String VERSION = "1.0.0";

    public static final Logger logger = LogManager.getLogger(NAME);

    private ModEventHandler eventHandler = new ModEventHandler();
    public static final ModConfig MOD_CONFIG = new ModConfig();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        initConfig();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(eventHandler);
        Pixelmon.EVENT_BUS.register(eventHandler);

        ModKeys.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        BaseStats.allBaseStats.get(EnumSpecies.Greninja).forms.get(1).eggGroups[0] = EnumEggGroup.Water1;
    }

    private void initConfig() {
        File configFile = new File(Loader.instance().getConfigDir(), MODID+".cfg");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
                logger.warn("Could not create a new config file.");
                logger.warn(e.getLocalizedMessage());
            }
        }
        Configuration config = new Configuration(configFile);
        config.load();

        MOD_CONFIG.init(config);
        config.save();
    }
}

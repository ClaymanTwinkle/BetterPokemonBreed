package com.kesar.betterpokemonbreed.config;

import net.minecraftforge.common.config.Configuration;

public class ModConfig {
    private float breedASHChance = 0.6f;

    public void init(Configuration config) {
        this.breedASHChance = config.getFloat("breedASHChance", "all", 0.6f, 0f, 1.0f, "遗传牵绊进化特性的几率,范围：0.0~1.0");
    }

    public float getBreedASHChance() {
        return breedASHChance;
    }
}

package com.kesar.betterpokemonbreed.event;

import com.kesar.betterpokemonbreed.BetterPokemonBreedMod;
import com.kesar.betterpokemonbreed.key.ModKeys;
import com.pixelmonmod.pixelmon.RandomHelper;
import com.pixelmonmod.pixelmon.api.events.BattleStartedEvent;
import com.pixelmonmod.pixelmon.api.events.BreedEvent;
import com.pixelmonmod.pixelmon.api.events.FishingEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.spawning.SpawnAction;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnActionPokemon;
import com.pixelmonmod.pixelmon.api.spawning.archetypes.entities.pokemon.SpawnInfoPokemon;
import com.pixelmonmod.pixelmon.battles.controller.participants.BattleParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.battles.controller.participants.PlayerParticipant;
import com.pixelmonmod.pixelmon.battles.controller.participants.WildPixelmonParticipant;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.forms.EnumGreninja;
import com.pixelmonmod.pixelmon.util.helpers.BreedLogic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class ModEventHandler {

    @SubscribeEvent
    public void onBattleStartedEvent(BattleStartedEvent event) {
//        if (event.bc.containsParticipantType(WildPixelmonParticipant.class) && event.bc.containsParticipantType(PlayerParticipant.class)) {
//            BattleParticipant wild = event.participant1[0] instanceof WildPixelmonParticipant ? event.participant1[0] : event.participant2[0];
//            PixelmonWrapper pixel = wild.controlledPokemon.get(0);
//            String name = pixel.getPokemonName();
//            if (isMatch(name)) {
//                EntityPlayerSP player = Minecraft.getMinecraft().player;
//                player.sendStatusMessage(new TextComponentString(pixel.getForm() + " " + pixel.getAbility().getName()), false);
//                if (pixel.getForm() == 0) {
//                    fishing = true;
//                    player.sendChatMessage("/endbattle " + player.getName());
//                }
//            }
//        }
    }

    @SubscribeEvent
    public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
    }

    @SubscribeEvent
    public void onClientTickEvent(TickEvent.ClientTickEvent event) {
    }


//    @SubscribeEvent
//    public void onStarterPixelmonEvent(PixelmonReceivedEvent event) {
//        if (event.receiveType == ReceiveType.Starter) {
//            Pokemon pokemon = event.pokemon;
//            if (pokemon != null && pokemon.getSpecies() == EnumSpecies.Froakie) {
//                pokemon.setForm(1);
//                pokemon.setShiny(true);
//            }
//        }
//    }

    @SubscribeEvent
    public void onMakeEggEvent(BreedEvent.MakeEgg event) {
        Pokemon mother = BreedLogic.findMother(event.parent1, event.parent2);
        Pokemon father = BreedLogic.findFather(event.parent1, event.parent2);
        Pokemon egg = event.getEgg();

        if (isGreninja(egg)) {
            if (isASHGreninja(mother)
                    || (mother.getSpecies() == EnumSpecies.Ditto && isASHGreninja(father))) {
                if (new Random().nextFloat() <= BetterPokemonBreedMod.MOD_CONFIG.getBreedASHChance()) {
                    egg.setForm(EnumGreninja.BATTLE_BOND.getForm());
                }
            }
        }
    }

    private boolean isASHGreninja(Pokemon pokemon) {
        return isGreninja(pokemon) && pokemon.getForm() == EnumGreninja.BATTLE_BOND.getForm();
    }

    private boolean isGreninja(Pokemon pokemon) {
        return pokemon.getSpecies() == EnumSpecies.Greninja || pokemon.getSpecies() == EnumSpecies.Froakie || pokemon.getSpecies() == EnumSpecies.Frogadier;
    }
}

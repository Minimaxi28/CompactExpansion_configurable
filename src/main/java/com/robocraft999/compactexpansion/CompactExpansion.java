package com.robocraft999.compactexpansion;

import com.mojang.logging.LogUtils;
import com.robocraft999.compactexpansion.config.CompactExpansionWorldConfig;

import dev.compactmods.machines.api.room.RoomSize;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.slf4j.Logger;

import java.lang.reflect.Field;

@Mod(CompactExpansion.MODID)
public class CompactExpansion {
    public static final String MODID = "compactexpansion";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static Field internalSizeField;

    public CompactExpansion() {
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.SERVER,
                CompactExpansionWorldConfig.SPEC,
                "compactexpansion-server.toml"
        );

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConfigReload);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onConfigLoad);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                internalSizeField = RoomSize.class.getDeclaredField("internalSize");
                internalSizeField.setAccessible(true);
            } catch (Exception e) {
                LOGGER.error("Failed to initialize!", e);
            }
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerAboutToStartEvent event) {
        applyCurrentConfig();
    }

    @SubscribeEvent
    public void onConfigLoad(ModConfigEvent.Loading event) {
        applyCurrentConfig();
    }

    @SubscribeEvent
    public void onConfigReload(ModConfigEvent.Reloading event) {
        LOGGER.info("Config file changed, applying new room sizes.");
        applyCurrentConfig();
    }

    private static void applyCurrentConfig() {
        if (internalSizeField == null) {
            LOGGER.error("Cannot apply config: internalSizeField not initialized");
            return;
        }

        try {
            int tiny = CompactExpansionWorldConfig.TINY_SIZE.get();
            int small = CompactExpansionWorldConfig.SMALL_SIZE.get();
            int normal = CompactExpansionWorldConfig.NORMAL_SIZE.get();
            int large = CompactExpansionWorldConfig.LARGE_SIZE.get();
            int giant = CompactExpansionWorldConfig.GIANT_SIZE.get();
            int maximum = CompactExpansionWorldConfig.MAXIMUM_SIZE.get();

            setRoomSize(RoomSize.TINY, tiny);
            setRoomSize(RoomSize.SMALL, small);
            setRoomSize(RoomSize.NORMAL, normal);
            setRoomSize(RoomSize.LARGE, large);
            setRoomSize(RoomSize.GIANT, giant);
            setRoomSize(RoomSize.MAXIMUM, maximum);

            LOGGER.info("Applied room sizes: Tiny={}, Small={}, Normal={}, Large={}, Giant={}, Maximum={}",
                    tiny, small, normal, large, giant, maximum);

        } catch (Exception e) {
            LOGGER.error("Failed to apply room sizes!", e);
        }
    }

    private static void setRoomSize(RoomSize roomSize, int newSize) throws Exception {
        try {
            internalSizeField.set(roomSize, newSize);
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to set size for {}: {}", roomSize.getName(), e.getMessage());
            throw e;
        }
    }
}
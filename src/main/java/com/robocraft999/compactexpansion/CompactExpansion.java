package com.robocraft999.compactexpansion;

import com.mojang.logging.LogUtils;
import com.robocraft999.compactexpansion.config.CompactExpansionConfig;
import dev.compactmods.machines.api.room.RoomSize;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.lang.reflect.Field;

@Mod(CompactExpansion.MODID)
public class CompactExpansion {
    public static final String MODID = "compactexpansion";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CompactExpansion() {
        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                CompactExpansionConfig.SPEC,
                "compactexpansion-common.toml"
        );

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            try {
                updateRoomSizes();
            } catch (Exception e) {
                LOGGER.error("Failed to update room sizes!", e);
            }

            LOGGER.info("=== CompactExpansion Configuration ===");
            LOGGER.info("Tiny size: {}", CompactExpansionConfig.TINY_SIZE.get());
            LOGGER.info("Small size: {}", CompactExpansionConfig.SMALL_SIZE.get());
            LOGGER.info("Normal size: {}", CompactExpansionConfig.NORMAL_SIZE.get());
            LOGGER.info("Large size: {}", CompactExpansionConfig.LARGE_SIZE.get());
            LOGGER.info("Giant size: {}", CompactExpansionConfig.GIANT_SIZE.get());
            LOGGER.info("Maximum size: {}", CompactExpansionConfig.MAXIMUM_SIZE.get());
            LOGGER.info("======================================");
        });
    }

    private void updateRoomSizes() throws Exception {
        Field internalSizeField = RoomSize.class.getDeclaredField("internalSize");
        internalSizeField.setAccessible(true);

        setRoomSize(RoomSize.TINY, CompactExpansionConfig.TINY_SIZE.get(), internalSizeField);
        setRoomSize(RoomSize.SMALL, CompactExpansionConfig.SMALL_SIZE.get(), internalSizeField);
        setRoomSize(RoomSize.NORMAL, CompactExpansionConfig.NORMAL_SIZE.get(), internalSizeField);
        setRoomSize(RoomSize.LARGE, CompactExpansionConfig.LARGE_SIZE.get(), internalSizeField);
        setRoomSize(RoomSize.GIANT, CompactExpansionConfig.GIANT_SIZE.get(), internalSizeField);
        setRoomSize(RoomSize.MAXIMUM, CompactExpansionConfig.MAXIMUM_SIZE.get(), internalSizeField);

        LOGGER.info("Room sizes updated successfully!");
    }

    private void setRoomSize(RoomSize roomSize, int newSize, Field field) throws Exception {
        try {
            field.set(roomSize, newSize);
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to set size for {}: {}", roomSize.getName(), e.getMessage());
            throw e;
        }
    }
}
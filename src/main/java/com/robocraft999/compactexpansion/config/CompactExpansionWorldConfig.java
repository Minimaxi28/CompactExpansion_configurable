package com.robocraft999.compactexpansion.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CompactExpansionWorldConfig {
    public static final ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.IntValue TINY_SIZE;
    public static ForgeConfigSpec.IntValue SMALL_SIZE;
    public static ForgeConfigSpec.IntValue NORMAL_SIZE;
    public static ForgeConfigSpec.IntValue LARGE_SIZE;
    public static ForgeConfigSpec.IntValue GIANT_SIZE;
    public static ForgeConfigSpec.IntValue MAXIMUM_SIZE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("Room Sizes");
        builder.comment("Values will be floored to the nearest odd number (e.g., 20 = 19, 12 = 11).");
        builder.comment("Values represent the internal size of the rooms in blocks (distance between walls).");
        builder.comment("WARNING: Very large values may cause server lag during room generation.");
        builder.comment("WARNING: Changing these values can corrupt existing rooms!");
        builder.comment("Before changing values, empty all affected Compact Machines, change the values, then place new ones.");
        builder.comment("Changing values requires a restart.");
        builder.comment("");

        TINY_SIZE = builder
                .comment("Tiny room (default = 5)")
                .defineInRange("tiny", 5, 1, 297);

        SMALL_SIZE = builder
                .comment("Small room (default = 9)")
                .defineInRange("small", 9, 1, 297);

        NORMAL_SIZE = builder
                .comment("Normal room (default = 13)")
                .defineInRange("normal", 13, 1, 297);

        LARGE_SIZE = builder
                .comment("Large room (default = 17)")
                .defineInRange("large", 17, 1, 297);

        GIANT_SIZE = builder
                .comment("Giant room (default = 21)")
                .defineInRange("giant", 21, 1, 297);

        MAXIMUM_SIZE = builder
                .comment("Maximum room (default = 25)")
                .defineInRange("maximum", 25, 1, 297);

        builder.pop();

        SPEC = builder.build();
    }
}
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
        builder.comment(" Values will be floored to the nearest odd number (e.g., 20 = 19, 12 = 11).");
        builder.comment(" Values represent the internal size of the rooms in blocks (distance between walls).");
        builder.comment(" WARNING: Very large values may cause server lag during room generation.");
        builder.comment(" WARNING: Changing these values can corrupt existing rooms!");
        builder.comment(" Before changing values, empty all affected Compact Machines, change the values, then place new ones.");
        builder.comment(" Changing values does not require a restart, just save this file and that's it, build a new Compact Machine.");
        builder.comment(" Note: Changes take effect immediately when saving this file (in the serverconfig folder of the world).");
        builder.comment("");

        TINY_SIZE = builder
                .comment("Tiny room (original = 3 ; default = 5)")
                .defineInRange("tiny", 5, 3, 297);

        SMALL_SIZE = builder
                .comment("Small room (original = 5 ; default = 9)")
                .defineInRange("small", 9, 3, 297);

        NORMAL_SIZE = builder
                .comment("Normal room (original = 7 ; default = 13)")
                .defineInRange("normal", 13, 3, 297);

        LARGE_SIZE = builder
                .comment("Large room (original = 9 ; default = 17)")
                .defineInRange("large", 17, 3, 297);

        GIANT_SIZE = builder
                .comment("Giant room (original = 11 ; default = 21)")
                .defineInRange("giant", 21, 3, 297);

        MAXIMUM_SIZE = builder
                .comment("Maximum room (original = 13 ; default = 25)")
                .defineInRange("maximum", 25, 3, 297);

        builder.pop();

        SPEC = builder.build();
    }
}
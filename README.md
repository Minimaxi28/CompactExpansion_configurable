# Compact Expansion (configurable)

This mod is a modification of [Compact Expansion](https://www.curseforge.com/minecraft/mc-mods/compact-expansion) which adds a configuration file to be able to choose any size of compact machines you want.

Configuration file is found at ``minecraft\config`` and is named ``compactexpansion-common.toml``.

Default configuration file:
````toml
["Room Sizes"]
    #Values will be floored to the nearest odd number (e.g., 20 = 19, 12 = 11).
    #Values represent the internal size of the rooms in blocks (distance between walls).
    #WARNING: Very large values may cause server lag during room generation.
    #WARNING: Lowering these values can corrupt existing rooms!
    #Before changing values, empty all affected Compact Machines, change the values, then place new ones.
    #Changing values requires a restart.
    #
    #Tiny room (original size = 3)
    #Range: 1 ~ 297
    tiny = 5
    #Small room (original size = 5)
    #Range: 1 ~ 297
    small = 9
    #Normal room (original size = 7)
    #Range: 1 ~ 297
    normal = 13
    #Large room (original size = 9)
    #Range: 1 ~ 297
    large = 17
    #Giant room (original size = 11)
    #Range: 1 ~ 297
    giant = 21
    #Maximum room (original size = 13)
    #Range: 1 ~ 297
    maximum = 25
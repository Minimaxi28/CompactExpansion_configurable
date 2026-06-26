# Compact Expansion (configurable)

This mod is a modification of [Compact Expansion](https://www.curseforge.com/minecraft/mc-mods/compact-expansion) which adds a configuration file to be able to choose any size of compact machines you want.

Configuration file is per world, found at ``minecraft\saves\WORLD_NAME\serverconfig`` and is named ``compactexpansion-server.toml``.

For modpack creators:
Copy that file in ``minecraft\defaultconfigs`` to make all worlds be created with that file instead.

Default configuration file:
````toml
["Room Sizes"]
	# Values will be floored to the nearest odd number (e.g., 20 = 19, 12 = 11).
	# Values represent the internal size of the rooms in blocks (distance between walls).
	# WARNING: Very large values may cause server lag during room generation.
	# WARNING: Changing these values can corrupt existing rooms!
	# Before changing values, empty all affected Compact Machines, change the values, then place new ones.
	# Changing values does not require a restart, just save this file and that's it, build a new Compact Machine.
	# Note: Changes take effect immediately when saving this file (in the serverconfig folder of the world).
	#
	#Tiny room (original = 3 ; default = 5)
	#Range: 3 ~ 297
	tiny = 51
	#Small room (original = 5 ; default = 9)
	#Range: 3 ~ 297
	small = 51
	#Normal room (original = 7 ; default = 13)
	#Range: 3 ~ 297
	normal = 13
	#Large room (original = 9 ; default = 17)
	#Range: 3 ~ 297
	large = 17
	#Giant room (original = 11 ; default = 21)
	#Range: 3 ~ 297
	giant = 21
	#Maximum room (original = 13 ; default = 25)
	#Range: 3 ~ 297
	maximum = 25
# LoD-Desecrator

Simulates the Terror Zones introduced in D2R. It's not a perfect replica, but as close as I could get without breaking literally everything. Things it changes:
+ alvl of zones that are terrorized
+ names of the monsters (suffixed by (Desecrated)
+ superuniques and bosses (along with treasure classes and the bonus XP gain from terror zone monsters)

Bosses and uniques do not get the (Desecrated) suffix behind their name. That is a limitation by how they are spawned and to change their name would require a fair amount of hardcoding.

Just extract the zip file to any folder (I recommend not using your Diablo II install folder) and double-click the jar to run it. Make sure the OriginalFiles folder is in the same directory as the jar.

The TOTAL CHAOS button simply terrorizes every TZ area at the same time, if you are too lazy to switch all the time.

Reset removes all active TZs.

The (initially) grey dot is a status indicator. If you select a zone to be terrorized or press the TOTAL CHAOS button, it will turn red if something went wrong and turn green when everything is ok. Double-clicking the area around the status indicator will open up the [TZ calendar.](https://www.d2emu.com/tz-sp)

Known wont-fix:
TZs only work in Nightmare and Hell. The reason for this is that changing mlvls for monsters in Normal mode breaks preset monsters on all difficulties, so I had to make amends.

## Many D2R changes ported to LoD

I ported most of the balance changes from D2R in the mod. Pretty much all I could do without hardcoding.
Here is a list of the most impactful changes:
+ Skill balance and synergy changes (95% of them anyways)
+ D2R runewords (all except Mosaic; Metamorphosis is bugged as it gives you both buffs regardless of your state)
+ Nihla temple vipers fixed (their poison spray applied their main hand damage every frame)
+ Mercenary changes except for the equipment changes (so no dual-wielding and equivalent class)
+ Upgradable set items (same recipe as in D2R)
+ Andariel quest bug fixed (always drops from quest TC now)

Here is a rather incomplete list of stuff that is not changed due to mod limitations:

+ Mosaic
+ Local delay
+ Next hit delay
+ Fist of Heavens and Holy Bolt changes
+ Whirlwind and Druid form attack speed calculations
+ Assassin traps benefitting from -%res (I told them to do that, but haven't tested it)
+ Mana burn fix
+ Sunders
+ Merc equips (horrible hardcoded mess)

All of the above require hardcoding, this mod is soft-code only.

### Install/Uninstall

To install: drag the Data folder in the mod.zip into your Diablo 2 install folder. Add -direct -txt to the starting parameters (either in the PlugY.ini or in your shortcut).

To uninstall: delete the Data folder from your Diablo 2 install folder. Remove -direct -txt starting parameters.

## Link to latest release

https://github.com/Karyoplasma/LoD-Desecrator/releases/latest

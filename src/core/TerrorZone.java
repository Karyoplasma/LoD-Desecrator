package core;

public enum TerrorZone {
	RANDOM("Random", new int[]{}, new String[] {}, new SpecialMonster[] {}),
	BLOOD_MOOR("Blood Moor, Den of Evil", new int[] {3, 9}, new String[]{"zombie1", "fallen1", "quillrat1", "brute1", "fallenshaman1"}, new SpecialMonster[] {SpecialMonster.CORPSEFIRE}),
	COLD_PLAINS("Cold Plains, The Cave", new int[] {4, 10, 14}, new String[]{"brute1", "corruptrogue1", "fallenshaman1", "skeleton1", "cr_archer1", "cr_lancer1", "fallen1", "zombie2"}, new SpecialMonster[] {SpecialMonster.BISHIBOSH, SpecialMonster.COLDCROW}),
	BURIAL_GROUNDS("Burial Grounds, The Crypt, Mausoleum", new int[] {18, 19, 20}, new String[]{"skeleton1", "zombie2"}, new SpecialMonster[] {SpecialMonster.BLOODRAVEN, SpecialMonster.BONEBREAKER}),
	STONY_FIELD("The Stony Field", new int[] {5}, new String[]{"skeleton1", "cr_archer1", "foulcrow1", "goatman1", "zombie2", "crownest1"}, new SpecialMonster[] {SpecialMonster.RAKANISHU}),
	DARK_WOOD("Dark Wood, Underground Passage", new int[] {6, 11, 15}, new String[]{"corruptrogue2", "sk_archer1", "fallenshaman1", "fallen1", "fallenshaman2", "fallen2", "cr_lancer2", "quillrat2", "bighead3"}, new SpecialMonster[] {SpecialMonster.TREEHEADWOODFIST}),
	BLACK_MARSH("Black Marsh, The Hole", new int[] {7, 12, 16}, new String[]{"brute2", "skeleton2", "cr_archer2", "foulcrow2", "fallen2", "goatman2", "fallenshaman2", "crownest2", "bighead3"}, new SpecialMonster[]{}),
	FORGOTTEN_TOWER("Forgotten Tower", new int[] {22, 23, 24, 25, 26}, new String[]{"cr_archer3", "fallen3", "goatman3", "wraith1"}, new SpecialMonster[]{SpecialMonster.COUNTESS}),
	JAIL("Barracks, The Jail", new int[] {29, 30, 31, 32}, new String[]{"corruptrogue4", "fallen3", "fallen4", "fallenshaman3", "goatman5", "sk_archer3", "skmage_fire2", "skmage_ltng2", "wraith2"}, new SpecialMonster[]{SpecialMonster.PITSPAWNFOULDOG, SpecialMonster.THESMITH}),
	CATACOMBS("Cathedral, Catacombs", new int[] {34, 35, 36, 37, 38}, new String[]{"arach1", "bighead1", "bighead2", "fallen4", "fallenshaman4", "fallen8", "fallenshaman8", "fetish1", "vampire5", "wraith2", "zombie3"}, new SpecialMonster[]{SpecialMonster.BONEASH, SpecialMonster.ANDARIEL}),
	PIT("The Pit", new int[] {13, 17}, new String[]{"corruptrogue3", "cr_archer3", "fallen3", "fallenshaman3", "skeleton3"}, new SpecialMonster[]{}),
	TRISTRAM("Tristram", new int[] {39}, new String[]{"fallen2", "fallenshaman2", "goatman2", "sk_archer1", "skeleton2"}, new SpecialMonster[]{SpecialMonster.GRISWOLD}),
	COWS("The Cow Level", new int[] {40}, new String[]{"hellbovine"}, new SpecialMonster[]{SpecialMonster.COWKING}),
	LUT_SEWERS("Lut Gholein Sewers", new int[] {48, 49, 50}, new String[]{"mummy1", "pantherwoman1", "sandraider1", "sk_archer4", "skeleton4", "skeleton5", "skmage_fire3"}, new SpecialMonster[]{SpecialMonster.RADAMENT}),
	ROCKY_WASTE("Rocky Waste, The Stony Tomb", new int[] {42, 56, 60}, new String[]{"pantherwoman1", "sandleaper1", "scarab1", "skeleton5", "skmage_ltng3", "skmage_pois3", "slinger1", "vulture1"}, new SpecialMonster[]{SpecialMonster.LEATHERARM}),
	DRY_HILLS("Dry Hills, Halls of the Dead", new int[] {43, 57, 58, 61}, new String[]{"batdemon1", "mummy2", "pantherwoman2", "sandleaper2", "skeleton2", "slinger2", "slinger5", "unraveler1", "vulture2"}, new SpecialMonster[]{SpecialMonster.BLOODWITCHTHEWILD}),
	FAR_OASIS("Far Oasis", new int[] {44}, new String[]{"crownest3", "foulcrow3", "maggotegg1", "sandmaggot1", "maggotbaby1", "scarab2", "swarm1", "vulture2"}, new SpecialMonster[]{SpecialMonster.BEETLEBURST}),
	LOST_CITY("Lost City, Claw Viper Temple", new int[] {45, 46, 59, 62}, new String[]{"clawviper2", "clawviper3", "mummy3", "pantherwoman3", "sandleaper3", "sandraider2", "skeleton3", "slinger6", "unraveler2", "zombie5"}, new SpecialMonster[]{SpecialMonster.DARKELDER, SpecialMonster.FANGSKIN}),
	ANCIENT_TUNNELS("Ancient Tunnels", new int[] {66}, new String[]{"mummy3", "sandraider3", "skmage_ltng4", "zombie5"}, new SpecialMonster[]{}),
	ARCANE_SANCTUARY("Arcane Sanctuary", new int[] {75}, new String[]{"goatman4", "vampire1", "wraith3"}, new SpecialMonster[]{SpecialMonster.SUMMONER}),
	TOMBS("Tal Rasha's Tombs", new int[] {67, 68, 69, 70, 71, 72, 73, 74}, new String[]{"blunderbore2", "mummy4", "scarab4", "skeleton4", "unraveler3", "vampire1", "wraith4"}, new SpecialMonster[]{SpecialMonster.KAA, SpecialMonster.DURIEL}),
	SPIDER_FOREST("Spider Forest, Spider Cavern", new int[] {77, 86}, new String[]{"arach3", "arach4", "baboon3", "batdemon2", "fetish2", "fetishblow2", "foulcrow4", "maggotegg4", "maggotbaby4", "mosquito1", "sandmaggot4", "thornhulk1"}, new SpecialMonster[]{SpecialMonster.SSZARK}),
	GREAT_MARSH("Great Marsh", new int[] {78}, new String[]{"fetish2", "fetishblow2", "fetishshaman2", "frogdemon1", "frogdemon2", "thornhulk2", "willowisp1", "zombie4"}, new SpecialMonster[]{}),
	FLAYER_JUNGLE("Flayer Jungle, Flayer Dungeon", new int[] {79, 89, 90, 92}, new String[]{"batdemon3", "bonefetish3", "fetish3", "fetish4", "fetishblow3", "fetishblow4", "fetishshaman3", "fetishshaman4", "frogdemon3", "vulture4", "wraith5"}, new SpecialMonster[]{SpecialMonster.STORMTREE, SpecialMonster.WITCHDOCTORENDUGU}),
	KURAST_BAZAAR("Kurast Bazaar, Disused Fane, Ruined Temple", new int[] {81, 95, 96}, new String[]{"arach5", "brute5", "cantor1", "corruptrogue5", "swarm4", "thornhulk3", "vampire2", "zealot1", "zealot2"}, new SpecialMonster[]{SpecialMonster.BATTLEMAIDSARINA}),
	TRAVINCAL("Travincal", new int[] {84}, new String[]{"cantor3", "vampire2", "zealot3", "councilmember1", "councilmember2", "councilmember3"}, new SpecialMonster[]{SpecialMonster.ISMAIL, SpecialMonster.TOORC, SpecialMonster.GELEB}),
	DURANCE_OF_HATE("Durance of Hate", new int[] {101, 102, 103}, new String[]{"blunderbore3", "bonefetish5", "mummy5", "vampire3", "vampire4", "councilmember1", "councilmember2", "councilmember3"}, new SpecialMonster[]{SpecialMonster.BREMM, SpecialMonster.WYAND, SpecialMonster.MAFFER, SpecialMonster.MEPHISTO}),
	OUTER_STEPPES("Outer Steppes, Plains of Despair", new int[] {105, 106}, new String[]{"doomknight1", "fingermage1", "megademon1", "megademon2", "megademon3", "regurgitator1", "sandleaper5", "vilechild1", "vilemother1", "willowisp3"}, new SpecialMonster[]{SpecialMonster.IZUAL}),
	RIVER_OF_FLAME("City of the Damned, River of Flame", new int[] {107, 108}, new String[]{"batdemon5", "bighead5", "blunderbore4", "doomknight2", "fingermage2", "maggotegg5", "maggotbaby5", "megademon2", "megademon3", "regurgitator2", "regurgitator3", "sandmaggot5", "vilechild2", "vilechild3", "vilemother2", "vilemother3"}, new SpecialMonster[]{SpecialMonster.HEPHASTO}),
	CHAOS("Chaos Sanctuary", new int[] {109}, new String[]{"doomknight1", "doomknight3", "fingermage3", "megademon3"}, new SpecialMonster[]{SpecialMonster.DESEIS, SpecialMonster.INFECTOR, SpecialMonster.VIZIER, SpecialMonster.DIABLO}),
	FOOTHILLS("Bloody Foothills, Frigid Highlands, Abaddon", new int[] {112, 113, 127}, new String[]{"bighead6", "bloodlord3", "bloodlord4", "blunderbore5", "bonefetish6", "cr_archer6", "cr_lancer6", "deathmauler1", "deathmauler2", "fallen8", "fallenshaman8", "fingermage4", "foulcrow5", "imp1", "imp2", "imp3", "imp6", "megademon4", "minion1", "minion10", "minion2", "overseer1", "overseer2", "overseer3", "quillrat6", "siegebeast1", "siegebeast2", "sk_archer10", "sk_archer6", "slinger7", "slinger8", "succubus7", "succubuswitch7", "thornhulk5", "vulture5"}, new SpecialMonster[]{SpecialMonster.DACFARREN, SpecialMonster.SHENK, SpecialMonster.ELDRITCH, SpecialMonster.SHARPTOOTH, SpecialMonster.EYEBACK}),
	GLACIAL_TRAIL("Glacial Trail, Drifter Cavern", new int[] {117, 118}, new String[]{"batdemon6", "bighead6", "bloodlord6", "clawviper7", "cr_archer7", "deathmauler3", "frozenhorror1", "frozenhorror2", "frozenhorror3", "goatman7", "reanimatedhorde1", "reanimatedhorde2", "sandraider7", "skeleton6", "skmage_cold5", "snowyeti1", "snowyeti2", "succubus2", "succubus3", "succubus6", "wraith6", "wraith7"}, new SpecialMonster[]{SpecialMonster.BONESAW}),
	CRYSTALLINE_PASSAGE("Crystalline Passage, Frozen River", new int[] {115, 116}, new String[]{"bloodlord1", "bloodlord3", "clawviper6", "cr_archer7", "cr_lancer7", "deathmauler3", "frozenhorror1", "frozenhorror2", "reanimatedhorde1", "sandleaper6", "sandraider7", "skmage_cold5", "snowyeti1", "snowyeti2", "snowyeti4", "succubus1", "succubus3", "succubus5", "succubus6", "succubuswitch6", "willowisp5", "wraith6"}, new SpecialMonster[]{SpecialMonster.FROZENSTEIN}),
	ARREAT_PLATEAU("Arreat Plateau, Pit of Acheron", new int[] {114, 128}, new String[]{"bloodlord2", "bloodlord3", "bloodlord4", "blunderbore6", "clawviper8", "deathmauler3", "fallen6", "fallenshaman6", "goatman7", "goatman8", "imp3", "imp6", "megademon4", "minion2", "minion3", "minion4", "minion5", "overseer2", "overseer3", "overseer5", "regurgitator4", "siegebeast3", "skmage_fire5", "skmage_fire6", "skmage_ltng5", "slinger9", "succubus7", "unraveler7"}, new SpecialMonster[]{SpecialMonster.THRESHSOCKET}),
	NIHLATHAK("Pindle and Nihlatak", new int[] {123, 124, 125, 126}, new String[]{"arach6", "baboon6", "baboon7", "baboon8", "bloodlord2", "cantor5", "clawviper9", "deathmauler2", "deathmauler5", "fallen7", "fallenshaman7", "fetish6", "fetishblow6", "fetishshaman6", "goatman9", "maggotegg6", "maggotbaby6", "minion6", "minion9", "putriddefiler1", "putriddefiler2", "quillrat8", "reanimatedhorde2", "reanimatedhorde3", "reanimatedhorde4", "reanimatedhorde5", "sandleaper7", "sandmaggot6", "sandraider7", "scarab6", "sk_archer9", "skeleton7", "skmage_pois5", "succubus4", "unraveler6", "vilechild4", "vilemother4", "wraith6", "zealot4"}, new SpecialMonster[]{SpecialMonster.PINDLESKIN, SpecialMonster.NIHLATHAK}),
	ANCIENTS_WAY("Ancient's Way, Icy Cellar", new int[] {120, 121}, new String[]{"batdemon6", "bighead6", "bloodlord1", "bloodlord7", "bonefetish5", "clawviper7", "cr_lancer7", "deathmauler3", "deathmauler5", "fallen6", "fallenshaman6", "frozenhorror1", "frozenhorror2", "frozenhorror3", "quillrat7", "reanimatedhorde2", "reanimatedhorde3", "skmage_cold5", "snowyeti2", "snowyeti4", "succubus1", "succubus3", "succubus4", "succubus5", "succubuswitch6", "willowisp5", "wraith6"}, new SpecialMonster[]{SpecialMonster.SNAPCHIP}),
	WSK("Worldstone Keep", new int[] {130, 131, 132, 133, 134}, new String[]{"batdemon7", "bloodlord3", "bloodlord4", "bloodlord5", "bonefetish7", "cantor6", "clawviper10", "cr_lancer8", "dkfig2", "dkmag2", "fetish8", "fetishblow8", "fetishshaman8", "fingermage5", "imp5", "megademon5", "minion11", "minion4", "minion7", "mummy6", "overseer4", "putriddefiler2", "putriddefiler3", "putriddefiler4", "reanimatedhorde5", "reanimatedhorde6", "sandraider10", "sandraider9", "scarab7", "skmage_ltng6", "skmage_pois6", "succubus4", "succubuswitch2", "succubuswitch3", "succubuswitch5", "succubuswitch8", "suicideminion6", "unraveler8", "unraveler9", "vampire7", "vampire8", "vilechild5", "vilemother5", "willowisp6", "willowisp7", "wraith8", "zealot5"}, new SpecialMonster[]{SpecialMonster.COLENZO, SpecialMonster.BAALMUMMY, SpecialMonster.BAALCOUNCIL, SpecialMonster.VENTAR, SpecialMonster.LISTER, SpecialMonster.BAAL});
	
	private int[] levelLines;
	private String[] monsters;
	private String name;
	private SpecialMonster[] specialCases;
	
	private TerrorZone(String name, int[] levelLines, String[] monsters, SpecialMonster[] specialCases) {
		this.name = name;
		this.levelLines = levelLines;
		this.monsters = monsters;
		this.specialCases = specialCases;
	}
	
	public int[] getLevelLines() {
		return this.levelLines;
	}
	
	public String[] getMonsters() {
		return this.monsters;
	}

	public SpecialMonster[] getSpecialCases() {
		return this.specialCases;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

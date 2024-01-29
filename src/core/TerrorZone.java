package core;

public enum TerrorZone {
	RANDOM("Random", new int[]{}, new String[] {}, new SpecialMonster[] {}),
	BLOOD_MOOR("Blood Moor, Den of Evil", new int[] {3, 9}, new String[]{}, new SpecialMonster[] {SpecialMonster.CORPSEFIRE}),
	COLD_PLAINS("Cold Plains, The Cave", new int[] {4, 10, 14}, new String[]{"fallenshaman1", "fallen1"}, new SpecialMonster[] {SpecialMonster.BISHIBOSH, SpecialMonster.COLDCROW}),
	BURIAL_GROUNDS("Burial Grounds, The Crypt, Mausoleum", new int[] {18, 19, 20}, new String[]{}, new SpecialMonster[] {SpecialMonster.BLOODRAVEN, SpecialMonster.BONEBREAKER}),
	STONY_FIELD("The Stony Field", new int[] {5}, new String[]{"fallen2", "fallenshaman1"}, new SpecialMonster[] {SpecialMonster.RAKANISHU}),
	DARK_WOOD("Dark Wood, Underground Passage", new int[] {6, 11, 15}, new String[]{"fallenshaman1", "fallenshaman2"}, new SpecialMonster[] {SpecialMonster.TREEHEADWOODFIST}),
	BLACK_MARSH("Black Marsh, The Hole", new int[] {7, 12, 16}, new String[]{"fallenshaman2"}, new SpecialMonster[]{}),
	FORGOTTEN_TOWER("Forgotten Tower", new int[] {22, 23, 24, 25, 26}, new String[]{}, new SpecialMonster[]{SpecialMonster.COUNTESS}),
	JAIL("Barracks, The Jail", new int[] {29, 30, 31, 32}, new String[]{"gargoyletrap"}, new SpecialMonster[]{SpecialMonster.PITSPAWNFOULDOG, SpecialMonster.THESMITH}),
	CATACOMBS("Cathedral, Catacombs", new int[] {34, 35, 36, 37, 38}, new String[]{"gargoyletrap", "fallen4", "fallenshaman4"}, new SpecialMonster[]{SpecialMonster.BONEASH, SpecialMonster.ANDARIEL}),
	PIT("The Pit", new int[] {13, 17}, new String[]{"fallenshaman3"}, new SpecialMonster[]{}),
	TRISTRAM("Tristram", new int[] {39}, new String[]{}, new SpecialMonster[]{SpecialMonster.GRISWOLD}),
	COWS("The Cow Level", new int[] {40}, new String[]{}, new SpecialMonster[]{SpecialMonster.COWKING}),
	LUT_SEWERS("Lut Gholein Sewers", new int[] {48, 49, 50}, new String[]{"skeleton5"}, new SpecialMonster[]{SpecialMonster.RADAMENT}),
	ROCKY_WASTE("Rocky Waste, The Stony Tomb", new int[] {42, 56, 60}, new String[] {}, new SpecialMonster[]{SpecialMonster.LEATHERARM}),
	DRY_HILLS("Dry Hills, Halls of the Dead", new int[] {43, 57, 58, 61}, new String[]{"firetower"}, new SpecialMonster[]{SpecialMonster.BLOODWITCHTHEWILD}),
	FAR_OASIS("Far Oasis", new int[] {44}, new String[]{}, new SpecialMonster[]{SpecialMonster.BEETLEBURST}),
	LOST_CITY("Lost City, Claw Viper Temple", new int[] {45, 46, 59, 62}, new String[]{"firetower"}, new SpecialMonster[]{SpecialMonster.DARKELDER, SpecialMonster.FANGSKIN}),
	ANCIENT_TUNNELS("Ancient Tunnels", new int[] {66}, new String[]{}, new SpecialMonster[]{}),
	ARCANE_SANCTUARY("Arcane Sanctuary", new int[] {75}, new String[]{"lightningspire"}, new SpecialMonster[]{SpecialMonster.SUMMONER}),
	TOMBS("Tal Rasha's Tombs", new int[] {67, 68, 69, 70, 71, 72, 73, 74}, new String[]{}, new SpecialMonster[]{SpecialMonster.KAA, SpecialMonster.DURIEL}),
	SPIDER_FOREST("Spider Forest, Spider Cavern", new int[] {77, 86}, new String[]{"fetishshaman2"}, new SpecialMonster[]{SpecialMonster.SSZARK}),
	GREAT_MARSH("Great Marsh", new int[] {78}, new String[]{}, new SpecialMonster[]{}),
	FLAYER_JUNGLE("Flayer Jungle, Flayer Dungeon", new int[] {79, 89, 90, 92}, new String[]{}, new SpecialMonster[]{SpecialMonster.STORMTREE, SpecialMonster.WITCHDOCTORENDUGU}),
	KURAST_BAZAAR("Kurast Bazaar, Disused Fane, Ruined Temple", new int[] {81, 95, 96}, new String[]{}, new SpecialMonster[]{SpecialMonster.BATTLEMAIDSARINA}),
	TRAVINCAL("Travincal", new int[] {84}, new String[]{}, new SpecialMonster[]{SpecialMonster.ISMAIL, SpecialMonster.TOORC, SpecialMonster.GELEB}),
	DURANCE_OF_HATE("Durance of Hate", new int[] {101, 102, 103}, new String[]{}, new SpecialMonster[]{SpecialMonster.BREMM, SpecialMonster.WYAND, SpecialMonster.MAFFER, SpecialMonster.MEPHISTO}),
	OUTER_STEPPES("Outer Steppes, Plains of Despair", new int[] {105, 106}, new String[]{}, new SpecialMonster[]{SpecialMonster.IZUAL}),
	RIVER_OF_FLAME("City of the Damned, River of Flame", new int[] {107, 108}, new String[]{"regurgitator3"}, new SpecialMonster[]{SpecialMonster.HEPHASTO}),
	CHAOS("Chaos Sanctuary", new int[] {109}, new String[]{}, new SpecialMonster[]{SpecialMonster.DESEIS, SpecialMonster.INFECTOR, SpecialMonster.VIZIER, SpecialMonster.DIABLO}),
	FOOTHILLS("Bloody Foothills, Frigid Highlands, Abaddon", new int[] {112, 113, 127}, new String[]{"minion1"}, new SpecialMonster[]{SpecialMonster.DACFARREN, SpecialMonster.SHENK, SpecialMonster.ELDRITCH, SpecialMonster.SHARPTOOTH, SpecialMonster.EYEBACK}),
	GLACIAL_TRAIL("Glacial Trail, Drifter Cavern", new int[] {117, 118}, new String[]{}, new SpecialMonster[]{SpecialMonster.BONESAW}),
	CRYSTALLINE_PASSAGE("Crystalline Passage, Frozen River", new int[] {115, 116}, new String[]{}, new SpecialMonster[]{SpecialMonster.FROZENSTEIN}),
	ARREAT_PLATEAU("Arreat Plateau, Pit of Acheron", new int[] {114, 128}, new String[]{}, new SpecialMonster[]{SpecialMonster.THRESHSOCKET}),
	NIHLATHAK("Pindle and Nihlatak", new int[] {123, 124, 125, 126}, new String[]{"reanimatedhorde3"}, new SpecialMonster[]{SpecialMonster.PINDLESKIN, SpecialMonster.NIHLATHAK}),
	ANCIENTS_WAY("Ancient's Way, Icy Cellar", new int[] {120, 121}, new String[]{}, new SpecialMonster[]{SpecialMonster.SNAPCHIP}),
	WSK("Worldstone Keep", new int[] {130, 131, 132, 133, 134}, new String[]{}, new SpecialMonster[]{SpecialMonster.COLENZO, SpecialMonster.BAALMUMMY, SpecialMonster.BAALCOUNCIL, SpecialMonster.VENTAR, SpecialMonster.LISTER, SpecialMonster.BAAL});
	
	private int[] levelLines;
	private String[] presetMonsters;
	private String name;
	private SpecialMonster[] specialCases;
	
	private TerrorZone(String name, int[] levelLines, String[] presetMonsters, SpecialMonster[] specialCases) {
		this.name = name;
		this.levelLines = levelLines;
		this.presetMonsters = presetMonsters;
		this.specialCases = specialCases;
	}
	
	public int[] getLevelLines() {
		return this.levelLines;
	}
	
	public String[] getPresetMonsters() {
		return this.presetMonsters;
	}

	public SpecialMonster[] getSpecialCases() {
		return this.specialCases;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

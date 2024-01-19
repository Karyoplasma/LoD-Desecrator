package core;

public enum SpecialHandlingEntry {
	CORPSEFIRE("Corpsefire"),
	BISHIBOSH("Bishibosh"),
	COLDCROW("Coldcrow"),
	BLOODRAVEN("bloodraven", true, true),
	BONEBREAKER("Bonebreak"),
	RAKANISHU("Rakanishu"),
	TREEHEADWOODFIST("Treehead WoodFist"),
	COUNTESS("The Countess", true),
	PITSPAWNFOULDOG("Pitspawn Fouldog"),
	THESMITH("The Smith", true),
	BONEASH("Boneash"),
	ANDARIEL("andariel", true, true),
	GRISWOLD("Griswold", true, true),
	COWKING("The Cow King", true),
	RADAMENT("Radament", true, true),
	LEATHERARM("Leatherarm"),
	BLOODWITCHTHEWILD("Bloodwitch the Wild"),
	BEETLEBURST("Beetleburst"),
	DARKELDER("Dark Elder"),
	FANGSKIN("Fangskin"),
	SUMMONER("The Summoner", true, true),
	KAA("Ancient Kaa the Soulless"),
	DURIEL("duriel", true, true),
	SSZARK("Web Mage the Burning"),
	STORMTREE("Stormtree"),
	WITCHDOCTORENDUGU("Witch Doctor Endugu"),
	BATTLEMAIDSARINA("Sarina the Battlemaid"),
	ISMAIL("Ismail Vilehand", true),
	GELEB("Geleb Flamefinger", true),
	BREMM("Bremm Sparkfist", true),
	TOORC("Toorc Icefist", true),
	WYAND("Wyand Voidfinger", true),
	MAFFER("Maffer Dragonhand", true),
	MEPHISTO("mephisto", true),
	IZUAL("izual", true, true),
	HEPHASTO("The Feature Creep", true),
	INFECTOR("Infector of Souls"),
	DESEIS("Lord De Seis"),
	VIZIER("Grand Vizier of Chaos"),
	DIABLO("diablo", true, true),
	SHENK("Siege Boss"),
	ELDRITCH("Megaflow Rectifier"),
	EYEBACK("Eyeback Unleashed"),
	DACFARREN("Dac Farren"),
	SHARPTOOTH("Sharp Tooth Sayer"),
	BONESAW("Bonesaw Breaker"),
	FROZENSTEIN("Frozenstein"),
	THRESHSOCKET("Threash Socket"),
	PINDLESKIN("Pindleskin"),
	NIHLATHAK("Nihlathak Boss", true),
	SNAPCHIP("Snapchip Shatter"),
	BAAL ("baalcrab", true, true),
	COLENZO("Baal Subject 1"),
	BAALMUMMY("Baal Subject 2"),
	BAALCOUNCIL("Baal Subject 3"),
	VENTAR("Baal Subject 4"),
	LISTER("Baal Subject 5");
	
	private String name;
	private boolean hasSpecialTC, hasFixedTC;
	
	private SpecialHandlingEntry(String name) {
		this.name = name;
		this.hasSpecialTC = false;
		this.hasFixedTC = false;
	}
	
	private SpecialHandlingEntry(String name, boolean isMoreWork) {
		this.name = name;
		this.hasSpecialTC = isMoreWork;
		this.hasFixedTC = false;
	}
	
	private SpecialHandlingEntry(String name, boolean isMoreWork, boolean usesMonstatTC) {
		this.name = name;
		this.hasSpecialTC = isMoreWork;
		this.hasFixedTC = usesMonstatTC;
	}
	
	public boolean isMoreWork() {
		return hasSpecialTC;
	}
	
	public boolean hasFixedTC() {
		return hasFixedTC;
	}
	@Override
	public String toString() {
		return name;
	}
}

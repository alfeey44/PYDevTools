package PYDevTools.db.structures;

import PYDevTools.enums.ItemBonding;
import PYDevTools.enums.ItemType;

public class Item {
	private String name, description;
	private int entry, class_, display, quality, inventoryType, subclass, sheath, binds, delay,
				armor, block, reqlvl, ilvl, unique, statsCount, holy_resist, fire_resist,
				nature_resist, frost_resist, shadow_resist, arcane_resist, mindamage, maxdamage,
				duribility;

	private int[] stat_values;
	private int[] stat_types;
	private int[] spell_ids;
	private int[] spell_triggers;
	private int[] spell_cds;
	private int[] socket_colors;
	private int[] socket_contents;
	
	public Item() {
		name = "";
		description = "";
		entry = 0;
		display = 0;
		quality = 0;
		inventoryType = 0;
		class_ = 0;
		subclass = 0;
		sheath = 0;
		binds = 0;
		delay = 0;
		armor = 0;
		block = 0;
		reqlvl = 0;
		ilvl = 0;
		unique = 0;
		statsCount = 0;
		stat_types = new int[10];
		stat_values = new int[10];
		for (int i = 0; i < 10; i++) {
			stat_types[i] = 0;
			stat_values[i] = 0;
		}

		holy_resist = 0;
		fire_resist = 0;
		nature_resist = 0;
		frost_resist = 0;
		shadow_resist = 0;
		arcane_resist = 0;
		
		spell_ids = new int[5];
		spell_triggers = new int[5];
		spell_cds = new int[5];
		for (int i = 0; i < 5; i++) {
			spell_ids[i] = 0;
			spell_triggers[i] = 0;
			spell_cds[i] = 0;
		}
		
		socket_colors = new int[3];
		socket_contents = new int[3];
		for (int i = 0; i < 3; i++) {
			socket_colors[i] = 0;
			socket_contents[i] = 0;
		}
		
		duribility = 0;
	}

	////////////////////////////////
	////// Getters and Setters /////
	////////////////////////////////
	
	public int getSubclass() {
		return subclass;
	}

	public void setSubclass(int subclass) {
		this.subclass = subclass;
	}

	public int getStatsCount() {
		return statsCount;
	}

	public void setStatsCount(int statsCount) {
		this.statsCount = statsCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getClass_() {
		return class_;
	}

	public void setClass_(int class_) {
		this.class_ = class_;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getEntry() {
		return entry;
	}

	public void setEntry(int entry) {
		this.entry = entry;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(int inventoryType) {
		this.inventoryType = inventoryType;
	}

	public int getSheath() {
		return sheath;
	}

	public void setSheath(int sheath) {
		this.sheath = sheath;
	}

	public int getBinds() {
		return binds;
	}

	public void setBinds(int binds) {
		this.binds = binds;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getReqlvl() {
		return reqlvl;
	}

	public void setReqlvl(int reqlvl) {
		this.reqlvl = reqlvl;
	}

	public int getIlvl() {
		return ilvl;
	}

	public void setIlvl(int ilvl) {
		this.ilvl = ilvl;
	}

	public int getUnique() {
		return unique;
	}

	public void setUnique(int unique) {
		this.unique = unique;
	}

	public int getStat_type(int index) {
		return stat_types[index];
	}

	public void setStat_type(int index, int val) {
		this.stat_types[index] = val;
	}

	public int getStat_value(int index) {
		return stat_values[index];
	}

	public void setStat_value(int index, int val) {
		this.stat_values[index] = val;
	}

	public int getHoly_resist() {
		return holy_resist;
	}

	public void setHoly_resist(int holy_resist) {
		this.holy_resist = holy_resist;
	}

	public int getFire_resist() {
		return fire_resist;
	}

	public void setFire_resist(int fire_resist) {
		this.fire_resist = fire_resist;
	}

	public int getNature_resist() {
		return nature_resist;
	}

	public void setNature_resist(int nature_resist) {
		this.nature_resist = nature_resist;
	}

	public int getFrost_resist() {
		return frost_resist;
	}

	public void setFrost_resist(int frost_resist) {
		this.frost_resist = frost_resist;
	}

	public int getShadow_resist() {
		return shadow_resist;
	}

	public void setShadow_resist(int shadow_resist) {
		this.shadow_resist = shadow_resist;
	}

	public int getArcane_resist() {
		return arcane_resist;
	}

	public void setArcane_resist(int arcane_resist) {
		this.arcane_resist = arcane_resist;
	}

	public int getSpell_id(int index) {
		return spell_ids[index];
	}

	public void setSpell_id(int index, int spell_id) {
		this.spell_ids[index] = spell_id;
	}

	public int getSpell_trigger(int index) {
		return spell_triggers[index];
	}

	public void setSpell_trigger(int index, int spell_trigger) {
		this.spell_triggers[index] = spell_trigger;
	}

	public int getSpell_cd(int index) {
		return spell_cds[index];
	}

	public void setSpell_cd(int index, int spell_cd) {
		this.spell_cds[index] = spell_cd;
	}

	public int getSocket_color(int index) {
		return socket_colors[index];
	}

	public void setSocket_color(int index, int socket_color) {
		this.socket_colors[index] = socket_color;
	}

	public int getSocket_content(int index) {
		return socket_contents[index];
	}

	public void setSocket_content(int index, int socket_content) {
		this.socket_contents[index] = socket_content;
	}
	
	public int getMinDamage() {
		return mindamage;
	}

	public void setMinDamage(int mindamage) {
		this.mindamage = mindamage;
	}

	public int getMaxDamage() {
		return maxdamage;
	}

	public void setMaxDamage(int maxdamage) {
		this.maxdamage = maxdamage;
	}

	public int getDuribility() {
		return duribility;
	}

	public void setDuribility(int duribility) {
		this.duribility = duribility;
	}
	
	public ItemType getItemType() {
		switch(class_) {
		case 0:
			return ItemType.empty;
		case 2:
			return ItemType.weapon;
		case 4:
			return ItemType.armor;
		default:
			return ItemType.misc;
		}
	}
	
}

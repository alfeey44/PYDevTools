package PYDevTools.db.structures;

import PYDevTools.enums.ItemType;

public class Item {
	private String name, description, scriptName;
	private int entry, class_, display, quality, inventoryType, subclass, sheath, binds, delay,
				armor, block, reqlvl, ilvl, unique, statsCount, holy_resist, fire_resist,
				nature_resist, frost_resist, shadow_resist, arcane_resist, mindamage, maxdamage,
				duribility;
	private int soundOverrideSubclass, flags, flagsExtra, buyCount, buyPrice, sellPrice,
				allowableClass, allowableRace, requiredSkill, requiredSkillRank, requiredSpell,
				requiredHonorRank, requiredCityRank, requiredReputationFaction, requiredReputationRank,
				stackable, containerSlots, scalingStatDistribution, scalingStatValue, damageType,
				mindamage2, maxdamage2, damageType2, ammoType, rangedModRange, pageText, languageID,
				pageMaterial, startQuest, lockID, material, randomProperty, randomSuffix, itemSet, area, 
				map, bagFamily, totemCategory, socketBonus, gemProperties, requiredDisenchantSkill, 
				armorDamageModifier, duration, itemLimitCategory, holidayID, disenchantID, foodType, 
				minMoneyLoot, maxMoneyLoot, flagsCustom, verifiedBuild;

	private int[] stat_values;
	private int[] stat_types;
	private int[] spell_ids;
	private int[] spell_triggers;
	private int[] spell_charges;
	private int[] spell_ppm_rate;
	private int[] spell_cds;
	private int[] spell_categories;
	private int[] spell_category_cds;
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
		stackable = 1;
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
		spell_charges = new int[5];
		spell_ppm_rate = new int[5];
		spell_categories = new int[5];
		spell_category_cds = new int[5];
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
		soundOverrideSubclass = -1;
		scriptName = "";
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
	
	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public int getMindamage() {
		return mindamage;
	}

	public void setMindamage(int mindamage) {
		this.mindamage = mindamage;
	}

	public int getMaxdamage() {
		return maxdamage;
	}

	public void setMaxdamage(int maxdamage) {
		this.maxdamage = maxdamage;
	}

	public int getSoundOverrideSubclass() {
		return soundOverrideSubclass;
	}

	public void setSoundOverrideSubclass(int soundOverrideSubclass) {
		this.soundOverrideSubclass = soundOverrideSubclass;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public int getFlagsExtra() {
		return flagsExtra;
	}

	public void setFlagsExtra(int flagsExtra) {
		this.flagsExtra = flagsExtra;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getAllowableClass() {
		return allowableClass;
	}

	public void setAllowableClass(int allowableClass) {
		this.allowableClass = allowableClass;
	}

	public int getAllowableRace() {
		return allowableRace;
	}

	public void setAllowableRace(int allowableRace) {
		this.allowableRace = allowableRace;
	}

	public int getRequiredSkill() {
		return requiredSkill;
	}

	public void setRequiredSkill(int requiredSkill) {
		this.requiredSkill = requiredSkill;
	}

	public int getRequiredSkillRank() {
		return requiredSkillRank;
	}

	public void setRequiredSkillRank(int requiredSkillRank) {
		this.requiredSkillRank = requiredSkillRank;
	}

	public int getRequiredSpell() {
		return requiredSpell;
	}

	public void setRequiredSpell(int requiredSpell) {
		this.requiredSpell = requiredSpell;
	}

	public int getRequiredHonorRank() {
		return requiredHonorRank;
	}

	public void setRequiredHonorRank(int requiredHonorRank) {
		this.requiredHonorRank = requiredHonorRank;
	}

	public int getRequiredCityRank() {
		return requiredCityRank;
	}

	public void setRequiredCityRank(int requiredCityRank) {
		this.requiredCityRank = requiredCityRank;
	}

	public int getRequiredReputationFaction() {
		return requiredReputationFaction;
	}

	public void setRequiredReputationFaction(int requiredReputationFaction) {
		this.requiredReputationFaction = requiredReputationFaction;
	}

	public int getRequiredReputationRank() {
		return requiredReputationRank;
	}

	public void setRequiredReputationRank(int requiredReputationRank) {
		this.requiredReputationRank = requiredReputationRank;
	}

	public int getStackable() {
		return stackable;
	}

	public void setStackable(int stackable) {
		this.stackable = stackable;
	}

	public int getContainerSlots() {
		return containerSlots;
	}

	public void setContainerSlots(int containerSlots) {
		this.containerSlots = containerSlots;
	}

	public int getScalingStatDistribution() {
		return scalingStatDistribution;
	}

	public void setScalingStatDistribution(int scalingStatDistribution) {
		this.scalingStatDistribution = scalingStatDistribution;
	}

	public int getScalingStatValue() {
		return scalingStatValue;
	}

	public void setScalingStatValue(int scalingStatValue) {
		this.scalingStatValue = scalingStatValue;
	}

	public int getDamageType() {
		return damageType;
	}

	public void setDamageType(int damageType) {
		this.damageType = damageType;
	}

	public int getMindamage2() {
		return mindamage2;
	}

	public void setMindamage2(int mindamage2) {
		this.mindamage2 = mindamage2;
	}

	public int getMaxdamage2() {
		return maxdamage2;
	}

	public void setMaxdamage2(int maxdamage2) {
		this.maxdamage2 = maxdamage2;
	}

	public int getDamageType2() {
		return damageType2;
	}

	public void setDamageType2(int damageType2) {
		this.damageType2 = damageType2;
	}

	public int getAmmoType() {
		return ammoType;
	}

	public void setAmmoType(int ammoType) {
		this.ammoType = ammoType;
	}

	public int getRangedModRange() {
		return rangedModRange;
	}

	public void setRangedModRange(int rangedModRange) {
		this.rangedModRange = rangedModRange;
	}

	public int getPageText() {
		return pageText;
	}

	public void setPageText(int pageText) {
		this.pageText = pageText;
	}

	public int getLanguageID() {
		return languageID;
	}

	public void setLanguageID(int languageID) {
		this.languageID = languageID;
	}

	public int getPageMaterial() {
		return pageMaterial;
	}

	public void setPageMaterial(int pageMaterial) {
		this.pageMaterial = pageMaterial;
	}

	public int getStartQuest() {
		return startQuest;
	}

	public void setStartQuest(int startQuest) {
		this.startQuest = startQuest;
	}

	public int getLockID() {
		return lockID;
	}

	public void setLockID(int lockID) {
		this.lockID = lockID;
	}

	public int getMaterial() {
		return material;
	}

	public void setMaterial(int material) {
		this.material = material;
	}

	public int getRandomProperty() {
		return randomProperty;
	}

	public void setRandomProperty(int randomProperty) {
		this.randomProperty = randomProperty;
	}

	public int getRandomSuffix() {
		return randomSuffix;
	}

	public void setRandomSuffix(int randomSuffix) {
		this.randomSuffix = randomSuffix;
	}

	public int getItemSet() {
		return itemSet;
	}

	public void setItemSet(int itemSet) {
		this.itemSet = itemSet;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public int getBagFamily() {
		return bagFamily;
	}

	public void setBagFamily(int bagFamily) {
		this.bagFamily = bagFamily;
	}

	public int getTotemCategory() {
		return totemCategory;
	}

	public void setTotemCategory(int totemCategory) {
		this.totemCategory = totemCategory;
	}

	public int getSocketBonus() {
		return socketBonus;
	}

	public void setSocketBonus(int socketBonus) {
		this.socketBonus = socketBonus;
	}

	public int getGemProperties() {
		return gemProperties;
	}

	public void setGemProperties(int gemProperties) {
		this.gemProperties = gemProperties;
	}

	public int getRequiredDisenchantSkill() {
		return requiredDisenchantSkill;
	}

	public void setRequiredDisenchantSkill(int requiredDisenchantSkill) {
		this.requiredDisenchantSkill = requiredDisenchantSkill;
	}

	public int getArmorDamageModifier() {
		return armorDamageModifier;
	}

	public void setArmorDamageModifier(int armorDamageModifier) {
		this.armorDamageModifier = armorDamageModifier;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getItemLimitCategory() {
		return itemLimitCategory;
	}

	public void setItemLimitCategory(int itemLimitCategory) {
		this.itemLimitCategory = itemLimitCategory;
	}

	public int getHolidayID() {
		return holidayID;
	}

	public void setHolidayID(int holidayID) {
		this.holidayID = holidayID;
	}

	public int getDisenchantID() {
		return disenchantID;
	}

	public void setDisenchantID(int disenchantID) {
		this.disenchantID = disenchantID;
	}

	public int getFoodType() {
		return foodType;
	}

	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}

	public int getMinMoneyLoot() {
		return minMoneyLoot;
	}

	public void setMinMoneyLoot(int minMoneyLoot) {
		this.minMoneyLoot = minMoneyLoot;
	}

	public int getMaxMoneyLoot() {
		return maxMoneyLoot;
	}

	public void setMaxMoneyLoot(int maxMoneyLoot) {
		this.maxMoneyLoot = maxMoneyLoot;
	}

	public int getFlagsCustom() {
		return flagsCustom;
	}

	public void setFlagsCustom(int flagsCustom) {
		this.flagsCustom = flagsCustom;
	}

	public int getVerifiedBuild() {
		return verifiedBuild;
	}

	public void setVerifiedBuild(int verifiedBuild) {
		this.verifiedBuild = verifiedBuild;
	}

	public int[] getStat_values() {
		return stat_values;
	}

	public void setStat_values(int[] stat_values) {
		this.stat_values = stat_values;
	}

	public int[] getStat_types() {
		return stat_types;
	}

	public void setStat_types(int[] stat_types) {
		this.stat_types = stat_types;
	}

	public int[] getSpell_ids() {
		return spell_ids;
	}

	public void setSpell_ids(int[] spell_ids) {
		this.spell_ids = spell_ids;
	}

	public int[] getSpell_triggers() {
		return spell_triggers;
	}

	public void setSpell_triggers(int[] spell_triggers) {
		this.spell_triggers = spell_triggers;
	}

	public int[] getSpell_charges() {
		return spell_charges;
	}
	
	public int getSpell_charge(int index) {
		return spell_charges[index];
	}

	public void setSpell_charges(int[] spell_charges) {
		this.spell_charges = spell_charges;
	}
	
	public void setSpell_charge(int index, int charges) {
		this.spell_charges[index] = charges;
	}

	public int[] getSpell_ppm_rate() {
		return spell_ppm_rate;
	}
	
	public int getSpell_ppm_rate(int index) {
		return spell_ppm_rate[index];
	}

	public void setSpell_ppm_rate(int[] spell_ppm_rate) {
		this.spell_ppm_rate = spell_ppm_rate;
	}
	
	public void setSpell_ppm_rate(int index, int ppm) {
		this.spell_ppm_rate[index] = ppm;
	}

	public int[] getSpell_cds() {
		return spell_cds;
	}

	public void setSpell_cds(int[] spell_cds) {
		this.spell_cds = spell_cds;
	}

	public int[] getSpell_categories() {
		return spell_categories;
	}
	
	public int getSpell_category(int index) {
		return spell_categories[index];
	}

	public void setSpell_categories(int[] spell_categories) {
		this.spell_categories = spell_categories;
	}
	
	public void setSpell_category(int index, int category) {
		this.spell_categories[index] = category;
	}

	public int[] getSpell_category_cds() {
		return spell_category_cds;
	}
	
	public int getSpell_category_cd(int index) {
		return spell_category_cds[index];
	}

	public void setSpell_category_cds(int[] spell_category_cds) {
		this.spell_category_cds = spell_category_cds;
	}
	
	public void setSpell_category_cd(int index, int cd) {
		this.spell_category_cds[index] = cd;
	}

	public int[] getSocket_colors() {
		return socket_colors;
	}

	public void setSocket_colors(int[] socket_colors) {
		this.socket_colors = socket_colors;
	}

	public int[] getSocket_contents() {
		return socket_contents;
	}

	public void setSocket_contents(int[] socket_contents) {
		this.socket_contents = socket_contents;
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

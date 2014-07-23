package PYDevTools.db.structures;

public class Item {
	private String name, description;
	private int entry, display, quality, equip, type, sheath, binds, delay,
				armor, block, reqlvl, ilvl, unique, stat_type1, stat_type2, 
				stat_type3, stat_type4, stat_type5, stat_type6, stat_type7, 
				stat_type8, stat_type9, stat_type10, stat_value1, stat_value2,
				stat_value3, stat_value4, stat_value5, stat_value6, stat_value7,
				stat_value8, stat_value9, stat_value10, holy_resist, fire_resist,
				nature_resist, frost_resist, shadow_resist, arcane_resist,
				spell_id1, spell_trigger1, spell_cd1,
				spell_id2, spell_trigger2, spell_cd2,
				spell_id3, spell_trigger3, spell_cd3,
				spell_id4, spell_trigger4, spell_cd4,
				spell_id5, spell_trigger5, spell_cd5,
				socket_color1, socket_content1, socket_color2, socket_content2,
				socket_color3, socket_content3;
	
	public Item() {
		name = "";
		description = "";
		entry = 444444;
		display = 0;
		quality = 0;
		equip = 0;
		type = 0;
		sheath = 0;
		binds = 0;
		delay = 0;
		armor = 0;
		block = 0;
		reqlvl = 0;
		ilvl = 0;
		unique = 0;
		stat_type1 = 0;
		stat_type2 = 0;
		stat_type3 = 0;
		stat_type4 = 0;
		stat_type5 = 0;
		stat_type6 = 0;
		stat_type7 = 0;
		stat_type8 = 0;
		stat_type9 = 0;
		stat_type10 = 0;
		stat_value1 = 0;
		stat_value2 = 0;
		stat_value3 = 0;
		stat_value4 = 0;
		stat_value5 = 0;
		stat_value6 = 0;
		stat_value7 = 0;
		stat_value8 = 0;
		stat_value9 = 0;
		stat_value10 = 0;
		holy_resist = 0;
		fire_resist = 0;
		nature_resist = 0;
		frost_resist = 0;
		shadow_resist = 0;
		arcane_resist = 0;
		spell_id1 = 0;
		spell_trigger1 = 0;
		spell_cd1 = 0;
		spell_id2 = 0;
		spell_trigger2 = 0;
		spell_cd2 = 0;
		spell_id3 = 0;
		spell_trigger3 = 0;
		spell_cd3 = 0;
		spell_id4 = 0;
		spell_trigger4 = 0;
		spell_cd4 = 0;
		spell_id5 = 0;
		spell_trigger5 = 0;
		spell_cd5 = 0;
		socket_color1 = 0;
		socket_content1 = 0;
		socket_color2 = 0;
		socket_content2 = 0;
		socket_color3 = 0;
		socket_content3 = 0;
	}

	////////////////////////////////
	////// Getters and Setters /////
	////////////////////////////////
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getEquip() {
		return equip;
	}

	public void setEquip(int equip) {
		this.equip = equip;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getStat_type1() {
		return stat_type1;
	}

	public void setStat_type1(int stat_type1) {
		this.stat_type1 = stat_type1;
	}

	public int getStat_type2() {
		return stat_type2;
	}

	public void setStat_type2(int stat_type2) {
		this.stat_type2 = stat_type2;
	}

	public int getStat_type3() {
		return stat_type3;
	}

	public void setStat_type3(int stat_type3) {
		this.stat_type3 = stat_type3;
	}

	public int getStat_type4() {
		return stat_type4;
	}

	public void setStat_type4(int stat_type4) {
		this.stat_type4 = stat_type4;
	}

	public int getStat_type5() {
		return stat_type5;
	}

	public void setStat_type5(int stat_type5) {
		this.stat_type5 = stat_type5;
	}

	public int getStat_type6() {
		return stat_type6;
	}

	public void setStat_type6(int stat_type6) {
		this.stat_type6 = stat_type6;
	}

	public int getStat_type7() {
		return stat_type7;
	}

	public void setStat_type7(int stat_type7) {
		this.stat_type7 = stat_type7;
	}

	public int getStat_type8() {
		return stat_type8;
	}

	public void setStat_type8(int stat_type8) {
		this.stat_type8 = stat_type8;
	}

	public int getStat_type9() {
		return stat_type9;
	}

	public void setStat_type9(int stat_type9) {
		this.stat_type9 = stat_type9;
	}

	public int getStat_type10() {
		return stat_type10;
	}

	public void setStat_type10(int stat_type10) {
		this.stat_type10 = stat_type10;
	}

	public int getStat_value1() {
		return stat_value1;
	}

	public void setStat_value1(int stat_value1) {
		this.stat_value1 = stat_value1;
	}

	public int getStat_value2() {
		return stat_value2;
	}

	public void setStat_value2(int stat_value2) {
		this.stat_value2 = stat_value2;
	}

	public int getStat_value3() {
		return stat_value3;
	}

	public void setStat_value3(int stat_value3) {
		this.stat_value3 = stat_value3;
	}

	public int getStat_value4() {
		return stat_value4;
	}

	public void setStat_value4(int stat_value4) {
		this.stat_value4 = stat_value4;
	}

	public int getStat_value5() {
		return stat_value5;
	}

	public void setStat_value5(int stat_value5) {
		this.stat_value5 = stat_value5;
	}

	public int getStat_value6() {
		return stat_value6;
	}

	public void setStat_value6(int stat_value6) {
		this.stat_value6 = stat_value6;
	}

	public int getStat_value7() {
		return stat_value7;
	}

	public void setStat_value7(int stat_value7) {
		this.stat_value7 = stat_value7;
	}

	public int getStat_value8() {
		return stat_value8;
	}

	public void setStat_value8(int stat_value8) {
		this.stat_value8 = stat_value8;
	}

	public int getStat_value9() {
		return stat_value9;
	}

	public void setStat_value9(int stat_value9) {
		this.stat_value9 = stat_value9;
	}

	public int getStat_value10() {
		return stat_value10;
	}

	public void setStat_value10(int stat_value10) {
		this.stat_value10 = stat_value10;
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

	public int getSpell_id1() {
		return spell_id1;
	}

	public void setSpell_id1(int spell_id1) {
		this.spell_id1 = spell_id1;
	}

	public int getSpell_trigger1() {
		return spell_trigger1;
	}

	public void setSpell_trigger1(int spell_trigger1) {
		this.spell_trigger1 = spell_trigger1;
	}

	public int getSpell_cd1() {
		return spell_cd1;
	}

	public void setSpell_cd1(int spell_cd1) {
		this.spell_cd1 = spell_cd1;
	}

	public int getSpell_id2() {
		return spell_id2;
	}

	public void setSpell_id2(int spell_id2) {
		this.spell_id2 = spell_id2;
	}

	public int getSpell_trigger2() {
		return spell_trigger2;
	}

	public void setSpell_trigger2(int spell_trigger2) {
		this.spell_trigger2 = spell_trigger2;
	}

	public int getSpell_cd2() {
		return spell_cd2;
	}

	public void setSpell_cd2(int spell_cd2) {
		this.spell_cd2 = spell_cd2;
	}

	public int getSpell_id3() {
		return spell_id3;
	}

	public void setSpell_id3(int spell_id3) {
		this.spell_id3 = spell_id3;
	}

	public int getSpell_trigger3() {
		return spell_trigger3;
	}

	public void setSpell_trigger3(int spell_trigger3) {
		this.spell_trigger3 = spell_trigger3;
	}

	public int getSpell_cd3() {
		return spell_cd3;
	}

	public void setSpell_cd3(int spell_cd3) {
		this.spell_cd3 = spell_cd3;
	}

	public int getSpell_id4() {
		return spell_id4;
	}

	public void setSpell_id4(int spell_id4) {
		this.spell_id4 = spell_id4;
	}

	public int getSpell_trigger4() {
		return spell_trigger4;
	}

	public void setSpell_trigger4(int spell_trigger4) {
		this.spell_trigger4 = spell_trigger4;
	}

	public int getSpell_cd4() {
		return spell_cd4;
	}

	public void setSpell_cd4(int spell_cd4) {
		this.spell_cd4 = spell_cd4;
	}

	public int getSpell_id5() {
		return spell_id5;
	}

	public void setSpell_id5(int spell_id5) {
		this.spell_id5 = spell_id5;
	}

	public int getSpell_trigger5() {
		return spell_trigger5;
	}

	public void setSpell_trigger5(int spell_trigger5) {
		this.spell_trigger5 = spell_trigger5;
	}

	public int getSpell_cd5() {
		return spell_cd5;
	}

	public void setSpell_cd5(int spell_cd5) {
		this.spell_cd5 = spell_cd5;
	}

	public int getSocket_color1() {
		return socket_color1;
	}

	public void setSocket_color1(int socket_color1) {
		this.socket_color1 = socket_color1;
	}

	public int getSocket_content1() {
		return socket_content1;
	}

	public void setSocket_content1(int socket_content1) {
		this.socket_content1 = socket_content1;
	}

	public int getSocket_color2() {
		return socket_color2;
	}

	public void setSocket_color2(int socket_color2) {
		this.socket_color2 = socket_color2;
	}

	public int getSocket_content2() {
		return socket_content2;
	}

	public void setSocket_content2(int socket_content2) {
		this.socket_content2 = socket_content2;
	}

	public int getSocket_color3() {
		return socket_color3;
	}

	public void setSocket_color3(int socket_color3) {
		this.socket_color3 = socket_color3;
	}

	public int getSocket_content3() {
		return socket_content3;
	}

	public void setSocket_content3(int socket_content3) {
		this.socket_content3 = socket_content3;
	}
	
	
}

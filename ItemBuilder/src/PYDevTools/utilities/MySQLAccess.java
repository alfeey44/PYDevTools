package PYDevTools.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import PYDevTools.db.structures.Item;
import PYDevTools.view.SettingsPanel;

public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String username = "";
	private String password = "";
	private String dbname = "";
	private String host = "";

	public MySQLAccess() {
		username = SettingsPanel.getInstance().getDBUserName();
		password = SettingsPanel.getInstance().getDBPassword();
		dbname = SettingsPanel.getInstance().getDBWorldName();
		host = SettingsPanel.getInstance().getDBHostName();
	}

	public ArrayList<Item> getItemFromTemplate(String s) throws Exception {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			connectToDB();
			// resultSet gets the result of the SQL query
			resultSet = statement
					.executeQuery("SELECT * FROM " + dbname + ".item_template WHERE"
							+ " `name` LIKE '%" + s + "%'"
							+ " OR `entry` LIKE '%" + s + "%';");
			while (resultSet.next()) {
				Item item = new Item();
				fillItemFromTemplate(resultSet, item);
				items.add(item);
			}

			if (items.isEmpty()) {
				System.out.println("No items found.");
				close();
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return items;
	}

	private void fillItemFromTemplate(ResultSet resultSet, Item item) {
		try {
			item.setEntry(resultSet.getInt("entry"));
			item.setClass_(resultSet.getInt("class"));
			item.setSubclass(resultSet.getInt("subclass"));
			item.setName(resultSet.getString("name"));
			item.setDisplay(resultSet.getInt("displayid"));
			item.setQuality(resultSet.getInt("Quality"));
			item.setInventoryType(resultSet.getInt("InventoryType"));
			item.setIlvl(resultSet.getInt("ItemLevel"));
			item.setReqlvl(resultSet.getInt("RequiredLevel"));
			item.setUnique(resultSet.getInt("maxcount"));
			item.setStackable(resultSet.getInt("stackable"));
			item.setStatsCount(resultSet.getInt("StatsCount"));
			for (int i = 0; i < 10; i++) {
				item.setStat_type(i, resultSet.getInt("stat_type" + (i + 1)));
				item.setStat_value(i, resultSet.getInt("stat_value" + (i + 1)));
			}
			item.setMinDamage(resultSet.getInt("dmg_min1"));
			item.setMaxDamage(resultSet.getInt("dmg_max1"));
			item.setArmor(resultSet.getInt("armor"));
			item.setHoly_resist(resultSet.getInt("holy_res"));
			item.setFire_resist(resultSet.getInt("fire_res"));
			item.setNature_resist(resultSet.getInt("nature_res"));
			item.setFrost_resist(resultSet.getInt("frost_res"));
			item.setShadow_resist(resultSet.getInt("shadow_res"));
			item.setArcane_resist(resultSet.getInt("arcane_res"));
			item.setDelay(resultSet.getInt("delay"));
			item.setBinds(resultSet.getInt("bonding"));
			item.setDescription(resultSet.getString("description"));
			item.setSheath(resultSet.getInt("sheath"));
			item.setBlock(resultSet.getInt("block"));
			item.setDuribility(resultSet.getInt("MaxDurability"));
			item.setSpell_id(0, resultSet.getInt("spellid_1"));
			item.setSpell_id(1, resultSet.getInt("spellid_2"));
			item.setSpell_id(2, resultSet.getInt("spellid_3"));
			item.setSpell_id(3, resultSet.getInt("spellid_4"));
			item.setSpell_id(4, resultSet.getInt("spellid_5"));
			item.setSpell_trigger(0, resultSet.getInt("spelltrigger_1"));
			item.setSpell_trigger(1, resultSet.getInt("spelltrigger_2"));
			item.setSpell_trigger(2, resultSet.getInt("spelltrigger_3"));
			item.setSpell_trigger(3, resultSet.getInt("spelltrigger_4"));
			item.setSpell_trigger(4, resultSet.getInt("spelltrigger_5"));
			
			item.setSoundOverrideSubclass(resultSet.getInt("SoundOverrideSubclass"));
			item.setFlags(resultSet.getInt("Flags"));
			item.setFlagsExtra(resultSet.getInt("FlagsExtra"));
			item.setBuyCount(resultSet.getInt("BuyCount"));
			item.setBuyPrice(resultSet.getInt("BuyPrice"));
			item.setSellPrice(resultSet.getInt("SellPrice"));
			item.setAllowableClass(resultSet.getInt("AllowableClass"));
			item.setAllowableRace(resultSet.getInt("AllowableRace"));
			item.setRequiredSkill(resultSet.getInt("RequiredSkill"));
			item.setRequiredSkillRank(resultSet.getInt("RequiredSkillRank"));
			item.setRequiredSpell(resultSet.getInt("requiredspell"));
			item.setRequiredHonorRank(resultSet.getInt("requiredhonorrank"));
			item.setRequiredCityRank(resultSet.getInt("RequiredCityRank"));
			item.setRequiredReputationFaction(resultSet.getInt("RequiredReputationFaction"));
			item.setRequiredReputationRank(resultSet.getInt("RequiredReputationRank"));
			item.setStackable(resultSet.getInt("stackable"));
			item.setContainerSlots(resultSet.getInt("ContainerSlots"));
			item.setScalingStatDistribution(resultSet.getInt("ScalingStatDistribution"));
			item.setScalingStatValue(resultSet.getInt("ScalingStatValue"));
			item.setMindamage2(resultSet.getInt("dmg_min2"));
			item.setMaxdamage2(resultSet.getInt("dmg_max2"));
			item.setDamageType2(resultSet.getInt("dmg_type2"));
			item.setAmmoType(resultSet.getInt("ammo_type"));
			item.setRangedModRange(resultSet.getInt("RangedModRange"));
			try {
				item.setSpell_charge(0, resultSet.getInt("spellcharges_1"));
			} catch(NullPointerException ne) {
				item.setSpell_charge(0, -1);
			}
			item.setSpell_charge(1, resultSet.getInt("spellcharges_2"));
			item.setSpell_charge(2, resultSet.getInt("spellcharges_3"));
			item.setSpell_charge(3, resultSet.getInt("spellcharges_4"));
			item.setSpell_charge(4, resultSet.getInt("spellcharges_5"));
			item.setSpell_ppm_rate(0, resultSet.getInt("spellppmRate_1"));
			item.setSpell_ppm_rate(1, resultSet.getInt("spellppmRate_2"));
			item.setSpell_ppm_rate(2, resultSet.getInt("spellppmRate_3"));
			item.setSpell_ppm_rate(3, resultSet.getInt("spellppmRate_4"));
			item.setSpell_ppm_rate(4, resultSet.getInt("spellppmRate_5"));
			item.setSpell_cd(0, resultSet.getInt("spellcooldown_1"));
			item.setSpell_cd(1, resultSet.getInt("spellcooldown_2"));
			item.setSpell_cd(2, resultSet.getInt("spellcooldown_3"));
			item.setSpell_cd(3, resultSet.getInt("spellcooldown_4"));
			item.setSpell_cd(4, resultSet.getInt("spellcooldown_5"));
			item.setSpell_category(0, resultSet.getInt("spellcategory_1"));
			item.setSpell_category(1, resultSet.getInt("spellcategory_2"));
			item.setSpell_category(2, resultSet.getInt("spellcategory_3"));
			item.setSpell_category(3, resultSet.getInt("spellcategory_4"));
			item.setSpell_category(4, resultSet.getInt("spellcategory_5"));
			item.setSpell_category_cd(0, resultSet.getInt("spellcategorycooldown_1"));
			item.setSpell_category_cd(1, resultSet.getInt("spellcategorycooldown_2"));
			item.setSpell_category_cd(2, resultSet.getInt("spellcategorycooldown_3"));
			item.setSpell_category_cd(3, resultSet.getInt("spellcategorycooldown_4"));
			item.setSpell_category_cd(4, resultSet.getInt("spellcategorycooldown_5"));
			item.setPageText(resultSet.getInt("PageText"));
			item.setLanguageID(resultSet.getInt("LanguageID"));
			item.setPageMaterial(resultSet.getInt("PageMaterial"));
			item.setStartQuest(resultSet.getInt("startquest"));
			item.setLockID(resultSet.getInt("lockid"));
			item.setMaterial(resultSet.getInt("Material"));
			item.setRandomProperty(resultSet.getInt("RandomProperty"));
			item.setRandomSuffix(resultSet.getInt("RandomSuffix"));
			item.setItemSet(resultSet.getInt("itemset"));
			item.setArea(resultSet.getInt("area"));
			item.setMap(resultSet.getInt("Map"));
			item.setBagFamily(resultSet.getInt("BagFamily"));
			item.setTotemCategory(resultSet.getInt("TotemCategory"));
			item.setSocketBonus(resultSet.getInt("socketBonus"));
			item.setGemProperties(resultSet.getInt("GemProperties"));
			item.setRequiredDisenchantSkill(resultSet.getInt("RequiredDisenchantSkill"));
			item.setArmorDamageModifier(resultSet.getInt("ArmorDamageModifier"));
			item.setDuration(resultSet.getInt("duration"));
			item.setItemLimitCategory(resultSet.getInt("ItemLimitCategory"));
			item.setHolidayID(resultSet.getInt("HolidayId"));
			item.setScriptName(resultSet.getString("ScriptName"));
			item.setDisenchantID(resultSet.getInt("DisenchantID"));
			item.setFoodType(resultSet.getInt("FoodType"));
			item.setMinMoneyLoot(resultSet.getInt("minMoneyLoot"));
			item.setMaxMoneyLoot(resultSet.getInt("maxMoneyLoot"));
			item.setFlagsCustom(resultSet.getInt("flagsCustom"));
			item.setVerifiedBuild(resultSet.getInt("VerifiedBuild"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isUsedEntry(int entry) {
		try {
			connectToDB();
			resultSet = statement
					.executeQuery("SELECT * FROM " + dbname + ".item_template WHERE entry = "
							+ entry + ";");
			if (resultSet.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close();
		}
	}

	public void insertItemIntoTemplate(Item item) throws Exception {
		try {
			connectToDB();
			statement
					.executeUpdate("REPLACE INTO `item_template` (`entry`, `class`, `subclass`, `SoundOverrideSubclass`, `name`, `displayid`, `Quality`, `Flags`, `FlagsExtra`, `BuyCount`, `BuyPrice`, `SellPrice`, `InventoryType`, `AllowableClass`, `AllowableRace`, `ItemLevel`, `RequiredLevel`, `RequiredSkill`, `RequiredSkillRank`, `requiredspell`, `requiredhonorrank`, `RequiredCityRank`, `RequiredReputationFaction`, `RequiredReputationRank`, `maxcount`, `stackable`, `ContainerSlots`, `StatsCount`, `stat_type1`, `stat_value1`, `stat_type2`, `stat_value2`, `stat_type3`, `stat_value3`, `stat_type4`, `stat_value4`, `stat_type5`, `stat_value5`, `stat_type6`, `stat_value6`, `stat_type7`, `stat_value7`, `stat_type8`, `stat_value8`, `stat_type9`, `stat_value9`, `stat_type10`, `stat_value10`, `ScalingStatDistribution`, `ScalingStatValue`, `dmg_min1`, `dmg_max1`, `dmg_type1`, `dmg_min2`, `dmg_max2`, `dmg_type2`, `armor`, `holy_res`, `fire_res`, `nature_res`, `frost_res`, `shadow_res`, `arcane_res`, `delay`, `ammo_type`, `RangedModRange`, `spellid_1`, `spelltrigger_1`, `spellcharges_1`, `spellppmRate_1`, `spellcooldown_1`, `spellcategory_1`, `spellcategorycooldown_1`, `spellid_2`, `spelltrigger_2`, `spellcharges_2`, `spellppmRate_2`, `spellcooldown_2`, `spellcategory_2`, `spellcategorycooldown_2`, `spellid_3`, `spelltrigger_3`, `spellcharges_3`, `spellppmRate_3`, `spellcooldown_3`, `spellcategory_3`, `spellcategorycooldown_3`, `spellid_4`, `spelltrigger_4`, `spellcharges_4`, `spellppmRate_4`, `spellcooldown_4`, `spellcategory_4`, `spellcategorycooldown_4`, `spellid_5`, `spelltrigger_5`, `spellcharges_5`, `spellppmRate_5`, `spellcooldown_5`, `spellcategory_5`, `spellcategorycooldown_5`, `bonding`, `description`, `PageText`, `LanguageID`, `PageMaterial`, `startquest`, `lockid`, `Material`, `sheath`, `RandomProperty`, `RandomSuffix`, `block`, `itemset`, `MaxDurability`, `area`, `Map`, `BagFamily`, `TotemCategory`, `socketColor_1`, `socketContent_1`, `socketColor_2`, `socketContent_2`, `socketColor_3`, `socketContent_3`, `socketBonus`, `GemProperties`, `RequiredDisenchantSkill`, `ArmorDamageModifier`, `duration`, `ItemLimitCategory`, `HolidayId`, `ScriptName`, `DisenchantID`, `FoodType`, `minMoneyLoot`, `maxMoneyLoot`, `flagsCustom`, `VerifiedBuild`) VALUES ("
							+ item.getEntry()
							+ ", "
							+ item.getClass_()
							+ ", "
							+ item.getSubclass()
							+ ", "
							+ item.getSoundOverrideSubclass()
							+ ", '"
							+ item.getName()
							+ "', "
							+ item.getDisplay()
							+ ", "
							+ item.getQuality()
							+ ", "
							+ item.getFlags()
							+ ", "
							+ item.getFlagsExtra()
							+ ", "
							+ item.getBuyCount()
							+ ", "
							+ item.getBuyPrice()
							+ ", "
							+ item.getSellPrice()
							+ ", "
							+ item.getInventoryType()
							+ ", "
							+ item.getAllowableClass()
							+ ", "
							+ item.getAllowableRace()
							+ ", "
							+ item.getIlvl()
							+ ", "
							+ item.getReqlvl()
							+ ", "
							+ item.getRequiredSkill()
							+ ", "
							+ item.getRequiredSkillRank()
							+ ", "
							+ item.getRequiredSpell()
							+ ", "
							+ item.getRequiredHonorRank()
							+ ", "
							+ item.getRequiredCityRank()
							+ ", "
							+ item.getRequiredReputationFaction()
							+ ", "
							+ item.getRequiredReputationRank()
							+ ", "
							+ item.getUnique()
							+ ", "
							+ item.getStackable()
							+ ", "
							+ item.getContainerSlots()
							+ ", "
							+ item.getStatsCount()
							+ ", "
							+ item.getStat_type(0)
							+ ", "
							+ item.getStat_value(0)
							+ ", "
							+ item.getStat_type(1)
							+ ", "
							+ item.getStat_value(1)
							+ ", "
							+ item.getStat_type(2)
							+ ", "
							+ item.getStat_value(2)
							+ ", "
							+ item.getStat_type(3)
							+ ", "
							+ item.getStat_value(3)
							+ ", "
							+ item.getStat_type(4)
							+ ", "
							+ item.getStat_value(4)
							+ ", "
							+ item.getStat_type(5)
							+ ", "
							+ item.getStat_value(5)
							+ ", "
							+ item.getStat_type(6)
							+ ", "
							+ item.getStat_value(6)
							+ ", "
							+ item.getStat_type(7)
							+ ", "
							+ item.getStat_value(7)
							+ ", "
							+ item.getStat_type(8)
							+ ", "
							+ item.getStat_value(8)
							+ ", "
							+ item.getStat_type(9)
							+ ", "
							+ item.getStat_value(9)
							+ ", "
							+ item.getScalingStatDistribution()
							+ ", "
							+ item.getScalingStatValue()
							+ ", "
							+ item.getMinDamage()
							+ ", "
							+ item.getMaxDamage()
							+ ", "
							+ item.getDamageType()
							+ ", "
							+ item.getMindamage2()
							+ ", "
							+ item.getMaxdamage2()
							+ ", "
							+ item.getDamageType2()
							+ ", "
							+ item.getArmor()
							+ ", "
							+ item.getHoly_resist()
							+ ", "
							+ item.getFire_resist()
							+ ", "
							+ item.getNature_resist()
							+ ", "
							+ item.getFrost_resist()
							+ ", "
							+ item.getShadow_resist()
							+ ", "
							+ item.getArcane_resist()
							+ ", "
							+ item.getDelay()
							+ ", "
							+ item.getAmmoType()
							+ ", "
							+ item.getRangedModRange()
							+ ", "
							+ item.getSpell_id(0)
							+ ", "
							+ item.getSpell_trigger(0)
							+ ", "
							+ item.getSpell_charge(0)
							+ ", "
							+ item.getSpell_ppm_rate(0)
							+ ", "
							+ item.getSpell_cd(0)
							+ ", "
							+ item.getSpell_category(0)
							+ ", "
							+ item.getSpell_category_cd(0)
							+ ", "
							+ item.getSpell_id(1)
							+ ", "
							+ item.getSpell_trigger(1)
							+ ", "
							+ item.getSpell_charge(1)
							+ ", "
							+ item.getSpell_ppm_rate(1)
							+ ", "
							+ item.getSpell_cd(1)
							+ ", "
							+ item.getSpell_category(1)
							+ ", "
							+ item.getSpell_category_cd(1)
							+ ", "
							+ item.getSpell_id(2)
							+ ", "
							+ item.getSpell_trigger(2)
							+ ", "
							+ item.getSpell_charge(2)
							+ ", "
							+ item.getSpell_ppm_rate(2)
							+ ", "
							+ item.getSpell_cd(2)
							+ ", "
							+ item.getSpell_category(2)
							+ ", "
							+ item.getSpell_category_cd(2)
							+ ", "
							+ item.getSpell_id(3)
							+ ", "
							+ item.getSpell_trigger(3)
							+ ", "
							+ item.getSpell_charge(3)
							+ ", "
							+ item.getSpell_ppm_rate(3)
							+ ", "
							+ item.getSpell_cd(3)
							+ ", "
							+ item.getSpell_category(3)
							+ ", "
							+ item.getSpell_category_cd(3)
							+ ", "
							+ item.getSpell_id(4)
							+ ", "
							+ item.getSpell_trigger(4)
							+ ", "
							+ item.getSpell_charge(4)
							+ ", "
							+ item.getSpell_ppm_rate(4)
							+ ", "
							+ item.getSpell_cd(4)
							+ ", "
							+ item.getSpell_category(4)
							+ ", "
							+ item.getSpell_category_cd(4)
							+ ", "
							+ item.getBinds()
							+ ", '"
							+ item.getDescription()
							+ "', "
							+ item.getPageText()
							+ ", "
							+ item.getLanguageID()
							+ ", "
							+ item.getPageMaterial()
							+ ", "
							+ item.getStartQuest()
							+ ", "
							+ item.getLockID()
							+ ", "
							+ item.getMaterial()
							+ ", "
							+ item.getSheath()
							+ ", "
							+ item.getRandomProperty()
							+ ", "
							+ item.getRandomSuffix()
							+ ", "
							+ item.getBlock()
							+ ", "
							+ item.getItemSet()
							+ ", "
							+ item.getDuribility()
							+ ", "
							+ item.getArea()
							+ ", "
							+ item.getMap()
							+ ", "
							+ item.getBagFamily()
							+ ", "
							+ item.getTotemCategory()
							+ ", "
							+ item.getSocket_color(0)
							+ ", "
							+ item.getSocket_content(0)
							+ ", "
							+ item.getSocket_color(1)
							+ ", "
							+ item.getSocket_content(1)
							+ ", "
							+ item.getSocket_color(2)
							+ ", "
							+ item.getSocket_content(2)
							+ ", "
							+ item.getSocketBonus()
							+ ", "
							+ item.getGemProperties()
							+ ", "
							+ item.getRequiredDisenchantSkill()
							+ ", "
							+ item.getArmorDamageModifier()
							+ ", "
							+ item.getDuration()
							+ ", "
							+ item.getItemLimitCategory()
							+ ", "
							+ item.getHolidayID()
							+ ", '"
							+ item.getScriptName()
							+ "', "
							+ item.getDisenchantID()
							+ ", "
							+ item.getFoodType()
							+ ", "
							+ item.getMinMoneyLoot()
							+ ", "
							+ item.getMaxMoneyLoot()
							+ ", "
							+ item.getFlagsCustom()
							+ ", 1337);");
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// now get some metadata from the database
		System.out.println("The columns in the table are: ");
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " "
					+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// resultSet is initialized before the first data set
		while (resultSet.next()) {
			// it is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g., resultSet.getSTring(2);
			String entry = resultSet.getString("entry");
			String name = resultSet.getString("name");
			String display = resultSet.getString("displayid");

			System.out.println("Entry: " + entry);
			System.out.println("Name: " + name);
			System.out.println("Display id: " + display);
		}
	}

	// you need to close all three to make sure
	private void close() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (statement != null)
				statement.close();
			if (connect != null)
				connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connectToDB() {
		// reload db info from settings
		username = SettingsPanel.getInstance().getDBUserName();
		password = SettingsPanel.getInstance().getDBPassword();
		dbname = SettingsPanel.getInstance().getDBWorldName();
		host = SettingsPanel.getInstance().getDBHostName();

		// this will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// setup the connection with the DB.
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "/"
					+ dbname + "?" + "user=" + username + "&password="
					+ password);
			// statements allow to issue SQL queries to the database
			statement = connect.createStatement();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("Failed to connect to the Database.");
		}
	}
}

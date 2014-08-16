package PYDevTools.utilities;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

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

	public void getItemFromTemplate(int entry) throws Exception {
		try {
			connectToDB();
			// resultSet gets the result of the SQL query
			resultSet = statement
					.executeQuery("SELECT * FROM world.item_template WHERE entry = "
							+ entry + ";");
			writeResultSet(resultSet);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public boolean isUsedEntry(int entry) {
		try {
			connectToDB();
			resultSet = statement.executeQuery("SELECT * FROM world.item_template WHERE entry = " + entry + ";");
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
	
	public void insertWeaponIntoTemplate(Item item) throws Exception {
		try {
			connectToDB();
			statement.executeUpdate("INSERT INTO `item_template` (`entry`, `class`, `subclass`, `SoundOverrideSubclass`, `name`, `displayid`, `Quality`, `Flags`, `FlagsExtra`, `BuyCount`, `BuyPrice`, `SellPrice`, `InventoryType`, `AllowableClass`, `AllowableRace`, `ItemLevel`, `RequiredLevel`, `RequiredSkill`, `RequiredSkillRank`, `requiredspell`, `requiredhonorrank`, `RequiredCityRank`, `RequiredReputationFaction`, `RequiredReputationRank`, `maxcount`, `stackable`, `ContainerSlots`, `StatsCount`, `stat_type1`, `stat_value1`, `stat_type2`, `stat_value2`, `stat_type3`, `stat_value3`, `stat_type4`, `stat_value4`, `stat_type5`, `stat_value5`, `stat_type6`, `stat_value6`, `stat_type7`, `stat_value7`, `stat_type8`, `stat_value8`, `stat_type9`, `stat_value9`, `stat_type10`, `stat_value10`, `ScalingStatDistribution`, `ScalingStatValue`, `dmg_min1`, `dmg_max1`, `dmg_type1`, `dmg_min2`, `dmg_max2`, `dmg_type2`, `armor`, `holy_res`, `fire_res`, `nature_res`, `frost_res`, `shadow_res`, `arcane_res`, `delay`, `ammo_type`, `RangedModRange`, `spellid_1`, `spelltrigger_1`, `spellcharges_1`, `spellppmRate_1`, `spellcooldown_1`, `spellcategory_1`, `spellcategorycooldown_1`, `spellid_2`, `spelltrigger_2`, `spellcharges_2`, `spellppmRate_2`, `spellcooldown_2`, `spellcategory_2`, `spellcategorycooldown_2`, `spellid_3`, `spelltrigger_3`, `spellcharges_3`, `spellppmRate_3`, `spellcooldown_3`, `spellcategory_3`, `spellcategorycooldown_3`, `spellid_4`, `spelltrigger_4`, `spellcharges_4`, `spellppmRate_4`, `spellcooldown_4`, `spellcategory_4`, `spellcategorycooldown_4`, `spellid_5`, `spelltrigger_5`, `spellcharges_5`, `spellppmRate_5`, `spellcooldown_5`, `spellcategory_5`, `spellcategorycooldown_5`, `bonding`, `description`, `PageText`, `LanguageID`, `PageMaterial`, `startquest`, `lockid`, `Material`, `sheath`, `RandomProperty`, `RandomSuffix`, `block`, `itemset`, `MaxDurability`, `area`, `Map`, `BagFamily`, `TotemCategory`, `socketColor_1`, `socketContent_1`, `socketColor_2`, `socketContent_2`, `socketColor_3`, `socketContent_3`, `socketBonus`, `GemProperties`, `RequiredDisenchantSkill`, `ArmorDamageModifier`, `duration`, `ItemLimitCategory`, `HolidayId`, `ScriptName`, `DisenchantID`, `FoodType`, `minMoneyLoot`, `maxMoneyLoot`, `flagsCustom`, `WDBVerified`) VALUES (" + item.getEntry() + ", 2, " + item.getType() + ", -1, ' " + item.getName() + "', " + item.getDisplay() + ", " + item.getQuality() + ", 0, 0, 1, 0, 0, " + item.getEquip() + ", -1, -1, " + item.getIlvl() + ", " + item.getReqlvl() + ", 0, 0, 0, 0, 0, 0, 0, " + item.getUnique() + ", 1, 0, 10, " + item.getStat_type1() + ", " + item.getStat_value1() + ", " + item.getStat_type2() + ", " + item.getStat_value2() + ", " + item.getStat_type3() + ", " + item.getStat_value3() + ", " + item.getStat_type4() + ", " + item.getStat_value4() + ", " + item.getStat_type5() + ", " + item.getStat_value5() + ", " + item.getStat_type6() + ", " + item.getStat_value6() + ", " + item.getStat_type7() + ", " + item.getStat_value7() + ", " + item.getStat_type8() + ", " + item.getStat_value8() + ", " + item.getStat_type9() + ", " + item.getStat_value9() + ", " + item.getStat_type10() + ", " + item.getStat_value10() + ", 0, 0, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2900, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0, -1, 0, -1, 0, '', 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, '', 0, 0, 0, 0, 0, 12340);");
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
			
		}
	}
	
	private void connectToDB() {
		// this will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// setup the connection with the DB.
			connect = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbname + "?" + "user=" + username + "&password=" + password);
			// statements allow to issue SQL queries to the database
			statement = connect.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

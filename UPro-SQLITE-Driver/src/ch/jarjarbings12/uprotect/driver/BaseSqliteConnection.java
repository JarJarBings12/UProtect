package ch.jarjarbings12.uprotect.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author JarJarBings12
 * @creationDate 11.11.2015
 * © 2015 JarJarBings12
 */
public class BaseSqliteConnection
{
    public final String createRegionIndex = "CREATE TABLE IF NOT EXISTS UPRO_%w_INDEX (R_ID VARCHAR, X INTEGER, Z INTEGER, R_UUID VARCHAR(32))";
    public final String createRegionDatabase = "CREATE TABLE IF NOT EXISTS UPRO_%w_REGIONS (R_UUID VARCHAR(32) UNIQUE, REGION_DATA VARCHAR)";

    public final String existsWorld = "SELECT COUNT(*) AS _RESULT_ FROM UPRO_%w_INDEX";
    public final String loadRegionIndex = "SELECT * FROM UPRO_%w_INDEX";
    public final String loadRegionDatabase = "SELECT * FROM UPRO_%w_REGIONS";

    public final String insertRegionIndex = "INSERT OR REPLACE INTO UPRO_%w_INDEX (R_ID, X, Z, R_UUID) VALUES (?, ?, ?, ?)";
    public final String selectRegionIndexByXY = "SELECT * FROM UPRO_%w_INDEX WHERE X=? AND Z=?";
    public final String selectRegionIndexByID = "SELECT * FROM UPRO_%w_INDEX WHERE R_ID=?";
    public final String selectRegionIndexByUUID = "SELECT * FROM UPRO_%w_INDEX WHERE R_UUID=?";
    public final String removeRegionIndexByXY = "REMOVE * FROM UPRO_%w_INDEX WHERE X=? AND Z=?";
    public final String removeRegionIndexByID = "REMOVE * FROM UPRO_%w_INDEX WHERE R_ID=?";
    public final String removeRegionIndexByUUID = "REMOVE * FROM UPRO_%w_INDEX WHERE R_UUID=?";

    public final String insertRegion = "INSERT OR REPLACE INTO UPRO_%w_REGIONS (R_UUID, REGION_DATA) VALUES (?, ?)";
    public final String selectRegion = "SELECT * FROM UPRO_%w_REGIONS WHERE R_UUID=?";
    public final String updateRegion = "UPDATE UPRO_%w_REGIONS SET REGION_DATA=? WHERE R_UUID=?";
    public final String removeRegion = "REMOVE * FROM UPRO_%w_REGIONS WHERE R_UUID=?";

    //USER DB

    public final String createUserRegister = "CREATE TABLE IF NOT EXISTS UPRO_USER_REGISTER (UUID VARCHAR(32) UNIQUE, NAME VARCHAR, LAST_LOGIN INTEGER, FIRST_LOGIN INTEGER, PLAY_TIME INTEGER)";

    //NAME DB

    public final String isUUIDRegistered = "SELECT COUNT(*) AS _COUNT_ FROM UPRO_USER_REGISTER WHERE UUID=?";
    public final String isNameRegistered = "SELECT COUNT(*) AS _COUNT_ FROM UPRO_USER_REGISTER WHERE NAME=?";

    public final String insertUserNameFor = "INSERT OR IGNORE INTO UPRO_USER_REGISTER (UUID, NAME) VALUES (?, ?)";
    public final String updateUserNameFor = "UPDATE UPRO_USER_REGISTER SET UUID=?, NAME=?";

    public final String selectNameForUUID = "SELECT NAME FROM UPRO_USER_REGISTER WHERE UUID=?";
    public final String selectUUIDForName = "SELECT UUID FROM UPRO_USER_REGISTER WHERE NAME=?";

    //PLAYTIME DB

    public final String updatePlayTimeFor = "UPDATE UPRO_USER_REGISTER SET UUID=?, PLAY_TIME=?";
    public final String updateLastLoginFor = "UPDATE UPRO_USER_REGISTER SET UUID=?, LAST_LOGIN=?";
    public final String updateFirstLoginFor = "UPDATE UPRO_USER_REGISTER SET UUID=?, FIRST_LOGIN=?";

    public final String selectPlayTimeFor = "SELECT PLAY_TIME FROM UPRO_USER_REGISTER WHERE UUID=?";
    public final String selectLastLoginFor = "SELECT LAST_LOGIN FROM UPRO_USER_REGISTER WHERE UUID=?";
    public final String selectFirstLoginFor = "SELECT FIRST_LOGIN FROM UPRO_USER_REGISTER WHERE UUID=?";


    public Connection openConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:plugins/UProtect/UPro.db");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Statement createStatement()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:plugins/UProtect/UPro.db").createStatement();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement createPreparedStatement(String query)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:plugins/UProtect/UPro.db").prepareStatement(query);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

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

package ch.jarjarbings12.uprotect.sql.sqlite;

import ch.jarjarbings12.uprotect.core.Core;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 06.06.2015
 */
public class DatabaseManager extends BasicSqlConnector
{
    public boolean setup()
    {
        if (!new File(new Core().getDataFolder().getAbsolutePath()+"/upro.db").exists())
        {
            Connection c = null;
            Statement statement = null;

            try
            {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + new Core().getDataFolder().getAbsolutePath()+"/upro.db");
                statement = c.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS WIN_BACKUP (SYSTEM_NAME VARCHAR(32) NOT NULL UNIQUE, HISTORY BINARY NOT NULL)");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS WIN_CACHE (SYSTEM_NAME VARCHAR(32) NOT NULL UNIQUE, WIN_CACHE_OBJECT BINARY NOT NULL)");
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS WIN_STORAGE (SYSTEM_NAME VARCHAR(32) NOT NULL UNIQUE, WIN_OBJECT BINARY NOT NULL");
                c.close();
            }
            catch (Exception e)
            {
                Bukkit.getLogger().log(Level.WARNING, "Can't setup Sql Database!");
                e.printStackTrace();
            }
        }
        return true;
    }
}
package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.high;

import ch.jarjarbings12.uprotect2.protect.kernel.managers.storage.RegionDatabase;
import ch.jarjarbings12.uprotect3.core.UProtect;
import ch.jarjarbings12.uprotect3.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.SqlBasedDriver;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.extension.UDriver;

import java.sql.*;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by tobias on 23.08.2015.
 */
public class SqliteDriver extends UDriver implements SqlBasedDriver
{

    //TODO WRITE SQLITE DRIVER
    private final String name = "urpotect sqlite driver";

    private final String author = "JarJarBings12";

    private final String id = "upro-sqlite-driver";

    private final String version = "UProtect-Sqlite-1.0.0";

    @Override
    public void onEnable()
    {
        System.out.println();
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getID()
    {
        return this.id;
    }

    @Override
    public String getVersion()
    {
        return this.version;
    }

    @Override
    public String getAuthor()
    {
        return this.author;
    }

    @Override
    public void saveRegion(ProtectedChunkRegion protectedChunkRegion)
    {

    }

    @Override
    public void saveRegion(ProtectedChunkRegion... protectedChunkRegions)
    {

    }

    @Override
    public void saveRegionDatabase(RegionDatabase regionDatabase)
    {

    }

    @Override
    public void saveRegionDatabase(RegionDatabase... regionDatabases)
    {

    }

    @Override
    public Set<RegionDatabase> getRegionDatabase(String world)
    {
        return null;
    }

    @Override
    public Set<RegionDatabase> getRegionDatabase(String... world)
    {
        return null;
    }

    @Override
    public Set<RegionDatabase> getAll()
    {
        return null;
    }

    @Override
    public Connection openConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + new UProtect().getDataFolder().getAbsolutePath() + "/upro.db");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Statement createStatement()
    {
        try
        {
            return openConnection().createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PreparedStatement createPreparedStatement(String query)
    {
        try
        {
            return openConnection().prepareStatement(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setNameForUUID(UUID uuid, String name)
    {
        try
        {
            createStatement().executeQuery(String.format("INSERT OR REPLACE INTO ULO(UUID, NAME, LASTLOGIN) VALUES (%s, %s, (SELECT LASTLOGIN FROM ULO WHERE UUID=%s))", uuid.toString(), name, uuid.toString()));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getNameForUUID(UUID uuid)
    {
        try
        {
            return createStatement().executeQuery(String.format("SELECT * FROM ULO WHERE UUID=%s", uuid.toString())).getString("NAME");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return "UNKNOWN";
    }

    @Override
    public HashMap<UUID, String> getNameForUUID(UUID... uuids)
    {
        HashMap<UUID, String> temp = new HashMap<>();

        for (UUID uuid : uuids)
        {
            if (!existLogsFor(uuid))
            {
                temp.put(uuid, "UNKNOWN");
                continue;
            }
            temp.put(uuid, getNameForUUID(uuid));
        }
        return temp;
    }

    @Override
    public boolean existLogsFor(UUID uuid)
    {
        try
        {
            return createStatement().executeQuery(String.format("SELECT COUNT(*) AS UFC FROM ULO WHERE UUID=%s", uuid.toString())).getInt("UFC") > 0 ? true : false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public long getLastLogin(UUID uuid)
    {
        if (!existLogsFor(uuid))
            return -0;
        try
        {
            return createStatement().executeQuery(String.format("SELECT * FROM ULO WHERE UUID=%s", uuid.toString())).getLong("LASTLOGIN");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -0;
    }

    @Override
    public void setLastLogin(UUID uuid)
    {
        setLastLogin(uuid, System.currentTimeMillis());
    }

    @Override
    public void setLastLogin(UUID uuid, long timestamp)
    {
        try
        {
            createStatement().executeQuery(String.format("INSERT OR REPLACE INTO ULO(UUID, NAME, LASTLOGIN) VALUES (%s, %s, %d)", uuid.toString(), "UNKNOWN", timestamp));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

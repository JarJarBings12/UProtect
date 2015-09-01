package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.high;

import ch.jarjarbings12.uprotect2.protect.kernel.managers.storage.RegionDatabase;
import ch.jarjarbings12.uprotect3.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.SqlBasedDriver;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.extension.UDriver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by tobias on 23.08.2015.
 */
public class MysqlDriver extends UDriver implements SqlBasedDriver
{
    //TODO
    private final String name = "uprotect mysql driver";

    private final String author = "JarJarBings12";

    private final String id = "upro-mysql-driver";

    private final String version = "UProtect-Mysql-1.0.0";

    @Override
    public void onEnable()
    {

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
    public void setNameForUUID(UUID uuid, String name)
    {

    }

    @Override
    public String getNameForUUID(UUID uuid)
    {
        return null;
    }

    @Override
    public HashMap<UUID, String> getNameForUUID(UUID... uuids)
    {
        return null;
    }

    @Override
    public boolean existLogsFor(UUID uuid)
    {
        return false;
    }

    @Override
    public long getLastLogin(UUID uuid)
    {
        return 0;
    }

    @Override
    public void setLastLogin(UUID uuid)
    {

    }

    @Override
    public void setLastLogin(UUID uuid, long timestamp)
    {

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
        return null;
    }

    @Override
    public Statement createStatement()
    {
        return null;
    }

    @Override
    public PreparedStatement createPreparedStatement(String query)
    {
        return null;
    }
}

package ch.jarjarbings12.uprotect.protect.kernel.world.chunk.RDB;

import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.BasicChunkService;
import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.WorldChunkService;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.sql.*;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class RecordDatabase implements BasicChunkService
{
    private final WorldChunkService worldChunkService;
    private final UUID worldUUID;

    public RecordDatabase(UUID worldUUID, WorldChunkService worldChunkService)
    {
        this.worldChunkService = worldChunkService;
        this.worldUUID = worldUUID;
    }

    /**
     * Open a normal SQL {@link java.sql.Connection}
     * @return Return a normal SQL {@link java.sql.Connection}.
     */
    public Connection openConnection()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:plugins/UProtect/storage/internal/world/chunk/cbs.db");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create a new SQL {@link java.sql.Statement}
     * @return Return a normal SQL {@link java.sql.Statement}
     */
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

    /**
     * Create a new {@link java.sql.PreparedStatement}
     * @param query
     * @return Return a normal SQL {@link java.sql.PreparedStatement}
     */
    public PreparedStatement createPrepared(String query)
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

    /**
     * Insert a new chunk record
     * @param x X-Axis
     * @param z Z-Axis
     * @param bytes Serialized Object
     * @param time Current Time
     * @return {@link java.lang.Boolean}
     */
    public boolean executeInsertStatement(int x, int z, byte[] bytes, long time)
    {
        boolean var2 = false;
        try
        {
            PreparedStatement var1 = createPrepared(Querys.INSERT.getQuery().replace("%w", worldUUID.toString().replace("-", "")));
            var1.setInt(1, x);
            var1.setInt(2, z);
            var1.setBytes(3, bytes);
            var1.setLong(4, time);
            var2 = var1.execute();
            var1.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return var2;
    }

    /**
     * Select a chunk record.
     * @param x X-Axis
     * @param z Z-Axis
     * @return {@link java.lang.Byte} Array
     */
    public byte[] executeSelectStatement(int x, int z)
    {
        byte[] var1 = null;
        try
        {
            PreparedStatement var2 = createPrepared(Querys.SELECT.getQuery().replace("%w", worldUUID.toString().replace("-", "")));
            var2.setInt(1, x);
            var2.setInt(2, z);
            var1 = var2.executeQuery().getBytes("bin");
            var2.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return var1;
    }

    /**
     * Remove a chunk record.
     * @param x X-Axis
     * @param z Z-Axis
     * @return {@link java.lang.Boolean}
     */
    public boolean executeRemoveStatement(int x, int z)
    {
        boolean var1 = false;
        try
        {
            PreparedStatement var2 = createPrepared(Querys.REMOVE.getQuery().replace("%w", worldUUID.toString().replace("-", "")));
            var2.setInt(1, x);
            var2.setInt(2, z);
            var1 = var2.execute();
            var2.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return var1;
    }

    /**
     * Check existence of the recording.
     * @param x X-Axis
     * @param z Z-Axis
     * @return {@link java.lang.Boolean}
     */
    public boolean executeExistStatement(int x, int z)
    {
        boolean var1 = false;
        try
        {
            PreparedStatement var2 = createPrepared(Querys.EXIST.getQuery().replace("%w", worldUUID.toString().replace("-", "")));
            var2.setInt(1, x);
            var2.setInt(2, z);
            var1 = var2.executeQuery().getInt("REC") > 0 ? true : false;
            var2.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return var1;
    }

    public void setup() throws UnknownWorldException
    {
        if (Bukkit.getWorld(worldUUID) == null)
            throw new UnknownWorldException(worldUUID);

        try
        {
            createStatement().executeUpdate(String.format("CREATE TABLE IF NOT EXISTS WORLD_%s(x INTEGER, z INTEGER, bin BINARY, time INTEGER)", worldUUID.toString().replace("-", "")));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public UUID getWorldUUID()
    {
        return this.worldUUID;
    }

    @Override
    public World getWorld()
    {
        return Bukkit.getWorld(worldUUID);
    }

    @Override
    public WorldChunkService getChunkService()
    {
        return this.worldChunkService;
    }
}
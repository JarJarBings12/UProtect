package ch.jarjarbings12.uprotect.driver;

import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunk;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect.protect.kernel.services.objects.IndexDifference;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.RegionDBService;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 11.11.2015
 * © 2015 JarJarBings12
 */
public class RegionService implements RegionDBService
{
    private BaseSqliteConnection baseSqliteConnection = null;

    public RegionService(BaseSqliteConnection baseSqliteConnection)
    {
        this.baseSqliteConnection = baseSqliteConnection;
    }

    @Override
    public void save(RegionDatabase regionDatabase)
    {
        int newRegions = 0;
        int changedRegions = 0;
        int removedRegions = 0;

        Iterator<IndexDifference> iterator = regionDatabase.getAllDifferences().iterator();
        IndexDifference difference = null;
        while (iterator.hasNext())
        {
            difference = iterator.next();

            switch (difference.getDifferenceType())
            {
                case ADDED:
                {
                    save(difference.getRegion());
                    newRegions++;
                    break;
                }
                case CHANGED:
                {
                    save(difference.getRegion());
                    changedRegions++;
                    break;
                }
                case REMOVED:
                {
                    removeRegion(difference.getRegion().getWorld(), difference.getRegion().getUUID());
                    removedRegions++;
                    break;
                }

            }
        }
        System.out.println("[UProtect] Statistic:");
        System.out.printf("[UProtect] New Regions: %d", newRegions);
        System.out.printf("[UProtect] Changed Regions: %d", changedRegions);
        System.out.printf("[UProtect] Removed Regions: %d", removedRegions);
    }

    @Override
    public void save(RegionDatabase... regionDatabases)
    {
        for (RegionDatabase var1 : regionDatabases)
            save(var1);
    }

    @Override
    public void save(ProtectedChunkRegion protectedChunkRegion)
    {
        PreparedStatement var1 = null;
        Iterator<ProtectedChunk> var2 = protectedChunkRegion.getProtectedChunks().iterator();
        ProtectedChunk var3 = null;
        while (var2.hasNext())
        {
            try
            {
                var1 = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.insertRegionIndex.replace("%w", protectedChunkRegion.getWorld().getUID().toString().replace("-", "")));
                var3 = var2.next();

                var1.setString(1, protectedChunkRegion.getId());
                var1.setInt(2, var3.getX());
                var1.setInt(3, var3.getZ());
                var1.setString(4, protectedChunkRegion.getUUID().toString());
                var1.executeUpdate();
                var1.close();

                var1 = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.insertRegion.replace("%w", protectedChunkRegion.getWorld().getUID().toString().replace("-", "")));
                var1.setString(1, protectedChunkRegion.getUUID().toString());
                var1.setString(2, protectedChunkRegion.serialize());
                var1.executeUpdate();
                var1.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void save(ProtectedChunkRegion... protectedChunkRegions)
    {
        for (ProtectedChunkRegion protectedChunkRegion : protectedChunkRegions)
            save(protectedChunkRegion);
    }

    @Override
    public RegionDatabase load(Player player)
    {
        return load(player.getWorld().getUID());
    }

    @Override
    public RegionDatabase load(World world)
    {
        return load(world.getUID());
    }

    @Override
    public RegionDatabase load(Location location)
    {
        return load(location.getWorld().getUID());
    }

    @Override
    public RegionDatabase load(Chunk chunk)
    {
        return load(chunk.getWorld().getUID());
    }

    @Override
    public RegionDatabase load(UUID uuid)
    {
        HashMap<UUID, ProtectedChunkRegion> protectedChunks = new HashMap<>();
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement("SELECT COUNT(REGION_DATA) AS _SIZE_ FROM UPRO_%w_REGIONS".replace("%w", uuid.toString().replace("-", "")));

        try
        {
            int size = preparedStatement.executeQuery().getInt("_SIZE_");
            preparedStatement = baseSqliteConnection.createPreparedStatement("SELECT REGION_DATA FROM UPRO_%w_REGIONS".replace("%w", uuid.toString().replace("-", "")));
            ResultSet regions = preparedStatement.executeQuery();

            int i = 0;
            while (i < size)
            {
                ProtectedChunkRegion protectedChunkRegion = ProtectedChunkRegion.deserialize(regions.getString(1));
                protectedChunks.put(protectedChunkRegion.getUUID(), protectedChunkRegion);
                i++;
                regions.next();
            }
            preparedStatement.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return new RegionDatabase(protectedChunks);
    }

    @Override
    public RegionDatabase load(String s)
    {
        if (Bukkit.getWorld(s) == null)
            new UnknownWorldException(s).printStackTrace();
        else
            return load(Bukkit.getWorld(s).getUID());
        return null;
    }

    @Override
    public boolean isWorldNew(World world)
    {
        try
        {
            Statement statement = baseSqliteConnection.createStatement();
            boolean newWorld = statement.executeQuery(baseSqliteConnection.existsWorld.replace("%w", world.getUID().toString().replace("-", ""))).getInt("_RESULT_") > 0 ? false : true;
            statement.close();
            return newWorld;
        }
        catch (SQLException e)
        { e.printStackTrace(); }
        return true;
    }

    @Override
    public ProtectedChunkRegion getRegion(Player player)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Location location)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Chunk chunk)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Player player, UUID uuid)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(World world, UUID uuid)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Location location, UUID uuid)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Chunk chunk, UUID uuid)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Player player, String s)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(World world, String s)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Location location, String s)
    {
        return null;
    }

    @Override
    public ProtectedChunkRegion getRegion(Chunk chunk, String s)
    {
        return null;
    }

    //****

    @Override
    public void removeRegion(Player player, UUID uuid)
    {
        removeRegion(player.getWorld(), uuid);
    }

    @Override
    public void removeRegion(World world, UUID uuid)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.removeRegionIndexByUUID.replace("%w", uuid.toString().replace("-", "")));
        try
        {
            preparedStatement.setString(1, uuid.toString().replace("-", ""));
            preparedStatement.execute(); //REMOVE INDEXES
            preparedStatement.close();

            preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.removeRegion.replace("%w", world.getUID().toString().replace("-", "")));
            preparedStatement.setString(1, uuid.toString().replace("-", ""));
            preparedStatement.execute(); //REMOVE REGION
            preparedStatement.close();
        }
        catch (SQLException e)
        { e.printStackTrace(); }
    }

    @Override
    public void removeRegion(Location location, UUID uuid)
    {
        removeRegion(location.getWorld(), uuid);
    }

    @Override
    public void removeRegion(Chunk chunk, UUID uuid)
    {
        removeRegion(chunk.getWorld(), uuid);
    }

    @Override
    public void removeRegion(UUID uuid, UUID uuid1)
    {
        removeRegion(Bukkit.getWorld(uuid), uuid1);
    }

    //****

    @Override
    public void removeRegion(Player player, String s)
    {
        removeRegion(player.getWorld(), s);
    }

    @Override
    public void removeRegion(World world, String s)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.selectRegionIndexByID.replace("%w", world.getUID().toString().replace("-", "")));
        try
        {
            preparedStatement.setString(1, s);
            UUID uuid = UUID.fromString(preparedStatement.executeQuery().getString("R_UUID")); //Get UUID
            preparedStatement.close();
            preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.removeRegionIndexByUUID.replace("%w", world.getUID().toString().replace("-", "")));
            preparedStatement.setString(1, uuid.toString().toString().replace("-", ""));
            preparedStatement.execute(); //REMOVE INDEXES
            preparedStatement.close();
            //--
            preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.removeRegion.replace("%w", world.getUID().toString().replace("-", "")));
            preparedStatement.setString(1, uuid.toString().replace("-", ""));
            preparedStatement.execute(); //REMOVE REGION
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void removeRegion(Location location, String s)
    {
        removeRegion(location.getWorld(), s);
    }

    @Override
    public void removeRegion(Chunk chunk, String s)
    {
        removeRegion(chunk.getWorld(), s);
    }
}
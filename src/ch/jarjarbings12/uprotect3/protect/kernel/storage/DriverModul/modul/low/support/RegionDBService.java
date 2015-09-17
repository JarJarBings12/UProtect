package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.support;

import ch.jarjarbings12.uprotect3.protect.kernel.managers.RegionManager;
import ch.jarjarbings12.uprotect3.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect3.protect.kernel.objects.ProtectedChunkRegion;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by tobias on 13.09.2015.
 */
public interface RegionDBService
{

    /**
     * Save region manager
     **/

    void save(RegionManager regionManager);

    /**
     * Save region database/s
     **/

    void save(RegionDatabase regionDatabase);

    void save(RegionDatabase... regionDatabases);

    /**
     * Save protected region/s
     **/

    void save(ProtectedChunkRegion protectedChunkRegion);

    void save(ProtectedChunkRegion... protectedChunkRegions);

    /**
     * Load region database
     **/

    RegionDatabase load(Player player);

    RegionDatabase load(World world);

    RegionDatabase load(Location location);

    RegionDatabase load(Chunk chunk);

    RegionDatabase load(String worldName);

    /**
     * Fetch single region by location.
     **/

    ProtectedChunkRegion getRegion(Player player);

    ProtectedChunkRegion getRegion(Location location);

    ProtectedChunkRegion getRegion(Chunk chunk);

    /**
     * Fetch single region by world and uuid
     **/

    ProtectedChunkRegion getRegion(Player player, UUID uuid);

    ProtectedChunkRegion getRegion(World world, UUID uuid);

    ProtectedChunkRegion getRegion(Location location, UUID uuid);

    ProtectedChunkRegion getRegion(Chunk chunk, UUID uuid);

    /**
     * Fetch single region by world and id
     **/

    ProtectedChunkRegion getRegion(Player player, String ID);

    ProtectedChunkRegion getRegion(World world, String ID);

    ProtectedChunkRegion getRegion(Location location, String ID);

    ProtectedChunkRegion getRegion(Chunk chunk, String ID);
}

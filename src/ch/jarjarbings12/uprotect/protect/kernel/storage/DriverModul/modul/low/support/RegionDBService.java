package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support;

import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 13.09.2015
 * © 2015 JarJarBings12
 */
public interface RegionDBService
{
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

    RegionDatabase load(UUID worldUUID);

    RegionDatabase load(String worldName);

    boolean isWorldNew(World world);

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

    /**
     * Remove by UUID
     **/

    void removeRegion(Player player, UUID regionUUID);

    void removeRegion(World world, UUID regionUUID);

    void removeRegion(Location location, UUID regionUUID);

    void removeRegion(Chunk chunk, UUID regionUUID);

    void removeRegion(UUID worldUUID, UUID regionUUID);

    /**
     * Remove by ID
     **/

    void removeRegion(Player player, String ID);

    void removeRegion(World world, String ID);

    void removeRegion(Location location, String ID);

    void removeRegion(Chunk chunk, String ID);
}

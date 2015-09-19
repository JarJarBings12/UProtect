package ch.jarjarbings12.uprotect.protect.kernel.objects;


import ch.jarjarbings12.uprotect.protect.kernel.services.interfaces.IDifferenceService;
import ch.jarjarbings12.uprotect.protect.kernel.services.objects.IndexDifference;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 23.08.2015
 * © 2015 JarJarBings12
 */
public interface RegionIndex extends RegionChangeable, IDifferenceService
{

    boolean isProtected(Chunk chunk);

    boolean isProtected(Location location);

    boolean isProtected(Player player);

    /* get */

    ProtectedChunkRegion getRegion(UUID uuid);

    ProtectedChunkRegion getRegion(String id);

    ProtectedChunkRegion getRegion(Chunk chunk);

    ProtectedChunkRegion getRegion(Location location);

    ProtectedChunkRegion getRegion(Player player);

    Set<ProtectedChunkRegion> getAllRegions();

    Set<IndexDifference> getAllChangedRegions();

    /* set */

    void setRegion(UUID uuid, ProtectedChunkRegion protection);

    void setRegion(String id, ProtectedChunkRegion protection);

    void setRegion(Chunk chunk, ProtectedChunkRegion protection);

    void setRegion(Location location, ProtectedChunkRegion protection);

    void setRegion(Player player, ProtectedChunkRegion protection);

    /* UUID */

    UUID getRegionUUID(String id);

    UUID getRegionUUID(Chunk chunk);

    UUID getRegionUUID(Location location);

    UUID getRegionUUID(Player player);

    /* ID */

    String getRegionID(UUID uuid);

    String getRegionID(Chunk chunk);

    String getRegionID(Location location);

    String getRegionID(Player player);

    /* Save */

    void save(ProtectedChunkRegion protectedChunkRegion);

    void save(UUID uuid);

    void save(String id);

    void save(Chunk chunk);

    void save(Location location);

    void save(Player player);

    void saveAll();

}

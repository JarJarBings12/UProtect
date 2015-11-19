package ch.jarjarbings12.uprotect.protect.kernel.objects;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * © 2015 JarJarBings12
 */
public interface RegionChangeable
{
    MProtectedChunkRegion modifyRegion(UUID uuid);

    MProtectedChunkRegion modifyRegion(String id);

    MProtectedChunkRegion modifyRegion(Chunk chunk);

    MProtectedChunkRegion modifyRegion(Location location);

    MProtectedChunkRegion modifyRegion(Player player);
}

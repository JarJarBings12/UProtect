package ch.jarjarbings12.uprotect3.protect.kernel.objects;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * @since 1.0.0.0
 */
public interface RegionChangeable
{
    MProtectedRegion modifyRegion(UUID uuid);

    MProtectedRegion modifyRegion(String id);

    MProtectedRegion modifyRegion(Chunk chunk);

    MProtectedRegion modifyRegion(Location location);

    MProtectedRegion modifyRegion(Player player);
}

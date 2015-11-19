package ch.jarjarbings12.uprotect.protect.kernel.managers;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * © 2015 JarJarBings12
 */
public class RegionManager
{
    private final HashMap<String, UUID> nameUUID = new HashMap<>();
    private final HashMap<UUID, RegionDatabase> worldIndex = new HashMap<>();

    public RegionDatabase getRegionDatabase(Player player) throws UnknownWorldException
    {
        return getRegionDatabase(player.getLocation());
    }

    public RegionDatabase getRegionDatabase(Location location) throws UnknownWorldException
    {
        return getRegionDatabase(location.getWorld().getUID());
    }

    public RegionDatabase getRegionDatabase(Chunk chunk) throws UnknownWorldException
    {
        return getRegionDatabase(chunk.getWorld().getUID());
    }

    public RegionDatabase getRegionDatabase(String worldName) throws UnknownWorldException
    {
        return getRegionDatabase(nameUUID.get(worldName));
    }

    public RegionDatabase getRegionDatabase(World world) throws UnknownWorldException
    {
        return getRegionDatabase(world.getUID());
    }

    public RegionDatabase getRegionDatabase(UUID worldUUID) throws UnknownWorldException
    {
        if (!worldIndex.containsKey(worldUUID))
        {
            if (Bukkit.getWorlds().stream().filter(world -> world.getUID().equals(worldUUID)).findFirst().get() == null)
                throw new UnknownWorldException(worldUUID);
            else
                worldIndex.put(worldUUID, loadDatabase(worldUUID));
        }
        return worldIndex.get(worldUUID);

    }

    public void saveDatabase(String worldName) throws UnknownWorldException
    {
        if (!nameUUID.containsKey(worldName))
            throw new UnknownWorldException(worldName);
        saveDatabase(worldIndex.get(nameUUID.get(worldName)));
    }

    public void saveDatabase(UUID worldUUID) throws UnknownWorldException
    {
        if (!worldIndex.containsKey(worldUUID))
            throw new UnknownWorldException(worldUUID);
        saveDatabase(worldIndex.get(worldUUID));
    }

    public void saveDatabase(RegionDatabase regionDatabase)
    {
        Validate.notNull(regionDatabase);
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getRegionDBService().save(regionDatabase);
    }

    public RegionDatabase loadDatabase(Location location)
    {
        return loadDatabase(location.getWorld());
    }

    public RegionDatabase loadDatabase(String worldName)
    {
        return loadDatabase(Bukkit.getWorld(worldName));
    }

    public RegionDatabase loadDatabase(UUID worldUUID)
    {
        return loadDatabase(Bukkit.getWorld(worldUUID));
    }

    public RegionDatabase loadDatabase(World world)
    {
        if (UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getRegionDBService().isWorldNew(world))
        {
            return new RegionDatabase();
        }
        return UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getRegionDBService().load(world);
    }

    public void setup()
    {
        Bukkit.getWorlds().forEach(world -> {
            nameUUID.put(world.getName(), world.getUID());
            worldIndex.put(world.getUID(), loadDatabase(world.getUID()));
        });
    }

    public void shutdown()
    {
        worldIndex.values().forEach(regionDatabase -> UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getRegionDBService().save(regionDatabase));
    }
}
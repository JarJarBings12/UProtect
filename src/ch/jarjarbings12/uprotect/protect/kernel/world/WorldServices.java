package ch.jarjarbings12.uprotect.protect.kernel.world;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.world.chunk.WorldChunkService;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.09.2015
 * © 2015 JarJarBings12
 */
public class WorldServices
{
    private final HashMap<UUID, WorldChunkService> worldChunkServices = new HashMap<>();
    private final List<UUID> exceptedWorlds = new ArrayList<>();
    private YamlConfiguration configuration = null;

    public WorldServices()
    {}

    public WorldChunkService getChunkServiceFor(String worldName) throws UnknownWorldException
    {
        if (Bukkit.getWorld(worldName) == null)
            throw new UnknownWorldException(worldName);
        return getChunkServiceFor(Bukkit.getWorld(worldName).getUID(), false);
    }

    public WorldChunkService getChunkServiceFor(UUID worldUUID) throws UnknownWorldException
    {
        return getChunkServiceFor(worldUUID, true);
    }

    public WorldChunkService getChunkServiceFor(UUID worldUUID, boolean existCheck) throws UnknownWorldException
    {
        if (!existCheck)
            return worldChunkServices.get(worldUUID);
        if (!worldChunkServices.containsKey(worldUUID))
            throw new UnknownWorldException(worldUUID);
        return worldChunkServices.get(worldUUID);
    }

    public void setup()
    {
        configuration = UProtect.getUProtect().getUProtectAPI().getConfiguration();

        if (!configuration.getBoolean("world.chunk.restore.use"))
        {
            System.out.println("[UProtect][->]Disable restore Service.");
            return;
        }

        List<String> var1 = configuration.getStringList("worlds.chunk.restore.dont-record");

        var1.forEach(var2 -> {
           if (var2.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"))
           { exceptedWorlds.add(UUID.fromString(var2)); }
           else
           {
               if (Bukkit.getWorld(var2) != null)
               { exceptedWorlds.add(Bukkit.getWorld(var2).getUID()); }
               else
               {
                   try
                   { throw new UnknownWorldException(var2); }
                   catch (UnknownWorldException e)
                   { e.printStackTrace(); }
               }
           }
        });

        Bukkit.getWorlds().forEach(world -> {
            if (!exceptedWorlds.contains(world.getUID()))
                worldChunkServices.put(world.getUID(), new WorldChunkService(world.getUID()));
        });
    }
}
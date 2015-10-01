package ch.jarjarbings12.uprotect.protect.kernel.managers;

import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;

import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * © 2015 JarJarBings12
 */
public class RegionManager
{
    private HashMap<String, RegionDatabase> worldIndex = new HashMap<>();

    public RegionDatabase getRegionDatabase(String world)
    {
        return worldIndex.get(world);
    }

    public void addRegionDatabase(String world, RegionDatabase regionDatabase)
    {
        this.worldIndex.put(world, regionDatabase);
    }

    public void setup()
    {

    }
}

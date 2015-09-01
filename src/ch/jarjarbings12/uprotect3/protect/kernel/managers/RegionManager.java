package ch.jarjarbings12.uprotect3.protect.kernel.managers;

import ch.jarjarbings12.uprotect3.protect.kernel.managers.index.RegionDatabase;

import java.util.HashMap;

/**
 * Created by tobias on 26.08.2015.
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
}
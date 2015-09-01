package ch.jarjarbings12.uprotect3.core;

import ch.jarjarbings12.uprotect3.protect.kernel.managers.RegionManager;
import ch.jarjarbings12.uprotect3.protect.kernel.services.ObjectService;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DatabaseService;

import java.util.HashMap;

/**
 * Created by tobias on 26.08.2015.
 */
public class UProtectAPI
{
    private RegionManager regionManager = new RegionManager();
    private DatabaseService databaseService = new DatabaseService();
    private HashMap<String, RegionManager> regionIndex = new HashMap<>();
    private ObjectService objectService = new ObjectService();

    public RegionManager getRegionManager()
    {
        return this.regionManager;
    }

    public DatabaseService getDatabaseService()
    {
        return this.databaseService;
    }

    public ObjectService getObjectService()
    {
        return this.objectService;
    }

}

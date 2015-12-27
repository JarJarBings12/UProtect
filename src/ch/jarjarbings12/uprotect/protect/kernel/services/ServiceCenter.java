package ch.jarjarbings12.uprotect.protect.kernel.services;

import ch.jarjarbings12.uprotect.protect.kernel.events.SubscriptionManager;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.high.FlagService;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DatabaseService;
import ch.jarjarbings12.uprotect.protect.kernel.world.WorldServices;
import ch.jarjarbings12.uprotect.protect.utils.BooleanValue;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015
 * © 2015 JarJarBings12
 */
public class ServiceCenter
{
    private final FlagService flagService = new FlagService();
    private final SubscriptionManager subscriptionManager = new SubscriptionManager();
    private final DatabaseService databaseService = new DatabaseService();
    private final ObjectService objectService = new ObjectService();
    private final WorldServices worldServices = new WorldServices();
    private final BooleanValue booleanValue = new BooleanValue();

    public DatabaseService getDatabaseService()
    {
        return this.databaseService;
    }

    public SubscriptionManager getSubscriptionManager()
    {
        return this.subscriptionManager;
    }

    public FlagService getFlagService()
    {
        return this.flagService;
    }

    public ObjectService getObjectService()
    {
        return this.objectService;
    }

    public WorldServices getWorldServices()
    {
        return this.worldServices;
    }

    public BooleanValue getBooleanValue()
    {
        return this.booleanValue;
    }

}

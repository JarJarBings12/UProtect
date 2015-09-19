package ch.jarjarbings12.uprotect.protect.kernel.services;

import ch.jarjarbings12.uprotect.protect.kernel.events.SubscriptionManager;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.high.FlagService;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DatabaseService;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015
 * © 2015 JarJarBings12
 */
public class ServiceCenter
{
    private FlagService flagService = new FlagService();
    private SubscriptionManager subscriptionManager = new SubscriptionManager();
    private DatabaseService databaseService = new DatabaseService();
    private ObjectService objectService = new ObjectService();

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

}

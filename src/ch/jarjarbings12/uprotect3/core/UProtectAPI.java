package ch.jarjarbings12.uprotect3.core;

import ch.jarjarbings12.uprotect3.i18n.i18n;
import ch.jarjarbings12.uprotect3.protect.kernel.events.SubscriptionManager;
import ch.jarjarbings12.uprotect3.protect.kernel.managers.RegionManager;
import ch.jarjarbings12.uprotect3.protect.kernel.services.ObjectService;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DatabaseService;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * @since 1.0.0.0
 */
public class UProtectAPI
{
    private RegionManager regionManager = new RegionManager();
    private DatabaseService databaseService = new DatabaseService();
    private HashMap<String, RegionManager> regionIndex = new HashMap<>();
    private SubscriptionManager subscriptionManager = new SubscriptionManager();
    private ObjectService objectService = new ObjectService();
    private YamlConfiguration configuration = null;
    private i18n translate = null;

    public UProtectAPI()
    {
        this.configuration = YamlConfiguration.loadConfiguration(new File("plugins/UProtect/config.yml"));
        try
        {
            this.translate = new i18n(new FileInputStream(new File("plugins/UProtect/i18n/" + configuration.getString("local") + ".properties")));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public RegionManager getRegionManager()
    {
        return this.regionManager;
    }

    public DatabaseService getDatabaseService()
    {
        return this.databaseService;
    }

    public SubscriptionManager getSubscriptionManager()
    {
        return this.subscriptionManager;
    }

    public ObjectService getObjectService()
    {
        return this.objectService;
    }

    public i18n getTranslator()
    {
        return this.translate;
    }

    public YamlConfiguration getConfiguration()
    {
        return this.configuration;
    }

}

package ch.jarjarbings12.uprotect.core;

import ch.jarjarbings12.uprotect.protect.kernel.managers.RegionManager;
import ch.jarjarbings12.uprotect.protect.kernel.services.ServiceCenter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * © 2015 JarJarBings12
 */
public class UProtectAPI
{
    private RegionManager regionManager = new RegionManager();
    private HashMap<String, RegionManager> regionIndex = new HashMap<>();
    private YamlConfiguration configuration = null;
    private ServiceCenter serviceCenter = new ServiceCenter();

    public UProtectAPI()
    {
        this.configuration = YamlConfiguration.loadConfiguration(new File("plugins/UProtect/config.yml"));
    }

    public RegionManager getRegionManager()
    {
        return this.regionManager;
    }

    public ServiceCenter getServiceCenter()
    {
        return this.serviceCenter;
    }

    public YamlConfiguration getConfiguration()
    {
        return this.configuration;
    }

}

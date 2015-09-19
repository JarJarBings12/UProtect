package ch.jarjarbings12.uprotect.core;

import ch.jarjarbings12.uprotect.i18n.i18n;
import ch.jarjarbings12.uprotect.protect.kernel.managers.RegionManager;
import ch.jarjarbings12.uprotect.protect.kernel.services.ServiceCenter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public ServiceCenter getServiceCenter()
    {
        return this.serviceCenter;
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

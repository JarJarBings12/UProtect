package ch.jarjarbings12.uprotect3.core;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by tobias on 26.08.2015.
 */
public class UProtect extends JavaPlugin
{
    private static UProtect uProtect = null;
    private UProtectAPI uProtectAPI = null;

    @Override
    public void onEnable()
    {
        this.uProtect = this;
        this.uProtectAPI = new UProtectAPI();
    }

    @Override
    public void onDisable()
    {

    }

    public static UProtect getUProtect()
    {
        return uProtect;
    }

    public UProtectAPI getUProtectAPI()
    {
        return this.uProtectAPI;
    }
}

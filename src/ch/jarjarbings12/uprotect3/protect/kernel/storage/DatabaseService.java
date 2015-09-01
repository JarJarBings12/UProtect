package ch.jarjarbings12.uprotect3.protect.kernel.storage;

import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.DriverInfo;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.DriverService;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.RegionDBSupport;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.UserDBSupport;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.extension.UDriver;

/**
 * Created by tobias on 26.08.2015.
 */
public class DatabaseService
{

    private DriverService driverService = new DriverService();
    private UDriver driver = null;

    public DatabaseService()
    {
    }

    public RegionDBSupport getRegionDatabase()
    {
        return this.driver;
    }

    public UserDBSupport getUserDatabase()
    {
        return this.driver;
    }

    public UDriver getDriver()
    {
        return driver;
    }

    public DriverInfo getDriverInfo()
    {
        return driver;
    }

    public void setup()
    {
        driverService.loadDrivers();
        driver = driverService.getDriver("");
        driver.onEnable();
    }

    public void shutdown()
    {
        driver.onDisable();
        driverService.unloadDrivers();
    }

}

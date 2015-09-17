package ch.jarjarbings12.uprotect3.protect.kernel.storage;

import ch.jarjarbings12.uprotect3.core.UProtect;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.high.DriverService;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.high.extensions.ModuleInitializer;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.high.extensions.UDriver;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.ADriverInfo;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.properties.DescriptionParser;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.support.DriverServices;

import java.net.URLClassLoader;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * @since 1.0.0.0
 */
public final class DatabaseService
{
    private DriverService driverService = new DriverService();
    private UDriver driver = null;

    public DatabaseService()
    {
    }

    public UDriver getDriver()
    {
        return driver;
    }

    public ADriverInfo getDriverInfo()
    {
        return driver.getDriverDescription();
    }

    public DriverServices getDriverServices()
    {
        return driver;
    }

    public void setup()
    {
        System.out.println("[UProtect][DBS][START] Load Database Driver...");
        driverService.init();
        driver = driverService.getDriver(UProtect.getUProtect().getUProtectAPI().getConfiguration().getString("database.driver.id"));
        DescriptionParser descriptionParser = driverService.getDescriptionParser(driver.getID());
        driver = new ModuleInitializer(UProtect.getUProtect(), UProtect.getUProtect().getUProtectAPI(), descriptionParser.getDriverDescription(), descriptionParser.getProperty(), new URLClassLoader(driverService.getModuleClassLoader().makeURL(driverService.getDriverFileByID(driver.getID())))).initialize(driver);
        //printDriverInfo();
        driver.onEnable();
    }

    public void shutdown()
    {
        System.out.println("[UProtect][DBS][STOP] Stop Database Driver...");
        driver.onDisable();
    }

    public void printDriverInfo()
    {
        System.out.printf("[UProtect][DBS][->] Drive Info:");
        System.out.printf("[UProtect][DBS][->] Name: %s \n   Author: %s \n ID: %s \n Version: \n Classpath: %s", driver.getName(), driver.getAuthor(), driver.getID(), driver.getDriverDescription().getClassPath());
    }
}

package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high.extensions;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.core.UProtectAPI;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.DriverDescription;

import java.util.Properties;

/**
 * @author JarJarBings12
 * @creationDate 16.09.2015
 * © 2015 JarJarBings12
 */
public class ModuleInitializer
{
    private UProtect uProtect;
    private UProtectAPI uProtectAPI;
    private Properties driverProperties;
    private DriverDescription driverDescription;
    private ClassLoader classLoader;

    public ModuleInitializer(UProtect uProtect, UProtectAPI uProtectAPI, DriverDescription driverDescription, Properties driverProperties, ClassLoader classLoader)
    {
        this.uProtect = uProtect;
        this.uProtectAPI = uProtectAPI;
        this.driverDescription = driverDescription;
        this.driverProperties = driverProperties;
        this.classLoader = classLoader;
    }

    public UDriver initialize(UDriver uDriver)
    {
        UDriver initializedDriver = uDriver;
        initializedDriver.initialize(uProtect, uProtectAPI, driverDescription, driverProperties, classLoader);
        return uDriver;
    }
}

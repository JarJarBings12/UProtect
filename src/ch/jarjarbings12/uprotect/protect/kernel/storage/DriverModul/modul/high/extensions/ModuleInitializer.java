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
    private final UProtect uProtect;
    private final UProtectAPI uProtectAPI;
    private final Properties driverProperties;
    private final DriverDescription driverDescription;
    private final ClassLoader classLoader;

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
        uDriver.initialize(uProtect, uProtectAPI, driverDescription, driverProperties, classLoader);
        return uDriver;
    }
}

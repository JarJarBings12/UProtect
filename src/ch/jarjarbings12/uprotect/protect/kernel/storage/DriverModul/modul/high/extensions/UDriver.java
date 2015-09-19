package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high.extensions;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.core.UProtectAPI;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.BasicModule;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.DriverDescription;

import java.util.Properties;

/**
 * @author JarJarBings12
 * @creationDate 13.09.2015
 * © 2015 JarJarBings12
 */
public abstract class UDriver extends BasicModule
{

    private UProtect uProtect;
    private UProtectAPI uProtectAPI;
    private Properties driverProperties;
    private DriverDescription driverDescription;
    private ClassLoader classLoader;
    private boolean isEnabled = false;

    public void onLoad()
    {
    }

    final void initialize(UProtect uProtect, UProtectAPI uProtectAPI, DriverDescription driverDescription, Properties driverProperties, ClassLoader classLoader)
    {
        this.uProtect = uProtect;
        this.uProtectAPI = uProtectAPI;
        this.driverDescription = driverDescription;
        this.driverProperties = driverProperties;
        this.classLoader = classLoader;
    }

    public Properties getDriverProperty()
    {
        return this.driverProperties;
    }

    public DriverDescription getDriverDescription()
    {
        return this.driverDescription;
    }

    public UProtect getUProtect()
    {
        return this.uProtect;
    }

    public UProtectAPI getUProtectAPI()
    {
        return uProtectAPI;
    }

    public boolean isEnabled()
    {
        return this.isEnabled;
    }

    public void setEnabled()
    {
        this.isEnabled = true;
    }

    public void setEnabled(boolean bool)
    {
        this.isEnabled = bool;
    }
}

package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.core.UProtectAPI;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.DriverServices;

/**
 * @author JarJarBings12
 * @creationDate 14.09.2015
 * © 2015 JarJarBings12
 */
public abstract class BasicModule implements BasicHigh, DriverServices
{
    public abstract UProtect getUProtect();

    public abstract UProtectAPI getUProtectAPI();

    public abstract boolean isEnabled();

    public abstract void setEnabled();

    public abstract void setEnabled(boolean bool);
}

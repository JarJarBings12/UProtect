package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low;

import ch.jarjarbings12.uprotect3.core.UProtect;
import ch.jarjarbings12.uprotect3.core.UProtectAPI;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.support.DriverServices;

/**
 * Created by tobias on 14.09.2015.
 */
public abstract class BasicModule implements BasicHigh, DriverServices
{
    public abstract UProtect getUProtect();

    public abstract UProtectAPI getUProtectAPI();

    public abstract boolean isEnabled();

    public abstract void setEnabled();

    public abstract void setEnabled(boolean bool);
}

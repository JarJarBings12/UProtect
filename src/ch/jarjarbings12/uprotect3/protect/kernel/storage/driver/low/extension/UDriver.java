package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.extension;

import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.RegionDBSupport;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.DriverInfo;
import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.UserDBSupport;

/**
 * Created by tobias on 26.08.2015.
 */
public abstract class UDriver implements RegionDBSupport, UserDBSupport, DriverInfo
{
    public abstract void onEnable();

    public abstract void onDisable();
}

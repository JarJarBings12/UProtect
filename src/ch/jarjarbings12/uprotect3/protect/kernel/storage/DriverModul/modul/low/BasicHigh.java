package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low;

import java.util.Properties;

/**
 * Created by tobias on 13.09.2015.
 */
public interface BasicHigh extends Driver, DriverInfo
{
    Properties getDriverProperty();

    DriverDescription getDriverDescription();
}

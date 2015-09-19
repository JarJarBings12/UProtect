package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low;

import java.util.Properties;

/**
 * @author JarJarBings12
 * @creationDate 13.09.2015
 * © 2015 JarJarBings12
 */
public interface BasicHigh extends Driver, DriverInfo
{
    Properties getDriverProperty();

    DriverDescription getDriverDescription();
}

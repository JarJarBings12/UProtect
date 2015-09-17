package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.properties;

import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.DriverDescription;

import java.util.Properties;

/**
 * Created by tobias on 13.09.2015.
 */
public class DescriptionParser
{

    private final Properties property;
    private final DriverDescription driverDescription;

    public DescriptionParser(Properties properties)
    {
        this.property = properties;
        this.driverDescription = new DriverDescription(properties.getProperty("id"),
                                                       properties.getProperty("name"),
                                                       properties.getProperty("author"),
                                                       properties.getProperty("version"),
                                                       properties.getProperty("classpath"));
    }

    public Properties getProperty()
    {
        return this.property;
    }

    public DriverDescription getDriverDescription()
    {
        return this.driverDescription;
    }

}

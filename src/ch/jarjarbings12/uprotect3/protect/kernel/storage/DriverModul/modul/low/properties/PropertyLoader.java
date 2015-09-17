package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low.properties;

import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.exceptions.InvalidModuleDescription;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tobias on 13.09.2015.
 */
public class PropertyLoader
{
    public Properties getProperty(String filePath, ClassLoader classLoader) throws InvalidModuleDescription
    {
        Properties properties = new Properties();
        try
        {
            properties.load(classLoader.getResourceAsStream("udriver.properties"));
            if (properties == null)
                throw new InvalidModuleDescription(filePath);
            return properties;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        throw new InvalidModuleDescription(filePath);
    }
}

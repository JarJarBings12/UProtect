package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.properties;

import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.exceptions.InvalidModuleDescription;

import java.io.IOException;
import java.util.Properties;

/**
 * @author JarJarBings12
 * @creationDate 13.09.2015
 * © 2015 JarJarBings12
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

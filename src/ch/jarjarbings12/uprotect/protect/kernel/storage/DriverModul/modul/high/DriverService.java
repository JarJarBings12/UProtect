package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high;

import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.exceptions.InvalidClassPathException;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high.extensions.UDriver;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.DriverClassLoader;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.DriverDescription;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.properties.DescriptionParser;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.properties.PropertyLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author JarJarBings12
 * @creationDate 14.09.2015
 * � 2015 JarJarBings12
 */
public class DriverService
{
    private final File folder = new File("plugins/UProtect/storage/drivers");
    private final HashMap<String, UDriver> drivers = new HashMap<>();
    private final HashMap<String, String> idFilePath = new HashMap<>();
    private final HashMap<String, DescriptionParser> idDescription = new HashMap<>();
    private String[] classPaths = null;
    private final DriverClassLoader driverClassLoader = new DriverClassLoader();

    public DriverService()
    {
    }

    public UDriver getDriver(String id)
    {
        return drivers.get(id);
    }

    public DriverDescription getDriverDescription(String DriverID)
    {
        return idDescription.get(DriverID).getDriverDescription();
    }

    public DriverDescription getDriverDescription(File DriverFile)
    {
        return idDescription.get(idFilePath.entrySet().stream().filter(entry -> entry.getValue().equals(DriverFile.getAbsolutePath())).findFirst().get().getKey()).getDriverDescription();
    }

    public DescriptionParser getDescriptionParser(String DriverID)
    {
        return idDescription.get(DriverID);
    }

    public DescriptionParser getDriverParser(File DriverFile)
    {
        return idDescription.get(idFilePath.entrySet().stream().filter(entry -> entry.getValue().equals(DriverFile.getAbsolutePath())).findFirst().get().getKey());
    }

    public File getDriverFileByID(String id)
    {
        return new File(idFilePath.get(id));
    }

    public DriverClassLoader getDriverClassLoader()
    {
        return this.driverClassLoader;
    }

    private void reInit()
    {
        idDescription.clear();
        drivers.clear();
        classPaths = null;
        init();
    }


    public void init()
    {
        File[] files = folder.listFiles((dir, name) -> {
            return name.endsWith(".jar");
        });

        for (File f : files)
        {
            Properties properties = null;
            try
            { properties = new PropertyLoader().getProperty(f.getAbsolutePath(), new URLClassLoader(new URL[]{f.toURI().toURL()}, UDriver.class.getClassLoader()));
            } catch (MalformedURLException e) { e.printStackTrace(); }
            idDescription.put(properties.getProperty("id"), new DescriptionParser(properties));
            if (!driverClassLoader.existClassPath(f, idDescription.get(properties.getProperty("id")).getDriverDescription().getClassPath()))
            {
                idDescription.remove(properties.getProperty("id"));
                throw new InvalidClassPathException(f.getAbsolutePath(), idDescription.get(properties.getProperty("classpath")).getDriverDescription().getClassPath());
            }
            idFilePath.put(properties.getProperty("id"), f.getAbsolutePath());

            this.classPaths = new String[idDescription.size()];
            int indexCount = 0;

            for (DescriptionParser v : idDescription.values())
            {
                this.classPaths[indexCount] = v.getDriverDescription().getClassPath();
                indexCount++;
            }
        }
        initDrivers();
    }

    public void initDrivers()
    {
        idDescription.keySet().forEach(id -> drivers.put(this.idDescription.get(id).getDriverDescription().getID(), getModule(idFilePath.get(id), idDescription.get(id).getDriverDescription().getClassPath())));

        System.out.println("[UProtect][DSM][->] Drivers:");
        idDescription.values().forEach(v -> {
            DriverDescription temp = v.getDriverDescription();
            System.out.println("[UProtect][DSM][->] Name: " + temp.getName());
            System.out.printf("[UProtect][DSM][->] Author: " + temp.getAuthor());
            System.out.printf("[UProtect][DSM][->] ID: " + temp.getID());
        });
    }

    public UDriver getModule(String filePath, String classPath)
    {
        return driverClassLoader.loadModule(filePath, classPath);
    }
}

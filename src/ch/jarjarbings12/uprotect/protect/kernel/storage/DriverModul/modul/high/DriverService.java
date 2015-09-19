package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high;

import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.exceptions.InvalidClassPathException;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.exceptions.InvalidModuleDescription;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high.extensions.UDriver;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.DriverClassLoader;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.DriverDescription;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.properties.DescriptionParser;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.properties.PropertyLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author JarJarBings12
 * @creationDate 14.09.2015
 * © 2015 JarJarBings12
 */
public class DriverService
{
    private File folder = new File("plugins/UProtect/storage/drivers");
    private HashMap<String, UDriver> drivers = new HashMap<>();
    private HashMap<String, String> driverIDFileName = new HashMap<>();
    private HashMap<String, DescriptionParser> fileDriverDescription = new HashMap<>();
    private String[] classPaths = null;
    private DriverClassLoader driverClassLoader = new DriverClassLoader();

    public DriverService()
    {
    }

    public UDriver getDriver(String id)
    {
        return drivers.get(id);
    }

    public DriverDescription getDriverDescription(String DriverID)
    {
        return fileDriverDescription.values().stream().filter(d -> d.getDriverDescription().getID().equals(DriverID)).findFirst().get().getDriverDescription();
    }

    public DriverDescription getDriverDescription(File DriverFile)
    {
        return fileDriverDescription.get(DriverFile.getName()).getDriverDescription();
    }

    public DescriptionParser getDescriptionParser(String DriverID)
    {
        return fileDriverDescription.values().stream().filter(d -> d.getDriverDescription().getID().equals(DriverID)).findFirst().get();
    }

    public DescriptionParser getDriverParser(File DriverFile)
    {
        return fileDriverDescription.get(DriverFile.getName());
    }

    public File getDriverFileByID(String id)
    {
        return new File(driverIDFileName.get(id));
    }

    public DriverClassLoader getDriverClassLoader()
    {
        return this.driverClassLoader;
    }

    private void reInit()
    {
        fileDriverDescription.clear();
        drivers.clear();
        classPaths = null;
        init();
    }


    public void init()
    {
        File[] files = folder.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".jar");
            }
        });

        for (File f : files)
        {
            try
            {
                Properties properties = new PropertyLoader().getProperty(f.getAbsolutePath(), new URLClassLoader(new URL[]{f.toURI().toURL()}, UDriver.class.getClassLoader()));
                fileDriverDescription.put(f.getName(), new DescriptionParser(properties));
                if (!driverClassLoader.existClassPath(f, fileDriverDescription.get(f.getName()).getDriverDescription().getClassPath()))
                {
                    new InvalidClassPathException(f.getAbsolutePath(), fileDriverDescription.get(f.getName()).getDriverDescription().getClassPath()).printStackTrace();
                    fileDriverDescription.remove(f.getName());
                }
                driverIDFileName.put(properties.getProperty("id"), f.getAbsolutePath());
            }
            catch (InvalidModuleDescription invalidModuleDescription)
            {
                invalidModuleDescription.printStackTrace();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }

            this.classPaths = new String[fileDriverDescription.size()];
            int indexCount = 0;

            for (DescriptionParser v : fileDriverDescription.values())
            {
                this.classPaths[indexCount] = v.getDriverDescription().getClassPath();
                indexCount++;
            }
        }
        initDrivers();
    }

    public void initDrivers()
    {
        fileDriverDescription.keySet().forEach(k -> {
            driverIDFileName.put(fileDriverDescription.get(k).getDriverDescription().getID(), folder.getAbsolutePath() + "/" + k);
            drivers.put(this.fileDriverDescription.get(k).getDriverDescription().getID(), getModule(folder.getAbsolutePath() + "/" + k, fileDriverDescription.get(k).getDriverDescription().getClassPath()));
        });

        System.out.println("[UProtect][DSM][->] Drivers:");
        fileDriverDescription.values().forEach(v -> {
            DriverDescription temp = v.getDriverDescription();
            System.out.println("[UProtect][DSM][->] Name: " + temp.getName());
            System.out.printf("[UProtect][DSM][->] Author: " + temp.getAuthor());
            System.out.printf("[UProtect][DSM][->] ID: " + temp.getID());
        });
    }

    public UDriver getModule(String filePath, String classPath)
    {
        return (UDriver) driverClassLoader.loadModule(filePath, classPath);
    }
}

package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low;

import ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low.extension.UDriver;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Created by tobias on 23.08.2015.
 */
public class DriverService
{

    private File driverFolder = new File("plugins/UProtect/database/drivers");
    private ArrayList<UDriver> drivers = new ArrayList<>();

    public UDriver getDriver(String id)
    {
        return drivers.stream().filter(p -> p.getName().equals(id)).findFirst().get();
    }

    public void loadDrivers()
    {
        File[] files = driverFolder.listFiles(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.endsWith(".class");
            }
        });

        for (File f : files)
            drivers.add(loadDriverFromFile(f.getName()));
    }

    public void unloadDrivers()
    {
        drivers.clear();
    }


    public UDriver loadDriverFromFile(String file)
    {
        try
        {
            URL url = new File("plugins/UProtect/database/drivers").toURI().toURL();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url.toURI().toURL()}, UDriver.class.getClassLoader());
            Class<?> driverc = classLoader.loadClass(file);
            Class<? extends UDriver> driversb = driverc.asSubclass(UDriver.class);
            Constructor<? extends UDriver> divercc = driversb.getConstructor();
            return divercc.newInstance();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
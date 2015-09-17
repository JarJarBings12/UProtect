package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.low;

import ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.high.extensions.UDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by tobias on 14.09.2015.
 */
public class ModuleClassLoader
{
    public Object loadModule(String filePath, String classPath)
    {
        try
        {
            ClassLoader classLoader = new URLClassLoader(new URL[] {new File(filePath).toURI().toURL()}, UDriver.class.getClassLoader());
            return classLoader.loadClass(classPath).newInstance();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
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

    public boolean existClassPath(File file, String classPath)
    {
        try
        {
            return Class.forName(classPath, false, new URLClassLoader(new URL[]{file.toURI().toURL()}, UDriver.class.getClassLoader())) != null ? true : false;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public URL[] makeURL(File file)
    {
        try
        {
            return new URL[] {file.toURI().toURL()};
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

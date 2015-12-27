package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low;

import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high.extensions.UDriver;
import ch.jarjarbings12.uprotect.protect.utils.UClassLoader;

import java.io.File;
import java.net.URL;

/**
 * @author JarJarBings12
 * @creationDate 14.09.2015
 * © 2015 JarJarBings12
 */
public class DriverClassLoader
{
    private final UClassLoader uClassLoader = new UClassLoader();

    public UDriver loadModule(String filePath, String classPath)
    {
        return (UDriver) uClassLoader.loadClass(filePath, classPath, UDriver.class.getClassLoader());
    }

    public boolean existClassPath(File file, String classPath)
    {
        return uClassLoader.existClassPath(file, classPath, UDriver.class.getClassLoader());
    }

    public URL[] makeURL(File file)
    {
        return uClassLoader.makeURL(file);
    }
}

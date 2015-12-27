package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.support.FlagExtension;
import ch.jarjarbings12.uprotect.protect.utils.UClassLoader;

import java.io.File;
import java.net.URL;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015.
 * © 2015 JarJarBings12
 */
public class FlagClassLoader
{
    private final UClassLoader uClassLoader = new UClassLoader();

    public FlagExtension loadModule(String filePath, String classPath)
    {
        return (FlagExtension) uClassLoader.loadClass(filePath, classPath, FlagExtension.class.getClassLoader());
    }

    public boolean existClassPath(File file, String classPath)
    {
        return uClassLoader.existClassPath(file, classPath, FlagExtension.class.getClassLoader());
    }

    public URL[] makeURL(File file)
    {
        return uClassLoader.makeURL(file);
    }
}

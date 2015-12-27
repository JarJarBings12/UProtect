package ch.jarjarbings12.uprotect.protect.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author JarJarBings12
 * @creationDate 17.09.2015
 * © 2015 JarJarBings12
 */
public class UClassLoader
{
    public Object loadClass(String filePath, String classPath, ClassLoader parentClassLoader)
    {
        try
        {
            ClassLoader classLoader = new URLClassLoader(makeURL(new File(filePath)), parentClassLoader);
            return classLoader.loadClass(classPath).newInstance();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existClassPath(File file, String classPath, ClassLoader parentClassLoader)
    {
        try
        {
            return Class.forName(classPath, false, new URLClassLoader(makeURL(file), parentClassLoader)) != null;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public URL[] makeURL(File file)
    {
        try
        {
            return new URL[]{file.toURI().toURL()};
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

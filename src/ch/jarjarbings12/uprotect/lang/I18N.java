package ch.jarjarbings12.uprotect.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author JarJarBings12
 * @creationDate 27.12.2015
 * © 2015 JarJarBings12
 */
public class I18N
{
    private final Properties properties = new Properties();

    public I18N(File languageFile)
    {
        try
        { properties.load(new FileInputStream(languageFile)); }
        catch (IOException e)
        { e.printStackTrace(); }
    }

    public String translate(String key)
    {
        if (!properties.containsKey(key))
            return "{Unknown Key}";
        return (String) properties.get(key);
    }

}

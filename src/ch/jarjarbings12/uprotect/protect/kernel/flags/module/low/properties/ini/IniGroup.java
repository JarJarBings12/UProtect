package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini;

import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015.
 * © 2015 JarJarBings12
 */
public class IniGroup
{
    HashMap<String, String> props = new HashMap<>();

    public IniGroup()
    {
    }

    public void add(String key, String value)
    {
        props.put(key, value);
    }

    public String get(String key)
    {
        return props.get(key);
    }

    public boolean containsKey(String key)
    {
        return props.containsKey(key);
    }
}

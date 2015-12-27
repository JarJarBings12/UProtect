package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini;

import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015.
 * © 2015 JarJarBings12
 */
public class IniGroup
{
    HashMap<String, Object> props = new HashMap<>();

    public IniGroup()
    {
    }

    public IniGroup(HashMap<String, Object> values)
    {
        this.props = values;
    }

    public void add(String key, Object value)
    {
        props.compute(key, (k, v) -> v = value);
    }

    public Object get(String key)
    {
        return props.get(key);
    }

    public char getChar(String key)
    {
        return (char) props.get(key);
    }

    public String getString(String key)
    {
        return (String) props.get(key);
    }

    public short getShort(String key)
    {
        return (short) props.get(key);
    }

    public boolean getBoolean(String key)
    {
        return (boolean) props.get(key);
    }

    public int getInt(String key)
    {
        return (int) props.get(key);
    }

    public long getLong(String key)
    {
        return (int) props.get(key);
    }

    public float getFloat(String key)
    {
        return (float) props.get(key);
    }

    public double getDouble(String key)
    {
        return (double) props.get(key);
    }

    public void set(String key, String value)
    {
        props.computeIfPresent(key, (k, v) -> v = value);
    }

    public void remove(String key)
    {
        props.remove(key);
    }

    public boolean containsKey(String key)
    {
        return props.containsKey(key);
    }

    public IniGroupFactory getGroupFactory()
    {
        return new IniGroupFactory();
    }
}

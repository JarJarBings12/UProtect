package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini;

/**
 * @author JarJarBings12
 * @creationDate 25.12.2015
 * © 2015 JarJarBings12
 */
public class IniGroupFactory
{
    private IniGroup iniGroup = new IniGroup();

    public IniGroupFactory()
    {
    }

    public IniGroupFactory(IniGroup iniGroup)
    {
        this.iniGroup = iniGroup;
    }

    public IniGroupFactory add(String key, Object value)
    {
        add(key, value);
        return this;
    }

    public Object get(String key)
    {
        return iniGroup.get(key);
    }

    public char getChar(String key)
    {
        return iniGroup.getChar(key);
    }

    public String getString(String key)
    {
        return iniGroup.getString(key);
    }

    public short getShort(String key)
    {
        return iniGroup.getShort(key);
    }

    public boolean getBoolean(String key)
    {
        return iniGroup.getBoolean(key);
    }

    public int getInt(String key)
    {
        return iniGroup.getInt(key);
    }

    public long getLong(String key)
    {
        return iniGroup.getLong(key);
    }

    public float getFloat(String key)
    {
        return iniGroup.getFloat(key);
    }

    public double getDouble(String key)
    {
        return iniGroup.getDouble(key);
    }

    public IniGroupFactory set(String key, String value)
    {
        iniGroup.set(key, value);
        return this;
    }

    public IniGroupFactory remove(String key)
    {
        iniGroup.remove(key);
        return this;
    }

    public boolean containsKey(String key)
    {
        return iniGroup.containsKey(key);
    }

    public IniGroup getGroup()
    {
        return iniGroup;
    }
}

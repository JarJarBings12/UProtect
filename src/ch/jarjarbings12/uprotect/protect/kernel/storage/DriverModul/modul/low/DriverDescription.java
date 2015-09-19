package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public class DriverDescription extends ADriverInfo
{
    private final String ID;
    private final String name;
    private final String author;
    private final String version;
    private final String classpath;

    public DriverDescription(String ID, String name, String author, String version, String classpath)
    {
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.version = version;
        this.classpath = classpath;
    }

    @Override
    public String getID()
    {
        return this.ID;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public String getVersion()
    {
        return this.version;
    }

    @Override
    public String getAuthor()
    {
        return this.author;
    }

    @Override
    public String getClassPath()
    {
        return this.classpath;
    }
}

package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015
 * © 2015 JarJarBings12
 */
public class FlagInfo
{

    private final String file;
    private final String classpath;
    private final String name;
    private final String description;

    public FlagInfo(String file, String classpath, String name, String description)
    {
        this.file = file;
        this.classpath = classpath;
        this.name = name;
        this.description = description;
    }

    public String getFilePath()
    {
        return this.file;
    }

    public String getClassPath()
    {
        return this.classpath;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }
}

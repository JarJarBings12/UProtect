package ch.jarjarbings12.uprotect.protect.kernel.flag.module.low;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015
 * © 2015 JarJarBings12
 */
public class FlagInfo
{

    private String file;
    private String classpath;
    private String tag;
    private String description;

    public FlagInfo(String file, String classpath, String tag, String description)
    {
        this.file = file;
        this.classpath = classpath;
        this.tag = tag;
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

    public String getTag()
    {
        return this.tag;
    }

    public String getDescription()
    {
        return this.description;
    }
}

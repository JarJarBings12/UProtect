package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini;

import java.io.File;

/**
 * @author JarJarBings12
 * @creationDate 25.12.2015
 * © 2015 JarJarBings12
 */
public class ParserException extends RuntimeException
{
    public ParserException(int line, File file)
    {
        super(String.format("Error while parsing %s in line %d!", file.getName(), line));
    }
}

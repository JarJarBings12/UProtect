package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.exceptions;

/**
 * @author JarJarBings12
 * @creationDate 16.09.2015
 * © 2015 JarJarBings12
 */
public class InvalidClassPathException extends RuntimeException
{
    public InvalidClassPathException(String filePath, String classPath)
    {
        super(String.format("The file %s doesn't contains this classpath: %s", filePath, classPath));
    }
}

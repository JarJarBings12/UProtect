package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.exceptions;

/**
 * Created by tobias on 16.09.2015.
 */
public class InvalidClassPathException extends Exception
{
    public InvalidClassPathException(String filePath, String classPath)
    {
        super(String.format("The file %s doesn't contains this classpath: %s", filePath, classPath));
    }
}

package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.exceptions;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public class InvalidModuleDescription extends RuntimeException
{
    public InvalidModuleDescription(String filePath)
    {
        super(String.format("The module description in %s isn't exist or is invalid", filePath));
    }
}

package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.exceptions;

/**
 * @author JarJarBings12
 * @creationDate 28.10.2015
 * © 2015 JarJarBings12
 */
public class InvalidDriverException extends Exception
{
    public InvalidDriverException()
    {
        super("The database driver drive is empty! Are you sure that you installed a database driver?");
    }
}

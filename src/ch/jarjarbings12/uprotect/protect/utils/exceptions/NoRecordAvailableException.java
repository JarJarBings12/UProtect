package ch.jarjarbings12.uprotect.protect.utils.exceptions;

/**
 * @author JarJarBings12
 * @creationDate 23.09.2015
 * © 2015 JarJarBings12
 */
public class NoRecordAvailableException extends Exception
{
    public NoRecordAvailableException(int x, int y)
    {
        super(String.format("No record found for %d,%d", x, y));
    }
}

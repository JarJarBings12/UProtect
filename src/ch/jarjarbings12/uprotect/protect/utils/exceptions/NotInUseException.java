package ch.jarjarbings12.uprotect.protect.utils.exceptions;

/**
 * @author JarJarBings12
 * @creationDate 20.09.2015
 * © 2015 JarJarBings12
 */
public class NotInUseException extends RuntimeException
{
    public NotInUseException()
    {
        super();
    }

    public NotInUseException(String message)
    {
        super(message);
    }
}

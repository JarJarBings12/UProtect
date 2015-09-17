package ch.jarjarbings12.uprotect3.protect.kernel.storage.DriverModul.modul.exceptions;

/**
 * Created by tobias on 15.09.2015.
 */
public class InvalidModuleDescription extends Exception
{
    public InvalidModuleDescription(String filePath)
    {
        super(String.format("The module description in %s isn't exist or is invalid", filePath));
    }
}

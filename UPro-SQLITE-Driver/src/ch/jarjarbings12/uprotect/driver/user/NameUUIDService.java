package ch.jarjarbings12.uprotect.driver.user;

import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.UserNameService;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 11.11.2015
 * © 2015 JarJarBings12
 */
public class NameUUIDService implements UserNameService
{
    @Override
    public boolean isUUIDRegistered(UUID uuid)
    {
        return false;
    }

    @Override
    public boolean isNameRegistered(String s)
    {
        return false;
    }

    @Override
    public boolean isNameCurrently(UUID uuid, String s)
    {
        return false;
    }

    @Override
    public void setUserNameFor(UUID uuid, String s)
    {

    }

    @Override
    public String getUserNameFor(UUID uuid)
    {
        return null;
    }

    @Override
    public UUID getUUIDFor(String s)
    {
        return null;
    }
}

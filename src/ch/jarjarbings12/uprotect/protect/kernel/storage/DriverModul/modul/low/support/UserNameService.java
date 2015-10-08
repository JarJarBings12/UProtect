package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 13.09.2015
 * © 2015 JarJarBings12
 */
public interface UserNameService
{

    boolean isUUIDRegistered(UUID uuid);

    boolean isNameCurrently(UUID uuid, String name);

    void setUserNameFor(UUID uuid, String name);

    String getUserNameFor(UUID uuid);

    UUID getUUIDFor(String name);
}

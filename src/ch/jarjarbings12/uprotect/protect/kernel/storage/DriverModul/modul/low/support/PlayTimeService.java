package ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 13.09.2015
 * © 2015 JarJarBings12
 */
public interface PlayTimeService
{
    void setPlayTime(UUID uuid, long time);

    long getPlayTime(UUID uuid);

    void setLastLogin(UUID uuid, long lastLogin);

    long getLastLogin(UUID uuid);
}

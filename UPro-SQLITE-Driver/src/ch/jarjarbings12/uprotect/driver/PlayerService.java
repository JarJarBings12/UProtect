package ch.jarjarbings12.uprotect.driver;

import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.PlayTimeService;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.UserDBServices;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.UserNameService;

/**
 * @author JarJarBings12
 * @creationDate 11.11.2015
 * © 2015 JarJarBings12
 */
public class PlayerService implements UserDBServices
{
    private BaseSqliteConnection baseSqliteConnection = null;

    public PlayerService(BaseSqliteConnection baseSqliteConnection)
    {
        this.baseSqliteConnection = baseSqliteConnection;
    }

    @Override
    public PlayTimeService getPlayTimeService()
    {
        return null;
    }

    @Override
    public UserNameService getPlayerUUIDService()
    {
        return null;
    }
}

package ch.jarjarbings12.uprotect.driver;

import ch.jarjarbings12.uprotect.driver.user.NameUUIDService;
import ch.jarjarbings12.uprotect.driver.user.UserPlayTimeService;
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
    private NameUUIDService nameUUIDService = null;
    private UserPlayTimeService playTimeService = null;

    public PlayerService(BaseSqliteConnection baseSqliteConnection)
    {
        this.baseSqliteConnection = baseSqliteConnection;
        this.nameUUIDService = new NameUUIDService(baseSqliteConnection);
        this.playTimeService = new UserPlayTimeService(baseSqliteConnection);
    }

    @Override
    public PlayTimeService getPlayTimeService()
    {
        return playTimeService;
    }

    @Override
    public UserNameService getPlayerUUIDService()
    {
        return nameUUIDService;
    }
}

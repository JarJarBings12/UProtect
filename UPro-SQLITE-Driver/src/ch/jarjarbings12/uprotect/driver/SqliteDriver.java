package ch.jarjarbings12.uprotect.driver;

import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.high.extensions.UDriver;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.RegionDBService;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.UserDBServices;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * @author JarJarBings12
 * @creationDate 11.11.2015
 * © 2015 JarJarBings12
 */
public class SqliteDriver extends UDriver
{

    private BaseSqliteConnection baseSqliteConnection = null;
    private RegionService regionService;
    private PlayerService playerService;

    @Override
    public void onEnable()
    {
        baseSqliteConnection = new BaseSqliteConnection();
        regionService = new RegionService(baseSqliteConnection);
        playerService = new PlayerService(baseSqliteConnection);
        PreparedStatement preparedStatement;
        Iterator<World> var1 = Bukkit.getWorlds().iterator();
        World var2;
        while (var1.hasNext())
        {
            var2 = var1.next();
            try
            {
                preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.createRegionIndex.replace("%w", var2.getUID().toString().replace("-", "")));
                preparedStatement.execute();
                preparedStatement.close();

                preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.createRegionDatabase.replace("%w", var2.getUID().toString().replace("-", "")));
                preparedStatement.execute();
                preparedStatement.close();

                preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.createUserRegister);
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable()
    {

    }

    @Override
    public String getID()
    {
        return "UPRO-SQLITE-DRIVER-INDEV";
    }

    @Override
    public String getName()
    {
        return "UProtect-Sqlite-Driver";
    }

    @Override
    public String getVersion()
    {
        return "BETA-1.0.0.0";
    }

    @Override
    public String getAuthor()
    {
        return "JarJarBings12";
    }

    @Override
    public RegionDBService getRegionDBService()
    {
        return regionService;
    }

    @Override
    public UserDBServices getUserDBService()
    {
        return playerService;
    }
}

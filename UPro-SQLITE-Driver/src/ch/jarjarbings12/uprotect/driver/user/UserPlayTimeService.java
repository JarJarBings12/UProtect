package ch.jarjarbings12.uprotect.driver.user;

import ch.jarjarbings12.uprotect.driver.BaseSqliteConnection;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.PlayTimeService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 25.11.2015
 * © 2015 JarJarBings12
 */
public class UserPlayTimeService implements PlayTimeService
{

    public BaseSqliteConnection baseSqliteConnection = null;

    public UserPlayTimeService(BaseSqliteConnection baseSqliteConnection)
    {
        this.baseSqliteConnection = baseSqliteConnection;
    }

    @Override
    public void setPlayTime(UUID uuid, long l)
    {


        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.updatePlayTimeFor);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
                    preparedStatement.setLong(2, l);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public long getPlayTime(UUID uuid)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.selectPlayTimeFor);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            long temp = preparedStatement.executeQuery().getLong(1);
            preparedStatement.close();
            return temp;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setLastLogin(UUID uuid, long l)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.updateLastLoginFor);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            preparedStatement.setLong(2, l);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public long getLastLogin(UUID uuid)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.selectLastLoginFor);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            long temp = preparedStatement.executeQuery().getLong(1);
            preparedStatement.close();
            return temp;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void setFirstLogin(UUID uuid, long l)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.updateFirstLoginFor);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            preparedStatement.setLong(2, l);
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public long getFirstLogin(UUID uuid)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.selectFirstLoginFor);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            long temp = preparedStatement.executeQuery().getLong(1);
            preparedStatement.close();
            return temp;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}

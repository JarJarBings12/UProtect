package ch.jarjarbings12.uprotect.driver.user;

import ch.jarjarbings12.uprotect.driver.BaseSqliteConnection;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.UserNameService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 11.11.2015
 * © 2015 JarJarBings12
 */
public class NameUUIDService implements UserNameService
{
    private BaseSqliteConnection baseSqliteConnection = null;

    public NameUUIDService(BaseSqliteConnection baseSqliteConnection)
    {
        this.baseSqliteConnection = baseSqliteConnection;
    }

    @Override
    public boolean isUUIDRegistered(UUID uuid)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.isUUIDRegistered);
        try
        {
            preparedStatement.setString(1, String.format("%s-%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            boolean bool = preparedStatement.executeQuery().getInt("_COUNT_") != 0;
            preparedStatement.close();
            return bool;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isNameRegistered(String s)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.isNameRegistered);
        try
        {
            preparedStatement.setString(1, s);
            boolean bool = preparedStatement.executeQuery().getInt("_COUNT_") > 0;
            preparedStatement.close();
            return bool;
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setUserNameFor(UUID uuid, String s)
    {
        if (isUUIDRegistered(uuid))
        {
            PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.updateUserNameFor);
            try
            {
                preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
                preparedStatement.setString(2, s);
                preparedStatement.execute();
                preparedStatement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return;
        }

        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.insertUserNameFor);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            preparedStatement.setString(2, s);
            preparedStatement.execute();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getUserNameFor(UUID uuid)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.selectNameForUUID);
        try
        {
            preparedStatement.setString(1, String.format("%s:%s", String.valueOf(uuid.getMostSignificantBits()), String.valueOf(uuid.getLeastSignificantBits())));
            String string = preparedStatement.executeQuery().getString(1);
            preparedStatement.close();
            return string;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UUID getUUIDFor(String s)
    {
        PreparedStatement preparedStatement = baseSqliteConnection.createPreparedStatement(baseSqliteConnection.selectUUIDForName);
        try
        {
            preparedStatement.setString(1, s);
            String[] split = preparedStatement.executeQuery().getString(1).split(":");
            preparedStatement.close();
            return new UUID(Long.valueOf(split[0]), Long.valueOf(split[1]));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

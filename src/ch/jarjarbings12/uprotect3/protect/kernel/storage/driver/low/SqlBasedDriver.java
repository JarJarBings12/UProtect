package ch.jarjarbings12.uprotect3.protect.kernel.storage.driver.low;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by tobias on 23.08.2015.
 */
public interface SqlBasedDriver
{
    Connection openConnection();

    Statement createStatement();

    PreparedStatement createPreparedStatement(String query);
}

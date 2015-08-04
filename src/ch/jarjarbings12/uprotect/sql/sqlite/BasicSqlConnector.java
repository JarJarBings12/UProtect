package ch.jarjarbings12.uprotect.sql.sqlite;

import ch.jarjarbings12.uprotect.core.Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
* @since 1.0.0.0
* @author JarJarBings12
* @creationDate 14.06.2015
*/
public class BasicSqlConnector
{

   public Connection openConnection()
   {
	   try
	   {
		   Class.forName("org.sqlite.JDBC");
		   return DriverManager.getConnection("jdbc:sqlite:" + new Core().getDataFolder().getAbsolutePath()+"/upro.db");
	   }
	   catch (Exception e)
	   {
		   e.printStackTrace();
		   return null;
	   }
   }

    public Statement createStatement()
   {
	   try
	   {
		   Class.forName("org.sqlite.JDBC");
           return DriverManager.getConnection("jdbc:sqlite:" + new Core().getDataFolder().getAbsolutePath() + "/upro.db").createStatement();
	   }
	   catch (Exception e)
	   {
		   e.printStackTrace();
		   return null;
	   }
   }

   public PreparedStatement createPreparedStatement(String query)
   {
	   try
	   {
		   Class.forName("org.sqlite.JDBC");
           return DriverManager.getConnection("jdbc:sqlite:" + new Core().getDataFolder().getAbsolutePath() + "/upro.db").prepareStatement(query);
	   }
	   catch (Exception e)
	   {
		   e.printStackTrace();
		   return null;
	   }
   }
}

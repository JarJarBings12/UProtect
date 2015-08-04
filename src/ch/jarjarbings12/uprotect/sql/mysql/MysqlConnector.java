package ch.jarjarbings12.uprotect.sql.mysql;

import java.sql.*;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class MysqlConnector
{
	private String connectionUrl;

	private String host = "localhost";
	private int port = 3306;
	private String user = "user";
	private String password = "password";
	private String database = "database";

	private Connection connection;

	public MysqlConnector(String host, int port, String user, String pass, String database)
	{
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = pass;
		this.database = database;
		this.connectionUrl = "jdbc:mysql://" + this.host + ":" + this.port + ":" + this.database;
		this.connection = openConnection();

		if (!isConnected())
			System.out.println("[UProtect] UProtect can't connect to MySql!");
	}

	public Connection openConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(connectionUrl, user, password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Connection getConnection()
	{
		return this.connection;
	}

	public boolean isConnected()
	{
		try
		{
			return this.connection != null && this.connection.isValid(1);
		}
		catch (SQLException e)
		{
			return false;
		}
	}

	public Statement createStatement()
	{
		try
		{
			return this.connection.createStatement();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public PreparedStatement createPrepareStatement(String query)
	{
		try
		{
			return this.connection.prepareStatement(query);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection()
	{
		try
		{
			this.connection.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			this.connection = null;
		}
	}
}

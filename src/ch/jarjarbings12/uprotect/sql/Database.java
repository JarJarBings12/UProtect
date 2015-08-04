package ch.jarjarbings12.uprotect.sql;

import ch.jarjarbings12.uprotect.configuration.Configuration;
import ch.jarjarbings12.uprotect.sql.mysql.MysqlConnector;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class Database
{
	private db databaseType;

	private MysqlConnector mysql = null;

	/* sqlite */
	public Database(db type)
	{
		databaseType = type;
		setup();
	}

	public void setup()
	{
		YamlConfiguration yaml = new Configuration().yamlConfiguration;
		this.mysql = new MysqlConnector(yaml.getString("database.mysql.host"), yaml.getInt("database.mysql.port"), yaml.getString("database.mysql.user"), yaml.getString("database.mysql.pass"), yaml.getString("database.mysql.database"));
	}

	public MysqlConnector getMySqlConnector()
	{
		return mysql;
	}

	public enum db
	{
		sqlite,
		mysql
	}
}

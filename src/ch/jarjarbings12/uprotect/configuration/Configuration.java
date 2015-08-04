package ch.jarjarbings12.uprotect.configuration;

import ch.jarjarbings12.uprotect.core.Core;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class Configuration
{
	public YamlConfiguration yamlConfiguration = null;
	private String path = new Core().getDataFolder().getAbsolutePath();

	public void setup()
	{
		loadResources("resources/config.yml", "/config.yml");
		yamlConfiguration = YamlConfiguration.loadConfiguration(new File(path+"/config.yml"));
	}

	private boolean loadResources(String resourcePath, String name)
	{
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
		File output = new File(path + name);
		try
		{
			output.createNewFile();
			OutputStream outputStream = new FileOutputStream(output);
			int length;
			byte[] byteBuffer = new byte[1024];
			while ((length = inputStream.read(byteBuffer)) > 0)
			{
				outputStream.write(byteBuffer, 0, length);
			}
			outputStream.flush();
			outputStream.close();
			return true;
		}
		catch (FileNotFoundException e)
		{ e.printStackTrace(); }
		catch (IOException e)
		{ e.printStackTrace(); }
		return false;
	}
}

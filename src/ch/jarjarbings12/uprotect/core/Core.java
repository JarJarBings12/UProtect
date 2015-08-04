package ch.jarjarbings12.uprotect.core;

import ch.jarjarbings12.uprotect.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class Core extends JavaPlugin
{

	@Override
	public void onEnable()
	{
		new Configuration().setup();
	}
}

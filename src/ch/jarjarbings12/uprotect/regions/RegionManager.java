package ch.jarjarbings12.uprotect.regions;

import ch.jarjarbings12.uprotect.sql.sqlite.DatabaseManager;
import ch.jarjarbings12.uprotect.utils.objectSerializion.objectSerialize;
import org.bukkit.Location;

import java.sql.SQLException;

/**
 * @author JarJarBings12
 * @creationDate 30.07.2015
 * @since 1.0.0.0
 */
public class RegionManager
{
	private DatabaseManager db = new DatabaseManager();
	private objectSerialize serialize = new objectSerialize();

	public boolean isProtected(Location location)
	{
		try
		{
			if (db.createStatement().executeQuery("SELECT COUNT(*) AS total FROM RP WHERE WORLD="+location.getWorld()+" X="+location.getChunk().getX()+" Y="+location.getBlockY()).getInt("total") > 0)
				return true;
			return false;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return true;
	}

	public ProtectedChunkRegion getProtectedChunkRegion(Location location)
	{
		try
		{
			String region = db.createStatement().executeQuery("SELECT * FROM RP WHERE WORLD="+location.getWorld().getName() + " X=" + location.getChunk().getX() + " Z=" + location.getBlockY()).getString("REGION");
			return (ProtectedChunkRegion) serialize.deserializeByteArray(db.createStatement().executeQuery("SELECT * FROM PR WHERE WORLD=" + location.getWorld().getName() + " X=" + location.getChunk().getX() + " Z=" + location.getChunk().getZ()).getBytes("PROTECTION"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}

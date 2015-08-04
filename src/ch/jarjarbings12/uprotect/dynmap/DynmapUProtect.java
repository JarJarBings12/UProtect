package ch.jarjarbings12.uprotect.dynmap;

import ch.jarjarbings12.uprotect.regions.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect.regions.flags.Flag;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.AreaMarker;
import org.dynmap.markers.MarkerAPI;

import java.util.List;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class DynmapUProtect
{
	private DynmapAPI dynmapAPI;
	private MarkerAPI markerAPI;

	private String infoWindow = "<div class=\"infowindow\"> <span style=\"font-weight:bold;\"> Name: </span> %name% </br > <span style=\"font-weight:bold;\"> Owners: </span> %owners% </br > <span style=\"font-weight:bold;\"> Members: </span> %members% </br > <span style=\"font-weight:bold;\"> Flags: </span> %flags% </div>";


	public AreaMarker craftAreaMarker(ProtectedChunkRegion region, ChunkStyle.type type)
	{
		return null;
	}

	public String parseInfoWindow(ProtectedChunkRegion chunkRegion)
	{
		String temp = infoWindow;

		temp.replace("%name%", chunkRegion.getName());
		temp.replace("%owners%", parseOwners(chunkRegion.getOwners()));
		temp.replace("%members%", parseMembers(chunkRegion.getMembers()));
		temp.replace("%flags%", parseFlags(chunkRegion.getFlags()));

		return temp;
	}

	private String parseOwners(List<UUID> uuids)
	{
		StringBuilder temp = new StringBuilder(uuids.get(0).toString());

		if (temp.equals(""))
			temp.append(uuids.get(0));

		for (int i = 1; i < uuids.size(); i++)
		{
			temp.append(", " + uuids.get(i));
		}
		return temp.toString();
	}

	private String parseMembers(List<UUID> uuids)
	{
		StringBuilder temp = new StringBuilder(uuids.get(0).toString());

		for (int i = 1; i < uuids.size(); i++)
		{
			temp.append(", " + uuids.get(i).toString());
		}
		return temp.toString();
	}

	private String parseFlags(List<Flag<?>> flags)
	{
		StringBuilder temp = new StringBuilder(flags.get(0).getName());

		for (int i = 1; i < flags.size(); i++)
		{
			temp.append(", " + flags.get(i).getName());
		}
		return temp.toString();
	}
}

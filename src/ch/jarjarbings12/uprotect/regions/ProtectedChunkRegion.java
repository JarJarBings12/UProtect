package ch.jarjarbings12.uprotect.regions;

import ch.jarjarbings12.uprotect.regions.flags.Flag;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class ProtectedChunkRegion extends ProtectedRegion
{

	private final String ID;
	private String name;
	private List<ProtectedChunk> protectedChunks;
	private List<UUID> owners;
	private List<UUID> members;
	private List<Flag<?>> flags;

	public ProtectedChunkRegion(final String ID, String name, List<ProtectedChunk> selectedChunks)
	{
		this.ID = ID;
		this.name = name;
		this.protectedChunks = selectedChunks;
	}

	public ProtectedChunkRegion(final String ID, String name, List<ProtectedChunk> selectedChunks, List<UUID> owners)
	{
		this.ID = ID;
		this.name = name;
		this.protectedChunks = selectedChunks;
		this.owners = owners;
	}

	public ProtectedChunkRegion(final String ID, String name, List<ProtectedChunk> selectedChunks, List<UUID> owners, List<UUID> members)
	{
		this.ID = ID;
		this.name = name;
		this.protectedChunks = selectedChunks;
		this.owners = owners;
		this.members = members;
	}


	public ProtectedChunkRegion(final String ID, String name, List<ProtectedChunk> selectedChunks, List<UUID> owners, List<UUID> members, List<Flag<?>> flags)
	{
		this.ID = ID;
		this.name = name;
		this.protectedChunks = selectedChunks;
		this.owners = owners;
		this.members = members;
		this.flags = flags;
	}

	@Override
	public String getID()
	{
		return this.ID;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void addOwner(UUID uuid)
	{
		this.owners.add(uuid);
	}

	@Override
	public void addMember(UUID uuid)
	{
		this.members.add(uuid);
	}

	@Override
	public List<UUID> getMembers()
	{
		return this.members;
	}

	@Override
	public List<UUID> getOwners()
	{
		return this.owners;
	}

	@Override
	public boolean removeOwner(UUID uuid)
	{
		return this.owners.remove(uuid);
	}

	@Override
	public boolean removeMember(UUID uuid)
	{
		return this.owners.remove(uuid);
	}

	@Override
	public boolean hasAccess(UUID uuid)
	{
		return (this.owners.contains(uuid) || this.members.contains(uuid)) ? true : false;
	}

	@Override
	public List<ProtectedChunk> getProtectedChunks()
	{
		return this.protectedChunks;
	}

	@Override
	public void addProtectedChunk(ProtectedChunk chunk)
	{
		this.protectedChunks.add(chunk);
	}

	@Override
	public List<Flag<?>> getFlags()
	{
		return this.flags;
	}

	@Override
	public void addFlag(Flag<?> flag)
	{
		if (hasFlag(flag.getName()))
			removeFlag(flag.getName());

		this.flags.add(flag);
	}

	@Override
	public boolean hasFlag(String flag)
	{
		Iterator iterator = this.flags.iterator();

		while (iterator.hasNext())
		{
			if (((Flag)iterator.next()).getName().equals(flag))
				return true;
		}
		return false;
	}

	@Override
	public boolean removeFlag(String flag)
	{
		return this.flags.removeIf(f -> f.getName().equals(flag));
	}
}
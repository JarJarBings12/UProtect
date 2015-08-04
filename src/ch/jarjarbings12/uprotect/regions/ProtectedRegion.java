package ch.jarjarbings12.uprotect.regions;

import ch.jarjarbings12.uprotect.regions.flags.Flag;

import java.util.List;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public abstract class ProtectedRegion
{

	public abstract String getID();

	public abstract String getName();

	public abstract void setName(String name);

	public abstract void addOwner(UUID uuid);

	public abstract void addMember(UUID uuid);

	public abstract List<UUID> getMembers();

	public abstract List<UUID> getOwners();

	public abstract boolean removeOwner(UUID uuid);

	public abstract boolean removeMember(UUID uuid);

	public abstract boolean hasAccess(UUID uuid);

	public abstract List<ProtectedChunk> getProtectedChunks();

	public abstract void addProtectedChunk(ProtectedChunk chunk);

	public abstract List<Flag<?>> getFlags();

	public abstract void addFlag(Flag<?> flag);

	public abstract boolean hasFlag(String flag);

	public abstract boolean removeFlag(String flag);

}

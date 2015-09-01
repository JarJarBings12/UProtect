package ch.jarjarbings12.uprotect3.protect.kernel.objects;

import ch.jarjarbings12.uprotect3.protect.flags.Flag;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by tobias on 03.08.2015.
 */
public class ProtectedChunkRegion
{
    private final UUID uuid;
    private final String id;
    private String name;
    private final String world;
    private Set<ProtectedChunk> protectedChunks = new HashSet<>();
    private Set<UUID> owners;
    private Set<UUID> members;
    private Set<Flag<?>> flags;

    public ProtectedChunkRegion(final String id, String name, String worldName, CopyOnWriteArraySet<ProtectedChunk> protectedChunks, CopyOnWriteArraySet<UUID> owners, CopyOnWriteArraySet<UUID> members, CopyOnWriteArraySet<Flag<?>> flags)
    {
        this.uuid = UUID.randomUUID();
        this.id = id;
        this.name = name;
        this.world = worldName;
        this.protectedChunks = protectedChunks;
        this.owners = owners;
        this.members = members;
        this.flags = flags;
    }

    public final UUID getUUID()
    {
        return this.uuid;
    }

    public final String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public final String getWorld()
    {
        return this.world;
    }

    public void setName(String name)
    {
        this.name = name;
        return;
    }

    public Set<ProtectedChunk> getProtectedChunks()
    {
        return this.protectedChunks;
    }

    public ProtectedChunk getProtectedChunk(int x, int z)
    {
        return this.protectedChunks.stream().filter(c -> c.getX() == x && c.getZ() == z).findFirst().get();
    }

    public void addProtectedChunk(ProtectedChunk protectedChunk)
    {
        this.protectedChunks.add(protectedChunk);
        return;
    }

    public boolean removeProtectedChunk(int x, int z)
    {
       return this.protectedChunks.removeIf(pc -> pc.getX() == x && pc.getZ() == z);
    }

    public void setProtectedChunks(CopyOnWriteArraySet<ProtectedChunk> protectedChunks)
    {
        this.protectedChunks = protectedChunks;
        return;
    }

    public boolean hasAccess(UUID uuid)
    {
        return (isMember(uuid) || isOwner(uuid)) ? true : false;
    }

    public boolean isOwner(UUID uuid)
    {
        return this.owners.contains(uuid);
    }

    public boolean isMember(UUID uuid)
    {
        return this.members.contains(uuid);
    }

    public Set<UUID> getOwners()
    {
        return this.owners;
    }

    public void addOwner(UUID uuid)
    {
        if (isOwner(uuid))
            return;

        this.owners.add(uuid);
        return;
    }

    public boolean removeOwner(UUID uuid)
    {
        if (!isOwner(uuid))
            return false;

        return this.owners.remove(uuid);
    }

    public void setOwners(CopyOnWriteArraySet<UUID> owners)
    {
        this.owners = owners;
        return;
    }

    public Set<UUID> getMembers()
    {
        return this.members;
    }

    public void addMember(UUID uuid)
    {
        if (isMember(uuid))
            return;

        this.members.add(uuid);
        return;
    }

    public boolean removeMember(UUID uuid)
    {
        if (!isMember(uuid))
            return false;

        return this.members.remove(uuid);
    }

    public void setMembers(CopyOnWriteArraySet<UUID> members)
    {
        this.members = members;
        return;
    }

    public Set<Flag<?>> getFlags()
    {
        return this.flags;
    }

    public boolean hasFlag(String name) {
        Iterator<Flag<?>> flags = this.flags.iterator();

        while (flags.hasNext())
            if (flags.next().getName().equals(name)) return true;

        return false;
    }

    public void addFlag(Flag<?> flag)
    {
        if (hasFlag(flag.getName()))
            removeFlag(flag.getName());

        this.flags.add(flag);
        return;
    }

    public boolean removeFlag(String name)
    {
       return this.flags.removeIf(f -> f.getName().equals(name));
    }

    public void setFlags(Set<Flag<?>> flags)
    {
        this.flags = flags;
        return;
    }

}

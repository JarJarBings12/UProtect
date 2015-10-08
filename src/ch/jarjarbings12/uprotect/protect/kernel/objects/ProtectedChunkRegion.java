package ch.jarjarbings12.uprotect.protect.kernel.objects;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.kernel.services.objects.DifferenceType;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.UnknownWorldException;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author JarJarBings12
 * @creationDate 03.08.2015
 * © 2015 JarJarBings12
 */
public class ProtectedChunkRegion implements Serializable
{
    private final UUID uuid;
    private final String id;
    private final UUID world;
    private String name;
    private Set<ProtectedChunk> protectedChunks = new HashSet<>();
    private Set<UUID> owners;
    private Set<UUID> members;
    private Set<Flag> flags;

    public ProtectedChunkRegion(final String id, String name, UUID worldUUID, CopyOnWriteArraySet<ProtectedChunk> protectedChunks, CopyOnWriteArraySet<UUID> owners, CopyOnWriteArraySet<UUID> members, CopyOnWriteArraySet<Flag> flags)
    {
        this.uuid = UUID.randomUUID();
        this.id = id;
        this.name = name;
        this.world = worldUUID;
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

    public final World getWorld()
    {
        return Bukkit.getWorld(world);
    }

    public void setName(String name)
    {
        this.name = name;
        applyChanges();
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
        applyChanges();
        return;
    }

    public boolean removeProtectedChunk(int x, int z)
    {
        boolean var1 = this.protectedChunks.removeIf(pc -> pc.getX() == x && pc.getZ() == z);
        if (var1)
            applyChanges();
        return var1;
    }

    public void setProtectedChunks(CopyOnWriteArraySet<ProtectedChunk> protectedChunks)
    {
        this.protectedChunks = protectedChunks;
        applyChanges();
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
        this.owners.remove(uuid);
        applyChanges();
        return true;
    }

    public void setOwners(CopyOnWriteArraySet<UUID> owners)
    {
        this.owners = owners;
        applyChanges();
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
        applyChanges();
        return;
    }

    public boolean removeMember(UUID uuid)
    {
        if (!isMember(uuid))
            return false;
        this.members.remove(uuid);
        applyChanges();
        return true;
    }

    public void setMembers(CopyOnWriteArraySet<UUID> members)
    {
        this.members = members;
        applyChanges();
        return;
    }

    public Set<Flag> getFlags()
    {
        return this.flags;
    }

    public boolean hasFlag(UUID uuid)
    {
        Iterator<Flag> flags = this.flags.iterator();

        while (flags.hasNext())
            if (flags.next().getFlagID().equals(uuid))
                return true;

        return false;
    }

    public void addFlag(Flag flag)
    {
        if (hasFlag(flag.getFlagID()))
            removeFlag(flag.getFlagID());
        this.flags.add(flag);
        applyChanges();
        return;
    }

    public Flag getFlag(UUID flagID)
    {
        return flags.stream().filter(f -> f.getFlagID().equals(flagID)).findFirst().get();
    }

    public boolean removeFlag(UUID flagID)
    {
        boolean var1 = this.flags.removeIf(f -> f.getFlagID().equals(flagID));
        if (var1)
            applyChanges();
        return var1;
    }

    public void setFlags(Set<Flag> flags)
    {
        this.flags = flags;
        return;
    }

    public RegionDatabase getRegionDatabase() throws UnknownWorldException
    {
        return UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(world);
    }

    protected void applyChanges()
    {
        try
        { getRegionDatabase().addDifference(this, DifferenceType.CHANGED); }
        catch (UnknownWorldException e)
        { e.printStackTrace(); }
    }
}
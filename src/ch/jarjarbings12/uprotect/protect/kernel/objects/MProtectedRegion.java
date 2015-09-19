package ch.jarjarbings12.uprotect.protect.kernel.objects;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.Flag;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.BlockVector;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author JarJarBings12
 * @creationDate 08.08.2015
 * © 2015 JarJarBings12
 */
public class MProtectedRegion
{
    private final ProtectedChunkRegion protectedChunkRegion;
    private final World world;

    public MProtectedRegion(final ProtectedChunkRegion protectedChunkRegion)
    {
        this.protectedChunkRegion = protectedChunkRegion;
        this.world = Bukkit.getServer().getWorld(protectedChunkRegion.getWorld());
    }

    public ProtectedChunkRegion getSnapshot()
    {
        return this.protectedChunkRegion;
    }

    public void update()
    {
        UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(world.getName()).setRegion(protectedChunkRegion.getUUID(), protectedChunkRegion);
    }

    public Set<ProtectedChunk> getProtectedChunks()
    {
        return this.protectedChunkRegion.getProtectedChunks();
    }

    public MProtectedRegion addProtectedChunk(BlockVector vector)
    {
        this.protectedChunkRegion.addProtectedChunk(new ProtectedChunk(vector.getBlockX(), vector.getBlockZ(), world.getChunkAt(vector.getBlockX(), vector.getBlockZ())));
        return this;
    }

    public MProtectedRegion addProtectedChunk(CopyOnWriteArraySet<ProtectedChunk> protectedChunks)
    {
        protectedChunks.forEach(c -> this.protectedChunkRegion.addProtectedChunk(c));
        return this;
    }

    public MProtectedRegion removeProtectedChunks(BlockVector vector)
    {
        this.protectedChunkRegion.removeProtectedChunk(vector.getBlockX(), vector.getBlockZ());
        return this;
    }

    public MProtectedRegion removeProtectedChunks(CopyOnWriteArraySet<BlockVector> vectors)
    {
        vectors.forEach(v -> this.protectedChunkRegion.removeProtectedChunk(v.getBlockX(), v.getBlockX()));
        return this;
    }

    public MProtectedRegion setProtectedChunks(CopyOnWriteArraySet<ProtectedChunk> protectedChunks)
    {
        this.protectedChunkRegion.setProtectedChunks(protectedChunks);
        return this;
    }

    public boolean hasAccess(UUID uuid)
    {
        return (isMember(uuid) || isOwner(uuid)) ? true : false;
    }

    public boolean isOwner(UUID uuid)
    {
        return this.protectedChunkRegion.getOwners().contains(uuid);
    }

    public boolean isMember(UUID uuid)
    {
        return this.protectedChunkRegion.getMembers().contains(uuid);
    }

    public Set<UUID> getOwners()
    {
        return this.protectedChunkRegion.getOwners();
    }

    public MProtectedRegion addOwner(UUID uuid)
    {
        if (!isOwner(uuid))
            this.protectedChunkRegion.addOwner(uuid);
        return this;
    }

    public MProtectedRegion removeOwner(UUID uuid)
    {
        if (isOwner(uuid))
            this.protectedChunkRegion.getOwners().remove(uuid);
        return this;
    }

    public MProtectedRegion setOwners(CopyOnWriteArraySet<UUID> owners)
    {
        this.protectedChunkRegion.setOwners(owners);
        return this;
    }

    public Set<UUID> getMembers()
    {
        return this.protectedChunkRegion.getMembers();
    }

    public MProtectedRegion addMember(UUID uuid)
    {
        if (!isMember(uuid))
            this.protectedChunkRegion.addMember(uuid);
        return this;
    }

    public MProtectedRegion removeMember(UUID uuid)
    {
        if (isMember(uuid))
            this.protectedChunkRegion.removeMember(uuid);

        return this;
    }

    public MProtectedRegion setMembers(CopyOnWriteArraySet<UUID> members)
    {
        this.protectedChunkRegion.setMembers(members);
        return this;
    }

    public Set<Flag> getFlags()
    {
        return this.protectedChunkRegion.getFlags();
    }

    public boolean hasFlag(String name)
    {
        Iterator<Flag> flags = this.protectedChunkRegion.getFlags().iterator();

        while (flags.hasNext())
            if (flags.next().getName().equals(name))
                return true;

        return false;
    }

    public MProtectedRegion addFlag(Flag flag)
    {
        if (hasFlag(flag.getName()))
            removeFlag(flag.getName());

        this.protectedChunkRegion.addFlag(flag);
        return this;
    }

    public MProtectedRegion removeFlag(String name)
    {
        this.protectedChunkRegion.getFlags().removeIf(f -> f.getName().equals(name));
        return this;
    }

    public MProtectedRegion setFlags(Set<Flag> flags)
    {
        this.protectedChunkRegion.setFlags(flags);
        return this;
    }
}

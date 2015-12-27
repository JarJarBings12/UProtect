package ch.jarjarbings12.uprotect.protect.kernel.objects;

import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import org.bukkit.util.BlockVector;

import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 08.08.2015
 * © 2015 JarJarBings12
 */
public class MProtectedChunkRegion
{
    //TODO
    private final ProtectedChunkRegion protectedChunkRegion;

    public MProtectedChunkRegion(final ProtectedChunkRegion protectedChunkRegion)
    {
        this.protectedChunkRegion = protectedChunkRegion;
    }

    public Set<ProtectedChunk> getProtectedChunks()
    {
        return this.protectedChunkRegion.getProtectedChunks();
    }

    public MProtectedChunkRegion addProtectedChunk(BlockVector vector)
    {
        this.protectedChunkRegion.addProtectedChunk(new ProtectedChunk(vector.getBlockX(), vector.getBlockZ()));
        return this;
    }

    public MProtectedChunkRegion addProtectedChunk(Set<ProtectedChunk> protectedChunks)
    {
        protectedChunks.forEach(this.protectedChunkRegion::addProtectedChunk);
        return this;
    }

    public MProtectedChunkRegion removeProtectedChunks(BlockVector vector)
    {
        this.protectedChunkRegion.removeProtectedChunk(vector.getBlockX(), vector.getBlockZ());
        return this;
    }

    public MProtectedChunkRegion removeProtectedChunks(Set<BlockVector> vectors)
    {
        vectors.forEach(v -> this.protectedChunkRegion.removeProtectedChunk(v.getBlockX(), v.getBlockX()));
        return this;
    }

    public MProtectedChunkRegion setProtectedChunks(Set<ProtectedChunk> protectedChunks)
    {
        this.protectedChunkRegion.setProtectedChunks(protectedChunks);
        return this;
    }

    public boolean hasAccess(UUID uuid)
    {
        return (isMember(uuid) || isOwner(uuid));
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

    public MProtectedChunkRegion addOwner(UUID uuid)
    {
        if (!isOwner(uuid))
            this.protectedChunkRegion.addOwner(uuid);
        return this;
    }

    public MProtectedChunkRegion removeOwner(UUID uuid)
    {
        if (isOwner(uuid))
            this.protectedChunkRegion.getOwners().remove(uuid);
        return this;
    }

    public MProtectedChunkRegion setOwners(Set<UUID> owners)
    {
        this.protectedChunkRegion.setOwners(owners);
        return this;
    }

    public Set<UUID> getMembers()
    {
        return this.protectedChunkRegion.getMembers();
    }

    public MProtectedChunkRegion addMember(UUID uuid)
    {
        if (!isMember(uuid))
            this.protectedChunkRegion.addMember(uuid);
        return this;
    }

    public MProtectedChunkRegion removeMember(UUID uuid)
    {
        if (isMember(uuid))
            this.protectedChunkRegion.removeMember(uuid);

        return this;
    }

    public MProtectedChunkRegion setMembers(Set<UUID> members)
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

        for (Flag flag : this.protectedChunkRegion.getFlags())
            if (flag.getName().equals(name))
            {
                return true;
            }

        return false;
    }

    public MProtectedChunkRegion addFlag(Flag flag)
    {
        if (hasFlag(flag.getName()))
            removeFlag(flag.getName());

        this.protectedChunkRegion.addFlag(flag);
        return this;
    }

    public MProtectedChunkRegion removeFlag(String name)
    {
        this.protectedChunkRegion.getFlags().removeIf(f -> f.getName().equals(name));
        return this;
    }

    public MProtectedChunkRegion setFlags(Set<Flag> flags)
    {
        this.protectedChunkRegion.setFlags(flags);
        return this;
    }


    public ProtectedChunkRegion getSnapshot()
    {
        return this.protectedChunkRegion;
    }

    public void update()
    {
        getSnapshot().update();
    }
}
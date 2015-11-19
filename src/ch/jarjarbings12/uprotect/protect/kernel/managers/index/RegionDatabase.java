package ch.jarjarbings12.uprotect.protect.kernel.managers.index;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.objects.MProtectedChunkRegion;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect.protect.kernel.objects.RegionIndex;
import ch.jarjarbings12.uprotect.protect.kernel.services.ObjectService;
import ch.jarjarbings12.uprotect.protect.kernel.services.objects.DifferenceType;
import ch.jarjarbings12.uprotect.protect.kernel.services.objects.IndexDifference;
import ch.jarjarbings12.uprotect.protect.utils.ChunkVector;
import org.apache.commons.lang.Validate;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 29.08.2015
 * � 2015 JarJarBings12
 */
public class RegionDatabase implements RegionIndex
{
    private HashMap<UUID, IndexDifference> indexDifferences = new HashMap<>();
    private HashMap<UUID, ProtectedChunkRegion> index = new HashMap<>();
    private HashMap<UUID, String> uuidIDIndex = new HashMap<>();
    private HashMap<String, UUID> nameUUIDIndex = new HashMap<>();
    private HashMap<ChunkVector, UUID> chunkUUIDIndex = new HashMap<>();

    public RegionDatabase()
    {}

    public RegionDatabase(Set<byte[]> byteArrays)
    {
        ObjectService objectService = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getObjectService();

        byteArrays.forEach(bytes -> {
            ProtectedChunkRegion temp = (ProtectedChunkRegion) objectService.getSerializeService().deserializeByteArray(bytes);
            index.put(temp.getUUID(), temp);
            uuidIDIndex.put(temp.getUUID(), temp.getId());
            nameUUIDIndex.put(temp.getId(), temp.getUUID());
            temp.getProtectedChunks().forEach(pc -> {
                chunkUUIDIndex.put(new ChunkVector(pc.getX(), pc.getZ()), temp.getUUID());
            });
        });
    }

    public RegionDatabase(HashMap<UUID, ProtectedChunkRegion> protectedChunkRegions)
    {
        this.index = protectedChunkRegions;
        this.index.values().forEach(pcr -> {
            this.uuidIDIndex.put(pcr.getUUID(), pcr.getId());
            this.nameUUIDIndex.put(pcr.getId(), pcr.getUUID());
            pcr.getProtectedChunks().forEach(pc -> {
                this.chunkUUIDIndex.put(new ChunkVector(pc.getX(), pc.getZ()), pcr.getUUID());
            });
        });
    }

    @Override
    public boolean isProtected(Chunk chunk)
    {
        return chunkUUIDIndex.keySet().stream().filter(key -> key.Z == chunk.getZ() && key.X == chunk.getX()).findFirst().isPresent();
    }

    @Override
    public boolean isProtected(Location location)
    {
        return isProtected(location.getChunk());
    }

    @Override
    public boolean isProtected(Player player)
    {
        return isProtected(player.getLocation());
    }

    @Override
    public boolean existRegion(String id)
    {
        return nameUUIDIndex.containsKey(id);
    }

    @Override
    public boolean existRegion(UUID uuid)
    {
        return uuidIDIndex.containsKey(uuid);
    }

    @Override
    public ProtectedChunkRegion getRegion(UUID uuid)
    {
        Validate.notNull(uuid);
        return this.index.get(uuid);
    }

    @Override
    public ProtectedChunkRegion getRegion(String id)
    {
        return getRegion(getRegionUUID(id));
    }

    @Override
    public ProtectedChunkRegion getRegion(Chunk chunk)
    {
        return getRegion(getRegionUUID(chunk));
    }

    @Override
    public ProtectedChunkRegion getRegion(Location location)
    {
        return getRegion(getRegionUUID(location));
    }

    @Override
    public ProtectedChunkRegion getRegion(Player player)
    {
        return getRegion(getRegionUUID(player));
    }

    @Override
    public Set<ProtectedChunkRegion> getAllRegions()
    {
        return new HashSet<>(this.index.values());
    }

    @Override
    public void removeRegion(UUID uuid)
    {
        addDifference(getRegion(uuid), DifferenceType.REMOVED);
        this.nameUUIDIndex.remove(getRegionID(uuid));
        this.uuidIDIndex.remove(uuid);
        this.index.remove(uuid);
        this.chunkUUIDIndex.entrySet().removeIf(entry -> entry.getValue().equals(uuid));
    }

    @Override
    public void removeRegion(String id)
    {
        removeRegion(getRegionUUID(id));
    }

    @Override
    public void removeRegion(Chunk chunk)
    {
        removeRegion(getRegionUUID(chunk));
    }

    @Override
    public void removeRegion(Location location)
    {
        removeRegion(getRegionUUID(location));
    }

    @Override
    public void removeRegion(Player player)
    {
        removeRegion(getRegionUUID(player));
    }

    @Override
    public void setRegion(UUID uuid, ProtectedChunkRegion protection)
    {
        Validate.notNull(uuid);
        Validate.notNull(protection);
        if (!this.uuidIDIndex.containsKey(protection.getUUID()))
        {
            this.index.put(uuid, protection);
            this.uuidIDIndex.put(protection.getUUID(), protection.getId());
            this.nameUUIDIndex.put(protection.getId(), uuid);
            protection.getProtectedChunks().forEach(protectedChunk -> {
                chunkUUIDIndex.put(new ChunkVector(protectedChunk.getX(), protectedChunk.getZ()), protection.getUUID());
            });
        }

        if (this.indexDifferences.containsKey(protection.getUUID()))
        {
            this.indexDifferences.get(uuid).setRegion(protection);
            this.indexDifferences.get(uuid).setDifferenceType(DifferenceType.CHANGED);
            return;
        }
        this.indexDifferences.put(uuid, new IndexDifference(protection, DifferenceType.ADDED));
        return;
    }

    @Override
    public void setRegion(String id, ProtectedChunkRegion protection)
    {
        setRegion(getRegionUUID(id), protection);
    }

    @Override
    public void setRegion(Chunk chunk, ProtectedChunkRegion protection)
    {
        setRegion(getRegionUUID(chunk), protection);
    }

    @Override
    public void setRegion(Location location, ProtectedChunkRegion protection)
    {
        setRegion(getRegionUUID(location), protection);
    }

    @Override
    public void setRegion(Player player, ProtectedChunkRegion protection)
    {
        setRegion(getRegionUUID(player), protection);
    }

    @Override
    public UUID getRegionUUID(String id)
    {
        Validate.notNull(id);
        return nameUUIDIndex.get(id);
    }

    @Override
    public UUID getRegionUUID(Chunk chunk)
    {
        Validate.notNull(chunk);
        return chunkUUIDIndex.entrySet().stream().filter(entry -> entry.getKey().Z == chunk.getZ() && entry.getKey().X == chunk.getX()).findFirst().get().getValue();
    }

    @Override
    public UUID getRegionUUID(Location location)
    {
        return getRegionUUID(location.getChunk());
    }

    @Override
    public UUID getRegionUUID(Player player)
    {
        return getRegionUUID(player.getLocation());
    }

    @Override
    public String getRegionID(UUID uuid)
    {
        Validate.notNull(uuid);
        return uuidIDIndex.get(uuid);
    }

    @Override
    public String getRegionID(Chunk chunk)
    {
        Validate.notNull(chunk);
        return getRegionID(getRegionUUID(chunk));
    }

    @Override
    public String getRegionID(Location location)
    {
        return getRegionID(location.getChunk());
    }

    @Override
    public String getRegionID(Player player)
    {
        return getRegionID(player.getLocation());
    }

    @Override
    public void save(ProtectedChunkRegion protectedChunkRegion)
    {
        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getRegionDBService().save(protectedChunkRegion);
    }

    @Override
    public void save(UUID uuid)
    {
        save(getRegion(uuid));
    }

    @Override
    public void save(String id)
    {
        save(getRegion(id));
    }

    @Override
    public void save(Chunk chunk)
    {
        save(getRegion(chunk));
    }

    @Override
    public void save(Location location)
    {
        save(getRegion(location));
    }

    @Override
    public void save(Player player)
    {
        save(getRegion(player));
    }

    @Override
    public void saveAll()
    {
        this.index.values().forEach(pcr -> {
            save(pcr);
        });
    }

    @Override
    public MProtectedChunkRegion modifyRegion(UUID uuid)
    {
        return new MProtectedChunkRegion(getRegion(uuid));
    }

    @Override
    public MProtectedChunkRegion modifyRegion(String id)
    {
        Validate.notNull(id);
        return modifyRegion(getRegionUUID(id));
    }

    @Override
    public MProtectedChunkRegion modifyRegion(Chunk chunk)
    {
        Validate.notNull(chunk);
        return modifyRegion(getRegionUUID(chunk));
    }

    @Override
    public MProtectedChunkRegion modifyRegion(Location location)
    {
        return modifyRegion(location.getChunk());
    }

    @Override
    public MProtectedChunkRegion modifyRegion(Player player)
    {
        return modifyRegion(player.getLocation());
    }

    @Override
    public void addDifference(ProtectedChunkRegion region, DifferenceType differenceType)
    {
        this.indexDifferences.put(region.getUUID(), new IndexDifference(region, differenceType));
    }

    @Override
    public IndexDifference getDifferences(UUID key)
    {

        return this.indexDifferences.get(key);
    }

    @Override
    public Set<IndexDifference> getDifferences(UUID... keys)
    {
        Set<IndexDifference> temp = new HashSet<>();
        for (UUID key : keys)
            temp.add(getDifferences(key));
        return temp;
    }

    @Override
    public Set<IndexDifference> getAllDifferences()
    {
        return new HashSet<>(this.indexDifferences.values());
    }

    @Override
    public void clearDifferences()
    {
        this.indexDifferences.clear();
    }
}
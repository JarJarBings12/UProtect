package ch.jarjarbings12.uprotect3.protect.kernel.managers.index;

import ch.jarjarbings12.uprotect3.core.UProtect;
import ch.jarjarbings12.uprotect3.protect.kernel.objects.MProtectedRegion;
import ch.jarjarbings12.uprotect3.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect3.protect.kernel.objects.RegionIndex;
import ch.jarjarbings12.uprotect3.protect.kernel.services.ObjectService;
import ch.jarjarbings12.uprotect3.protect.kernel.services.objects.DifferenceType;
import ch.jarjarbings12.uprotect3.protect.kernel.services.objects.IndexDifference;
import ch.jarjarbings12.uprotect3.protect.utils.ChunkVector;
import org.apache.commons.lang.Validate;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author JarJarBings12
 * @creationDate 29.08.2015
 * @since 1.1.0.0
 */
public class RegionDatabase implements RegionIndex
{
    private HashMap<UUID, IndexDifference> indexDifferences = new HashMap<>();
    private HashMap<UUID, ProtectedChunkRegion> index = new HashMap<>();
    private HashMap<UUID, String> uuidIDIndex = new HashMap<>();
    private HashMap<String, UUID> nameUUIDIndex = new HashMap<>();
    private HashMap<ChunkVector, UUID> chunkUUIDIndex = new HashMap<>();

    public RegionDatabase(Set<byte[]> byteArrays)
    {
        ObjectService objectService = UProtect.getUProtect().getUProtectAPI().getObjectService();

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
        return chunkUUIDIndex.containsKey(new ChunkVector(chunk.getX(), chunk.getZ()));
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
    public ProtectedChunkRegion
    getRegion(Location location)
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
    public Set<IndexDifference> getAllChangedRegions()
    {
        return new HashSet<>(this.indexDifferences.values());
    }

    @Override
    public void setRegion(UUID uuid, ProtectedChunkRegion protection)
    {
        Validate.notNull(uuid);
        Validate.notNull(protection);
        if (!this.indexDifferences.containsKey(uuid))
        {
            this.index.put(uuid, protection);
            return;
        }
        this.indexDifferences.get(uuid).setRegion(protection);
        this.indexDifferences.get(uuid).setDifferenceType(DifferenceType.SET);
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
        return chunkUUIDIndex.get(new ChunkVector(chunk.getX(), chunk.getZ()));
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
       // UProtect.getUProtect().getUProtectAPI().getDatabaseService().getRegionDatabase().saveRegion(protectedChunkRegion);
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
    public MProtectedRegion modifyRegion(UUID uuid)
    {
        return new MProtectedRegion(getRegion(uuid));
    }

    @Override
    public MProtectedRegion modifyRegion(String id)
    {
        Validate.notNull(id);
        return modifyRegion(getRegionUUID(id));
    }

    @Override
    public MProtectedRegion modifyRegion(Chunk chunk)
    {
        Validate.notNull(chunk);
        return modifyRegion(getRegionUUID(chunk));
    }

    @Override
    public MProtectedRegion modifyRegion(Location location)
    {
        return modifyRegion(location.getChunk());
    }

    @Override
    public MProtectedRegion modifyRegion(Player player)
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
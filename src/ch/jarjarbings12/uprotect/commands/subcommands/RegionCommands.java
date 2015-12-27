package ch.jarjarbings12.uprotect.commands.subcommands;

import ch.jarjarbings12.uprotect.commands.UCommand;
import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.managers.index.RegionDatabase;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunk;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.UserDBServices;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * @author JarJarBings12
 * @creationDate 29.10.2015
 * © 2015 JarJarBings12
 */
public class RegionCommands
{
    /**
     * Create new {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void create(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);

        if (regionDatabase.isProtected(location))
        {
            sender.sendMessage("[UProtect] This chunk is already protected.");
            return;
        }

        if (args.length == 2)
        {
            if (regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] This region ID already exists!");
                return;
            }
            Set<ProtectedChunk> chunks = new HashSet<>();
            chunks.add(ProtectedChunk.craftProtectedChunk(location));
            ProtectedChunkRegion protectedChunkRegion = new ProtectedChunkRegion(args[1], location.getWorld().getUID(), chunks);
            regionDatabase.setRegion(protectedChunkRegion.getUUID(), protectedChunkRegion);
            sender.sendMessage("[UProtect] Created new region with id: " + args[1]);
        }
        else if (args.length == 3)
        {
            sender.sendMessage("[UProtect] /chunkprotect create <region id>");
            sender.sendMessage("[UProtect] - Create a region with no owner/s or member/s.");
            sender.sendMessage("[UProtect] /chunkprotect create <region id> [o: <owners>...]{Optional}");
            sender.sendMessage("[UProtect] - Create a region with defined owner/s.");
            sender.sendMessage("[UProtect] /chunkprotect create <region id> [m: <members>...]{Optional}");
            sender.sendMessage("[UProtect] - Create a region with defined member/s.");
            sender.sendMessage("[UProtect] /chunkprotect create <region id> [o: <owners>...]{Optional} [m: <members>...]{Optional}");
            sender.sendMessage("[UProtect] - Create a region with defined owner/s and member/s.");
        }
        else if (args.length <= 4)
        {
            int memberIndex = -1;
            int ownerIndex = -1;

            for (int i = 0; i < args.length; i++)
            {
                if (args[i].equalsIgnoreCase("m:"))
                    memberIndex = i;
                else if (args[i].equalsIgnoreCase("o:"))
                    ownerIndex = i;
            }

            if (memberIndex < 0 && ownerIndex < 0)
            {
                sender.sendMessage("[UProtect] No user type definition found");
                sender.sendMessage("[UProtect] Please type: m: <member/s> to add member/s");
                sender.sendMessage("[UProtect] or type: o: <owner/s> to add owner/s");
                return;
            }

            UserDBServices userDBServices = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService();

            Set<ProtectedChunk> chunks = new HashSet<>();               //FINAL REGION FIELD
            chunks.add(ProtectedChunk.craftProtectedChunk(location));

            Set<UUID> members = new HashSet<>();                        //FINAL REGION FIELD
            Set<UUID> owners = new HashSet<>();                         //FINAL REGION FIELD

            if (memberIndex > 0)
            {
                for (int i = memberIndex; i < args.length; i++)
                {
                    if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
                    {
                        if (!userDBServices.getPlayerUUIDService().isUUIDRegistered(UUID.fromString(args[i])))
                            sender.sendMessage("[UProtect] Unknown User UUID: " + args[i]);
                        else
                            members.add(UUID.fromString(args[i]));
                    }
                    else
                    {
                        if (!userDBServices.getPlayerUUIDService().isNameRegistered(args[i]))
                            sender.sendMessage("UProtect] Unknown User Name: " + args[i]);
                        else
                            members.add(userDBServices.getPlayerUUIDService().getUUIDFor(args[i]));
                    }

                }
            }

            if (ownerIndex > 0)
            {
                for (int i = ownerIndex; i < args.length; i++)
                {
                    if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
                    {
                        if (!userDBServices.getPlayerUUIDService().isUUIDRegistered(UUID.fromString(args[i])))
                            sender.sendMessage("[UProtect] Unknown User UUID: " + args[i]);
                        else
                            owners.add(UUID.fromString(args[i]));
                    }
                    else
                    {
                        if (!userDBServices.getPlayerUUIDService().isNameRegistered(args[i]))
                            sender.sendMessage("UProtect] Unknown User Name: " + args[i]);
                        else
                            owners.add(userDBServices.getPlayerUUIDService().getUUIDFor(args[i]));
                    }

                }
            }
            ProtectedChunkRegion protectedChunkRegion = new ProtectedChunkRegion(args[1], args[1], location.getWorld().getUID(), chunks, owners, members, new HashSet<>());
            regionDatabase.setRegion(protectedChunkRegion.getUUID(), protectedChunkRegion);
            sender.sendMessage("[UProtect] Created new region with id: " + args[1]);
        }
        else
        {
            sender.sendMessage("[UProtect] /chunkprotect create <region id>");
            sender.sendMessage("[UProtect] - Create a region with no owner/s or member/s.");
            sender.sendMessage("[UProtect] /chunkprotect create <region id> [o: <owners>...]{Optional}");
            sender.sendMessage("[UProtect] - Create a region with defined owner/s.");
            sender.sendMessage("[UProtect] /chunkprotect create <region id> [m: <members>...]{Optional}");
            sender.sendMessage("[UProtect] - Create a region with defined member/s.");
            sender.sendMessage("[UProtect] /chunkprotect create <region id> [o: <owners>...]{Optional} [m: <members>...]{Optional}");
            sender.sendMessage("[UProtect] - Create a region with defined owner/s and member/s.");
        }

    }

    /**
     * Delete a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void delete(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);

        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            UUID uuid = UUID.fromString(args[1]);
            if (regionDatabase.existRegion(uuid))
            {
                regionDatabase.removeRegion(uuid);
                sender.sendMessage("[UProtect] Deleted region: " + uuid.toString());
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region uuid: " + uuid.toString());
            }
        }
        else
        {
            if (regionDatabase.existRegion(args[1]))
            {
                regionDatabase.removeRegion(args[1]);
                sender.sendMessage("[UProtect] Deleted region: " + args[1]);
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region id: " + args[1]);
            }
        }
    }

    /**
     * Append a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunk} to a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void append(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);

        if (regionDatabase.isProtected(location))
        {
            sender.sendMessage("[UProtect] This chunk is already protected.");
            return;
        }

        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            if (!regionDatabase.existRegion(UUID.fromString(args[1])))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
            ProtectedChunkRegion region = regionDatabase.getRegion(UUID.fromString(args[1]));
            region.addProtectedChunk(new ProtectedChunk(location.getChunk().getX(), location.getChunk().getZ()));
            sender.sendMessage(String.format("[UProtect] Appended chunk(%d/%d) to the region %s", location.getChunk().getX(), location.getChunk().getZ(), region.getId()));
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region ID!");
                return;
            }
            ProtectedChunkRegion region = regionDatabase.getRegion(args[1]);
            region.addProtectedChunk(new ProtectedChunk(location.getChunk().getX(), location.getChunk().getZ()));
            sender.sendMessage(String.format("[UProtect] Appended chunk(%d/%d) to the region %s", location.getChunk().getX(), location.getChunk().getZ(), region.getId()));
        }
    }

    /**
     * Remove a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunk} from a {@link ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion}.
     *
     * @param sender
     * @param label
     * @param args
     */
    public void remove(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);

        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            if (regionDatabase.existRegion(UUID.fromString(args[1])))
            {
                ProtectedChunkRegion region = regionDatabase.getRegion(UUID.fromString(args[1]));
                if (region.getProtectedChunks().stream().filter(protectedChunk -> protectedChunk.getX() == location.getChunk().getX() && protectedChunk.getZ() == location.getChunk().getZ()).count() > 0)
                {
                    region.removeProtectedChunk(location.getChunk().getX(), location.getChunk().getZ());
                }
                else
                {
                    sender.sendMessage("[UProtect] This chunk isn't a part of this region!");
                }
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
            }
        }
        else
        {
            if (regionDatabase.existRegion(args[1]))
            {
                ProtectedChunkRegion region = regionDatabase.getRegion(args[1]);

                if (region.getProtectedChunks().stream().filter(protectedChunk -> protectedChunk.getX() == location.getChunk().getX() && protectedChunk.getZ() == location.getChunk().getZ()).count() > 0)
                {
                    region.removeProtectedChunk(location.getChunk().getX(), location.getChunk().getZ());
                }
                else
                {
                    sender.sendMessage("[UProtect] This chunk isn't a part of this region!");
                }
            }
            else
            {
                sender.sendMessage("[UProtect] Unknown region id!");
            }
        }
    }

    public void list(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);

        List<ProtectedChunkRegion> protectedChunkRegions = new ArrayList<>(regionDatabase.getAllRegions());

        int page = args.length > 1 ? Integer.valueOf(args[1]) : 1;
        int maxPage = (int) Math.floor((protectedChunkRegions.size() + 1) / 10);
        maxPage = maxPage > 1 ? maxPage : 1;

        if (args.length == 1)
        {
            sender.sendMessage("[UProtect] ID / Name");
            if (regionDatabase.getAllRegions().size() > 10)
            {
                for (int i = 0; i < 10; i++)
                    sender.sendMessage(String.format("%s (%s)", protectedChunkRegions.get(i).getId(), protectedChunkRegions.get(i).getName()));
                sender.sendMessage(String.format("[UProtect] List page: %d/%d", page, maxPage));
            }
            else
            {
                regionDatabase.getAllRegions().forEach(region -> sender.sendMessage(String.format("%s (%s)", region.getId(), region.getName())));
                sender.sendMessage(String.format("[UProtect] List page: %d/%d", page, maxPage));
            }
        }
        else if (args.length == 2)
        {
            if (page > protectedChunkRegions.size() / 10)
            {
                sender.sendMessage(String.format("[UProtect] Can't display page %d/%d", page, maxPage));
                return;
            }

            sender.sendMessage("[UProtect] ID / Name");
            for (int i = page; page < page + 10; i++)
            {
                if (i == protectedChunkRegions.size())
                {
                    break;
                }
                sender.sendMessage(String.format("%s (%s)", protectedChunkRegions.get(i).getId(), protectedChunkRegions.get(i).getName()));
            }
            sender.sendMessage(String.format("[UProtect] List page: %d/%d", page, maxPage));
        }
    }

    //Flag Commands

    public void addFlag(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        ProtectedChunkRegion region;
        UUID currentUUID;


        /* Get region */
        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            currentUUID = UUID.fromString(args[1]);
            if (!regionDatabase.existRegion(currentUUID))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region id!");
                return;
            }
            currentUUID = regionDatabase.getRegionUUID(args[1]);
        }
        region = regionDatabase.getRegion(currentUUID);

        /* Permissions check */
        if (!region.isOwner(((Player) sender).getUniqueId()) && !sender.hasPermission("uprotect.region.bypass"))
        {
            sender.sendMessage("[UProtect] You don't have access to this region!");
            return;
        }

        UUID flagID = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlagID(args[2]);

        if (flagID == null)
        {
            sender.sendMessage("[UProtect] Unknown flag!");
            showFlags(sender, label, new String[]{"flags"});
            return;
        }

        /* Parse arguments */
        Flag flag = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlag(flagID);
        System.out.println(flag == null);
        flag.applyForRegion(region, flag, new UCommand(sender, label, args));
    }


    public void removeFlag(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        ProtectedChunkRegion region;
        UUID currentUUID;


        /* Get region */
        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            currentUUID = UUID.fromString(args[1]);
            if (!regionDatabase.existRegion(currentUUID))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region id!");
                return;
            }
            currentUUID = regionDatabase.getRegionUUID(args[1]);
        }
        region = regionDatabase.getRegion(currentUUID);

        /* Permissions check */
        if (!region.isOwner(((Player) sender).getUniqueId()) && !sender.hasPermission("uprotect.region.bypass"))
        {
            sender.sendMessage("[UProtect] You don't have access to this region!");
            return;
        }

        UUID flagID;
        String flagName = "Unknown";
        if (args[2].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            flagID = UUID.fromString(args[2]);
            if (UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlag(flagID) != null)
                flagName = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlag(flagID).getName();

            if (region.hasFlag(flagID))
            {
                region.removeFlag(flagID);
                sender.sendMessage(String.format("[UProtect] Removed flag %s (%s) form this region.", flagID, flagName));
                return;
            }
            sender.sendMessage("[UProtect] this flag isn't applied to the region.");
        }
        else
        {
            flagID = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getFlagID(args[2]);
            if (flagID == null)
            {
                sender.sendMessage("[UProtect] Unknown flag!");
                showFlags(sender, label, new String[] {"flags"});
            }
            flagName = args[2];

            region.removeFlag(flagID);
            sender.sendMessage(String.format("[UProtect] Removed flag %s (%s) form this region.", flagName, flagID));
        }
    }

    public void showFlags(CommandSender sender, String label, String[] args)
    {

        if (args.length == 1)
        {
            StringBuilder stringBuilder = new StringBuilder();
            UProtect.getUProtect().getUProtectAPI().getServiceCenter().getFlagService().getAllFlags().values().forEach(value -> stringBuilder.append(value.getName()).append(", "));
            sender.sendMessage(stringBuilder.toString().substring(0, stringBuilder.length() - 2));
        }
        else if (args.length == 2)
        {
            StringBuilder stringBuilder = new StringBuilder();

            RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(((Player) sender).getWorld());
            ProtectedChunkRegion region;
            UUID currentUUID;

            /* Get region */
            if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
            {
                currentUUID = UUID.fromString(args[1]);
                if (!regionDatabase.existRegion(currentUUID))
                {
                    sender.sendMessage("[UProtect] Unknown region uuid!");
                    return;
                }
            }
            else
            {
                if (!regionDatabase.existRegion(args[1]))
                {
                    sender.sendMessage("[UProtect] Unknown region id!");
                    return;
                }
                currentUUID = regionDatabase.getRegionUUID(args[1]);
            }
            region = regionDatabase.getRegion(currentUUID);

            //Build message
            if (region.getFlags().isEmpty())
            {
                stringBuilder.append("This Region has no flags!  ");
            }
            region.getFlags().forEach(flag -> stringBuilder.append(flag.getName()).append(", "));

            sender.sendMessage(stringBuilder.toString().substring(0, stringBuilder.length() - 2));
        }
    }

    // Access commands

    public void addMember(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        ProtectedChunkRegion region;
        UUID currentUUID;


        /* Get region */
        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            currentUUID = UUID.fromString(args[1]);
            if (!regionDatabase.existRegion(currentUUID))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region id!");
                return;
            }
            currentUUID = regionDatabase.getRegionUUID(args[1]);
        }
        region = regionDatabase.getRegion(currentUUID);

        /* Permissions check */
        if (!region.isOwner(((Player) sender).getUniqueId()) && !sender.hasPermission("uprotect.region.bypass"))
        {
            sender.sendMessage("[UProtect] You don't have access to this region!");
            return;
        }

        /* Parse arguments */
        UserDBServices userDBServices = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService();
        if (args.length == 3)
        {
            String message = "[UProtect] Added %s to %s";
            String name = args[2];
            if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
            {
                currentUUID = UUID.fromString(name);
                if (!userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                {
                    sender.sendMessage("[UProtect] Unknown player uuid!");
                    return;
                }
                name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
            }
            else
            {
                if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                {
                    sender.sendMessage("[UProtect] Unknown player name!");
                    return;
                }
                currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
            }
            region.addMember(currentUUID);
            sender.sendMessage(String.format(message, name, region.getId()));
        }
        else
        {
            StringBuilder messagePartOne = new StringBuilder();
            String name;
            for (int i = 2; i < args.length; i++)
            {
                name = args[i];
                if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
                {
                    currentUUID = UUID.fromString(name);
                    if (!userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                    {
                        sender.sendMessage(String.format("[UProtect] Unknown player uuid (%s)!", currentUUID.toString()));
                        continue;
                    }
                    name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
                }
                else
                {
                    if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                    {
                        sender.sendMessage(String.format("[UProtect] Unknown player name (%s)!", name));
                        continue;
                    }
                    currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
                }
                messagePartOne.append(", ").append(name);
                region.addMember(currentUUID);
                sender.sendMessage(String.format("Added %s to %s", messagePartOne.substring(2, messagePartOne.length()), region.getId()));
                return;
            }
        }
    }

    public void removeMember(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player)sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);



        ProtectedChunkRegion region;
        UUID currentUUID;


        /* Get region */
        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            currentUUID = UUID.fromString(args[1]);
            if (!regionDatabase.existRegion(currentUUID))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region id!");
                return;
            }
            currentUUID = regionDatabase.getRegionUUID(args[1]);
        }
        region = regionDatabase.getRegion(currentUUID);

        /* Permissions check */
        if (!region.isOwner(((Player) sender).getUniqueId()) && !sender.hasPermission("uprotect.region.bypass"))
        {
            sender.sendMessage("[UProtect] You don't have access to this region!");
            return;
        }

        /* Parse arguments */
        UserDBServices userDBServices = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService();
        if (args.length == 3)
        {
            String message = "[UProtect] Added %s to %s";
            String name = args[2];
            if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
            {
                currentUUID = UUID.fromString(name);
                if (userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                {
                    name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
                }
            }
            else
            {
                if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                {
                    sender.sendMessage("[UProtect] Unknown player name!");
                    return;
                }
                currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
            }
            region.removeMember(currentUUID);
            sender.sendMessage(String.format(message, name, region.getId()));
        }
        else
        {
            StringBuilder messagePartOne = new StringBuilder();
            String name;
            for (int i = 2; i < args.length; i++)
            {
                name = args[i];
                if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
                {
                    currentUUID = UUID.fromString(name);
                    if (userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                        name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
                }
                else
                {
                    if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                    {
                        sender.sendMessage(String.format("[UProtect] Unknown player name (%s)!", name));
                        continue;
                    }
                    currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
                }
                messagePartOne.append(", ").append(name);
                region.removeMember(currentUUID);
                return;
            }
            sender.sendMessage(String.format("Added %s to %s", messagePartOne.substring(2, messagePartOne.length()), region.getId()));
        }
    }

    public void addOwner(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player) sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        ProtectedChunkRegion region;
        UUID currentUUID ;


        /* Get region */
        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            currentUUID = UUID.fromString(args[1]);
            if (!regionDatabase.existRegion(currentUUID))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region id!");
                return;
            }
            currentUUID = regionDatabase.getRegionUUID(args[1]);
        }
        region = regionDatabase.getRegion(currentUUID);

        /* Permissions check */
        if (!region.isOwner(((Player) sender).getUniqueId()) && !sender.hasPermission("uprotect.region.bypass"))
        {
            sender.sendMessage("[UProtect] You don't have access to this region!");
            return;
        }

        /* Parse arguments */
        UserDBServices userDBServices = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService();
        if (args.length == 3)
        {
            String message = "[UProtect] Added %s to %s";
            String name = args[2];
            if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
            {
                currentUUID = UUID.fromString(name);
                if (!userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                {
                    sender.sendMessage("[UProtect] Unknown player uuid!");
                    return;
                }
                name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
            }
            else
            {
                if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                {
                    sender.sendMessage("[UProtect] Unknown player name!");
                    return;
                }
                currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
            }

            region.addOwner(currentUUID);
            sender.sendMessage(String.format(message, name, region.getId()));
        }
        else
        {
            StringBuilder messagePartOne = new StringBuilder();
            String name;
            for (int i = 2; i < args.length; i++)
            {
                name = args[i];
                if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
                {
                    currentUUID = UUID.fromString(name);
                    if (!userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                    {
                        sender.sendMessage(String.format("[UProtect] Unknown player uuid (%s)!", currentUUID.toString()));
                        continue;
                    }
                    name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
                }
                else
                {
                    if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                    {
                        sender.sendMessage(String.format("[UProtect] Unknown player name (%s)!", name));
                        continue;
                    }
                    currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
                }
                messagePartOne.append(", ").append(name);
                region.addOwner(currentUUID);
                sender.sendMessage(String.format("Added %s to %s", messagePartOne.substring(2, messagePartOne.length()), region.getId()));
                return;
            }
        }
    }

    public void removeOwner(CommandSender sender, String label, String[] args)
    {
        Location location = ((Player)sender).getLocation();
        RegionDatabase regionDatabase = UProtect.getUProtect().getUProtectAPI().getRegionManager().getRegionDatabase(location);
        ProtectedChunkRegion region;
        UUID currentUUID;


        /* Get region */
        if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
        {
            currentUUID = UUID.fromString(args[1]);
            if (!regionDatabase.existRegion(currentUUID))
            {
                sender.sendMessage("[UProtect] Unknown region uuid!");
                return;
            }
        }
        else
        {
            if (!regionDatabase.existRegion(args[1]))
            {
                sender.sendMessage("[UProtect] Unknown region id!");
                return;
            }
            currentUUID = regionDatabase.getRegionUUID(args[1]);
        }
        region = regionDatabase.getRegion(currentUUID);

        /* Permissions check */
        if (!region.isOwner(((Player) sender).getUniqueId()) && !sender.hasPermission("uprotect.region.bypass"))
        {
            sender.sendMessage("[UProtect] You don't have access to this region!");
            return;
        }

        /* Parse arguments */
        UserDBServices userDBServices = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService();
        if (args.length == 3)
        {
            String message = "[UProtect] Added %s to %s";
            String name = args[2];
            if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
            {
                currentUUID = UUID.fromString(name);
                if (userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                {
                    name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
                }
            }
            else
            {
                if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                {
                    sender.sendMessage("[UProtect] Unknown player name!");
                    return;
                }
                currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
            }
            region.removeOwner(currentUUID);
            sender.sendMessage(String.format(message, name, region.getId()));
        }
        else
        {
            StringBuilder messagePartOne = new StringBuilder();
            String name;
            for (int i = 2; i < args.length; i++)
            {
                name = args[i];
                if (name.matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
                {
                    currentUUID = UUID.fromString(name);
                    if (userDBServices.getPlayerUUIDService().isUUIDRegistered(currentUUID))
                        name = userDBServices.getPlayerUUIDService().getUserNameFor(currentUUID);
                }
                else
                {
                    if (!userDBServices.getPlayerUUIDService().isNameRegistered(name))
                    {
                        sender.sendMessage(String.format("[UProtect] Unknown player name (%s)!", name));
                        continue;
                    }
                    currentUUID = userDBServices.getPlayerUUIDService().getUUIDFor(name);
                }
                messagePartOne.append(", ").append(name);
                region.removeOwner(currentUUID);
                return;
            }
            sender.sendMessage(String.format("Added %s to %s", messagePartOne.substring(2, messagePartOne.length()), region.getId()));
        }
    }
}

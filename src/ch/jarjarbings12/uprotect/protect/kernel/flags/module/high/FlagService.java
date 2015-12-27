package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.Events;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.FlagClassLoader;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.FlagInfo;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini.IniGroup;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini.IniReader;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.support.FlagExtension;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015
 * © 2015 JarJarBings12
 */
public class FlagService
{
    private final FlagClassLoader flagClassLoader = new FlagClassLoader();
    private final HashMap<UUID, Flag> flags = new HashMap<>(); //FlagID -> Flag
    private final HashMap<String, UUID> flagNameUUID = new HashMap<>(); //Flag tag -> FlagID
    private final HashMap<Integer, Set<UUID>> usedEvents = new HashMap<>(); //EventID -> Flags who use this event
    private final HashMap<String, FlagInfo> nameFlagInfo = new HashMap<>();
    private final IniReader props = new IniReader();

    public FlagService()
    {
    }

    public Flag getFlag(UUID flagID)
    {
        return this.flags.get(flagID);
    }

    public UUID getFlagID(String flagName)
    {
        return flagNameUUID.get(flagName);
    }

    public Set<UUID> getFlagIDsForEventID(int i)
    {
        return this.usedEvents.get(i);
    }

    public FlagInfo getFlagInfo(String name)
    {
        if (nameFlagInfo.containsKey(name))
            return nameFlagInfo.get(name);

        if (props.existGroup(name))
        {
            IniGroup group = props.getGroup(name);
            nameFlagInfo.put(name, new FlagInfo(group.getString("FILE"), group.getString("CLASSPATH"), name, group.getString("DESCRIPTION")));
            return nameFlagInfo.get(name);
        }
        else
        {
            return null;
        }
    }

    public FlagInfo getFlagInfo(UUID uuid)
    {
        return getFlagInfo(flagNameUUID.entrySet().stream().filter(entry -> entry.getValue().equals(uuid)).findFirst().get().getKey());
    }

    public HashMap<UUID, Flag> getAllFlags()
    {
        return this.flags;
    }

    public void setup()
    {
        props.load(new File("plugins/UProtect/extensions/flags/flagconfig.ini"));
        final Set<String> keys = new HashSet<>();
        props.getKeys().forEach(k -> {
            if (!k.equals("base"))
                keys.add(k);
        });

        props.getGroups(keys).forEach(g -> {
            FlagExtension flag = flagClassLoader.loadModule(g.getString("FILE"), g.getString("CLASSPATH"));
            flag.load();
        });
    }

    public void register(UUID flagID, Flag flag, int... requiredEvents)
    {
        flags.put(flagID, flag);
        flagNameUUID.put(flag.getName(), flagID);

        if (requiredEvents != null)
            for (int i : requiredEvents)
            {
                if (!Events.existEvent(i))
                {
                    System.out.printf("[UProtect][FMS][->] %s try to register a unknown event id(%d)", flagID.toString(), i);
                    continue;
                }

                if (!usedEvents.containsKey(i))
                    usedEvents.put(i, new HashSet<>());

                usedEvents.get(i).add(flagID);
                if (!UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().hasSubscribers(i))
                {
                    FlagCallerService temp = new FlagCallerService(i);
                    UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(i, temp);
                }
            }
    }

    public void unregister(UUID flagID)
    {
        flags.remove(flagID);
    }

    public void loadedFlags()
    {
        System.out.println(String.format("[UProtect][FMS][->] The flag system use %d/%d Events.", usedEvents.size(), Events.values().length));
        System.out.println("[UProtect][FMS][->] Flags:");
        flags.values().forEach(f -> System.out.println(String.format("[UProtect][FMS][->] - %s - %s - %s", f.getName(), f.getFlagID(), f.getPriority().toString())));
    }
}

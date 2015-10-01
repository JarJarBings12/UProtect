package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.FlagInfo;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.FlagUtils;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini.IniReader;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.support.FlagExtension;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.Flag;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.FlagClassLoader;

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
    private FlagClassLoader flagClassLoader = new FlagClassLoader();
    private FlagUtils flagUtils = new FlagUtils();
    private HashMap<UUID, Flag> flags = new HashMap<>(); //FlagID -> Flag
    private HashMap<String, UUID> flagTagUUID = new HashMap<>(); //Flag tag -> FlagID
    private HashMap<Integer, Set<UUID>> usedEvents = new HashMap<>(); //EventID -> Flags who use this event
    private HashMap<UUID, FlagInfo> flagInfos = new HashMap<>();
    private IniReader props = new IniReader();

    public FlagService()
    {
        usedEvents.put(1, new HashSet<>());
    }

    public Flag getFlag(UUID flagID)
    {
        return this.flags.get(flagID);
    }

    public Set<UUID> getFlagIDsForEventID(int i)
    {
        return this.usedEvents.get(i);
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
            FlagExtension flag = flagClassLoader.loadModule(g.get("FILE"), g.get("CLASSPATH"));
            flag.load();
        });
        flags.values().forEach(f -> {
            System.out.println(flagUtils.getTechnicalInformation(f));
        });

    }

    public void register(UUID flagID, Flag flag, int... requiredEvents)
    {
        flags.put(flagID, flag);
        flagTagUUID.put(flag.getFlagTag(), flagID);

        if (requiredEvents != null)
            for (int i : requiredEvents)
                if (usedEvents.containsKey(i))
                {
                    usedEvents.get(i).add(flagID);
                    if (UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().hasSubscribers() || UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().getSubscribersFor(i).isEmpty() || UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().getSubscribersFor(i).stream().filter(s -> s instanceof FlagCallerService).findFirst().get() == null)
                    {
                        FlagCallerService temp = new FlagCallerService(i);
                        UProtect.getUProtect().getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(i, temp);
                    }
                }
                else
                {System.out.printf("[UProtect][FMS][->] %s try to register a unknown event id(%d)", flagID.toString(), i);}
    }

    public void unregister(UUID flagID)
    {
        flags.remove(flagID);
    }

    private void loadFlags()
    {
        System.out.println("[UProtect][FMS][->] Flags:");
        flags.values().forEach(f -> {
            System.out.printf("[UProtect][FMS][->] - %s - %s - %s", f.getName(), f.getFlagTag(), f.getFlagID());
        });
    }


}

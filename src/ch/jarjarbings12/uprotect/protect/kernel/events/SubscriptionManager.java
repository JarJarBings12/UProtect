package ch.jarjarbings12.uprotect.protect.kernel.events;

import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.Events;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.AFlagEventSupport;
import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public class SubscriptionManager
{

    private HashMap<Integer, Set<AbstractEventSubscription>> subscriptions = new HashMap<>();
    private HashMap<UUID, Integer> subscriptionsByUUID = new HashMap<>();

    public SubscriptionManager()
    {
    }

    public void subscribe(Events event, AbstractEventSubscription abstractEventSubscription)
    {
        subscribe(event.getID(), abstractEventSubscription);
    }

    public void subscribe(int eventID, AbstractEventSubscription abstractEventSubscription)
    {
        try
        {
            if (!(abstractEventSubscription instanceof AFlagEventSupport))
                System.out.println(String.format("[UProtect][ESM][->] Register event for %s subscriber id %s", Events.getNameByID(eventID), abstractEventSubscription.getSubscriberID().toString()));
        }
        catch (NotInUseException e)
        { e.printStackTrace(); }

        if (!subscriptions.containsKey(eventID))
        {
            Set<AbstractEventSubscription> temp = new HashSet<>();
            subscriptions.put(eventID, temp);
        }
        subscriptions.get(eventID).add(abstractEventSubscription);

        try
        {
            if (!(abstractEventSubscription instanceof AFlagEventSupport))
                abstractEventSubscription.onSubscribe();
        } catch (NotInUseException e)
        { e.printStackTrace(); }
        return;
    }

    public void unsubscribe(Events event, UUID uuid)
    {
        unsubscribe(event.getID(), uuid);
    }


    public void unsubscribe(Events event, Set<UUID> uuids)
    {
        unsubscribe(event.getID(), uuids);
    }

    public void unsubscribe(int eventID, Set<UUID> uuids)
    {
        uuids.forEach(uuid -> {
            unsubscribe(eventID, uuid);
        });
    }

    public void unsubscribe(int eventID, UUID uuid)
    {
        subscriptions.get(eventID).removeIf(sub -> {
            try
            {
                if (sub.getSubscriberID() == uuid)
                {
                    sub.onUnsubscribe();
                    return true;
                }
                return false;
            }
            catch (NotInUseException e)
            {
                e.printStackTrace();
            }
            return false;
        });
        return;
    }

    public void callSubscribers(int eventID, Event event)
    {
        Set<AbstractEventSubscription> temp = getSubscribersFor(eventID);
        if (!temp.isEmpty())
            temp.forEach(e -> e.call(event));
    }

    public boolean hasSubscribers()
    {
        return !subscriptions.isEmpty();
    }

    public Set<AbstractEventSubscription> getSubscribersFor(int eventID)
    {
        if (!subscriptions.containsKey(eventID) && Events.getEventByID(eventID) != null)
            subscriptions.put(eventID, new HashSet<>());
        return subscriptions.get(eventID);
    }
}
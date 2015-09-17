package ch.jarjarbings12.uprotect3.protect.kernel.events;

import ch.jarjarbings12.uprotect3.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect3.protect.kernel.events.module.low.Events;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by tobias on 15.09.2015.
 */
public class SubscriptionManager
{

    private HashMap<Integer, Set<AbstractEventSubscription>> subscriptions = new HashMap<>();
    private HashMap<UUID, Integer> subscriptionsByUUID = new HashMap<>();

    public void subscribe(Events event, AbstractEventSubscription abstractEventSubscription)
    {
        subscribe(event.getID(), abstractEventSubscription);
    }

    public void subscribe(int eventID, AbstractEventSubscription abstractEventSubscription)
    {
        System.out.println(String.format("[UProtect][ESM][->] Register event for %s subscriber id %s", Events.getNameByID(eventID), abstractEventSubscription.getSubscriberID().toString()));
        if (subscriptions.containsKey(eventID))
        {
            subscriptions.get(eventID).add(abstractEventSubscription);
            return;
        }
        Set<AbstractEventSubscription> temp = new HashSet<>();
        temp.add(abstractEventSubscription);
        subscriptions.put(eventID, temp);
        return;
    }

    public void unsubscribe(Events event, UUID uuid)
    {
        unsubscribe(event.getID(), uuid);
    }

    public void unsubscribe(int eventID, UUID uuid)
    {
        subscriptions.get(eventID).removeIf(sub -> sub.getSubscriberID() == uuid);
        return;
    }

    public void unsubscribe(Events event, Set<UUID> uuids)
    {
        unsubscribe(event.getID(), uuids);
    }

    public void unsubscribe(int eventID, Set<UUID> uuids)
    {
        uuids.forEach(uuid -> subscriptions.get(eventID).removeIf(sub -> sub.getSubscriberID() == uuid));
    }

    public void callSubscribers(int eventID, Event event)
    {
        subscriptions.get(eventID).forEach(e -> e.call(event));
    }
}

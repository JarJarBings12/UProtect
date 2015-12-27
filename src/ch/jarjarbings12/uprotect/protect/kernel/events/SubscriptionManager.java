package ch.jarjarbings12.uprotect.protect.kernel.events;

import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.AbstractEventSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.low.Events;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.AFlagEventSupport;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

import java.util.*;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public class SubscriptionManager
{
    private final HashMap<Integer, Set<AbstractEventSubscription>> subscriptions = new HashMap<>();

    public SubscriptionManager()
    {
    }

    public void subscribe(Events event, AbstractEventSubscription abstractEventSubscription)
    {
        subscribe(event.getID(), abstractEventSubscription);
    }

    public void subscribe(int eventID, AbstractEventSubscription abstractEventSubscription)
    {
        if (!(abstractEventSubscription instanceof AFlagEventSupport))
            System.out.println(String.format("[UProtect][ESM][->] Register new subscriber for %s with subscriber id %s. Priority %s", Events.getNameByID(eventID), abstractEventSubscription.getSubscriberID().toString(), abstractEventSubscription.getPriority()));

        if (!subscriptions.containsKey(eventID))
            subscriptions.put(eventID, new HashSet<>());
        subscriptions.get(eventID).add(abstractEventSubscription);

        if (!(abstractEventSubscription instanceof AFlagEventSupport))
            abstractEventSubscription.onSubscribe();

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
        uuids.forEach(uuid -> unsubscribe(eventID, uuid));
    }

    public void unsubscribe(int eventID, UUID uuid)
    {
        subscriptions.get(eventID).removeIf(sub -> {
            if (sub.getSubscriberID() == uuid)
            {
                sub.onUnsubscribe();
                return true;
            }
            return false;
        });
    }

    public void callSubscribers(int eventID, Event event)
    {
        if (!hasSubscribers(eventID))
            return;

        HashSet<AbstractEventSubscription> temp = (HashSet)((HashSet) getSubscribersFor(eventID)).clone();
        if (temp.isEmpty())
            return;

        Iterator<AbstractEventSubscription> iterator = temp.iterator();
        AbstractEventSubscription subscription;
        if (isEventCancellable(event))
        {
            Cancellable cancellableInstance = (Cancellable)event;
            for (int i = 0; i < 6; i++)
            {
                while (iterator.hasNext())
                {
                    subscription = iterator.next();
                    System.out.println(subscription.getSubscriberID());
                    System.out.println(!cancellableInstance.isCancelled() || (!cancellableInstance.isCancelled() && (subscription.ignoreCancelled() || subscription.getPriority().getPriority() == 0)));
                    if (!cancellableInstance.isCancelled() || (!cancellableInstance.isCancelled() && (subscription.ignoreCancelled() || subscription.getPriority().getPriority() == 0)))
                        subscription.call(event);
                    iterator.remove();
                }
                /*
                for (AbstractEventSubscription subscription : loopList)
                {
                    if ((cancellableInstance.isCancelled() && !subscription.ignoreCancelled()) && subscription.getPriority().getPriority() != 0)
                    {
                        temp.remove(subscription);
                        continue;
                    }
                    subscription.call(event);
                    temp.remove(subscription);
                }
                loopList = temp;
                if (loopList.isEmpty())
                    break;
                    */
            }
        }
        else
        {
            for (int i = 0; i < 6; i++)
            {
                while (iterator.hasNext())
                {
                    subscription = iterator.next();
                    subscription.call(event);
                    iterator.remove();
                }
            }
        }
    }

    public boolean hasSubscribers()
    {
        return !subscriptions.isEmpty();
    }

    public boolean hasSubscribers(int eventID)
    {
        return subscriptions.containsKey(eventID);
    }

    public Set<AbstractEventSubscription> getSubscribersFor(int eventID)
    {
        return subscriptions.get(eventID);
    }

    protected boolean isEventCancellable(Event event)
    {
        return (event instanceof Cancellable);
    }
}
package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low;

import ch.jarjarbings12.uprotect.protect.utils.exceptions.NotInUseException;
import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public abstract class Flag extends AFlagEventSupport implements Serializable
{
    public abstract UUID getFlagID();

    public abstract String getName();

    public void eventTriggeredCall(Event event)
    {
    }

    public UUID getSubscriberID() throws NotInUseException
    {
        throw new NotInUseException("This event doesn't use any event subscriptions.");
    }

    public void onSubscribe() throws NotInUseException
    {
        throw new NotInUseException("This event doesn't use any event subscriptions.");
    }

    public void onUnsubscribe() throws NotInUseException
    {
        throw new NotInUseException("This event doesn't use any event subscriptions.");
    }

    public JSONObject serialize()
    {
        JSONObject object = new JSONObject();
        object.put("fid", getFlagID().toString());
        return object;
    }

    public abstract Flag deserialize(JSONObject value);
}

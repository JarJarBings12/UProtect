package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.*;
import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public class BooleanFlag extends Flag implements ValueFlag<Boolean>, PermissionFlag, CallableFlag<Event>
{
    private final UUID flagID;
    private final String name;
    private boolean value;
    private final String permission;
    private final ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.flagCall<Event, Boolean> flagCall;

    public BooleanFlag(UUID flagID, String name, boolean value, String permission, flagCall<Event, Boolean> flagCall)
    {
        this.flagID = flagID;
        this.name = name;
        this.value = value;
        this.permission = permission;
        this.flagCall = flagCall;
    }

    @Override
    public UUID getFlagID()
    {
        return this.flagID;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void eventTriggeredCall(Event event)
    {
        callFlag(event);
    }

    @Override
    public String getPermission()
    {
        return this.permission;
    }

    @Override
    public Flag setValue(Boolean value)
    {
        this.value = value;
        return this;
    }

    @Override
    public Boolean getValue()
    {
        return this.value;
    }

    @Override
    public void callFlag(Event event)
    {
        this.flagCall.flagCall(this, event, value);
    }

    @Override
    public JSONObject serialize()
    {
        JSONObject object = new JSONObject();
        object.put("fid", flagID.toString());
        object.put("value", value);
        return object;
    }

    @Override
    public Flag deserialize(JSONObject value)
    {
        return new BooleanFlag(flagID, name, (boolean)value.get("value"), permission, flagCall);
    }
}

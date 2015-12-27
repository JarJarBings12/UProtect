package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.commands.UCommand;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.*;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import org.bukkit.event.Event;
import org.json.simple.JSONObject;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 05.09.2015
 * © 2015 JarJarBings12
 */
public class StringFlag extends Flag implements ValueFlag<String>, PermissionFlag, CallableFlag<Event>
{
    private final UUID flagID;
    private final String name;
    private String value;
    private final String permission;
    private final ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.flagCall<Event, String> flagCall;

    public StringFlag(UUID flagID, String name, String value, String permission, flagCall<Event, String> flagCall)
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
    public void applyForRegion(ProtectedChunkRegion protectedChunkRegion, Flag flag, UCommand command)
    {
    }

    @Override
    public String getPermission()
    {
        return this.permission;
    }

    @Override
    public Flag setValue(String value)
    {
        this.value = value;
        return this;
    }

    @Override
    public String getValue()
    {
        return this.value;
    }

    @Override
    public void callFlag(Event event)
    {
        this.flagCall.flagCall(this, event, this.value);
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
        return new StringFlag(flagID, name, (String)value.get("value"), permission, flagCall);
    }
}

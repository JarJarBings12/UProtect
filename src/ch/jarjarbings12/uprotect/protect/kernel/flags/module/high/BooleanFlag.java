package ch.jarjarbings12.uprotect.protect.kernel.flags.module.high;

import ch.jarjarbings12.uprotect.commands.UCommand;
import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.*;
import ch.jarjarbings12.uprotect.protect.kernel.objects.ProtectedChunkRegion;
import org.bukkit.command.CommandSender;
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
    public void applyForRegion(ProtectedChunkRegion protectedChunkRegion, Flag flag, UCommand command)
    {
        String[] args = command.getArguemnts();
        CommandSender sender = command.getSender();

        if (command.getArguemnts().length == 3)
        {
            sender.sendMessage("[UProtect] /chunkprotect addflag <region_id / region_uuid> <flag> <true[1, t, yes, enabled] / false[0, f, no, disabled]>");
            return;
        }

        System.out.println(args[3]);
        boolean value = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getBooleanValue().parse(args[3]);

        if (protectedChunkRegion.hasFlag(flag.getFlagID()))
        {
            ((BooleanFlag)protectedChunkRegion.getFlag(flag.getFlagID())).setValue(value);
        }
        else
        {
            BooleanFlag booleanFlag = ((BooleanFlag)flag);
            booleanFlag.setValue(value);
            protectedChunkRegion.addFlag(booleanFlag);
        }

        sender.sendMessage(String.format("[UProtect] Set %s to %s", flag.getName(), value));
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

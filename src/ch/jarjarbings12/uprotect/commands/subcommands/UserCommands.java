package ch.jarjarbings12.uprotect.commands.subcommands;

import ch.jarjarbings12.uprotect.core.UProtect;
import ch.jarjarbings12.uprotect.protect.kernel.storage.DriverModul.modul.low.support.UserDBServices;
import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 28.10.2015
 * © 2015 JarJarBings12
 */
public class UserCommands
{
    public void playtimeLookup(CommandSender sender, String label, String[] args)
    {
        if (args.length == 2)
        {
            if (args[1].matches("/[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}/"))
            {
                UserDBServices services = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService();
                sender.sendMessage("[UProtect] %s played %d".replace("%s", services.getPlayerUUIDService().getUserNameFor(UUID.fromString(args[1]))) .replace("%d", String.valueOf(services.getPlayTimeService().getPlayTime(UUID.fromString(args[1])))));
                return;
            }
            else
            {
                UserDBServices services = UProtect.getUProtect().getUProtectAPI().getServiceCenter().getDatabaseService().getDriverServices().getUserDBService();
                if (services.getPlayerUUIDService().isNameRegistered(args[2]))
                {
                    UUID uuid = services.getPlayerUUIDService().getUUIDFor(args[2]);
                    sender.sendMessage("[UProtect] %s played %d".replace("%s", args[2]).replace("%d", String.valueOf(services.getPlayTimeService().getPlayTime(uuid))));
                    return;
                }
                sender.sendMessage("[UProtect] %s never played on this Server!");
                return;
            }
        }
    }
}

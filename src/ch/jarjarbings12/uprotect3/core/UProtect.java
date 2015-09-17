package ch.jarjarbings12.uprotect3.core;

import ch.jarjarbings12.uprotect3.protect.FlagModule.high.BooleanFlag;
import ch.jarjarbings12.uprotect3.protect.FlagModule.low.Flag;
import ch.jarjarbings12.uprotect3.protect.FlagModule.low.flagCall;
import ch.jarjarbings12.uprotect3.protect.kernel.events.module.low.AbstractEventSubscription;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.UUID;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * @since 1.0.0.0
 */
public class UProtect extends JavaPlugin
{
    private static UProtect uProtect = null;
    private UProtectAPI uProtectAPI = null;

    @Override
    public void onEnable()
    {
        uProtect = this;
        initSource();
        this.uProtectAPI = new UProtectAPI();
        this.getUProtectAPI().getDatabaseService().setup();
        evenListenerInitialize();
    }

    @Override
    public void onDisable()
    {

    }

    public static UProtect getUProtect()
    {
        return uProtect;
    }

    public UProtectAPI getUProtectAPI()
    {
        return this.uProtectAPI;
    }

    private boolean resourceCopy(String path, String output)
    {
        if (new File(output).exists())
            return false;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        File outputFile = new File(output);
        System.out.println("[UProtect][I] Copy File to \n => " + outputFile.getAbsolutePath());
        try
        {
            outputFile.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(output);
            int length;
            byte[] byteBuffer = new byte[1024];
            while ((length = inputStream.read(byteBuffer)) > 0)
            {
                outputStream.write(byteBuffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    private void initSource()
    {
        if (new File("plugins/UProtect").mkdirs())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect");
        if (new File("plugins/UProtect/i18n").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/i18n");
        if (new File("plugins/UProtect/drivers").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/drivers");
        if (new File("plugins/UProtect/extensions").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/extensions");
        if (new File("plugins/UProtect/extensions/flags").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/extensions/flags");

        resourceCopy("resources/config.yml", "plugins/UProtect/config.yml");
        resourceCopy("resources/languages/gearman.properties", "plugins/UProtect/i18n/gearman.properties");
    }

    private void evenListenerInitialize()
    {
        getUProtectAPI().getSubscriptionManager().subscribe(1, new AbstractEventSubscription(){

            @Override
            public void call(Event event)
            {
                ((BooleanFlag) build).call(event);
            }

            @Override
            public UUID getSubscriberID()
            {
                return UUID.fromString("b904df89-1747-46d2-ab8c-f08bf6cedae3");
            }
        });
    }

    private Flag build = new BooleanFlag("build", false, "uprotect.flags.build.set", new flagCall<Event, Boolean>()
    {
        @Override
        public void flagCall(Event event, Boolean value)
        {
            if (event instanceof BlockPlaceEvent)
            {
                BlockPlaceEvent e1 = (BlockPlaceEvent) event;
                if (value)
                {
                    e1.getPlayer().sendMessage("[UProtect] No Build");
                    e1.setCancelled(true);
                    return;
                }

                e1.setCancelled(false);
            }
            else if (event instanceof BlockBreakEvent)
            {
                BlockBreakEvent e1 = (BlockBreakEvent) event;

                if (value)
                {
                    e1.getPlayer().sendMessage("[UProtect] No Build");
                    e1.setCancelled(true);
                    return;
                }
            }
        }
    });
}
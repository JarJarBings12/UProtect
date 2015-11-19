package ch.jarjarbings12.uprotect.core;

import ch.jarjarbings12.uprotect.commands.ChunkProtectCommands;
import ch.jarjarbings12.uprotect.commands.UProtectCommand;
import ch.jarjarbings12.uprotect.protect.kernel.events.internal.*;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit.BlockEvents;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit.ChunkEvents;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * © 2015 JarJarBings12
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
        initCommands();
        this.uProtectAPI = new UProtectAPI();
        this.getUProtectAPI().getServiceCenter().getDatabaseService().setup();
        this.getUProtectAPI().getRegionManager().setup();
        this.getUProtectAPI().getServiceCenter().getFlagService().setup();
        this.getUProtectAPI().getServiceCenter().getWorldServices().setup();
        Bukkit.getPluginManager().registerEvents(new ChunkEvents(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
        this.getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(0, new PlayerBuildSubscription());
        this.getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(1, new PlayerBreakSubscription());
        //this.getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(20, new PlayerLoginSubscription());
        //this.getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(21, new PlayerQuitSubscription());
        this.getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(200, new ChunkGenSubscription());


    }

    @Override
    public void onDisable()
    {
        this.getUProtectAPI().getRegionManager().shutdown();
        this.getUProtectAPI().getServiceCenter().getDatabaseService().shutdown();
    }

    public static UProtect getUProtect()
    {
        return uProtect;
    }

    public UProtectAPI getUProtectAPI()
    {
        return this.uProtectAPI;
    }

    private void initSource()
    {
        if (new File("plugins/UProtect").mkdirs())
            System.out.println("[UProtect][INIT] Dir path => plugins/UProtect");
        if (new File("plugins/UProtect/i18n").mkdir())
            System.out.println("[UProtect][INIT] Dir path => plugins/UProtect/i18n");
        if (new File("plugins/UProtect/extensions").mkdir())
            System.out.println("[UProtect][INIT] Dir path => plugins/UProtect/extensions");
        if (new File("plugins/UProtect/extensions/flags").mkdir())
            System.out.println("[UProtect][INIT] Dir path => plugins/UProtect/extensions/flags");
        if (new File("plugins/UProtect/storage").mkdir())
            System.out.println("[UProtect][INIT] Dir path => plugins/UProtect/storage");
        if (new File("plugins/UProtect/storage/drivers").mkdir())
            System.out.println("[UProtect][INIT Dir path => plugins/UProtect/storage/drivers");
        if (new File("plugins/UProtect/storage/internal/world/chunk").mkdirs())
            System.out.println("[UProtect][INIT] Dir path => plugins/UProtect/storage/internal/world/chunk");
        try
        {
            if (new File("plugins/UProtect/storage/internal/world/chunk/cbs.db").createNewFile())
                System.out.println("[UProtect][I] Create new file at path => plugins/UProtect/storage/internal/world/chunk/cbs.db");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        resourceCopy("resources/config.yml", "plugins/UProtect/config.yml");
        resourceCopy("resources/languages/german.properties", "plugins/UProtect/i18n/german.properties");
        resourceCopy("resources/flagconfig.ini", "plugins/UProtect/extensions/flags/flagconfig.ini");
        resourceCopy("resources/notices.txt", "plugins/UProtect/notices.txt");
        resourceCopy("ch/jarjarbings12/uprotect/utils/test.class", "plugins/UProtect/extensions/flags/test.class");
        resourceCopy("resources/drivers/UPRO-SQLITE-DRIVER.jar", "plugins/UProtect/storage/drivers/UPRO-SQLITE-DRIVER.jar");
    }

    private void initCommands()
    {
        try
        {
            Field fieldCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            fieldCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) fieldCommandMap.get(Bukkit.getServer());
            commandMap.register("UProtect", new ChunkProtectCommands("ChunkProtect"));
            commandMap.register("UProtect", new UProtectCommand("UProtect"));
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    private boolean resourceCopy(String path, String output)
    {
        if (new File(output).exists())
            return false;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
        File outputFile = new File(output);
        System.out.println("[UProtect][CORE] Copy File to \n => " + outputFile.getAbsolutePath());
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

}
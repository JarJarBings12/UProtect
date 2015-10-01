package ch.jarjarbings12.uprotect.core;

import ch.jarjarbings12.uprotect.protect.kernel.events.internal.ChunkGenSubscription;
import ch.jarjarbings12.uprotect.protect.kernel.events.module.bukkit.ChunkEvents;
import ch.jarjarbings12.uprotect.utils.eventtest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

/**
 * @author JarJarBings12
 * @creationDate 26.08.2015
 * © 2015 JarJarBings12
 */
public class UProtect extends JavaPlugin
{
    private static UProtect uProtect = null;
    private UProtectAPI uProtectAPI = null;
    private eventtest d = new eventtest();

    @Override
    public void onEnable()
    {
        uProtect = this;
        initSource();
        this.uProtectAPI = new UProtectAPI();
        this.getUProtectAPI().getServiceCenter().getDatabaseService().setup();
        this.getUProtectAPI().getServiceCenter().getFlagService().setup();
        this.getUProtectAPI().getServiceCenter().getWorldServices().setup();
        Bukkit.getPluginManager().registerEvents(d, this);
        Bukkit.getPluginManager().registerEvents(new ChunkEvents(), this);
        this.getUProtectAPI().getServiceCenter().getSubscriptionManager().subscribe(200, new ChunkGenSubscription());
    }

    @Override
    public void onDisable()
    {
        this.getUProtectAPI().getServiceCenter().getDatabaseService().shutdown();
        this.getUProtectAPI().getServiceCenter().getWorldServices();
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
            System.out.println("[UProtect][I] Dir path => plugins/UProtect");
        if (new File("plugins/UProtect/i18n").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/i18n");
        if (new File("plugins/UProtect/drivers").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/drivers");
        if (new File("plugins/UProtect/extensions").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/extensions");
        if (new File("plugins/UProtect/extensions/flags").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/extensions/flags");
        if (new File("plugins/UProtect/storage").mkdir())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/storage");
        if (new File("plugins/UProtect/storage/internal/world/chunk").mkdirs())
            System.out.println("[UProtect][I] Dir path => plugins/UProtect/storage/internal/world/chunk");
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
        resourceCopy("resources/languages/gearman.properties", "plugins/UProtect/i18n/gearman.properties");
        resourceCopy("resources/flagconfig.ini", "plugins/UProtect/extensions/flags/flagconfig.ini");
        resourceCopy("resources/notices.txt", "plugins/UProtect/notices.txt");
        resourceCopy("ch/jarjarbings12/uprotect/utils/test.class", "plugins/UProtect/extensions/flags/test.class");
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
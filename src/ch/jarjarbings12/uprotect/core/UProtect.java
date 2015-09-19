package ch.jarjarbings12.uprotect.core;

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
        //evenListenerInitialize();
        Bukkit.getPluginManager().registerEvents(d, this);
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
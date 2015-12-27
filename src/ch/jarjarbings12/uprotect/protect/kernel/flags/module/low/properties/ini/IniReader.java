package ch.jarjarbings12.uprotect.protect.kernel.flags.module.low.properties.ini;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JarJarBings12
 * @creationDate 18.09.2015.
 * © 2015 JarJarBings12
 */
public class IniReader
{
    private File file;
    private final HashMap<String, IniGroup> values = new HashMap<>();
    public IniReader()
    {
        values.put("base", new IniGroup());
    }

    public void load(File file)
    {
        if (!file.getName().endsWith(".ini"))
            throw new IllegalArgumentException();
        this.file = file;
        this.parse(file);
    }

    public void addGroup(String name)
    {
        values.put(name, new IniGroup());
    }

    public void addGroup(String name, IniGroup group)
    {
        values.put(name, group);
    }

    public void setGroup(String name, IniGroup group)
    {
        values.compute(name, (k, v) -> v = group);
    }

    public IniGroup getGroup(String group)
    {
        return values.get(group);
    }

    public void removeGroup(String name)
    {
        this.values.remove(name);
    }

    public Set<IniGroup> getGroups(Set<String> keys)
    {
        final Set<IniGroup> temp = new HashSet<>();
        values.entrySet().forEach(e -> {
            if (keys.contains(e.getKey()))
                temp.add(e.getValue());
        });
        return temp;

    }

    public Object getValue(String group, String value)
    {
        return values.get(group).get(value);
    }

    public Set<String> getKeys()
    {
        return values.keySet();
    }

    public Set<IniGroup> getGroups()
    {
        return new HashSet<>(values.values());
    }

    public boolean existGroup(String group)
    {
        return values.containsKey(group);
    }

    public HashMap<String, IniGroup> getValues()
    {
        return this.values;
    }

    private void parse(File file)
    {
        String lastSection = "base";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Matcher matcher;
            int lineCount = 0;
            while ((line = br.readLine()) != null)
            {
                lineCount++;
                if (Pattern.compile("\\s*\\[.*\\]\\s*").matcher(line).matches())
                {
                    matcher = Pattern.compile("\\s*\\[(.*)\\]\\s*").matcher(line);
                    matcher.find();
                    lastSection = matcher.group(1);
                    values.put(lastSection, new IniGroup());
                }
                else if (Pattern.compile("\\s*.*\\s*=\\s*.*\\s*").matcher(line).matches())
                {
                    matcher = Pattern.compile("\\s*(.*)\\s*=\\s*(.*)\\s*").matcher(line);
                    matcher.find();
                    values.get(lastSection).add(matcher.group(1), matcher.group(2));
                }
                else if (!line.startsWith(";") && !line.isEmpty())
                {
                    throw new ParserException(lineCount, file);
                }

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void save()
    {
        write(file);
    }

    public void write(File file)
    {
        write(file, values);
    }

    /* !NOT TESTED! */
    public static void write(File file, HashMap<String, IniGroup> values)
    {
        try
        {
            /*
            File tempFile = File.createTempFile("flagconfig.ini", ".temp", file.getParentFile());
            tempFile.deleteOnExit();
            BufferedWriter output = new BufferedWriter(new FileWriter(tempFile));

            values.entrySet().forEach(entry -> {
                try
                {
                    output.write(String.format("[%s]", String.valueOf(entry.getKey())));
                    for (Map.Entry<String, Object> value : entry.getValue().props.entrySet())
                        output.write(String.format("%s=%s", String.valueOf(value.getKey()), String.valueOf(value.getValue())));
                }
                catch (IOException e)
                { e.printStackTrace(); }
            });

            FileChannel fromFile =  new FileInputStream(tempFile).getChannel();
            fromFile.transferTo(0, fromFile.size(), new FileOutputStream(file).getChannel());
            */
            System.out.println(file.getAbsoluteFile());
            BufferedWriter output = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));

            values.entrySet().forEach(entry -> {
                try
                {
                    output.write(String.format("[%s]", String.valueOf(entry.getKey())));
                    for (Map.Entry<String, Object> value : entry.getValue().props.entrySet())
                        output.write(String.format("%s=%s", String.valueOf(value.getKey()), String.valueOf(value.getValue())));
                }
                catch (IOException e)
                { e.printStackTrace(); }
            });

            output.flush();
            output.close();
        }
        catch (IOException e)
        { e.printStackTrace(); }
    }
}

package ch.jarjarbings12.uprotect.protect.kernel.flag.module.low.properties.ini;

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
    private HashMap<String, IniGroup> values = new HashMap<>();

    public IniReader()
    {
        values.put("base", new IniGroup());
    }

    public void load(File file)
    {
        if (!file.getName().endsWith(".ini"))
            throw new IllegalArgumentException();

        parse(file);
    }

    public IniGroup getGroup(String group)
    {
        return values.get(group);
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

    public String getValue(String group, String value)
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

    private void parse(File file)
    {
        Scanner scanner = null;
        String lastSection = "base";
        boolean noGroup = true;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Matcher matcher;
            while ((line = br.readLine()) != null)
            {
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
                else
                {
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

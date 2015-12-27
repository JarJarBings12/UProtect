package ch.jarjarbings12.uprotect.protect.utils;

import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 14.12.2015
 * © 2015 JarJarBings12
 */
public class BooleanValue
{
    private final HashMap<String, Boolean> booleanValues;

    public BooleanValue()
    {
        booleanValues = new HashMapFactory<String, Boolean>().put("true", true).put("false", false)
                                                             .put("1", true).put("0", false)
                                                             .put("y", true).put("n", false)
                                                             .put("yes", true).put("no", false)
                                                             .put("true", true).put("false", false)
                                                             .put("enabled", true).put("disabled", false)
                                                             .put("allow", true).put("deny", false)
                                                             .getHashMap();

    }

    public boolean canParse(String string)
    {
        return booleanValues.containsKey(string);
    }

    public boolean parse(String string)
    {
        return booleanValues.get(string);
    }

    public HashMap<String, Boolean> getSupportetStringValues()
    {
        return booleanValues;
    }
}

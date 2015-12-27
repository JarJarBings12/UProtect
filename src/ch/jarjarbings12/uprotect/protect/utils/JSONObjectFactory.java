package ch.jarjarbings12.uprotect.protect.utils;

import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 08.12.2015
 * © 2015 JarJarBings12
 */
public class JSONObjectFactory
{
    private HashMap<String, Object> map = null;

    public JSONObjectFactory()
    {
        map = new HashMap<>();
    }

    public JSONObjectFactory put(String key, Object value)
    {
        map.put(key, value);
        return this;
    }

    public JSONObjectFactory remove(String key)
    {
        map.remove(key);
        return this;
    }

    public JSONObject getJSONObject()
    {
        return new JSONObject(map);
    }

    public String toJSONString()
    {
        return new JSONObject(map).toJSONString();
    }
}

package ch.jarjarbings12.uprotect.protect.utils;

import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 10.11.2015
 * © 2015 JarJarBings12
 */
public class HashMapFactory<K, V>
{
    private HashMap<K, V> map = null;

    public HashMapFactory()
    {
        this.map = new HashMap<>();
    }

    public HashMapFactory(HashMap<K, V> map)
    {
        this.map = map;
    }

    public HashMapFactory<K, V> put(K key, V value)
    {
        this.map.put(key, value);
        return this;
    }

    public HashMapFactory<K, V> remove(K key)
    {
        this.map.remove(key);
        return this;
    }

    public V get(K key)
    {
        return this.map.get(key);
    }

    public HashMap<K, V> getHashMap()
    {
        return this.map;
    }
}

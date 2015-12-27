package ch.jarjarbings12.uprotect.protect.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JarJarBings12
 * @creationDate 14.12.2015
 * © 2015 JarJarBings12
 */
public class SetFactory<E>
{
    private Set<E> set = null;

    public SetFactory()
    {
        this.set = new HashSet<>();
    }

    public SetFactory(Set set)
    {
        this.set = set;
    }

    public SetFactory<E> add(E element)
    {
        this.set.add(element);
        return this;
    }

    public SetFactory<E> remove(E element)
    {
        this.set.remove(element);
        return this;
    }

    public Set<E> getSet()
    {
        return this.set;
    }

}

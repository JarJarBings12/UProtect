package ch.jarjarbings12.uprotect.protect.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JarJarBings12
 * @creationDate 12.12.2015
 * © 2015 JarJarBings12
 */
public class ListFactory<E>
{
    private List<E> list = null;

    public ListFactory()
    {
        this.list = new ArrayList<>();
    }

    public ListFactory<E> add(E element)
    {
        this.list.add(element);
        return this;
    }

    public E get(int index)
    {
        return this.list.get(index);
    }

    public ListFactory<E> remove(E element)
    {
        this.list.remove(element);
        return this;
    }

    public List<E> getList()
    {
        return this.list;
    }

}

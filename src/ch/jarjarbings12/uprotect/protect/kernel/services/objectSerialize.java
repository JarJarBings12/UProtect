package ch.jarjarbings12.uprotect.protect.kernel.services;

import ch.jarjarbings12.uprotect.protect.kernel.services.interfaces.serialize;

import java.io.*;

/**
 * @author JarJarBings12
 * @creationDate 04.06.2015
 * © 2015 JarJarBings12
 */
public class objectSerialize implements serialize
{

    public objectSerialize()
    {

    }

    @Override
    public byte[] serializeObject(Object object)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    @Override
    public Object deserializeByteArray(byte[] bytes)
    {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try
        {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

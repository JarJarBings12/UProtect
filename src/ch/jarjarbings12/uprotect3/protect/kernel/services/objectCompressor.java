package ch.jarjarbings12.uprotect3.protect.kernel.services;

import ch.jarjarbings12.uprotect3.protect.kernel.services.interfaces.compressor;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @since 1.0.0.0
 * @author JarJarBings12
 * @creationDate 12.06.2015
 */
public class objectCompressor implements compressor
{

    @Override
    public byte[] compress(Object object)
    {
        try
        {
            ByteArrayOutputStream byteArrayOutputStream = null;
            new ObjectOutputStream(new GZIPOutputStream(byteArrayOutputStream)).writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object decompress(InputStream inputStream)
    {
        try
        {
            byte[] buffer = new byte[1024];
            new GZIPInputStream(inputStream).read(buffer, 0, 1024);
            return new ObjectInputStream(new ByteArrayInputStream(buffer)).readObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}

package ch.jarjarbings12.uprotect3.protect.kernel.services;

/**
 * @author JarJarBings12
 * @creationDate 30.08.2015
 * @since 1.0.0.0
 */
public class ObjectService
{
    private objectSerialize serialize = null;
    private objectCompressor compressor = null;

    public ObjectService()
    {
        this.serialize = new objectSerialize();
        this.compressor = new objectCompressor();
    }

    public objectSerialize getSerializeService()
    {
        return this.serialize;
    }

    public objectCompressor getCompressorService()
    {
        return this.compressor;
    }


}

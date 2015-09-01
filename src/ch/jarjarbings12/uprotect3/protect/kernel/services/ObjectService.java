package ch.jarjarbings12.uprotect3.protect.kernel.services;

/**
 * Created by tobias on 30.08.2015.
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

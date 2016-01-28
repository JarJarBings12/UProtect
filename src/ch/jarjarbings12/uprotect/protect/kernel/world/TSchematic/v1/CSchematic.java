package ch.jarjarbings12.uprotect.protect.kernel.world.TSchematic.v1;
import ch.jarjarbings12.uprotect.protect.utils.JSONObjectFactory;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author JarJarBings12
 * @creationDate 06.12.2015
 * © 2015 JarJarBings12
 */
public class CSchematic
{
    public CSchematic()
    {

    }

    public Chunk deserialize(JSONObject jsonObject)
    {
        return null;
    }

    public JSONObject serialize(Chunk chunk)
    {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("VERSION", "1.0.0-Bluedust");

        JSONObject blockSection = new JSONObject();
        try
        {
            for (int x = 0; x < 16; x++)
            {
                for (int y = 0; y < 256; y++)
                {
                    for (int z = 0; z < 16; z++)
                    {
                        Block block = chunk.getBlock(x, y, z);
                        jsonObject.put(jsonParser.parse(parse3DLocation(x, y, z)), parseBlock(chunk.getBlock(x, y, z)));
                    }
                }
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String parseBlock(Block block)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", block.getType().getId());
        jsonObject.put("loc", new JSONObjectFactory()
                    .put("x", block.getLocation().getX())
                    .put("y", block.getLocation().getY())
                    .put("z", block.getLocation().getZ())
                    .getJSONObject());
        jsonObject.put("data", block.getData());
        return jsonObject.toJSONString();
    }

    public String parse2DLocation(int x, int z)
    {
        return String.format("{\"x\":\"%s\", \"z\":\"%s\"}", x, z);
    }

    public String parse3DLocation(int x, int y, int z)
    {
        return String.format("{\"x\":\"%s\", \"y\":\"%s\", \"z\":\"%s\"}", x, y, z);
    }
}

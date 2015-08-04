package ch.jarjarbings12.uprotect.dynmap;

/**
 * @author JarJarBings12
 * @creationDate 30.07.2015
 * @since 1.0.0.0
 */
public class ChunkStyle
{
	public String borderColor;
	public double borderOpacity;
	public int borderSize;
	public String fillColor;
	public double fillOpacity;
	public String label;

	public ChunkStyle(type chunkType)
	{
		if (chunkType == type.borderNoOwner)
		{
			this.borderColor = "##CC0000";
			this.borderOpacity = 0.8D;
			this.borderSize = 1;
			this.fillColor = "##CC0000";
			this.fillOpacity = 0.2D;
		}
		else if (chunkType == type.contentNoOwner)
		{
			this.borderColor = "##CC0000";
			this.borderOpacity = 0.0D;
			this.borderSize = 0;
			this.fillColor = "##CC0000";
			this.fillOpacity = 0.2D;
		}
		else if (chunkType == type.borderWithOwner)
		{
			this.borderColor = "#00FF00";
			this.borderOpacity = 0.8D;
			this.borderSize = 1;
			this.fillColor = "#00FF00";
			this.fillOpacity = 0.2D;
		}
		else if (chunkType == type.contentWithOwner)
		{
			this.borderColor = "#00FF00";
			this.borderOpacity = 0.0D;
			this.borderSize = 0;
			this.fillColor = "#00FF00";
			this.fillOpacity = 0.2D;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	enum type
	{
		borderNoOwner,
		contentNoOwner,
		borderWithOwner,
		contentWithOwner
	}
}

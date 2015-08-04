package ch.jarjarbings12.uprotect.regions.flags;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class BooleanFlag extends Flag<Boolean>
{
	private String name;
	private boolean value;
	private String permission;

	public BooleanFlag(String name, boolean value, String permission)
	{
		this.name = name;
		this.value = value;
		this.permission = permission;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public void setValue(Boolean value)
	{
		this.value = value;
	}

	@Override
	public Boolean getValue()
	{
		return this.value;
	}

	@Override public String getPermission()
	{
		return this.permission;
	}

	@Override
	public type getType()
	{
		return type.BOOLEAN;
	}
}

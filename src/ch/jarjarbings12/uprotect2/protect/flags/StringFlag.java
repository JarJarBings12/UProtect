package ch.jarjarbings12.uprotect2.protect.flags;

/**
 * @author JarJarBings12
 * @creationDate 29.07.2015
 * @since 1.0.0.0
 */
public class StringFlag extends Flag<String>
{
	private String name;
	private String value;
	private String permission;

	public StringFlag(String name, String value, String permission)
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
	public void setValue(String value)
	{
		this.value = value;
	}

	@Override
	public String getValue()
	{
		return this.value;
	}

	@Override
	public String getPermission()
	{
		return this.permission;
	}

	@Override
	public type getType()
	{
		return type.STRING;
	}
}

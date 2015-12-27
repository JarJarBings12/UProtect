package ch.jarjarbings12.uprotect.protect.kernel.events.module.low;

import java.util.HashMap;

/**
 * @author JarJarBings12
 * @creationDate 15.09.2015
 * © 2015 JarJarBings12
 */
public enum Events
{
    BLOCK_PLACE_EVENT(0, "BLOCK_PLACE_EVENT"),
    BLOCK_BREAK_EVENT(1, "BLOCK_BREAK_EVENT"),

    BLOCK_RIGHT_CLICK_EVENT(2, "BLOCK_RIGHT_CLICK_EVENT"),
    BLOCK_LEFT_CLICK_EVENT(3, "BLOCK_LEFT_CLICK_EVENT"),
    AIR_RIGHT_CLICK_EVENT(4, "AIR_RIGHT_CLICK_EVENT"),
    AIR_LEFT_CLICK_EVENT(5, "AIR_LEFT_CLICK_EVENT"),

    PLAYER_LOGIN_EVENT(20, "PLAYER_LOGIN_EVENT"),
    PLAYER_QUIT_EVENT(21, "PLAYER_QUIT_EVENT"),

    ENTITY_DAMAGE_BY_ENTITY(50, "ENTITY_DAMAGED_ENTITY"),
    ENTITY_DAMAGE_BY_BLOCK(51, "ENTITY_DAMAGE_BY_ENTITY"),

    CHUNK_LOAD_EVENT(200, "CHUNK_LOAD_EVENT");

    private final int id;
    private final String name;
    private static final HashMap<Integer, Events> byID = new HashMap<>();
    private static final HashMap<String, Integer> byNameToID = new HashMap<>();
    private static final HashMap<String, Events> byNameToEvent = new HashMap<>();

    static
    {
        for (Events e : Events.values())
        {
            byID.put(e.getID(), e);
            byNameToEvent.put(e.getName(), e);
            byNameToID.put(e.getName(), e.getID());
        }
    }

    Events(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getID()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public static String getNameByID(int id)
    {
        return byID.get(id).getName();
    }

    public static int getIDByName(String name)
    {
        return byNameToID.get(name);
    }

    public static Events getEventByID(int id)
    {
        return byID.get(id);
    }

    public static Events getEventByName(String name)
    {
        return byNameToEvent.get(name);
    }

    public static boolean existEvent(int id)
    {
        return byID.containsKey(id);
    }
}

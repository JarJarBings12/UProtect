package ch.jarjarbings12.uprotect3.protect.kernel.events.module.low;

import java.util.HashMap;

/**
 * Created by tobias on 15.09.2015.
 */
public enum Events
{
    BLOCK_PLACE_EVENT(0, "BLOCK_PLACE_EVENT"),
    BLOCK_BREAK_EVENT(1, "BLOCK_BREAK_EVENT"),
    BLOCK_RIGHT_CLICK_EVENT(2, "BLOCK_RIGHT_CLICK_EVENT"),
    BLOCK_LEFT_CLICK_EVENT(3, "BLOCK_LEFT_CLICK_EVENT");

    private int id;
    private String name;
    private static HashMap<Integer, Events> byID = new HashMap<>();
    private static HashMap<String, Integer> byNameToID = new HashMap<>();
    private static HashMap<String, Events> byNameToEvent = new HashMap<>();

    static
    {
        for(Events e : Events.values())
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
}

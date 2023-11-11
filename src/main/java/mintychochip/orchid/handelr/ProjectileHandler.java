package mintychochip.orchid.handelr;

import mintychochip.orchid.container.OrchidMechanic;
import mintychochip.orchid.container.OrchidSpell;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProjectileHandler {

    private static ProjectileHandler instance;



    private Map<Integer, OrchidSpell> hitMap = new HashMap<>(); //have to add projectile deletion in here

    private Map<Integer, UUID> playerProjectileLaunch = new HashMap<>();

    public ProjectileHandler() {
        instance = this;
    }

    public Map<Integer, OrchidSpell> getHitMap() {
        return hitMap;
    }

    public static ProjectileHandler getInstance() {
        return instance;
    }
}

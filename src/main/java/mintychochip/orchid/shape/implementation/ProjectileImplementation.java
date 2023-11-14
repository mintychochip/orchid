package mintychochip.orchid.shape.implementation;

import mintychochip.orchid.container.OrchidMechanic;
import mintychochip.orchid.registry.OrchidRegistry;
import mintychochip.orchid.shape.OrchidProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;

public class ProjectileImplementation extends Implementation implements OrchidProjectile {
    private Projectile spawnedProjectile;
    private EntityType type;

    public ProjectileImplementation(OrchidMechanic mechanic) {
        super(mechanic);
        String name = mechanic.getName();
        if(name != null) {
            type = OrchidRegistry.getProjectileAlias().get(name);
        }
    }


    @Override
    public int castProjectile() { //generalized method to cast projectile objects
        Location playerLocation = player.getLocation();
        //injection
        if(playerLocation.getWorld() != null && type != null) {
            BlockFace facing = player.getFacing();
            Bukkit.broadcastMessage(facing.toString());
            spawnedProjectile = (Projectile) playerLocation.getWorld().spawnEntity(playerLocation.add(playerLocation.getDirection().getX(),1f,playerLocation.getDirection().getZ()), type); //cant have errors at this line, because type is pulled from hash
            spawnedProjectile.setVelocity(playerLocation.getDirection().multiply(mechanic.getOrchidModifier().getVelocity()));
            return spawnedProjectile.getEntityId();
        }
        if (mechanic instanceof OrchidProjectile projectile) {
            projectile.castProjectile();
        }
        return -1;
    }

    @Override
    public void effect() {
        if(mechanic instanceof OrchidProjectile projectile) {
            projectile.effect();
        }
    }

    @Override
    public void applyParticleProjectile() {
        if(spawnedProjectile != null) {//add this projectile, and give it effect, probs hash
        }
    }
}
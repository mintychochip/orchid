package mintychochip.ollivanders.betterwand.container;

import mintychochip.ollivanders.betterwand.ComponentType;
import mintychochip.ollivanders.betterwand.WandBoost;
import org.bukkit.Material;

import java.io.Serializable;

public class WizardWandData implements Serializable { //holds all wand related objects
    private static final long serialVersionUID = 120594823L;

    private ComponentType type;
    private WandBoost wandBoost;
    private Material material;
    private String name;

    public ComponentType getType() {
        return type;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public WandBoost getWandBoost() {
        return wandBoost;
    }

    public void setWandBoost(WandBoost wandBoost) {
        this.wandBoost = wandBoost;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
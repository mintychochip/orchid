package mintychochip.ollivanders.api;

import mintychochip.ollivanders.container.Context;
import mintychochip.ollivanders.container.Spell;
import mintychochip.ollivanders.wand.container.WandData;

public class SelfCastEvent extends SpellCastEvent{
    public SelfCastEvent(Spell spell, Context context, WandData wandData) {
        super(spell, context, wandData);
    }
}

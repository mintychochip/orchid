package mintychochip.ollivanders;

import mintychochip.ollivanders.config.KeywordConfig;
import mintychochip.ollivanders.config.MechanicConfig;
import mintychochip.ollivanders.handler.PersistentSpellManager;
import mintychochip.ollivanders.handler.ProjectileHandler;
import mintychochip.ollivanders.listener.PlayerListener;
import mintychochip.ollivanders.registry.MechanicRegistry;
import mintychochip.ollivanders.registry.WizardRegistry;
import mintychochip.ollivanders.sequencer.BetterSpellSequencer;
import mintychochip.ollivanders.sequencer.BookReader;
import mintychochip.ollivanders.util.BetterSpellTokenizer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Ollivanders extends JavaPlugin {

    private static KeywordConfig keywordConfig;
    private static MechanicConfig mechanicConfig;
    private static BetterSpellSequencer sequencer;
    private static BetterSpellTokenizer tokenizer;
    private static Ollivanders instance;
    private static BookReader reader;

    private static PersistentSpellManager persistentSpellManager;

    public static KeywordConfig getKeywordConfig() {
        return keywordConfig;
    }

    public static MechanicConfig getMechanicConfig() {
        return mechanicConfig;
    }

    public static Ollivanders getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void onEnable() {
        instance = this;
        ProjectileHandler projectileHandler = new ProjectileHandler();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
        keywordConfig = new KeywordConfig("keywords.yml");
        mechanicConfig = new MechanicConfig("spells.yml");
        MechanicRegistry mechanicRegistry = new MechanicRegistry();
        WizardRegistry registry = new WizardRegistry();
        tokenizer = new BetterSpellTokenizer();
        sequencer = new BetterSpellSequencer();
        reader = new BookReader();
        persistentSpellManager = new PersistentSpellManager();
    }

    public static BookReader getReader() {
        return reader;
    }

    public static BetterSpellTokenizer getTokenizer() {
        return tokenizer;
    }

    public static BetterSpellSequencer getSequencer() {
        return sequencer;
    }

    public static PersistentSpellManager getPersistentSpellManager() {
        return persistentSpellManager;
    }
}

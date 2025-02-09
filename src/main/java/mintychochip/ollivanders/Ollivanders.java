package mintychochip.ollivanders;

import mintychochip.genesis.Genesis;
import mintychochip.genesis.commands.abstraction.GenericMainCommandManager;
import mintychochip.ollivanders.commands.book.BookDataSize;
import mintychochip.ollivanders.commands.book.CurrentSlot;
import mintychochip.ollivanders.commands.generate.*;
import mintychochip.ollivanders.commands.spells.SpellInfo;
import mintychochip.ollivanders.config.SpellConfig;
import mintychochip.ollivanders.handler.CooldownManager;
import mintychochip.ollivanders.handler.PersistentSpellManager;
import mintychochip.ollivanders.handler.ProjectileHandler;
import mintychochip.ollivanders.items.config.ComponentConfig;
import mintychochip.ollivanders.items.config.ComponentRegistry;
import mintychochip.ollivanders.items.config.ItemConfig;
import mintychochip.ollivanders.items.config.WandConfig;
import mintychochip.ollivanders.listener.BookListener;
import mintychochip.ollivanders.listener.SpellDamageListener;
import mintychochip.ollivanders.listener.SpellListener;
import mintychochip.ollivanders.util.RecipeItemLoader;
import mintychochip.ollivanders.util.SpellCaster;
import mintychochip.ollivanders.util.SpellTokenizer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Ollivanders extends JavaPlugin {

    public static RecipeItemLoader itemLoader;
    private static ComponentConfig componentConfig;
    private static WandConfig wandConfig;
    private static SpellConfig spellConfig;
    private static SpellTokenizer tokenizer;
    private static ProjectileHandler projectileHandler;
    private static Ollivanders instance;
    private static ItemConfig itemConfig;
    public static WandConfig getWandConfig() {
        return wandConfig;
    }

    public static ComponentConfig getComponentConfig() {
        return componentConfig;
    }

    public static Ollivanders getInstance() {
        return instance;
    }

    public static SpellTokenizer getTokenizer() {
        return tokenizer;
    }

    public static SpellConfig getSpellConfig() {
        return spellConfig;
    }

    public static ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }
    public static ItemConfig getItemConfig() {
        return itemConfig;
    }

    public static RecipeItemLoader getItemLoader() {
        return itemLoader;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        String[] spells = {"fire"};
        Genesis.getKeys().generateKeys(this, spells);
        SpellCaster spellCaster = new SpellCaster(new PersistentSpellManager(), new CooldownManager());
        projectileHandler = new ProjectileHandler();
        tokenizer = new SpellTokenizer();
        componentConfig = new ComponentConfig("components.yml", this);
        itemConfig = new ItemConfig("items.yml", this);
        wandConfig = new WandConfig("wand.yml", this);
        try {
            spellConfig = new SpellConfig("spells.yml", this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        itemLoader = new RecipeItemLoader();
        enableRegistries();
        Bukkit.getPluginManager().registerEvents(new SpellListener(spellCaster), this);
        Bukkit.getPluginManager().registerEvents(new BookListener(), this);
        Bukkit.getPluginManager().registerEvents(new SpellDamageListener(), this);
        commands();
    }

    public void commands() {
        GenericMainCommandManager spells = new GenericMainCommandManager("spells", "spells");
        spells.addSubCommand(new SpellInfo("info", "tells the description of a spell"));
        GenericMainCommandManager books = new GenericMainCommandManager("books", "sets books");
        GenericMainCommandManager generate = new GenericMainCommandManager("generate", "generates items");
        generate.instantiateSubCommandManager("all", "sets generator to generate all")
                .addSubCommand(new GenerateAllBooks("books", "generates all books"))
                .addSubCommand(new GenerateAllComponent("component", "generates all components"));
        generate.instantiateSubCommandManager("single", "generates a single item")
                .addSubCommand(new GenerateBook("books", "generates a book", Ollivanders.getItemConfig().getBooks().getKeys(false)))
                .addSubCommand(new GenerateComponent("component", "generates a component", Ollivanders.getItemConfig().getComponents().getKeys(false)))
                .addSubCommand(new GenerateWand("wand", "generates a wand"));
        books.instantiateSubCommandManager("set", "sets books")
                .addSubCommand(new BookDataSize("size", "sets inventory size"))
                .addSubCommand(new CurrentSlot("slot", "sets current slot"));
        getCommand("books").setExecutor(books);
        getCommand("generate").setExecutor(generate);
        getCommand("spells").setExecutor(spells);
    }

    public void enableRegistries() {
        new ComponentRegistry();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

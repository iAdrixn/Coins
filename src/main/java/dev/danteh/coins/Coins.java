package dev.danteh.coins;


import dev.danteh.coins.commands.CoinsCommand;
import dev.danteh.coins.commands.ShopCommand;
import dev.danteh.coins.managers.category.CategoryManager;
import dev.danteh.coins.managers.product.ProductManager;
import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.License;
import dev.danteh.coins.utils.command.CommandManager;
import dev.danteh.coins.utils.file.FileConfig;
import dev.danteh.coins.utils.menu.ButtonListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class Coins extends JavaPlugin {
    @Getter
    private static Coins instance;
    private FileConfig mainConfig;
    private CommandManager commandManager;
    private CategoryManager categoryManager;
    private ProductManager productManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        loadConfigs();
        instance = this;
        License license = new License(getConfig().getString("LICENSE"), this);
        license.request();
        if (!license.isValid()) {
            CC.failedLicense();
            return;
        }
        CC.checkLicense(license);
        loadCommands();
        loadListeners();
        loadManagers();
        CC.loadPlugin();
    }

    public void onDisable() {
        super.onDisable();
    }


    private void loadConfigs() {
        this.mainConfig = new FileConfig(this, "config.yml");
    }

    private void loadManagers() {
        this.commandManager = new CommandManager(this);
        this.categoryManager = new CategoryManager();
        this.productManager = new ProductManager();
    }

    private void loadListeners() {
        new ButtonListener();
    }

    private void loadCommands() {
        new CoinsCommand();
        new ShopCommand();
    }

    public FileConfig getMainConfig() {
        return this.mainConfig;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public CategoryManager getCategoryManager() {
        return this.categoryManager;
    }

    public ProductManager getProductManager() {
        return this.productManager;
    }
}

package dev.danteh.coins.managers.product;

import dev.danteh.coins.Coins;
import dev.danteh.coins.utils.CC;
import java.util.ArrayList;
import java.util.List;

import dev.danteh.coins.utils.file.FileConfig;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

public class ProductManager {
    private final List<Product> products = new ArrayList();
    private final FileConfig mainConfig = Coins.getInstance().getMainConfig();

    public void loadProducts() {
        this.products.clear();
        ConfigurationSection categorySection = this.mainConfig.getConfiguration().getConfigurationSection("SHOP-MENU.CATEGORY");
        categorySection.getKeys(false).forEach((category) -> {
            if (this.mainConfig.getBoolean("SHOP-MENU.CATEGORY." + category + ".SUB-MENU")) {
                ConfigurationSection section = this.mainConfig.getConfiguration().getConfigurationSection("CATEGORY-MENU.CATEGORY." + category);
                section.getKeys(false).forEach((product) -> {
                    boolean glow = this.mainConfig.getBoolean("CATEGORY-MENU.CATEGORY." + category + "." + product + ".ICON.GLOW");
                    String displayName = this.mainConfig.getString("CATEGORY-MENU.CATEGORY." + category + "." + product + ".ICON.DISPLAYNAME");
                    List<String> description = this.mainConfig.getStringList("CATEGORY-MENU.CATEGORY." + category + "." + product + ".ICON.DESCRIPTION");
                    List<String> commands = this.mainConfig.getStringList("CATEGORY-MENU.CATEGORY." + category + "." + product + ".COMMANDS");
                    String material = this.mainConfig.getString("CATEGORY-MENU.CATEGORY." + category + "." + product + ".ICON.MATERIAL");
                    int data = this.mainConfig.getInt("CATEGORY-MENU.CATEGORY." + category + "." + product + ".ICON.DATA");
                    int slot = this.mainConfig.getInt("CATEGORY-MENU.CATEGORY." + category + "." + product + ".ICON.SLOT");
                    int coins = this.mainConfig.getInt("CATEGORY-MENU.CATEGORY." + category + "." + product + ".COINS");
                    this.products.add(new Product(category, product, glow, displayName, description, commands, Material.valueOf(material), data, slot, coins));
                });
            }
        });
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public FileConfig getMainConfig() {
        return this.mainConfig;
    }
}
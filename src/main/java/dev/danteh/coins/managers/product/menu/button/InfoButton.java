package dev.danteh.coins.managers.product.menu.button;

import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.product.Product;
import dev.danteh.coins.utils.item.ItemBuilder;
import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

import dev.danteh.coins.utils.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InfoButton extends Button {
    private final Product product;

    public ItemStack getButtonItem(Player player) {
        return (new ItemBuilder(Material.PAPER)).name(Coins.getInstance().getMainConfig().getString("CONFIRM-MENU.INFO.ICON.DISPLAYNAME")).lore((List)Coins.getInstance().getMainConfig().getStringList("CONFIRM-MENU.INFO.ICON.DESCRIPTION").stream().map((lore) -> {
            return lore.replace("%PRODUCT%", this.product.getDisplayName()).replace("%COINS%", String.valueOf(this.product.getCoins()));
        }).collect(Collectors.toList())).build();
    }

    @ConstructorProperties({"product"})
    public InfoButton(Product product) {
        this.product = product;
    }
}
package dev.danteh.coins.managers.product.menu.button;

import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.aquacore.AquaCoreManager;
import dev.danteh.coins.managers.product.Product;
import dev.danteh.coins.managers.product.menu.ConfirmMenu;
import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
import java.beans.ConstructorProperties;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ProductButton extends Button {
    private final Product product;

    @ConstructorProperties({"product"})
    public ProductButton(Product product) {
        this.product = product;
    }

    public ItemStack getButtonItem(Player player) {
        return (new ItemBuilder(this.product.getIcon()))
                .data(this.product.getData())
                .name(this.product.getDisplayName())
                .lore(this.product.getDescription())
                .build();
    }

    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (AquaCoreManager.getCoins(player) < this.product.getCoins()) {
            int missing = this.product.getCoins() - AquaCoreManager.getCoins(player);
            playFail(player);
            CC.message(player, Coins.getInstance().getMainConfig().getString("NO-MONEY")
                    .replace("%COINS%", String.valueOf(missing)));
            return;
        }
        (new ConfirmMenu(this.product)).openMenu(player);
    }
}

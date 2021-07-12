package dev.danteh.coins.managers.product.menu;


import java.beans.ConstructorProperties;
import java.util.HashMap;
import java.util.Map;

import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.product.menu.button.ProductButton;
import dev.danteh.coins.utils.menu.Button;
import dev.danteh.coins.utils.menu.Menu;
import org.bukkit.entity.Player;

public class ProductMenu extends Menu {
    private final String category;

    public String getTitle(Player player) {
        return Coins.getInstance().getMainConfig().getString("CATEGORY-MENU.TITLE").replace("%CATEGORY%", this.category);
    }

    public int getSize() {
        return 9 * Coins.getInstance().getMainConfig().getInt("CATEGORY-MENU.SIZE");
    }

    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> button = new HashMap();
        Coins.getInstance().getProductManager().getProducts().stream().filter((product) -> {
            return product.getCategory().equalsIgnoreCase(this.category); }).forEach((product) -> {
            int slot = product.getSlot();
            button.put(slot, new ProductButton(product));
        });
        return button;
    }

    @ConstructorProperties({"category"})
    public ProductMenu(String category) {
        this.setAutoUpdate(false);
        this.setUpdateAfterClick(false);
        this.setPlaceholder(Coins.getInstance().getMainConfig().getBoolean("FILL_MENU.ENABLE"));
        this.category = category;
    }
}
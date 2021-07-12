package dev.danteh.coins.managers.category.menu;

import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.category.menu.button.CategoryButton;
import dev.danteh.coins.utils.menu.Button;
import dev.danteh.coins.utils.menu.Menu;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class CategoryMenu extends Menu {
    public CategoryMenu() {
        this.setAutoUpdate(false);
        this.setUpdateAfterClick(false);
        this.setPlaceholder(Coins.getInstance().getMainConfig().getBoolean("FILL_MENU.ENABLE"));
    }

    public String getTitle(Player player) {
        return Coins.getInstance().getMainConfig().getString("SHOP-MENU.TITLE");
    }

    public int getSize() {
        return 9 * Coins.getInstance().getMainConfig().getInt("SHOP-MENU.SIZE");
    }

    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> button = new HashMap();
        Coins.getInstance().getCategoryManager().getCategories().forEach((category) -> {
            int slot = category.getSlot();
            button.put(slot, new CategoryButton(category));
        });
        return button;
    }
}
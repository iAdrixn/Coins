package dev.danteh.coins.utils.menu;

import dev.danteh.coins.Coins;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.file.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

public abstract class Menu {
    private FileConfig mainConfig = Coins.getInstance().getMainConfig();
    public static Map<String, Menu> currentlyOpenedMenus = new HashMap();
    private Map<Integer, Button> buttons = new HashMap();
    private boolean autoUpdate = false;
    private boolean updateAfterClick = true;
    private boolean closedByMenu = false;
    private boolean placeholder = false;
    private Button placeholderButton;
    private BukkitTask task;

    public Menu() {
        this.placeholderButton = Button.placeholder(this.mainConfig.getString("FILL_MENU.MATERIAL"), this.mainConfig.getInt("FILL_MENU.DATA"), this.mainConfig.getString("FILL_MENU.DISPLAYNAME"));
    }

    private ItemStack createItemStack(Player player, Button button) {
        ItemStack item = button.getButtonItem(player);
        if (item.getType() != Material.SKULL) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasDisplayName()) {
                meta.setDisplayName(meta.getDisplayName() + "§b§c§d§e");
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    public void openMenu(Player player) {
        this.buttons = this.getButtons(player);
        Menu previousMenu = (Menu)currentlyOpenedMenus.get(player.getName());
        Inventory inventory = null;
        int size = this.getSize() == -1 ? this.size(this.buttons) : this.getSize();
        boolean update = false;
        String title = CC.translate(this.getTitle(player));
        if (title.length() > 32) {
            title = title.substring(0, 32);
        }

        int index;
        if (player.getOpenInventory() != null) {
            if (previousMenu == null) {
                player.closeInventory();
            } else {
                index = player.getOpenInventory().getTopInventory().getSize();
                if (index == size && player.getOpenInventory().getTopInventory().getTitle().equals(title)) {
                    inventory = player.getOpenInventory().getTopInventory();
                    update = true;
                } else {
                    previousMenu.setClosedByMenu(true);
                    player.closeInventory();
                }
            }
        }

        if (inventory == null) {
            inventory = Bukkit.createInventory(player, size, title);
        }

        inventory.setContents(new ItemStack[inventory.getSize()]);
        currentlyOpenedMenus.put(player.getName(), this);
        Iterator var9 = this.buttons.entrySet().iterator();

        while(var9.hasNext()) {
            Entry<Integer, Button> buttonEntry = (Entry)var9.next();
            inventory.setItem((Integer)buttonEntry.getKey(), this.createItemStack(player, (Button)buttonEntry.getValue()));
        }

        if (this.isPlaceholder()) {
            for(index = 0; index < size; ++index) {
                if (this.buttons.get(index) == null) {
                    this.buttons.put(index, this.placeholderButton);
                    inventory.setItem(index, this.placeholderButton.getButtonItem(player));
                }
            }
        }

        if (update) {
            player.updateInventory();
        } else {
            player.openInventory(inventory);
        }

        this.setClosedByMenu(false);
        if (this.autoUpdate && this.task == null) {
            this.task = Coins.getInstance().getServer().getScheduler().runTaskTimer(Coins.getInstance(), () -> {
                this.openMenu(player);
            }, 0L, 20L);
        }

    }

    public int size(Map<Integer, Button> buttons) {
        int highest = 0;
        Iterator var3 = buttons.keySet().iterator();

        while(var3.hasNext()) {
            int buttonValue = (Integer)var3.next();
            if (buttonValue > highest) {
                highest = buttonValue;
            }
        }

        return (int)(Math.ceil((double)(highest + 1) / 9.0D) * 9.0D);
    }

    public int getSlot(int x, int y) {
        return 9 * y + x;
    }

    public int getSize() {
        return -1;
    }

    public abstract String getTitle(Player var1);

    public abstract Map<Integer, Button> getButtons(Player var1);

    public void onClose(Player player) {
        if (this.task != null) {
            this.task.cancel();
        }

    }

    public FileConfig getMainConfig() {
        return this.mainConfig;
    }

    public boolean isAutoUpdate() {
        return this.autoUpdate;
    }

    public boolean isUpdateAfterClick() {
        return this.updateAfterClick;
    }

    public boolean isClosedByMenu() {
        return this.closedByMenu;
    }

    public boolean isPlaceholder() {
        return this.placeholder;
    }

    public Button getPlaceholderButton() {
        return this.placeholderButton;
    }

    public BukkitTask getTask() {
        return this.task;
    }

    public void setMainConfig(FileConfig mainConfig) {
        this.mainConfig = mainConfig;
    }

    public void setButtons(Map<Integer, Button> buttons) {
        this.buttons = buttons;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public void setUpdateAfterClick(boolean updateAfterClick) {
        this.updateAfterClick = updateAfterClick;
    }

    public void setClosedByMenu(boolean closedByMenu) {
        this.closedByMenu = closedByMenu;
    }

    public void setPlaceholder(boolean placeholder) {
        this.placeholder = placeholder;
    }

    public void setPlaceholderButton(Button placeholderButton) {
        this.placeholderButton = placeholderButton;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public Map<Integer, Button> getButtons() {
        return this.buttons;
    }
}
    
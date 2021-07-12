package dev.danteh.coins.utils.menu;

import dev.danteh.coins.utils.item.ItemBuilder;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class Button {
    public static Button placeholder(final String material, final int data, final String title) {
        return new Button() {
            public ItemStack getButtonItem(Player player) {
                return (new ItemBuilder(material)).data(data).name(title).build();
            }
        };
    }

    public static void playFail(Player player) {
        player.playSound(player.getLocation(), Sound.DIG_GRASS, 20.0F, 0.1F);
    }

    public static void playSuccess(Player player) {
        player.playSound(player.getLocation(), Sound.NOTE_PIANO, 20.0F, 15.0F);
    }

    public static void playNeutral(Player player) {
        player.playSound(player.getLocation(), Sound.CLICK, 20.0F, 1.0F);
    }

    public abstract ItemStack getButtonItem(Player var1);

    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
    }

    public boolean shouldCancel(Player player, int slot, ClickType clickType) {
        return true;
    }

    public boolean shouldUpdate(Player player, int slot, ClickType clickType) {
        return false;
    }
}
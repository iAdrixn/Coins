package dev.danteh.coins.managers.aquacore;

import me.activated.core.plugin.AquaCoreAPI;
import org.bukkit.entity.Player;

public class AquaCoreManager {
    public static int getCoins(Player player) {
        return AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId()).getCoins();
    }

    public static void setCoins(Player player, int amount) {
        AquaCoreAPI.INSTANCE.getPlayerData(player.getUniqueId()).setCoins(amount);
    }
}

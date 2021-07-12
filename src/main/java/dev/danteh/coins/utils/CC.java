package dev.danteh.coins.utils;

import java.util.List;
import java.util.stream.Collectors;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CC {
    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> translate(List<String> in) {
        return (List)in.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static void message(Player player, String message) {
        player.sendMessage(translate(message));
    }

    public static void sender(CommandSender sender, String in) {
        sender.sendMessage(translate(in));
    }

    public static void log(String in) {
        Bukkit.getConsoleSender().sendMessage(translate(in));
    }

    public static String CHAT_BAR = org.bukkit.ChatColor.GRAY.toString() + org.bukkit.ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------";

    public static void checkLicense(License license) {
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
        Bukkit.getConsoleSender().sendMessage(translate("&bChecking license..."));
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate("&bSuccessfully loaded license."));
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate("&bBuyer&7: &f" + license.getBuyer()));
        Bukkit.getConsoleSender().sendMessage(translate("&bGenerated&7: &f" + license.getGenerateDate()));
        Bukkit.getConsoleSender().sendMessage(translate("&bLicense&7: &f" + license.getLicense()));
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate("&bThanks for purchase in Neptune Development."));
        Bukkit.getConsoleSender().sendMessage(translate("&bhttps://dsc.gg/lunedev"));
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
    }

    public static void failedLicense() {
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
        Bukkit.getConsoleSender().sendMessage(translate("&bChecking license..."));
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate("&bInvalid License."));
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(translate("&bJoin our discord server for support."));
        Bukkit.getConsoleSender().sendMessage(translate("&bhttps://dsc.gg/lunedev"));
        Bukkit.getConsoleSender().sendMessage(translate(" "));
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
    }
    public static void loadPlugin() {
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
        Bukkit.getConsoleSender().sendMessage(translate("                  &bLune Coins"));
        Bukkit.getConsoleSender().sendMessage(translate(""));
        Bukkit.getConsoleSender().sendMessage(translate("&bAuthor&7: &flDqnteh"));
        Bukkit.getConsoleSender().sendMessage(translate("&bVersion&7: &f0.1-STABLE"));
        Bukkit.getConsoleSender().sendMessage(translate("&bDiscord&7: &fdsc.gg/lunedev"));
        Bukkit.getConsoleSender().sendMessage(CHAT_BAR);
    }
}

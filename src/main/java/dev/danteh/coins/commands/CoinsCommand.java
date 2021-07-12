package dev.danteh.coins.commands;

import dev.danteh.coins.Coins;
import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.command.BaseCommand;
import dev.danteh.coins.utils.command.Command;
import dev.danteh.coins.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class CoinsCommand extends BaseCommand {
    private final Coins plugin = Coins.getInstance();

    @Command(
            name = "lcoins",
            permission = "coins.reload",
            inGameOnly = false
    )
    public void onCommand( CommandArgs command) {
        CommandSender sender = command.getSender();

        String[] args = command.getArgs();
        if (args.length >= 1)
            if (args[0].equalsIgnoreCase("reload")) {
                this.reload();
                CC.sender(sender, "&aSuccessfully reloaded all file.");
            }
    }
        private void reload () {
            this.plugin.getMainConfig().reload();
            this.plugin.getCategoryManager().loadCategories();
            this.plugin.getProductManager().loadProducts();
        }
    }


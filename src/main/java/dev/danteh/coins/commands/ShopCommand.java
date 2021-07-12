 package dev.danteh.coins.commands;

import dev.danteh.coins.managers.category.menu.CategoryMenu;
import dev.danteh.coins.utils.command.BaseCommand;
import dev.danteh.coins.utils.command.Command;
import dev.danteh.coins.utils.command.CommandArgs;

public class ShopCommand extends BaseCommand {
    @Command(
            name = "shop"
    )
            public void onCommand(CommandArgs command) {
            (new CategoryMenu()).openMenu(command.getPlayer());
  }
 }
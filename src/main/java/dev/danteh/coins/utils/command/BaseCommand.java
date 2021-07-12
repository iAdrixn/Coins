package dev.danteh.coins.utils.command;

import dev.danteh.coins.Coins;

public abstract class BaseCommand {
    public BaseCommand() {
    }

    public abstract void onCommand(CommandArgs paramCommandArgs);
}

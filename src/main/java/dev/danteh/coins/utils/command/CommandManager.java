package dev.danteh.coins.utils.command;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import dev.danteh.coins.Coins;
import dev.danteh.coins.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.plugin.SimplePluginManager;

public class CommandManager implements CommandExecutor {
    private final Map<String, Entry<Method, Object>> commandMap = new HashMap();
    private CommandMap map;
    private final Coins plugin;

    public CommandManager(Coins plugin) {
        this.plugin = plugin;
        if (plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
            SimplePluginManager manager = (SimplePluginManager)plugin.getServer().getPluginManager();

            try {
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);
                this.map = (CommandMap)field.get(manager);
            } catch (SecurityException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException var4) {
                var4.printStackTrace();
            }
        }

    }

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        return this.handleCommand(sender, cmd, label, args);
    }

    public boolean handleCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        for(int i = args.length; i >= 0; --i) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(label.toLowerCase());

            for(int x = 0; x < i; ++x) {
                buffer.append("." + args[x].toLowerCase());
            }

            String cmdLabel = buffer.toString();
            if (this.commandMap.containsKey(cmdLabel)) {
                Method method = (Method)((Entry)this.commandMap.get(cmdLabel)).getKey();
                Object methodObject = ((Entry)this.commandMap.get(cmdLabel)).getValue();
                Command command = (Command)method.getAnnotation(Command.class);
                if (!command.permission().equals("") && !sender.hasPermission(command.permission())) {
                    CC.sender(sender, "&cYou don't have permissions.");
                    return true;
                }

                if (command.inGameOnly() && !(sender instanceof Player)) {
                    CC.sender(sender, "&cThis command is only performable in game.");
                    return true;
                }

                try {
                    method.invoke(methodObject, new CommandArgs(sender, cmd, label, args, cmdLabel.split("\\.").length - 1));
                } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException var12) {
                    var12.printStackTrace();
                }

                return true;
            }
        }

        this.defaultCommand(new CommandArgs(sender, cmd, label, args, 0));
        return true;
    }

    public void registerCommands(Object obj) {
        Method[] var2 = obj.getClass().getMethods();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Method m = var2[var4];
            String[] var7;
            int var8;
            int var9;
            String alias;
            if (m.getAnnotation(Command.class) != null) {
                Command command = (Command)m.getAnnotation(Command.class);
                if (m.getParameterTypes().length <= 1 && m.getParameterTypes()[0] == CommandArgs.class) {
                    this.registerCommand(command, command.name(), m, obj);
                    var7 = command.aliases();
                    var8 = var7.length;

                    for(var9 = 0; var9 < var8; ++var9) {
                        alias = var7[var9];
                        this.registerCommand(command, alias, m, obj);
                    }
                } else {
                    System.out.println("Unable to register command " + m.getName() + ". Unexpected method arguments");
                }
            } else if (m.getAnnotation(Completer.class) != null) {
                Completer comp = (Completer)m.getAnnotation(Completer.class);
                if (m.getParameterTypes().length <= 1 && m.getParameterTypes().length != 0 && m.getParameterTypes()[0] == CommandArgs.class) {
                    if (m.getReturnType() != List.class) {
                        System.out.println("Unable to register tab completer " + m.getName() + ". Unexpected return type");
                    } else {
                        this.registerCompleter(comp.name(), m, obj);
                        var7 = comp.aliases();
                        var8 = var7.length;

                        for(var9 = 0; var9 < var8; ++var9) {
                            alias = var7[var9];
                            this.registerCompleter(alias, m, obj);
                        }
                    }
                } else {
                    System.out.println("Unable to register tab completer " + m.getName() + ". Unexpected method arguments");
                }
            }
        }

    }

    public void registerHelp() {
        Set<HelpTopic> help = new TreeSet(HelpTopicComparator.helpTopicComparatorInstance());
        Iterator var2 = this.commandMap.keySet().iterator();

        while(var2.hasNext()) {
            String s = (String)var2.next();
            if (!s.contains(".")) {
                org.bukkit.command.Command cmd = this.map.getCommand(s);
                HelpTopic topic = new GenericCommandHelpTopic(cmd);
                help.add(topic);
            }
        }

        IndexHelpTopic topic = new IndexHelpTopic(this.plugin.getName(), "All command for " + this.plugin.getName(), (String)null, help, "Below is a list of all " + this.plugin.getName() + " command:");
        Bukkit.getServer().getHelpMap().addTopic(topic);
    }

    public void unregisterCommands(Object obj) {
        Method[] var2 = obj.getClass().getMethods();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Method m = var2[var4];
            if (m.getAnnotation(Command.class) != null) {
                Command command = (Command)m.getAnnotation(Command.class);
                this.commandMap.remove(command.name().toLowerCase());
                this.commandMap.remove(this.plugin.getName() + ":" + command.name().toLowerCase());
                this.map.getCommand(command.name().toLowerCase()).unregister(this.map);
            }
        }

    }

    public void registerCommand(Command command, String label, Method m, Object obj) {
        this.commandMap.put(label.toLowerCase(), new SimpleEntry(m, obj));
        this.commandMap.put(this.plugin.getName() + ':' + label.toLowerCase(), new SimpleEntry(m, obj));
        String cmdLabel = label.replace(".", ",").split(",")[0].toLowerCase();
        if (this.map.getCommand(cmdLabel) == null) {
            org.bukkit.command.Command cmd = new BukkitCommand(cmdLabel, this, this.plugin);
            this.map.register(this.plugin.getName(), cmd);
        }

        if (!command.description().equalsIgnoreCase("") && cmdLabel == label) {
            this.map.getCommand(cmdLabel).setDescription(command.description());
        }

        if (!command.usage().equalsIgnoreCase("") && cmdLabel == label) {
            this.map.getCommand(cmdLabel).setUsage(command.usage());
        }

    }

    public void registerCompleter(String label, Method m, Object obj) {
        String cmdLabel = label.replace(".", ",").split(",")[0].toLowerCase();
        BukkitCommand command2;
        if (this.map.getCommand(cmdLabel) == null) {
            command2 = new BukkitCommand(cmdLabel, this, this.plugin);
            this.map.register(this.plugin.getName(), command2);
        }

        if (this.map.getCommand(cmdLabel) instanceof BukkitCommand) {
            command2 = (BukkitCommand)this.map.getCommand(cmdLabel);
            if (command2.completer == null) {
                command2.completer = new BukkitCompleter();
            }

            command2.completer.addCompleter(label, m, obj);
        } else if (this.map.getCommand(cmdLabel) instanceof PluginCommand) {
            try {
                Object command = this.map.getCommand(cmdLabel);
                Field field = command.getClass().getDeclaredField("completer");
                field.setAccessible(true);
                BukkitCompleter completer;
                if (field.get(command) == null) {
                    completer = new BukkitCompleter();
                    completer.addCompleter(label, m, obj);
                    field.set(command, completer);
                } else if (field.get(command) instanceof BukkitCompleter) {
                    completer = (BukkitCompleter)field.get(command);
                    completer.addCompleter(label, m, obj);
                } else {
                    System.out.println("Unable to register tab completer " + m.getName() + ". A tab completer is already registered for that command!");
                }
            } catch (Exception var8) {
                var8.printStackTrace();
            }
        }

    }

    private void defaultCommand(CommandArgs args) {
        args.getSender().sendMessage("Unknown command. Type \"/help\" for help.");
    }
}
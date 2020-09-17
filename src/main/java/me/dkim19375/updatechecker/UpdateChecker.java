package me.dkim19375.updatechecker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class UpdateChecker implements CommandExecutor {
    public String name;
    public String pluginID;

    public Main main;
    public UpdateChecker(Main main) { this.main = main; }
    public UpdateChecker(Main main, int i) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("updatechecker")) {
            if (args[0].equalsIgnoreCase("check")) {
                String ReturnValue[] = new String[10];
                checkForUpdates(ReturnValue, false);
                String[] var = checkForUpdates(ReturnValue, false);
                sender.sendMessage(var[0]);
                sender.sendMessage(var[7]);
            }
            if (args[0].equalsIgnoreCase("debug")) {
                sender.sendMessage("Starting Debug...");
                String ReturnValue[] = new String[10];
                checkForUpdates(ReturnValue, true);
                String[] var = checkForUpdates(ReturnValue, true);
                checkForUpdates(ReturnValue, true);
                sender.sendMessage(var[0]);
                sender.sendMessage(var[1]);
                sender.sendMessage(var[2]);
                sender.sendMessage(var[3]);
                sender.sendMessage(var[4]);
                sender.sendMessage(var[5]);
                sender.sendMessage(var[6]);
                sender.sendMessage(var[7]);
                sender.sendMessage(var[8]);
            }
        }
        return false;
    }

    public String[] checkForUpdates(String[] ReturnValue, boolean Debug) {
        ReturnValue[0] = ChatColor.GOLD + "Checking for updates!";
        final FileConfiguration data = main.getConfig();
        for(String key : data.getKeys(false)) {
            name = data.getString(key + ".name");
            pluginID = data.getString(key + ".pluginID");
            ReturnValue[1] = "me.dkim19375.updatechecker.UpdateChecker String Var name: " + name + ", pluginID: " + pluginID;
            getPluginVersion(pluginID, name, ReturnValue, Debug);
        }
        return ReturnValue;
    }

    private String[] getPluginVersion(String pluginID, String name, String[] ReturnValue, boolean Debug) {
        String result;
        result = Bukkit.getPluginManager().getPlugin(name).getDescription().getVersion();
        if (Debug) {
            ReturnValue[2] = "me.dkim19375.updatechecker.UpdateChecker String Var result = " + result;
            ReturnValue[3] = "me.dkim19375.updatechecker.UpdateChecker String Var pluginID = " + pluginID;
            ReturnValue[4] = "me.dkim19375.updatechecker.UpdateChecker String Var name = " + name;
        }
        if (main.updateChecker(pluginID, ReturnValue, Debug).equals(result)) {
            ReturnValue[7] = "Plugin " + name + " is up to date! (version " + main.updateChecker(pluginID, ReturnValue, Debug) + ")";
        } else {
            ReturnValue[7] = "Plugin " + name + " is outdated! (Current version: " + result + ". Newest Version: " + main.updateChecker(pluginID, ReturnValue, Debug) + ")";
        }
        return ReturnValue;
    }
}

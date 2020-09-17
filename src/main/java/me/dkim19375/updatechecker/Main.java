package me.dkim19375.updatechecker;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("updatechecker").setExecutor(new UpdateChecker(this));
        this.saveDefaultConfig();
    }
    @Override
    public void onDisable() {

    }
    public String returnValue;

    public String[] updateChecker(String pluginID, String[] ReturnValue, boolean Debug) {
        if (Debug) {
            ReturnValue[5] = ChatColor.GOLD + "WARNING: MORE INFO IN CONSOLE!";
            new PluginUpdateChecker(this, Integer.parseInt(pluginID)).getLatestVersionDebug(version -> {
                ReturnValue[8] = version;
                ReturnValue[6] = "me.dkim19375.updatechecker.UpdateChecker.Main String Var version: " + version;
            });
        }
        new PluginUpdateChecker(this, Integer.parseInt(pluginID)).getLatestVersion(version -> {
            ReturnValue[8] = version;
        });
        return ReturnValue;
    }
}

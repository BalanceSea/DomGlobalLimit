package com.BalanceSea.domGlobalLimit;

import cn.lunadeer.dominion.api.DominionAPI;
import cn.lunadeer.dominion.api.dtos.PlayerDTO;
import com.BalanceSea.domGlobalLimit.Command.DomGlobalLimitCommand;
import com.BalanceSea.domGlobalLimit.Listener.DomGlobalLimitListener;
import com.BalanceSea.domGlobalLimit.Utils.DomMySQLUtil;
import com.BalanceSea.domGlobalLimit.Utils.DominionUtil;
import com.BalanceSea.domGlobalLimit.Utils.MessageUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public final class DomGlobalLimit extends JavaPlugin {
    public static  DominionAPI dominionAPI;
    public static YamlConfiguration config;
    public static JavaPlugin plugin;
    public static Boolean debug = false;

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Dominion") != null){
            dominionAPI = DominionAPI.getInstance();
        }else {
            getLogger().info("未找到 Dominion 插件 ,请安装 Dominion");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        DomMySQLUtil.init();

        plugin = this;
        loadConfig();
        MessageUtil.init();
        DominionUtil.init();

        getServer().getPluginManager().registerEvents(new DomGlobalLimitListener(),this);
        DomGlobalLimitCommand command = new DomGlobalLimitCommand();
        Objects.requireNonNull(getCommand("DomGlobalLimit")).setExecutor(command);
        Objects.requireNonNull(getCommand("DomGlobalLimit")).setTabCompleter(command);

        getLogger().info("插件已加载");
    }

    @Override
    public void onDisable() {
        getLogger().info("插件已关闭");
    }

    public DominionAPI getDominionAPI() {
        return dominionAPI;
    }

    public static void loadConfig(){
        File file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()){
            plugin.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);

        debug =  config.getBoolean("Debug");

    }

    public static YamlConfiguration getMainConfig(){
        return config;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static Boolean isDebug() {
        return debug;
    }
}

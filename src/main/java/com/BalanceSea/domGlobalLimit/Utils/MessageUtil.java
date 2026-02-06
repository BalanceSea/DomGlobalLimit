package com.BalanceSea.domGlobalLimit.Utils;

/*
 *@Author: 山海
 *@CreateTime: 2026-02-06
 *@Description: 消息类工具
 *@Version: 1.0
 */

import com.BalanceSea.domGlobalLimit.DomGlobalLimit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.MessageFormat;

public class MessageUtil {
    private static final JavaPlugin plugin = DomGlobalLimit.getPlugin();
    private static YamlConfiguration messageConfig;

    public static void init(){
        File file =  new File(plugin.getDataFolder(), "message.yml");
        if (!file.exists()){
            plugin.saveResource("message.yml", false);
        }
        messageConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static String getMessage(String key){
        return messageConfig.getString(key);
    }

    /**
     * 向玩家发送消息
     * @param player 玩家实例
     * @param key 标识符
     * @param placeholders 占位符
     */
    public static void  sendMessage(Player player, String key, Object... placeholders){
        player.sendMessage(HandleMessage(key, placeholders));
    }

    /**
     * 向指令发送者发送消息
     * @param sender 指令发送者
     * @param key 标识符
     * @param placeholders 占位符
     */
    public static void sendMessage(CommandSender sender, String key, Object... placeholders){
        sender.sendMessage(HandleMessage(key, placeholders));
    }

    public static void sendMessage(String message, Object... placeholders){
        plugin.getLogger().info(MessageFormat.format(message, placeholders));
    }

    public static void sendDebugMessage(String Message, Object... placeholders){
        if (!DomGlobalLimit.isDebug())return;

        plugin.getLogger().info(MessageFormat.format(Message, placeholders));
    }

    private static String HandleMessage(String key,Object... placeholders){
        String message = messageConfig.getString(key,"");
        if (placeholders != null && placeholders.length > 0){
             message = MessageFormat.format(message,placeholders);
        }
        return ChatColor.translateAlternateColorCodes('&', messageConfig.getString("prefix","&6[&e领地&6]&f") + message);
    }
}

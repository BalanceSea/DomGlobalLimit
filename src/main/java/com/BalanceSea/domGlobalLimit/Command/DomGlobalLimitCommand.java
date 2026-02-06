package com.BalanceSea.domGlobalLimit.Command;

/*
 *@Author: 山海
 *@CreateTime: 2026-02-06
 *@Description: 主指令
 *@Version: 1.0
 */

import com.BalanceSea.domGlobalLimit.DomGlobalLimit;
import com.BalanceSea.domGlobalLimit.Utils.DominionUtil;
import com.BalanceSea.domGlobalLimit.Utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class DomGlobalLimitCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        if (sender.isOp()){
            if (args.length >= 1){
                if (args[0].equalsIgnoreCase("reload")){
                    DomGlobalLimit.loadConfig();
                    MessageUtil.init();
                    DominionUtil.init();
                    MessageUtil.sendMessage(sender,"reload");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String str, String[] args) {
        if (sender.isOp()){
            return List.of("reload");
        }
        return List.of();
    }
}

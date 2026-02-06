package com.BalanceSea.domGlobalLimit.Utils;

/*
 *@Author: 山海
 *@CreateTime: 2026-02-06
 *@Description: Dominion相关工具
 *@Version: 1.0
 */

import cn.lunadeer.dominion.api.DominionAPI;
import cn.lunadeer.dominion.api.dtos.DominionDTO;
import com.BalanceSea.domGlobalLimit.DomGlobalLimit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.BalanceSea.domGlobalLimit.Utils.MessageUtil.*;

public class DominionUtil {

    private static  Map<String,Integer> map = new HashMap<String,Integer>();

    public static void init(){
        map.clear();
        YamlConfiguration yaml = DomGlobalLimit.getMainConfig();
        ConfigurationSection section = yaml.getConfigurationSection("permissions");

        if (section != null) {
            for (String fullPath : section.getKeys(true)) {
                if (section.isInt(fullPath)) {
                    int limitValue = section.getInt(fullPath);
                    map.put(fullPath, limitValue);
                    MessageUtil.sendDebugMessage("读取权限配置：" + fullPath + " = " + limitValue);
                }
            }
        } else {
            MessageUtil.sendMessage("配置文件中未找到 permissions 节点");
        }
    }


    public static int getPlayerDominionLimit(Player player){
        int limit = 1;

        for (String key : map.keySet()) {
            if (player.hasPermission(key)){
                int valie = map.get(key);
                if (valie > limit){
                    limit = valie;
                }
            }
        }

        return  limit;
    }
}

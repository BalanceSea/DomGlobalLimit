package com.BalanceSea.domGlobalLimit.Listener;

/*
 *@Author: 山海
 *@CreateTime: 2026-02-06
 *@Description: 监听领地创建
 *@Version: 1.0
 */

import cn.lunadeer.dominion.api.DominionAPI;
import cn.lunadeer.dominion.api.dtos.DominionDTO;
import cn.lunadeer.dominion.events.dominion.DominionCreateEvent;
import com.BalanceSea.domGlobalLimit.Utils.DomMySQLUtil;
import com.BalanceSea.domGlobalLimit.Utils.DominionUtil;
import com.BalanceSea.domGlobalLimit.Utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

import static com.BalanceSea.domGlobalLimit.Utils.MessageUtil.sendDebugMessage;

public class DomGlobalLimitListener implements Listener {

    @EventHandler
    public void onPlayerCreateDom(DominionCreateEvent event){

        Player player = Bukkit.getPlayer(event.getOwner());

        if (player == null) return;

        int playerDominions = DomMySQLUtil.countRecordsByKey(player.getUniqueId().toString());
        int playerDominionLimit = DominionUtil.getPlayerDominionLimit(player);

        boolean isMax = playerDominions >= playerDominionLimit;
        sendDebugMessage("玩家 {0} 当前领地数量: {1} 领地上限为: {2} 是否达到上限: {3}", player.getName(),playerDominions , playerDominionLimit ,isMax);

        if (isMax) {
            event.setCancelled(true);
            MessageUtil.sendMessage(player,"createMax",playerDominionLimit);
        }
    }

}

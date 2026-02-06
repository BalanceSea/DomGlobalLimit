package com.BalanceSea.domGlobalLimit.Utils;

/*
 *@Author: 山海
 *@CreateTime: 2026-02-06
 *@Description: Dom数据库相关工具
 *@Version: 1.0
 */

import com.BalanceSea.domGlobalLimit.DomGlobalLimit;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class DomMySQLUtil {

    private static final HikariDataSource dataSource;

    static {
        HikariConfig  config = new HikariConfig();
        File file = new File("plugins/Dominion/config.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        String type = yaml.getString("type");
        if (type != null && !type.equals("mysql")) {
            Bukkit.getLogger().info("Dominion 未开启MYSQL模式 已卸载插件");
            Bukkit.getPluginManager().disablePlugin(DomGlobalLimit.getPlugin());
        }
        String host = yaml.getString("database.host");
        String port = yaml.getString("database.port");
        String database = yaml.getString("database.database");
        String username = yaml.getString("database.username");
        String password = yaml.getString("database.password");
        int poolSize = yaml.getInt("database.connection-pool-size");
        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database;
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(poolSize);

        dataSource = new HikariDataSource(config);
    }

    public static void init(){}

    /**
     * 查询指定键值的记录总数
     * @param uuid 玩家UUID
     * @return 匹配的记录数量（无匹配则返回0）
     */
    //此处为AI生成
    public static int countRecordsByKey(String uuid) {
        // 使用COUNT(*)让数据库直接计算总数，效率最高
        String sql = "SELECT COUNT(*) AS total FROM dominion WHERE owner = ?";

        // try-with-resources自动关闭资源
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置查询参数，防止SQL注入
            pstmt.setString(1, uuid);

            // 执行查询
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
                return 0;
            }

        } catch (SQLException e) {
            Bukkit.getLogger().warning("获取玩家 "+ uuid + " 的领地数量失败: " + e.getMessage());
            throw new RuntimeException("数据库统计异常", e);
        }
    }
}

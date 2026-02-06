# DomGlobalLimit

<div align="center">

![Version](https://img.shields.io/badge/version-1.0--SNAPSHOT-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green)

**一个Dominion的群组领地数量限制附属**

</div>

## 📖 简介

DomGlobalLimit 是一个基于 Spigot/Bukkit API 的 Minecraft 服务器插件，旨在为 Dominion 领地插件添加全局领地数量限制功能。该插件允许服务器管理员根据玩家的权限组设置不同的领地创建上限，从而更好地管理服务器资源。

## ✨ 主要功能

- 🎯 **全局领地限制**: 限制玩家可创建的领地总数
- 👥 **权限组管理**: 根据玩家权限设置不同的领地上限
- 🔄 **热重载配置**: 支持无需重启服务器即可重载配置文件
- 💬 **自定义消息**: 支持自定义提示消息和前缀

## 📋 依赖项

- [Spigot/Bukkit](https://www.spigotmc.org/) 1.20.1 或更高版本
- [Dominion](https://github.com/LunaDeerMC/Dominion)
- Java 17 或更高版本

## 🚀 安装

1. 从 [Releases](../../releases) 下载最新版本的 DomGlobalLimit.jar
2. 将插件文件放入服务器的 `plugins` 文件夹
3. 重启服务器或加载插件
4. 配置 `config.yml` 文件以满足您的需求
5. 重启服务器或使用 `/DomGlobalLimit reload` 命令重载配置

## ⚙️ 配置

### config.yml

```yaml
# 调试模式
# 生产环境中请勿开启
Debug: false

# 权限组设置
permissions:
  # 权限名 : 领地数量
  # 如果有多个权限则按照数量最多的生效
  dominion.limit.default: 10
  dominion.limit.vip: 15
```

### message.yml

```yaml
prefix: '&6[&e领地&6]&f'
createMax: '&c领地已达上限, 当前最大上限为: &e{0}'
reload: '&a重载成功'
```

## 🎮 命令

| 命令 | 别名 | 权限 | 描述 |
| --- | --- | --- | --- |
| `/DomGlobalLimit reload` | `/dgl reload`, `/DomLimit reload` | OP | 重载插件配置 |

## 📝 权限

| 权限节点 | 描述 |
| --- | --- |
| `dominion.limit.default` | 默认领地数量限制 |
| `dominion.limit.vip` | VIP玩家领地数量限制 |
| ... | 可根据需要自定义更多权限组 |

## 🔧 开发

### 构建要求

- Java 17
- Maven 3.6+

### 构建步骤

```bash
git clone https://github.com/BalanceSea/DomGlobalLimit.git
cd DomGlobalLimit
mvn clean package
```

构建后的 JAR 文件将位于 `target` 目录中。

## 👥 贡献

欢迎提交问题报告和拉取请求。如果您想为项目做出贡献，请遵循以下步骤：

1. Fork 本仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个拉取请求

## 📧 联系方式

- 作者: 山海
- 项目链接: [BalanceSea/DomGlobalLimit](https://github.com/BalanceSea/DomGlobalLimit)

## 🙏 致谢

- [Dominion](https://github.com/LunaDeerMC/Dominion) - 提供领地管理API
- [Spigot](https://www.spigotmc.org/) - 提供Minecraft服务器API
- 所有为本项目做出贡献的开发者

---

<div align="center">

如果这个项目对您有帮助，请考虑给我们一个 ⭐️

</div>

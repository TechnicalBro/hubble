package com.devsteady.hubble;

import com.devsteady.hubble.config.HubbleConfig;
import com.devsteady.hubble.listeners.ConnectionListener;
import com.devsteady.hubble.menu.ServerSelectionMenu;
import com.devsteady.hubble.users.HubUser;
import com.devsteady.hubble.users.HubUserManager;
import com.devsteady.onyx.chat.Chat;
import com.devsteady.onyx.plugin.BukkitPlugin;
import com.devsteady.onyx.yml.InvalidConfigurationException;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;

/**
 * Hub plugin for HcPrisons!
 */
public class Hubble extends BukkitPlugin implements PluginMessageListener {

    private static Hubble instance = null;

    public static Hubble getInstance() {
        return instance;
    }

    @Getter
    private HubbleConfig hubbleConfig = new HubbleConfig();

    @Getter
    private HubUserManager hubUsers;

    @Override
    public void startup() {

        hubUsers = new HubUserManager(this);

        registerListeners(
                new ConnectionListener(this),
                hubUsers
        );

        getServer().getMessenger().registerOutgoingPluginChannel(this,"BungeeCord");

        getLogger().info("Hubble Connection Listener Registered");

    }

    @Override
    public void shutdown() {
        Messenger pluginMessenger = getServer().getMessenger();
        pluginMessenger.unregisterOutgoingPluginChannel(this);

    }

    @Override
    public String getAuthor() {
        return "TheGamersCave";
    }

    @Override
    public void initConfig() {
        instance = this;

        File hubbleConfigFile = new File(getDataFolder(),"config.yml");

        if (!hubbleConfigFile.exists()) {
            try {
                hubbleConfig.init(hubbleConfigFile);
                Chat.broadcast("&2Hubble Configuration has been created!");
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        try {
            hubbleConfig.load(hubbleConfigFile);
            getLogger().info("Hubble configuration loaded");
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {

    }

    public static class API {
        public static HubUser getUser(Player player) {
            return getInstance().getHubUsers().getUser(player);
        }

        public static HubUserManager getUserManager() {
            return getInstance().getHubUsers();
        }

        /**
         * Will open the server selector for the player if they're not teleporting.
         * @param player player to open the server selector for.
         */
        public static void openServerSelector(Player player) {
            if (getUser(player).isTeleporting()) {
                return;
            }
            ServerSelectionMenu.getInstance().openMenu(player);
        }
    }
}

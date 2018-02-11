package com.devsteady.hubble.menu;

import com.devsteady.hubble.Hubble;
import com.devsteady.hubble.config.HubbleConfig;
import com.devsteady.hubble.config.ServerItemData;
import com.devsteady.hubble.users.HubUser;
import com.devsteady.onyx.inventory.menu.*;
import com.devsteady.onyx.item.ItemBuilder;
import com.devsteady.onyx.item.Items;
import com.devsteady.onyx.time.TimeHandler;
import com.devsteady.onyx.time.TimeType;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ServerSelectionMenu extends ItemMenu {

    private static ServerSelectionMenu instance = null;

    public static ServerSelectionMenu getInstance() {
        if (instance == null) {
            instance = new ServerSelectionMenu(Hubble.getInstance().getHubbleConfig());
        }

        return instance;
    }

    public static class ServerMenuItem extends MenuItem {

        private String serverName;
        public ServerMenuItem(String serverName, ItemStack icon) {
            super(Items.getName(icon),icon.getData());
            setDescriptions(Items.getLore(icon));
            this.serverName = serverName;

        }

        @Override
        public void onClick(Player player, ClickType clickType) {
            Hubble.API.getUser(player).setTeleporting(true);

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(serverName);
//            Bukkit.dispatchCommand(player, "server " + serverName);

            Hubble.getInstance().getThreadManager().runTaskLater(() -> {
                player.sendPluginMessage(Hubble.getInstance(),"BungeeCord",out.toByteArray());
            }, 2);
            Hubble.API.getUser(player).setTeleporting(false);
        }
    }

    public ServerSelectionMenu(HubbleConfig config) {
        super("Server Selector", 1);
        setExitOnClickOutside(false);

        /*
        If we're not able to close this menu, let's add that behaviour.
         */
        if (!config.isCloseable()) {
            addBehaviour(MenuAction.CLOSE, new MenuBehaviour() {
                @Override
                public void doAction(Menu menu, Player player) {
                    Hubble.getInstance().getThreadManager().runTaskOneTickLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (Hubble.API.getUser(player).isTeleporting()) {
                                    return;
                                }
                                Hubble.API.openServerSelector(player);
                            } catch (NullPointerException ex) {

                            }
                        }
                    });
                }
            });
        }

        addMenuItem(new ServerMenuItem("5-man", ItemBuilder.of(Material.IRON_SWORD).name("&l&7Hardcore Prison &eP &cV &eP").lore("&7Completely Custom").item()),0);
//        Map<Integer, ServerItemData> slotData = config.getServerItems();
//
//        for(Map.Entry<Integer, ServerItemData> slot : slotData.entrySet()) {
//            setItem(slot.getKey(),new ServerMenuItem(slot.getValue().getServerName(),slot.getValue().getItemIcon()));
//        }
    }
}

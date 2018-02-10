package com.devsteady.hubble.menu;

import com.devsteady.hubble.Hubble;
import com.devsteady.hubble.config.HubbleConfig;
import com.devsteady.hubble.config.ServerItemData;
import com.devsteady.onyx.inventory.menu.*;
import com.devsteady.onyx.item.ItemBuilder;
import com.devsteady.onyx.item.Items;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ServerSelectionMenu extends ItemMenu {

    public static class ServerMenuItem extends MenuItem {

        private String serverName;
        public ServerMenuItem(String serverName, ItemStack icon) {
            super(Items.getName(icon),icon.getData());
            setDescriptions(Items.getLore(icon));
            this.serverName = serverName;

        }

        @Override
        public void onClick(Player player, ClickType clickType) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(serverName);
//            Bukkit.dispatchCommand(player, "server " + serverName);
            player.sendPluginMessage(Hubble.getInstance(),"BungeeCord",out.toByteArray());
        }
    }

    public ServerSelectionMenu(HubbleConfig config) {
        super("Server Selector", 1);

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
                            Hubble.API.openServerSelector(player);
                        }
                    });
                }
            });
        }

        addMenuItem(new ServerMenuItem("5-man", ItemBuilder.of(Material.IRON_SWORD).name("&l&7Harcore Prison &eP &cV &eP").lore("&7Completely Custom").item()),0);

//        Map<Integer, ServerItemData> slotData = config.getServerItems();
//
//        for(Map.Entry<Integer, ServerItemData> slot : slotData.entrySet()) {
//            setItem(slot.getKey(),new ServerMenuItem(slot.getValue().getServerName(),slot.getValue().getItemIcon()));
//        }
    }
}

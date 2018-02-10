package com.devsteady.hubble.config;

import com.devsteady.onyx.item.ItemBuilder;
import com.devsteady.onyx.yml.Path;
import com.devsteady.onyx.yml.YamlConfig;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Holds the item for the menu and the name of the server
 */
public class ServerItemData {

    @Path("server")
    @Setter
    @Getter
    private String serverName = "5-man";

    @Path("icon")
    @Getter @Setter
    private ItemStack itemIcon = ItemBuilder.of(Material.DIAMOND_SWORD).name("&a&lHardcore Prison &cP V P").lore(
            "&7Our custom take on the classic Hardcore Prison",
            "&7- Create a gang",
            "&7- Fight for power",
            "&7- Kill rival gangs."
    ).item();

    public ServerItemData() {

    }

}

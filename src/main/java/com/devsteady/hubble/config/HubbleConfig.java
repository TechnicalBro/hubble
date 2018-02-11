package com.devsteady.hubble.config;

import com.devsteady.onyx.item.ItemBuilder;
import com.devsteady.onyx.yml.Path;
import com.devsteady.onyx.yml.YamlConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Hubble Plugin Configuration.
 */
public class HubbleConfig extends YamlConfig {

    @Path("open-on-join")
    @Getter
    @Setter
    private boolean openOnJoin = true;

    @Path("closable")
    @Getter @Setter
    private boolean closeable = true;

    @Path("disabled-commands")
    @Getter @Setter
    private Set<String> disabledCommands = new HashSet<>();

    @Path("server-selector.item")
    @Getter @Setter
    private ItemStack serverSelectorItem = ItemBuilder.of(Material.COMPASS).name("&bServer Selector").lore("&cRight click to open server selector menu").item();


//    @Path("menu-items")
//    @Getter
//    @Setter
//    private Map<Integer, ServerItemData> serverItems = new HashMap<>();

    public HubbleConfig() {
        disabledCommands.add("/pl");
        disabledCommands.add("/bukkit:");
        disabledCommands.add("/spigot:");
//        serverItems.put(0, ServerItemData.builder().serverName("5-man").itemIcon(ItemBuilder.of(Material.IRON_PICKAXE).name("&7&lHardcore Prison &cP &eV &cP").enchantment(Enchantment.LOOT_BONUS_MOBS,1).item()).build());
    }

    public void addDisabledCommand(String cmd) {
        this.disabledCommands.add(cmd);
    }
}

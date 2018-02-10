package com.devsteady.hubble.config;

import com.devsteady.onyx.item.ItemBuilder;
import com.devsteady.onyx.yml.Path;
import com.devsteady.onyx.yml.YamlConfig;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

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
    private boolean closeable = false;

//    @Path("menu-items")
//    @Getter
//    @Setter
//    private Map<Integer, ServerItemData> serverItems = new HashMap<>();

    public HubbleConfig() {
//        serverItems.put(0, ServerItemData.builder().serverName("5-man").itemIcon(ItemBuilder.of(Material.IRON_PICKAXE).name("&7&lHardcore Prison &cP &eV &cP").enchantment(Enchantment.LOOT_BONUS_MOBS,1).item()).build());
    }
}

package com.devsteady.hubble.gadget;

import com.devsteady.hubble.Hubble;
import com.devsteady.onyx.game.gadget.ItemGadget;
import com.devsteady.onyx.game.item.BaseTool;
import com.devsteady.onyx.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ServerMenuGadget extends BaseTool {

    private static ServerMenuGadget instance = null;

    public static ServerMenuGadget getInstance() {
        if (instance == null) {
            instance = new ServerMenuGadget(Hubble.getInstance().getHubbleConfig().getServerSelectorItem());
        }

        return instance;
    }

    public ServerMenuGadget(ItemStack gadgetItem) {
        super(gadgetItem);
        properties().droppable(false);
    }

    @Override
    public void perform(Player player) {
        Hubble.API.openServerSelector(player);
    }

    @Override
    public void onSwing(Player player) {
        Hubble.API.openServerSelector(player);
    }

    @Override
    public boolean onInteract(Player player, Block block) {
        Hubble.API.openServerSelector(player);
        return false;
    }

    @Override
    public boolean onInteract(Player player, Entity entity) {
        return false;
    }

    @Override
    public boolean onBlockDamage(Player player, Block block) {
        return false;
    }

    @Override
    public boolean onBlockBreak(Player player, Block block) {
        return false;
    }
}

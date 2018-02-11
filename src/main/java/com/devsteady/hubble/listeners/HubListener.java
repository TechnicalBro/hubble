package com.devsteady.hubble.listeners;

import com.devsteady.hubble.Hubble;
import com.devsteady.hubble.gadget.ServerMenuGadget;
import com.devsteady.onyx.chat.Chat;
import com.devsteady.onyx.player.Players;
import com.devsteady.onyx.time.TimeHandler;
import com.devsteady.onyx.time.TimeType;
import com.devsteady.onyx.world.Worlds;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.Set;

public class HubListener implements Listener {

    private Hubble plugin;
    public HubListener(Hubble plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        Players.clearInventory(player,true);

        Players.setHotbarContents(player, ServerMenuGadget.getInstance().getItem());
        if (plugin.getHubbleConfig().isOpenOnJoin()) {
            plugin.getThreadManager().runTaskLater(() -> Hubble.API.openServerSelector(player), TimeHandler.getTimeInTicks(1, TimeType.SECOND));
        }
    }

    @EventHandler
    public void onPlayerSpawn(PlayerSpawnLocationEvent e) {
        e.setSpawnLocation(Worlds.getDefaultWorld().getSpawnLocation());
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntityType() == EntityType.BAT) {
            return;
        }

        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onPlayerCommandEvent(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();

        Set<String> disabledCommands = Hubble.getInstance().getHubbleConfig().getDisabledCommands();

        if (disabledCommands.isEmpty()) {
            return;
        }

        for(String command : disabledCommands) {
            if (e.getMessage().startsWith(command)) {
                Chat.messageCentered(player,"&7---------------","&aThanks for playing","&c&lHCPrisons","","&6We use a mix of public and custom plugins","&7---------------");
                e.setCancelled(true);
                return;
            }
        }

        //disable plugins command.
        if (e.getMessage().startsWith("/pl") || e.getMessage().startsWith("/bukkit:") || e.getMessage().startsWith("/spigot:")) {
            Chat.messageCentered(player,"&7---------------","&aThanks for playing","&c&lHCPrisons","","&6We use a mix of public and custom plugins","&7---------------");
//            TextComponent onyxComponent = new TextComponent(Chat.format("&l&5Onyx"));
//            onyxComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new TextComponent[] {
//                    new TextComponent(Chat.format("&eOnyx is our custom core &7(api)&e.")),
//                    new TextComponent(Chat.format("&r")),
//                    new TextComponent(Chat.format("&eIt packs alot of power, and utility which help")),
//                    new TextComponent(Chat.format("&eus provide a fast and efficient experience."))
//            }));



            e.setCancelled(true);
        }
    }


}

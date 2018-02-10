package com.devsteady.hubble.listeners;

import com.devsteady.hubble.Hubble;
import com.devsteady.onyx.time.TimeHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.devsteady.onyx.time.TimeType.SECOND;

public class ConnectionListener implements Listener {

    private Hubble plugin;
    public ConnectionListener(Hubble plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (plugin.getHubbleConfig().isOpenOnJoin()) {
            plugin.getThreadManager().runTaskLater(new Runnable() {
                @Override
                public void run() {
                    Hubble.API.openServerSelector(e.getPlayer());
                }
            }, TimeHandler.getTimeInTicks(1,SECOND));
        }
    }
}

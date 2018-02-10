package com.devsteady.hubble.users;

import com.devsteady.hubble.Hubble;
import com.devsteady.onyx.game.listener.IUserManagerHandler;
import com.devsteady.onyx.game.players.UserManager;
import org.bukkit.entity.Player;

public class HubUserManager extends UserManager<HubUser> implements IUserManagerHandler{

    private Hubble plugin;
    public HubUserManager(Hubble plugin) {
        this.plugin = plugin;
    }


    @Override
    public void handleJoin(Player player) {
        addUser(new HubUser(player));
    }

    @Override
    public void handleLeave(Player player) {
        removeUser(player.getUniqueId());
    }
}

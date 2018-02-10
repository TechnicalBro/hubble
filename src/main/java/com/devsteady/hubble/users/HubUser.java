package com.devsteady.hubble.users;

import com.devsteady.onyx.player.User;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class HubUser extends User {

    @Getter @Setter
    private boolean teleporting = false;

    public HubUser(Player p) {
        super(p);
    }


}

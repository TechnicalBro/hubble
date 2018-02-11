package com.devsteady.hubble.command;

import com.devsteady.hubble.Hubble;
import com.devsteady.hubble.menu.ServerSelectionMenu;
import com.devsteady.onyx.command.Command;
import com.devsteady.onyx.yml.YamlConfig;
import org.bukkit.entity.Player;

public class ServerMenuCommand extends YamlConfig {

    @Command(identifier = "servermenu")
    public void onServerMenuCommand(Player player) {
        Hubble.API.openServerSelector(player);
    }

}

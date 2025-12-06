package org.example.com.manageGuiPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

    private MenuManager menu;
    public MenuCommand(MenuManager menu) {
        this.menu = menu;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player))return false;

        Player player = (Player) commandSender;
        menu.openMenu(player);

        return false;
    }
}

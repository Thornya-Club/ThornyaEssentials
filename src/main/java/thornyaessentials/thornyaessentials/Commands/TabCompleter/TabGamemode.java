package thornyaessentials.thornyaessentials.Commands.TabCompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabGamemode implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> options = new ArrayList<String>();
        if (args.length > 0){
            if(args.length == 1){
                options.add("creative");
                options.add("survival");
                options.add("adventure");
                options.add("spectator");
            }
            if(args.length == 2){
                Bukkit.getOnlinePlayers().forEach(p -> options.add(p.getName()));
            }
        }

        return options;
    }
}

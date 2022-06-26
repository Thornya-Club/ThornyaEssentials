package thornyaessentials.thornyaessentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import thornyaessentials.thornyaessentials.Utils.Messages;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender snd, Command cmd, String s, String[] args) {

        if(!(snd instanceof Player)){
            //Console dar gm para jogadores
            if(args.length == 2){
                if(Bukkit.getPlayer(args[1]) != null) {
                    if (Bukkit.getPlayer(args[1]).isOnline()) {
                        Player target = Bukkit.getPlayer(args[1]);
                        changeGamemodePlayer(target, args[0], false);
                        Bukkit.getConsoleSender().sendMessage("§aVocê mudou o gamemode de §c" + target.getDisplayName());
                    } else {
                        Bukkit.getConsoleSender().sendMessage("§cJogador não existe.");
                    }
                }else {
                    Bukkit.getConsoleSender().sendMessage("§cJogador não está online.");
                }
            }else {
                Bukkit.getConsoleSender().sendMessage("§cUse /gamemode (gamemode) (player)");
            }
        }else {
            if(cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm")){
                //permission gamemode set
                Player p = (Player) snd;
                if(p.hasPermission("thornya.essentials.gamemode")) {
                    if (args.length == 0) {
                        switchGamemodePlayer(p);
                    } else if (args.length == 1) {
                        changeGamemodePlayer(p, args[0], true);
                    } else if (args.length == 2) {
                        //permission gamemode others
                        if (p.hasPermission("thornya.essentials.gamemode.others")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                if (Bukkit.getPlayer(args[1]).isOnline()) {
                                    Player target = Bukkit.getPlayer(args[1]);
                                    changeGamemodePlayer(target, args[0], false);
                                    p.sendMessage("§aVocê mudou o gamemode de §c" + target.getDisplayName());
                                } else {
                                    p.sendMessage("§cJogador não está online.");
                                }
                            } else {
                                p.sendMessage("§cJogador não existe.");
                            }
                        } else {
                            p.sendMessage("§cUse /gm (gamemode) [player]");
                        }
                    }else {
                        p.sendMessage(Messages.NO_PERMISSION);
                    }
                }else{
                    p.sendMessage(Messages.NO_PERMISSION);
                }
            }
        }

        return false;
    }

    public static Boolean changeGamemodePlayer(Player player, String gameMode, Boolean showMessage){
        GameMode gm;
        //permission gamemodes
        if(gameMode.equalsIgnoreCase("1") || gameMode.equalsIgnoreCase("CREATIVE") || gameMode.equalsIgnoreCase("criativo")){
            if(!player.hasPermission("thornya.essentials.gamemode.creative")) return false;
            gm = GameMode.CREATIVE;
            if(showMessage) player.sendMessage("§aVocê mudou seu gamemode para o §ccriativo");
        }else if(gameMode.equalsIgnoreCase("0") || gameMode.equalsIgnoreCase("SURVIVAL")){
            if(!player.hasPermission("thornya.essentials.gamemode.survival")) return false;
            gm = GameMode.SURVIVAL;
            if(showMessage) player.sendMessage("§aVocê mudou seu gamemode para o §csobrevivência");
        }else if(gameMode.equalsIgnoreCase("2") || gameMode.equalsIgnoreCase("ADVENTURE") || gameMode.equalsIgnoreCase("aventura")){
            if(!player.hasPermission("thornya.essentials.gamemode.adventure")) return false;
            gm = GameMode.ADVENTURE;
            if(showMessage) player.sendMessage("§aVocê mudou seu gamemode para o modo §caventura");
        }else if(gameMode.equalsIgnoreCase("3") || gameMode.equalsIgnoreCase("SPECTATOR") || gameMode.equalsIgnoreCase("espectador")){
            if(!player.hasPermission("thornya.essentials.gamemode.spectator")) return false;
            gm = GameMode.SPECTATOR;
            if(showMessage) player.sendMessage("§aVocê mudou seu gamemode para o §cespectador");
        } else {
            player.sendMessage("§cEsse modo de jogo não existe!");
            return false;
        }
        player.setGameMode(gm);
        return true;
    }
    public static Boolean switchGamemodePlayer(Player player){
        if(player.getGameMode() == GameMode.SURVIVAL){
            changeGamemodePlayer(player, String.valueOf(GameMode.CREATIVE.getValue()), true);
            return true;
        }
        if(player.getGameMode() != GameMode.SURVIVAL){
            changeGamemodePlayer(player, String.valueOf(GameMode.SURVIVAL.getValue()), true);
            return true;
        }
        return false;
    }

}


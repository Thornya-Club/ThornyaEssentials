package thornyaessentials.thornyaessentials;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import thornyaessentials.thornyaessentials.Commands.Gamemode;
import thornyaessentials.thornyaessentials.Commands.TabCompleter.TabGamemode;

import java.util.Objects;

public final class ThornyaEssentials extends JavaPlugin {

    private static ThornyaEssentials pl;

    @Override
    public void onEnable() {
        pl = this;
        registrarComandos();
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ThornyaEssentials instance(){
        return pl;
    }

    protected void registrarComandos(){
        registrarComando("gamemode", new Gamemode(), new TabGamemode());
    }


    protected void registrarComando(String nome, CommandExecutor comando) {
        Objects.requireNonNull(this.getCommand(nome)).setExecutor(comando);
    }
    protected void registrarComando(String nome, CommandExecutor comando, TabCompleter tabCompleter) {
        Objects.requireNonNull(this.getCommand(nome)).setExecutor(comando);
        Objects.requireNonNull(getCommand(nome)).setTabCompleter(tabCompleter);
    }

    protected void registrarListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }
}

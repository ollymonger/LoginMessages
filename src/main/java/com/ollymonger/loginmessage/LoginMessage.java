package com.ollymonger.loginmessage;

import jdk.internal.util.xml.impl.Input;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public final class LoginMessage extends JavaPlugin {

    public String prefix = ChatColor.translateAlternateColorCodes('&', "&6[&7LMSG&6]&r ");

    public void getData() {
        try {
            if(!getDataFolder().exists()){
                getDataFolder().mkdirs(); //makes directory if none exists
            }
            File file = new File(getDataFolder(), "config.yml"); //sets file name and file folder
            if(!file.exists()){ //makes file if none exists
                getLogger().info("CONFIG NOT FOUND, CREATING A NEW CONFIG!");
                getConfig().options().copyDefaults(false);
                saveDefaultConfig(); //saves default config
            } else {
                getLogger().info("CONFIG FOUND! Loading!");
                try{
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                    this.getConfig().load(reader);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                if(!this.getConfig().contains("joinmessages")){
                    this.getConfig().createSection("joinmessages");
                    getLogger().info("Config successfully set up!");
                    saveConfig();
                }
                if(this.getConfig() == null){
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin: LoginMessage is now enabled");
        getLogger().info("Plugin Version: " + getDescription().getVersion());
        getLogger().info("Last Updated: (23/07/19)");
        getData(); //calls getData
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

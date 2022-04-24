package com.ruserious99.bungeechannel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public final class BungeeChannel extends JavaPlugin implements PluginMessageListener {

    @Override
    public void onEnable() {

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        Player player = Bukkit.getPlayer("Player");
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF("Survival");

        if (player != null) {
            player.sendPluginMessage(this, "BungeeCord", out.toByteArray());
        }
    }
    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!channel.equals("BungeeCord")){
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String server = in.readUTF();
        int count = in.readInt();

    }
}


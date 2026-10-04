package me.memencio.asynccatcherfabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class AsyncCatcherFabric implements ModInitializer {

    private static MinecraftServer minecraftServer;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> minecraftServer = server);
    }

    public static MinecraftServer getServer() {
        if (minecraftServer == null) {
            throw new IllegalStateException("Server has not been initialized yet.");
        }
        return minecraftServer;
    }
}

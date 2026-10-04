package me.memencio.asynccatcherfabric;

import net.minecraft.server.MinecraftServer;

import static me.memencio.asynccatcherfabric.AsyncCatcherFabric.getServer;

public class AsyncCatcher {

    public static void catchOp(String reason) {
        if (!getServer().isSameThread()) { // Paper - chunk system
            MinecraftServer.LOGGER.error("Thread {} failed main thread check: {}", Thread.currentThread().getName(), reason, new Throwable()); // Paper
            throw new IllegalStateException("Asynchronous " + reason + "!");
        }
    }
}
package me.memencio.asynccatcherfabric.mixin;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.memencio.asynccatcherfabric.AsyncCatcher.catchOp;

@Mixin(ChunkMap.class)
public class ChunkMapMixin {
    @Inject(method = "addEntity", at = @At("HEAD"))
    private void guardAddEntity(Entity entity, CallbackInfo ci) {
        catchOp("entity track"); // Spigot
    }

    @Inject(method = "removeEntity", at = @At("HEAD"))
    private void guardRemoveEntity(Entity entity, CallbackInfo ci) {
        catchOp("entity untrack"); // Spigot
    }

    @Mixin(targets = "net.minecraft.server.level.ChunkMap$TrackedEntity")
    public static class TrackedEntityMixin {
        @Inject(method = "removePlayer", at = @At("HEAD"))
        private void guardRemovePlayer(ServerPlayer serverPlayer, CallbackInfo ci) {
            catchOp("player tracker clear"); // Spigot
        }

        @Inject(method = "updatePlayer", at = @At("HEAD"))
        private void guardUpdatePlayer(ServerPlayer serverPlayer, CallbackInfo ci) {
            catchOp("player tracker update"); // Spigot
        }
    }
}

package me.memencio.asynccatcherfabric.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.LevelEntityGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.memencio.asynccatcherfabric.AsyncCatcher.catchOp;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(method = "addEntity", at = @At("HEAD"))
    private void guardAddEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        catchOp("entity add"); // Spigot
    }

    @Inject(method = "getEntities()Lnet/minecraft/world/level/entity/LevelEntityGetter;", at = @At("HEAD"))
    private void guardGetEntities(CallbackInfoReturnable<LevelEntityGetter<Entity>> cir) {
        catchOp("Chunk getEntities call"); // Spigot
    }

    @Mixin(targets = "net.minecraft.server.level.ServerLevel$EntityCallbacks")
    public static class EntityCallbacksMixin {
        @Inject(method = "onTrackingStart(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"))
        private void guardOnTrackingStart(Entity entity, CallbackInfo ci) {
            catchOp("entity register"); // Spigot
        }

        @Inject(method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V", at = @At("HEAD"))
        private void guardOnTrackingEnd(Entity entity, CallbackInfo ci) {
            catchOp("entity unregister"); // Spigot
        }
    }
}

package me.memencio.asynccatcherfabric.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import net.minecraft.world.level.entity.Visibility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.memencio.asynccatcherfabric.AsyncCatcher.catchOp;

@Mixin(PersistentEntitySectionManager.class)
public class PersistentEntitySectionManagerMixin<T extends EntityAccess> {
    @Inject(method = "addEntityUuid", at = @At("HEAD"))
    private void guardAddEntityUuid(T entityAccess, CallbackInfoReturnable<Boolean> cir) {
        catchOp("Entity add by UUID"); // Paper
    }

    @Inject(method = "addEntity", at = @At("HEAD"))
    private void guardAddEntity(T entityAccess, boolean bl, CallbackInfoReturnable<Boolean> cir) {
        catchOp("Entity add"); // Paper
    }

    @Inject(method = "startTicking", at = @At("HEAD"))
    private void guardStartTicking(T entityAccess, CallbackInfo ci) {
        catchOp("Entity start ticking"); // Paper
    }

    @Inject(method = "stopTicking", at = @At("HEAD"))
    private void guardStopTicking(T entityAccess, CallbackInfo ci) {
        catchOp("Entity stop ticking"); // Paper
    }

    @Inject(method = "startTracking", at = @At("HEAD"))
    private void guardStartTracking(T entityAccess, CallbackInfo ci) {
        catchOp("Entity start tracking"); // Paper
    }

    @Inject(method = "stopTracking", at = @At("HEAD"))
    private void guardStopTracking(T entityAccess, CallbackInfo ci) {
        catchOp("Entity stop tracking"); // Paper
    }

    @Inject(method = "updateChunkStatus(Lnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/entity/Visibility;)V", at = @At("HEAD"))
    private void guardUpdateChunkStatus(ChunkPos chunkPos, Visibility visibility, CallbackInfo ci) {
        catchOp("Update chunk status"); // Paper
    }

    @Inject(method = "ensureChunkQueuedForLoad", at = @At("HEAD"))
    private void guardEnsureChunkQueuedForLoad(long l, CallbackInfo ci) {
        catchOp("Entity chunk save"); // Paper
    }

    @Inject(method = "requestChunkLoad", at = @At("HEAD"))
    private void guardRequestChunkLoad(long l, CallbackInfo ci) {
        catchOp("Entity chunk load request"); // Paper
    }

    @Inject(method = "processChunkUnload", at = @At("HEAD"))
    private void guardProcessChunkUnload(long l, CallbackInfoReturnable<Boolean> cir) {
        catchOp("Entity chunk unload process"); // Paper
    }

    @Inject(method = "processPendingLoads", at = @At("HEAD"))
    private void guardProcessPendingLoads(CallbackInfo ci) {
        catchOp("Entity chunk process pending loads"); // Paper
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void guardTick(CallbackInfo ci) {
        catchOp("Entity manager tick"); // Paper
    }

    @Inject(method = "autoSave", at = @At("HEAD"))
    private void guardAutoSave(CallbackInfo ci) {
        catchOp("Entity manager autosave"); // Paper
    }

    @Inject(method = "saveAll", at = @At("HEAD"))
    private void guardSaveAll(CallbackInfo ci) {
        catchOp("Entity manager save"); // Paper
    }

    @Mixin(targets = "net.minecraft.world.level.entity.PersistentEntitySectionManager$Callback")
    public static class CallbackMixin {
        @Inject(method = "onMove", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/entity/EntitySection;getStatus()Lnet/minecraft/world/level/entity/Visibility;"))
        private void guardOnMove(CallbackInfo ci) {
            catchOp("Entity move"); // Paper
        }

        @Inject(method = "onRemove", at = @At("HEAD"))
        private void guardOnRemove(Entity.RemovalReason removalReason, CallbackInfo ci) {
            catchOp("Entity remove"); // Paper
        }
    }
}

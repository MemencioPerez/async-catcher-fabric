package me.memencio.asynccatcherfabric.mixin;

import net.minecraft.server.level.DistanceManager;
import net.minecraft.server.level.Ticket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.memencio.asynccatcherfabric.AsyncCatcher.catchOp;

@Mixin(DistanceManager.class)
public class DistanceManagerMixin {
    @Inject(method = "addTicket(JLnet/minecraft/server/level/Ticket;)V", at = @At("HEAD"))
    private void guardAddTicket(long l, Ticket<?> ticket, CallbackInfo ci) {
        catchOp("DistanceManager::addTicket"); // Paper
    }

    @Inject(method = "removeTicket(JLnet/minecraft/server/level/Ticket;)V", at = @At("HEAD"))
    private void guardRemoveTicket(long l, Ticket<?> ticket, CallbackInfo ci) {
        catchOp("DistanceManager::removeTicket"); // Paper
    }
}

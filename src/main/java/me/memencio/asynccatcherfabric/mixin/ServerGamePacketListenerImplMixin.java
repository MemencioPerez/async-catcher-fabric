package me.memencio.asynccatcherfabric.mixin;

import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.RelativeMovement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

import static me.memencio.asynccatcherfabric.AsyncCatcher.catchOp;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
    @Inject(method = "teleport(DDDFFLjava/util/Set;)V", at = @At("HEAD"))
    private void guardTeleport(double d, double e, double f, float g, float h, Set<RelativeMovement> set, CallbackInfo ci) {
        catchOp("teleport"); // Paper
    }
}

package me.memencio.asynccatcherfabric.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BaseCommandBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.memencio.asynccatcherfabric.AsyncCatcher.catchOp;

@Mixin(BaseCommandBlock.class)
public class BaseCommandBlockMixin {
    @Inject(method = "sendSystemMessage", at = @At("HEAD"))
    private void guardSendSystemMessage(Component component, CallbackInfo ci) {
        catchOp("sendSystemMessage to a command block"); // Paper - Don't broadcast messages to command blocks
    }
}

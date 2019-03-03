package nerdhub.deathmaps.mixins;

import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ServerPlayerEntity.class, priority = 5000)
public class MixinPlayerManager {

    @Inject(method = "method_14203", at = @At("RETURN"))
    private void onRespawn(ServerPlayerEntity serverPlayerEntity_1, boolean boolean_1, CallbackInfo ci){

        if (!boolean_1){
            ItemStack itemStack = FilledMapItem.method_8005(serverPlayerEntity_1.getServerWorld(), serverPlayerEntity_1.getPos().getX(), serverPlayerEntity_1.getPos().getZ(), (byte) 2,true,true);
            FilledMapItem.method_8002(serverPlayerEntity_1.getServerWorld(), itemStack);
            MapState.method_110(itemStack, serverPlayerEntity_1.getPos(),"Death", MapIcon.Type.TARGET_X);
            itemStack.setDisplayName(new StringTextComponent("Grave Digger's Map").setStyle(new Style().setColor(TextFormat.RED)));
            ((ServerPlayerEntity) (Object) this).inventory.insertStack(itemStack);
        }
    }
}

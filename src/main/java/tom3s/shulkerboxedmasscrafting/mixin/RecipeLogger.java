package tom3s.shulkerboxedmasscrafting.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.screen.ScreenHandler;

import tom3s.shulkerboxedmasscrafting.MassCrafter;

@Mixin(CraftingInventory.class)
public class RecipeLogger extends Object{

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/screen/ScreenHandler;II)V")
    private void logRecipes(ScreenHandler handler, int width, int height, CallbackInfo callbackInfo){
        // this.width = width;
        // this.height = height;
        int temp_width = ((CraftingInventory)(Object)this).getWidth();
        int temp_height = ((CraftingInventory)(Object)this).getHeight();
        MassCrafter.LOGGER.info("Crafting inventory in action: width " + temp_width + " - height " + temp_height);
        // MassCrafter.LOGGER.info("");
    }
    
    @Inject(at = @At("TAIL"), method = "provideRecipeInputs(Lnet/minecraft/recipe/RecipeMatcher;)V")
    private void getIngredients(RecipeMatcher finder, CallbackInfo callbackInfo){
        int temp_width = ((CraftingInventory)(Object)this).getWidth();
        int temp_height = ((CraftingInventory)(Object)this).getHeight();
        for (int i = 0; i < temp_width * temp_height; i++){
            ItemStack stack = ((CraftingInventory)(Object)this).getStack(i);
            MassCrafter.LOGGER.info("Slot "+ i + " " + stack.getName().toString());
        }
    }
}

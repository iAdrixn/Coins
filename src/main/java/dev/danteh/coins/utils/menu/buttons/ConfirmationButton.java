/*    */ package dev.danteh.coins.utils.menu.buttons;
/*    */ import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;


/*    */ import dev.danteh.coins.utils.menu.callback.TypeCallback;
import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class ConfirmationButton extends Button {
/*    */   @ConstructorProperties({"confirm", "callback", "closeAfterResponse"})
/*    */   public ConfirmationButton(boolean confirm, TypeCallback<Boolean> callback, boolean closeAfterResponse) {
/* 13 */     this.confirm = confirm; this.callback = callback; this.closeAfterResponse = closeAfterResponse;
/*    */   }
/*    */ 
/*    */   
/*    */   private final boolean confirm;
/*    */   private final TypeCallback<Boolean> callback;
/*    */   private final boolean closeAfterResponse;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 22 */     return (new ItemBuilder(Material.WOOL))
/* 23 */       .data(this.confirm ? 5 : 14)
/* 24 */       .name(this.confirm ? "&aConfirm" : "&cCancel")
/* 25 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int i, ClickType clickType, int hb) {
/* 30 */     if (this.confirm) {
/* 31 */       player.playSound(player.getLocation(), Sound.NOTE_PIANO, 20.0F, 0.1F);
/*    */     } else {
/*    */       
/* 34 */       player.playSound(player.getLocation(), Sound.DIG_GRAVEL, 20.0F, 0.1F);
/*    */     } 
/*    */     
/* 37 */     if (this.closeAfterResponse) {
/* 38 */       player.closeInventory();
/*    */     }
/*    */     
/* 41 */     this.callback.callback(Boolean.valueOf(this.confirm));
/*    */   }
/*    */ }
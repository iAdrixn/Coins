/*    */ package dev.danteh.coins.utils.menu.buttons;
/*    */ import dev.danteh.coins.utils.menu.Button;
import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class DisplayButton extends Button {
/*    */   private ItemStack itemStack;
/*    */   
/*    */   @ConstructorProperties({"itemStack", "cancel"})
/*    */   public DisplayButton(ItemStack itemStack, boolean cancel) {
/* 12 */     this.itemStack = itemStack; this.cancel = cancel;
/*    */   } private boolean cancel; public void setItemStack(ItemStack itemStack) {
/* 14 */     this.itemStack = itemStack; } public void setCancel(boolean cancel) { this.cancel = cancel; }
/*    */ 
/*    */   
/* 17 */   public ItemStack getItemStack() { return this.itemStack; } public boolean isCancel() {
/* 18 */     return this.cancel;
/*    */   }
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 22 */     if (this.itemStack == null) {
/* 23 */       return new ItemStack(Material.AIR);
/*    */     }
/* 25 */     return this.itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldCancel(Player player, int slot, ClickType clickType) {
/* 31 */     return this.cancel;
/*    */   }
/*    */ }
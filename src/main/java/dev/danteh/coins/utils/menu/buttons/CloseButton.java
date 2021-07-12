/*    */ package dev.danteh.coins.utils.menu.buttons;
/*    */ 
/*    */ import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CloseButton
/*    */   extends Button
/*    */ {
/*    */   public ItemStack getButtonItem(Player player) {
/* 14 */     return (new ItemBuilder(Material.INK_SACK))
/* 15 */       .data(1)
/* 16 */       .name("&cClose")
/* 17 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int i, ClickType clickType, int hb) {
/* 22 */     playNeutral(player);
/* 23 */     player.closeInventory();
/*    */   }
/*    */ }

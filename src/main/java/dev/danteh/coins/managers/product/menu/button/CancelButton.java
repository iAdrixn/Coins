/*    */ package dev.danteh.coins.managers.product.menu.button;
/*    */ import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.product.Product;
import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class CancelButton extends Button {
/*    */   @ConstructorProperties({"product"})
/*    */   public CancelButton(Product product) {
/* 14 */     this.product = product;
/*    */   }
/*    */ 
/*    */   
/*    */   private final Product product;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 21 */     return (new ItemBuilder(Material.STAINED_CLAY))
/* 22 */       .data(14)
/* 23 */       .name(Coins.getInstance().getMainConfig().getString("CONFIRM-MENU.CANCEL.ICON.DISPLAYNAME"))
/* 24 */       .lore(Coins.getInstance().getMainConfig().getStringList("CONFIRM-MENU.CANCEL.ICON.DESCRIPTION"))
/* 25 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
/* 30 */     playFail(player);
/* 31 */     player.closeInventory();
/* 32 */     CC.message(player, Coins.getInstance().getMainConfig().getString("NO-PURCHASED")
/* 33 */         .replace("%PRODUCT%", this.product.getDisplayName()));
/*    */   }
/*    */ }

/*    */ package dev.danteh.coins.managers.category.menu.button;
/*    */ import dev.danteh.coins.managers.category.Category;
import dev.danteh.coins.managers.product.menu.ProductMenu;
import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class CategoryButton extends Button {
/*    */   @ConstructorProperties({"category"})
/*    */   public CategoryButton(Category category) {
/* 12 */     this.category = category;
/*    */   }
/*    */ 
/*    */   
/*    */   private final Category category;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 19 */     return (new ItemBuilder(this.category.getIcon()))
/* 20 */       .data(this.category.getData())
/* 21 */       .name(this.category.getDisplayName())
/* 22 */       .lore(this.category.getDescription(player))
/* 23 */       .glow(this.category.isGlow())
/* 24 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
/* 29 */     if (!this.category.isSubMenu())
/* 30 */       return;  playNeutral(player);
/* 31 */     (new ProductMenu(this.category.getName())).openMenu(player);
/*    */   }
/*    */ }
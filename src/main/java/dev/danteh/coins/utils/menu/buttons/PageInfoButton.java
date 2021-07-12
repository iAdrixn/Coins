/*    */ package dev.danteh.coins.utils.menu.buttons;
/*    */ import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
/*    */ import dev.danteh.coins.utils.menu.pagination.PaginatedMenu;
import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class PageInfoButton extends Button {
/*    */   @ConstructorProperties({"paginatedMenu"})
/*    */   public PageInfoButton(PaginatedMenu paginatedMenu) {
/* 12 */     this.paginatedMenu = paginatedMenu;
/*    */   }
/*    */ 
/*    */   
/*    */   private final PaginatedMenu paginatedMenu;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 19 */     return (new ItemBuilder(Material.NETHER_STAR))
/* 20 */       .name("&ePage Info")
/* 21 */       .lore(new String[] { "&e" + this.paginatedMenu.getPage() + "&7/&a" + this.paginatedMenu.getPages(player)
/* 22 */         }).build();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCancel(Player player, int slot, ClickType clickType) {
/* 27 */     return true;
/*    */   }
/*    */ }
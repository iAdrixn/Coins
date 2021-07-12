/*    */ package dev.danteh.coins.utils.menu.pagination;
/*    */ import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class JumpToPageButton extends Button {
/*    */   @ConstructorProperties({"page", "menu", "current"})
/*    */   public JumpToPageButton(int page, PaginatedMenu menu, boolean current) {
/* 12 */     this.page = page; this.menu = menu; this.current = current;
/*    */   }
/*    */ 
/*    */   
/*    */   private final int page;
/*    */   private final PaginatedMenu menu;
/*    */   private final boolean current;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 21 */     ItemBuilder itemBuilder = new ItemBuilder(this.current ? Material.ENCHANTED_BOOK : Material.BOOK, this.page);
/* 22 */     itemBuilder.name(CC.translate("&cPage " + this.page));
/*    */     
/* 24 */     if (this.current) {
/* 25 */       itemBuilder.lore(new String[] { "", 
/*    */             
/* 27 */             CC.translate("&aCurrent page") });
/*    */     }
/*    */ 
/*    */     
/* 31 */     return itemBuilder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int i, ClickType clickType, int hb) {
/* 36 */     this.menu.modPage(player, this.page - this.menu.getPage());
/* 37 */     Button.playNeutral(player);
/*    */   }
/*    */ }
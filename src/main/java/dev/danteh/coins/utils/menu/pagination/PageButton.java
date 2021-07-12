/*    */ package dev.danteh.coins.utils.menu.pagination;
/*    */ import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class PageButton extends Button {
/*    */   @ConstructorProperties({"mod", "menu"})
/*    */   public PageButton(int mod, PaginatedMenu menu) {
/* 11 */     this.mod = mod; this.menu = menu;
/*    */   }
/*    */ 
/*    */   
/*    */   private final int mod;
/*    */   private final PaginatedMenu menu;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 19 */     ItemBuilder itemBuilder = new ItemBuilder(Material.CARPET);
/*    */     
/* 21 */     if (this.mod > 0) {
/* 22 */       itemBuilder.data(13);
/*    */     } else {
/*    */       
/* 25 */       itemBuilder.data(14);
/*    */     } 
/*    */     
/* 28 */     if (hasNext(player)) {
/* 29 */       itemBuilder.name((this.mod > 0) ? "&aNext page" : "&cPrevious page");
/*    */     } else {
/*    */       
/* 32 */       itemBuilder.name("&7" + ((this.mod > 0) ? "Last page" : "First page"));
/*    */     } 
/*    */     
/* 35 */     itemBuilder.lore(new String[] { "", "&eRight click to", "&ejump to a page" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 41 */     return itemBuilder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int i, ClickType clickType, int hb) {
/* 46 */     if (hasNext(player)) {
/* 47 */       this.menu.modPage(player, this.mod);
/* 48 */       Button.playNeutral(player);
/*    */     } else {
/* 50 */       Button.playFail(player);
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean hasNext(Player player) {
/* 55 */     int pg = this.menu.getPage() + this.mod;
/* 56 */     return (pg > 0 && this.menu.getPages(player) >= pg);
/*    */   }
/*    */ }

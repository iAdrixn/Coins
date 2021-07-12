/*    */ package dev.danteh.coins.utils.menu.buttons;
/*    */ import dev.danteh.coins.utils.item.ItemBuilder;
            import dev.danteh.coins.utils.menu.Button;
            import dev.danteh.coins.utils.menu.Menu;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class BackButton extends Button {
/*    */   @ConstructorProperties({"back"})
/*    */   public BackButton(Menu back) {
/* 12 */     this.back = back;
/*    */   }
/*    */ 
/*    */   
/*    */   private final Menu back;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 19 */     return (new ItemBuilder(Material.BED))
/* 20 */       .name("&cGo back")
/* 21 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int i, ClickType clickType, int hb) {
/* 26 */     Button.playNeutral(player);
/* 27 */     this.back.openMenu(player);
/*    */   }
/*    */ }
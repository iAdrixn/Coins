/*    */ package dev.danteh.coins.utils.menu.buttons;
/*    */ 
/*    */ import dev.danteh.coins.utils.item.ItemBuilder;
            import dev.danteh.coins.utils.menu.Button;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class AirButton
/*    */   extends Button
/*    */ {
/*    */   public ItemStack getButtonItem(Player player) {
/* 14 */     return (new ItemBuilder(Material.AIR)).build();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCancel(Player player, int slot, ClickType clickType) {
/* 19 */     return true;
/*    */   }
/*    */ }

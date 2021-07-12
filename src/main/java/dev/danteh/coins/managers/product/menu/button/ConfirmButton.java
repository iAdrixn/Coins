/*    */ package dev.danteh.coins.managers.product.menu.button;
/*    */ import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.aquacore.AquaCoreManager;
import dev.danteh.coins.managers.product.Product;
import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.item.ItemBuilder;
import dev.danteh.coins.utils.menu.Button;
import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.beans.ConstructorProperties;

/*    */
/*    */ public class ConfirmButton extends Button {
/*    */   @ConstructorProperties({"product"})
/*    */   public ConfirmButton(Product product) {
/* 16 */     this.product = product;
/*    */   }
/*    */ 
/*    */   
/*    */   private final Product product;
/*    */   
/*    */   public ItemStack getButtonItem(Player player) {
/* 23 */     return (new ItemBuilder(Material.STAINED_CLAY))
/* 24 */       .data(5)
/* 25 */       .name(Coins.getInstance().getMainConfig().getString("CONFIRM-MENU.CONFIRM.ICON.DISPLAYNAME"))
/* 26 */       .lore(Coins.getInstance().getMainConfig().getStringList("CONFIRM-MENU.CONFIRM.ICON.DESCRIPTION"))
/* 27 */       .build();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
/* 32 */     AquaCoreManager.setCoins(player, AquaCoreManager.getCoins(player) - this.product.getCoins());
/* 33 */     playSuccess(player);
/* 34 */     player.closeInventory();
/* 35 */     CC.message(player, Coins.getInstance().getMainConfig().getString("PURCHASED")
/* 36 */         .replace("%PRODUCT%", this.product.getDisplayName()));
/* 37 */     this.product.getCommands().forEach(command -> Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command.replace("%PLAYER%", player.getName())));
/*    */   }
/*    */ }

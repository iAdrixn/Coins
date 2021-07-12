/*    */ package dev.danteh.coins.managers.category;
/*    */ import dev.danteh.coins.managers.aquacore.AquaCoreManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

/*    */
/*    */ public class Category {
/*    */   private final String name;
/*    */   private final boolean glow;
/*    */   private final String displayName;
/*    */   private final List<String> description;
/*    */   
/*    */   @ConstructorProperties({"name", "glow", "displayName", "description", "icon", "data", "slot", "subMenu"})
/*    */   public Category(String name, boolean glow, String displayName, List<String> description, Material icon, int data, int slot, boolean subMenu) {
/* 12 */     this.name = name; this.glow = glow; this.displayName = displayName; this.description = description; this.icon = icon; this.data = data; this.slot = slot; this.subMenu = subMenu;
/*    */   }
/*    */   private final Material icon; private final int data; private final int slot; private final boolean subMenu;
/*    */   public String getName() {
/* 16 */     return this.name;
/* 17 */   } public boolean isGlow() { return this.glow; }
/* 18 */   public String getDisplayName() { return this.displayName; }
/* 19 */   public List<String> getDescription() { return this.description; }
/* 20 */   public Material getIcon() { return this.icon; }
/* 21 */   public int getData() { return this.data; }
/* 22 */   public int getSlot() { return this.slot; } public boolean isSubMenu() {
/* 23 */     return this.subMenu;
/*    */   }
/*    */   public List<String> getDescription(Player player) {
/* 26 */     return (List<String>)this.description.stream().map(lore -> lore.replace("%PLAYER_COINS%", String.valueOf(AquaCoreManager.getCoins(player))))
/*    */       
/* 28 */       .collect(Collectors.toList());
/*    */   }
/*    */ }

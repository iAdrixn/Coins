/*    */ package dev.danteh.coins.managers.product;

import org.bukkit.Material;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

public class Product {
/*    */   private final String category;
/*    */   private final String name;
/*    */   private final boolean glow;
/*    */   private final String displayName;
/*    */   private final List<String> description;
/*    */   
/*    */   @ConstructorProperties({"category", "name", "glow", "displayName", "description", "commands", "icon", "data", "slot", "coins"})
/*    */   public Product(String category, String name, boolean glow, String displayName, List<String> description, List<String> commands, Material icon, int data, int slot, int coins) {
/* 10 */     this.category = category; this.name = name; this.glow = glow; this.displayName = displayName; this.description = description; this.commands = commands; this.icon = icon; this.data = data; this.slot = slot; this.coins = coins;
/*    */   }
/*    */   private final List<String> commands; private final Material icon; private final int data; private final int slot; private final int coins;
/*    */   public String getCategory() {
/* 14 */     return this.category;
/* 15 */   } public String getName() { return this.name; }
/* 16 */   public boolean isGlow() { return this.glow; } public String getDisplayName() {
/* 17 */     return this.displayName;
/*    */   }
/* 19 */   public List<String> getCommands() { return this.commands; }
/* 20 */   public Material getIcon() { return this.icon; }
/* 21 */   public int getData() { return this.data; }
/* 22 */   public int getSlot() { return this.slot; } public int getCoins() {
/* 23 */     return this.coins;
/*    */   }
/*    */   public List<String> getDescription() {
/* 26 */     return (List<String>)this.description.stream().map(lore -> lore.replace("%COINS%", String.valueOf(getCoins())))
/* 27 */       .collect(Collectors.toList());
/*    */   }
/*    */ }

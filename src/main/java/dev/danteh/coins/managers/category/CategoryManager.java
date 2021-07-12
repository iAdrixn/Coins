/*    */ package dev.danteh.coins.managers.category;
/*    */ 
/*    */ import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.category.Category;
import dev.danteh.coins.utils.CC;
import dev.danteh.coins.utils.file.FileConfig;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CategoryManager
/*    */ {
    /* 16 */   private final List<Category> categories = new ArrayList<>(); public List<Category> getCategories() { return this.categories; }
/* 17 */    private final FileConfig mainConfig =Coins.getInstance().getMainConfig(); public FileConfig getMainConfig() { return this.mainConfig; }
/*    */   
/*    */   public void loadCategories() {
/* 20 */     this.categories.clear();
/*    */     
/* 22 */     ConfigurationSection section = this.mainConfig.getConfiguration().getConfigurationSection("SHOP-MENU.CATEGORY");
/*    */     
/* 24 */     section.getKeys(false).forEach(category -> {
/*    */           boolean glow = this.mainConfig.getBoolean("SHOP-MENU.CATEGORY." + category + ".ICON.GLOW");
/*    */           
/*    */           String displayName = this.mainConfig.getString("SHOP-MENU.CATEGORY." + category + ".ICON.DISPLAYNAME");
/*    */           List<String> description = this.mainConfig.getStringList("SHOP-MENU.CATEGORY." + category + ".ICON.DESCRIPTION");
/*    */           String material = this.mainConfig.getString("SHOP-MENU.CATEGORY." + category + ".ICON.MATERIAL");
/*    */           int data = this.mainConfig.getInt("SHOP-MENU.CATEGORY." + category + ".ICON.DATA");
/*    */           int slot = this.mainConfig.getInt("SHOP-MENU.CATEGORY." + category + ".ICON.SLOT");
/*    */           boolean subMenu = this.mainConfig.getBoolean("SHOP-MENU.CATEGORY." + category + ".SUB-MENU");
/*    */           this.categories.add(new Category(category, glow, displayName, description, Material.valueOf(material), data, slot, subMenu));
/*    */         });
/*    */   }
/*    */ }

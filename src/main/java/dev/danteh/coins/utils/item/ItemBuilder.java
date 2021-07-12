/*     */ package dev.danteh.coins.utils.item;
/*     */ 
/*     */
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import dev.danteh.coins.utils.CC;
import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.enchantments.EnchantmentTarget;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.inventory.meta.SkullMeta;
/*     */ 
/*     */ 
/*     */ public class ItemBuilder
/*     */ {
/*     */   private final ItemStack itemStack;
/*     */   
/*     */   public ItemBuilder(Material material) {
/*  23 */     this.itemStack = new ItemStack(material, 1);
/*     */   }
/*     */   
/*     */   public ItemBuilder(String material) {
/*  27 */     this.itemStack = new ItemStack(Material.valueOf(material), 1);
/*     */   }
/*     */   
/*     */   public ItemBuilder(int material) {
/*  31 */     this.itemStack = new ItemStack(material, 1);
/*     */   }
/*     */   
/*     */   public ItemBuilder(ItemStack itemStack) {
/*  35 */     this.itemStack = itemStack.clone();
/*     */   }
/*     */   
/*     */   public ItemBuilder(Material material, int damage) {
/*  39 */     this.itemStack = new ItemStack(material, 1, (short)damage);
/*     */   }
/*     */   
/*     */   public ItemBuilder(Material material, int amount, int damage) {
/*  43 */     this.itemStack = new ItemStack(material, amount, (short)damage);
/*     */   }
/*     */   
/*     */   public ItemBuilder name(String name) {
/*  47 */     if (name != null) {
/*  48 */       name = CC.translate(name);
/*  49 */       ItemMeta meta = this.itemStack.getItemMeta();
/*  50 */       meta.setDisplayName(name);
/*  51 */       this.itemStack.setItemMeta(meta);
/*     */     } 
/*  53 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder lore(List<String> lore) {
/*  57 */     if (lore != null) {
/*  58 */       ItemMeta meta = this.itemStack.getItemMeta();
/*  59 */       meta.setLore(CC.translate(lore));
/*  60 */       this.itemStack.setItemMeta(meta);
/*     */     } 
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder lore(String... lore) {
/*  66 */     if (lore != null) {
/*  67 */       ItemMeta meta = this.itemStack.getItemMeta();
/*  68 */       meta.setLore(CC.translate(Arrays.asList(lore)));
/*  69 */       this.itemStack.setItemMeta(meta);
/*     */     } 
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder amount(int amount) {
/*  75 */     this.itemStack.setAmount(amount);
/*  76 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder enchant(boolean enchanted) {
/*  80 */     if (enchanted) {
/*  81 */       ItemMeta meta = this.itemStack.getItemMeta();
/*  82 */       meta.addEnchant(Enchantment.DURABILITY, 1, true);
/*  83 */       this.itemStack.setItemMeta(meta);
/*     */     } 
/*  85 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder enchant(boolean enchanted, int level) {
/*  89 */     if (enchanted) {
/*  90 */       ItemMeta meta = this.itemStack.getItemMeta();
/*  91 */       meta.addEnchant(Enchantment.DURABILITY, level, true);
/*  92 */       this.itemStack.setItemMeta(meta);
/*     */     } 
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder enchant(boolean enchanted, Enchantment enchant, int level) {
/*  98 */     if (enchanted) {
/*  99 */       ItemMeta meta = this.itemStack.getItemMeta();
/* 100 */       meta.addEnchant(enchant, level, true);
/* 101 */       this.itemStack.setItemMeta(meta);
/*     */     } 
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder data(int dur) {
/* 107 */     this.itemStack.setDurability((short)dur);
/* 108 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder owner(String owner) {
/* 112 */     if (this.itemStack.getType() == Material.SKULL_ITEM) {
/* 113 */       SkullMeta meta = (SkullMeta)this.itemStack.getItemMeta();
/* 114 */       meta.setOwner(owner);
/* 115 */       this.itemStack.setItemMeta((ItemMeta)meta);
/* 116 */       return this;
/*     */     } 
/*     */     
/* 119 */     throw new IllegalArgumentException("setOwner() only applicable for Skull Item");
/*     */   }
/*     */   
/*     */   public ItemBuilder armorColor(Color color) {
/*     */     try {
/* 124 */       LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)this.itemStack.getItemMeta();
/* 125 */       leatherArmorMeta.setColor(color);
/* 126 */       this.itemStack.setItemMeta((ItemMeta)leatherArmorMeta);
/*     */     }
/* 128 */     catch (Exception exception) {
/* 129 */       Bukkit.getConsoleSender().sendMessage("Error set armor color.");
/*     */     } 
/* 131 */     return this;
/*     */   }
/*     */   
/*     */   public ItemBuilder glow(boolean glow) {
/* 135 */     if (glow) {
/* 136 */       ItemMeta meta = this.itemStack.getItemMeta();
/*     */       
/* 138 */       meta.addEnchant(new Glow(), 1, true);
/* 139 */       this.itemStack.setItemMeta(meta);
/*     */     } 
/* 141 */     return this;
/*     */   }
/*     */   
/*     */   public static void registerGlow() {
/*     */     try {
/* 146 */       Field f = Enchantment.class.getDeclaredField("acceptingNew");
/* 147 */       f.setAccessible(true);
/* 148 */       f.set((Object)null, Boolean.valueOf(true));
/* 149 */     } catch (Exception e) {
/* 150 */       e.printStackTrace();
/*     */     } 
/*     */     try {
/* 153 */       Glow glow = new Glow();
/* 154 */       Enchantment.registerEnchantment(glow);
/* 155 */     } catch (Exception e) {
/* 156 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static class Glow
/*     */     extends Enchantment {
/*     */     public Glow() {
/* 163 */       super(25);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canEnchantItem(ItemStack arg0) {
/* 168 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean conflictsWith(Enchantment arg0) {
/* 173 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public EnchantmentTarget getItemTarget() {
/* 178 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxLevel() {
/* 183 */       return 2;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 188 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getStartLevel() {
/* 193 */       return 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack build() {
/* 198 */     return this.itemStack;
/*     */   }
/*     */ }
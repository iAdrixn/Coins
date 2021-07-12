/*    */ package dev.danteh.coins.utils.menu.pagination;
/*    */
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import dev.danteh.coins.utils.menu.Button;
import dev.danteh.coins.utils.menu.Menu;
import org.bukkit.entity.Player;
/*    */ 
/*    */ public abstract class PaginatedMenu extends Menu {
/*    */   private int page;
/*    */   
/*    */   public PaginatedMenu() {
/* 13 */     this.page = 1;
/*    */ 
/*    */ 
/*    */     
/* 17 */     setUpdateAfterClick(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTitle(Player player) {
/* 22 */     return getPrePaginatedTitle(player) + " - " + this.page + "/" + getPages(player);
/*    */   } public int getPage() {
/*    */     return this.page;
/*    */   } public final void modPage(Player player, int mod) {
/* 26 */     this.page += mod;
/* 27 */     getButtons().clear();
/* 28 */     openMenu(player);
/*    */   }
/*    */   
/*    */   public final int getPages(Player player) {
/* 32 */     int buttonAmount = getAllPagesButtons(player).size();
/*    */     
/* 34 */     if (buttonAmount == 0) {
/* 35 */       return 1;
/*    */     }
/*    */     
/* 38 */     return (int)Math.ceil(buttonAmount / getMaxItemsPerPage(player));
/*    */   }
/*    */ 
/*    */   
/*    */   public final Map<Integer, Button> getButtons(Player player) {
/* 43 */     int minIndex = (int)((this.page - 1) * getMaxItemsPerPage(player));
/* 44 */     int maxIndex = (int)(this.page * getMaxItemsPerPage(player));
/*    */     
/* 46 */     HashMap<Integer, Button> buttons = new HashMap<>();
/*    */     
/* 48 */     for (Map.Entry<Integer, Button> entry : getAllPagesButtons(player).entrySet()) {
/* 49 */       int ind = ((Integer)entry.getKey()).intValue();
/*    */       
/* 51 */       if (ind >= minIndex && ind < maxIndex) {
/* 52 */         ind -= (int)(getMaxItemsPerPage(player) * (this.page - 1));
/* 53 */         buttons.put(Integer.valueOf(ind), entry.getValue());
/*    */       } 
/*    */     } 
/*    */     
/* 57 */     Map<Integer, Button> global = getGlobalButtons(player);
/*    */     
/* 59 */     if (global != null) {
/* 60 */       for (Map.Entry<Integer, Button> gent : global.entrySet()) {
/* 61 */         buttons.put(gent.getKey(), gent.getValue());
/*    */       }
/*    */     }
/*    */     
/* 65 */     return buttons;
/*    */   }
/*    */   
/*    */   public int getMaxItemsPerPage(Player player) {
/* 69 */     return 18;
/*    */   }
/*    */   
/*    */   public Map<Integer, Button> getGlobalButtons(Player player) {
/* 73 */     return null;
/*    */   }
/*    */   
/*    */   public abstract String getPrePaginatedTitle(Player paramPlayer);
/*    */   
/*    */   public abstract Map<Integer, Button> getAllPagesButtons(Player paramPlayer);
/*    */ }

/*    */ package dev.danteh.coins.utils.menu.pagination;
/*    */ 
/*    */ import dev.danteh.coins.utils.menu.Button;
import dev.danteh.coins.utils.menu.Menu;
import dev.danteh.coins.utils.menu.buttons.BackButton;
import dev.danteh.coins.utils.menu.pagination.JumpToPageButton;
import dev.danteh.coins.utils.menu.pagination.PaginatedMenu;;
/*    */ import java.beans.ConstructorProperties;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import lombok.NonNull;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ViewAllPagesMenu extends Menu {
/*    */   @ConstructorProperties({"menu"})
/* 14 */   public ViewAllPagesMenu(@NonNull PaginatedMenu menu) { if (menu == null) throw new NullPointerException("menu");  this.menu = menu; } @NonNull
/*    */   PaginatedMenu menu; @NonNull
/*    */   public PaginatedMenu getMenu() {
/* 17 */     return this.menu;
/*    */   }
/*    */   
/*    */   public String getTitle(Player player) {
/* 21 */     return "Jump to page";
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, Button> getButtons(Player player) {
/* 26 */     HashMap<Integer, Button> buttons = new HashMap<>();
/*    */     
/* 28 */     buttons.put(Integer.valueOf(0), new BackButton(this.menu));
/*    */     
/* 30 */     int index = 10;
/*    */     
/* 32 */     for (int i = 1; i <= this.menu.getPages(player); i++) {
/* 33 */       buttons.put(Integer.valueOf(index++), new JumpToPageButton(i, this.menu, (this.menu.getPage() == i)));
/*    */       
/* 35 */       if ((index - 8) % 9 == 0) {
/* 36 */         index += 2;
/*    */       }
/*    */     } 
/*    */     
/* 40 */     return buttons;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAutoUpdate() {
/* 45 */     return true;
/*    */   }
/*    */ }

/*    */ package dev.danteh.coins.managers.product.menu;
/*    */ 
/*    */ import dev.danteh.coins.Coins;
import dev.danteh.coins.managers.product.Product;
import dev.danteh.coins.managers.product.menu.button.CancelButton;
import dev.danteh.coins.managers.product.menu.button.ConfirmButton;
import dev.danteh.coins.managers.product.menu.button.InfoButton;
import dev.danteh.coins.utils.menu.Button;
import dev.danteh.coins.utils.menu.Menu;
/*    */ import java.beans.ConstructorProperties;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ConfirmMenu
/*    */   extends Menu
/*    */ {
/*    */   private final Product product;
/*    */   
/*    */   @ConstructorProperties({"product"})
/*    */   public ConfirmMenu(Product product) {
/* 22 */     setAutoUpdate(false);
/* 23 */     setUpdateAfterClick(false);
/* 24 */     setPlaceholder(Coins.getInstance().getMainConfig().getBoolean("FILL_MENU.ENABLE"));
/*    */     this.product = product;
/*    */   }
/*    */   
/*    */   public String getTitle(Player player) {
/* 29 */     return Coins.getInstance().getMainConfig().getString("CONFIRM-MENU.TITLE");
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 34 */     return 9 * Coins.getInstance().getMainConfig().getInt("CONFIRM-MENU.SIZE");
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<Integer, Button> getButtons(Player player) {
/* 39 */     Map<Integer, Button> buttons = new HashMap<>();
/* 40 */     buttons.put(Integer.valueOf(0), new ConfirmButton(this.product));
/* 41 */     buttons.put(Integer.valueOf(1), new ConfirmButton(this.product));
/* 42 */     buttons.put(Integer.valueOf(2), new ConfirmButton(this.product));
/* 43 */     buttons.put(Integer.valueOf(9), new ConfirmButton(this.product));
/* 44 */     buttons.put(Integer.valueOf(10), new ConfirmButton(this.product));
/* 45 */     buttons.put(Integer.valueOf(11), new ConfirmButton(this.product));
/* 46 */     buttons.put(Integer.valueOf(18), new ConfirmButton(this.product));
/* 47 */     buttons.put(Integer.valueOf(19), new ConfirmButton(this.product));
/* 48 */     buttons.put(Integer.valueOf(20), new ConfirmButton(this.product));
/*    */     
/* 50 */     buttons.put(Integer.valueOf(13), new InfoButton(this.product));
/*    */     
/* 52 */     buttons.put(Integer.valueOf(6), new CancelButton(this.product));
/* 53 */     buttons.put(Integer.valueOf(7), new CancelButton(this.product));
/* 54 */     buttons.put(Integer.valueOf(8), new CancelButton(this.product));
/* 55 */     buttons.put(Integer.valueOf(15), new CancelButton(this.product));
/* 56 */     buttons.put(Integer.valueOf(16), new CancelButton(this.product));
/* 57 */     buttons.put(Integer.valueOf(17), new CancelButton(this.product));
/* 58 */     buttons.put(Integer.valueOf(24), new CancelButton(this.product));
/* 59 */     buttons.put(Integer.valueOf(25), new CancelButton(this.product));
/* 60 */     buttons.put(Integer.valueOf(26), new CancelButton(this.product));
/*    */     
/* 62 */     return buttons;
/*    */   }
/*    */ }

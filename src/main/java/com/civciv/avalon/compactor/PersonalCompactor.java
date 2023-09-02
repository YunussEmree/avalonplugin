 /*
package com.civciv.avalon.compactor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonalCompactor implements Listener {

   GrapplingHook plugin;

   public PersonalCompactor(GrapplingHook plugin){
       this.plugin = plugin;
   }
   Inventory compactor = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "Personal Compactor");

   @EventHandler
   public void onInteract(PlayerInteractEvent event){
       if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR));
         if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null &&
             event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§9Personal Compactor 5000")){

             String enchitems = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(3);
             List<String> enchitemslist = enchitemslist = new ArrayList<>(Arrays.asList(enchitems.split("")));

             ItemMeta metaslot = ItemManager.CompactorSlot.getItemMeta();

             ItemMeta metaitem = ItemManager.CompactorItem.getItemMeta();
             List<String> loreitem = metaitem.getLore();

             for (int i = 0; i < compactor.getSize(); i++){
                 if(i == 12 || i == 13 || i == 14){

                     int compactorslot = i - 11;

                     metaslot.setDisplayName("§aAuto-Craft Slot #" + compactorslot);
                     ItemManager.CompactorSlot.setItemMeta(metaslot);
                     compactor.setItem(i, ItemManager.CompactorSlot);

                     if(!enchitemslist.isEmpty()){
                         for(String elements : enchitemslist){
                             if(elements.equals("§" + compactorslot + CompactItemNumbers.cobblestone)){
                                 ItemManager.CompactorItem.setType(Material.COBBLESTONE);
                                 metaitem.setDisplayName("§aAuto-Craft #" + compactorslot);
                                 loreitem.set(0, "§7Item: &aEnchanted Cobblestone");
                                 metaitem.setLore(loreitem);
                                 ItemManager.CompactorItem.setItemMeta(metaitem);
                                 compactor.setItem(i, ItemManager.CompactorItem);
                             }
                             else if(elements.equals("§" + compactorslot + CompactItemNumbers.sand)) {
                                 ItemManager.CompactorItem.setType(Material.SAND);
                                 metaitem.setDisplayName("§aAuto-Craft #" + compactorslot);
                                 loreitem.set(0, "§7Item: &aEnchanted Cobblestone");
                                 metaitem.setLore(loreitem);
                                 ItemManager.CompactorItem.setItemMeta(metaitem);
                                 compactor.setItem(i, ItemManager.CompactorItem);
                             }
                         }
                     }
                 }
                 else{
                     compactor.setItem(i, ItemManager.CompactorBg);
                 }
             }
             event.getPlayer().openInventory(compactor);
             event.setCancelled(true);
         }
   }

   @EventHandler
   public void onClick(InventoryClickEvent event){
       if(event.getWhoClicked() instanceof Player) {
           Player player = (Player) event.getWhoClicked();
           if (event.getClickedInventory() != null && event.getClickedInventory().equals(compactor)) {
               if(event.getView().getTitle().equals(ChatColor.DARK_GRAY + "Personal Compactor")){
                   if(event.getSlot() == 12 || event.getSlot() == 13 || event.getSlot() == 14){

                       int compactorslot = event.getSlot() - 11;

                       ItemMeta metaslot = ItemManager.CompactorSlot.getItemMeta();

                       ItemMeta metaitem = ItemManager.CompactorItem.getItemMeta();
                       List<String> loreitem = metaitem.getLore();

                       ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                       List<String> lore = meta.getLore();
                       String enchitems = lore.get(3);

                       if(event.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE)){
                           if(event.getCursor() != null){
                               if(event.getCursor().getItemMeta().getDisplayName().startsWith("§aEnchanted ")){

                                   metaitem.setDisplayName("§aAuto-Craft Slot #" + compactorslot);

                                   if(event.getCursor().isSimilar(ItemManager.EnchantedCobblestone)){
                                       ItemManager.CompactorItem.setType(Material.COBBLESTONE);
                                   }
                                   else if(event.getCursor().isSimilar(ItemManager.EnchantedSand)) {
                                       ItemManager.CompactorItem.setType(Material.SAND);
                                   }
                                   loreitem.set(0, "§7Item: " + event.getCursor().getItemMeta().getDisplayName());
                                   metaitem.setLore(loreitem);
                                   ItemManager.CompactorItem.setItemMeta(metaitem);
                                   compactor.setItem(event.getSlot(), ItemManager.CompactorItem);

                                   if(enchitems.isEmpty()) {
                                       enchitems = "§" + compactorslot;
                                   }
                                   else {
                                       enchitems = enchitems + " §" + compactorslot;
                                   }
                                   if (event.getCursor().isSimilar(ItemManager.EnchantedCobblestone)){
                                       enchitems = enchitems + CompactItemNumbers.cobblestone;
                                   }
                                   if (event.getCursor().isSimilar(ItemManager.EnchantedSand)) {
                                       enchitems = enchitems + CompactItemNumbers.sand;
                                   }

                                   lore.set(3, enchitems);
                                   meta.setLore(lore);
                                   player.getInventory().getItemInMainHand().setItemMeta(meta);

                                   event.getCursor().setAmount(event.getCursor().getAmount() - 1);
                               }
                               else {
                                       player.sendMessage(ChatColor.DARK_RED + "Bu eşyayı kişisel sıkıştırıcıya koyamazsın!");
                               }
                           }
                       }
                       event.setCancelled(true);
                   }
                   else{
                       if(!event.getCursor().hasItemMeta()) {

                           List<String> enchitemslist = new ArrayList<String>(Arrays.asList(enchitems.split("")));

                           metaslot.setDisplayname("§aAuto-Craft Slot #");
                           ItemManager.CompactorSlot.setItemMeta(metaslot);

                           event.setCurrentItem(ItemManager.CompactorSlot);

                           for (int i = 0; i < enchitemslist.size(); i++) {
                               String elements = enchitemslist.get(i);
                               if (elements.equals("§" + compactorslot + compactItemNumbers.cobblestone)) {
                                   event.setCursor(ItemManager.EnchantedCobblestone);
                                   enchitemslist.remove(elements);
                               } else if (elements.equals("§" + compactorslot + compactItemNumbers.cobblestone)) {
                                   event.setCursor(ItemManager.EnchantedCobblestone);
                                   enchitemslist.remove(elements);
                               }
                           }

                           enchitems = "";
                           for (String string : enchitemslist) {
                               if (enchitems.isEmpty()) {
                                   enchitems = string;
                               } else {
                                   enchitems = enchitems + " " + string;
                               }
                           }

                           lore.set(3, enchitems);
                           meta.setLore(lore);
                           player.getInventory().getItemInMainHand().setItemMeta(meta);

                           event.setCancelled(true);
                       }
                       else {
                           if(event.getCursor().getItemMeta().getDisplayName().startsWith("§aEnchanted ")){
                               if(event.getCursor().getAmount() == 1){

                                   List<String> enchitemslist = new ArrayList<String>(Arrays.asList(enchitems.split(" ")));

                                   String currentitem = "";

                                   metaslot.setDisplayName("§aAuto-Craft Slot #" + compactorslot);

                                   if (event.getCursor().equals(ItemManager.EnchantedCobblestone)) {
                                       ItemManager.CompactorItem.setType(Material.COBBLESTONE));
                                   } else if (event.getCursor()equals(ItemManager.EnchantedSand)){
                                       ItemManager.CompactorItem.setType(Material.SAND);
                                   }

                                   loreitem.set(0, "§7Item: " + event.getCursor().getItemMeta().getDisplayName());
                                   metaitem.setLore(loreitem);
                                   ItemManager.CompactorItem.setItemMeta(metaitem);
                                   event.setCurrentItem(ItemManager.CompactorItem);

                                   for(int i = 0; i < enchitemslist.size(); i++) {
                                       currentitem = enchitemslist.get(i);
                                       if (currentitem.startsWith("§" + compactorslot)) {
                                           enchitemslist.remove(currentitem);
                                       }
                                   }

                                   enchitems = "";
                                   for(String string : enchitemslist) {
                                       if (enchitems.isEmpty()) {
                                           enchitems = string;
                                       } else {
                                           enchitems = enchitems + " " + string;
                                       }
                                   }

                                   if(enchitems.isEmpty()) {
                                       enchitems = "§" + compactorslot;
                                   }
                                   else {
                                       enchitems = enchitems + " §" + compactorslot;
                                   }

                                   if(event.getCursor().isSimilar(ItemManager.EnchantedCobblestone)) {
                                       enchitems = enchitems + CompactItemNumbers.cobblestone;
                                   }
                                   else if(event.getCursor().isSimilar(ItemManager.EnchantedSand)) {
                                       enchitems = enchitems + CompactItemNumbers.sand;
                                   }

                                   lore.set(3, enchitems);
                                   meta.setLore(lore);
                                   player.getInventory().getItemInMainHand().setItemMeta(meta);

                                   event.setCancelled(true);
                               } else {
                                   if (event.getCursor().getItemMeta().getDisplayName().startsWith("§aEnchanted ")) {
                                       if (event.getCursor().getAmount() == 1) {

                                           List<String> enchitemslist = new ArrayList<String>(Arrays.asList(enchitems.split(" ")));

                                           String currentitem = "";

                                           metaslot.setDisplayName("§aAuto-Craft Slot #" + compactorslot);

                                           if (event.getCursor().equals(ItemManager.EnchantedCobblestone)) {
                                               ItemManager.CompactorItem.setType(Material.COBBLESTONE);
                                           } else if (event.getCursor().equals(ItemManager.EnchantedSand)) {
                                               ItemManager.CompactorItem.setType(Material.SAND);
                                           }

                                           loreitem.set(0, "§7Item: " + event.getCursor().getItemMeta().getDisplayName());
                                           metaitem.setLore(loreitem);
                                           ItemManager.CompactorItem.setItemMeta(metaitem);
                                           event.setCurrentItem(ItemManager.CompactorItem);

                                           for (int i = 0; i < enchitemslist.size(); i++) {
                                               currentitem = enchitemslist.get(i);
                                               if (currentitem.startsWith("§" + compactorslot)) {
                                                   enchitemslist.remove(currentitem);
                                               }
                                           }

                                           enchitems = "";
                                           for (String string : enchitemslist) {
                                               if (enchitems.isempty()) {
                                                   enchitems = string;
                                               } else {
                                                   enchitems = enchitems + " " + string;
                                               }
                                           }

                                           if (enchitems.isEmpty()) {
                                               enchitems = "§" + compactorslot;
                                           } else {
                                               enchitems = enchitems + " §" + compactorslot;
                                           }

                                           if (event.getCursor().isSimilar(ItemManager.EnchantedCobblestone)) {
                                               enchitems = enchitems + CompactItemNumbers.cobblestone
                                           } else if (event.getCursor().isSimilar(ItemManager.EnchantedCobblestone)) {
                                               enchitems = enchitems + CompactItemNumbers.cobblestone
                                           }

                                           lore.set(3, enchitems);
                                           meta.setLore(lore);
                                           player.getInventory().getItemInMainHand().setItemMeta(meta);

                                           if (currentitem.equals("§" + compactorslot + CompactItemNumbers.cobblestone)) {
                                               event.setCursor(ItemManager.EnchantedCobblestone);
                                           }
                                           else if (currentitem.equals("§" + compactorslot + CompactItemNumbers.cobblestone)) {
                                               event.setCursor(ItemManager.EnchantedCobblestone);
                                           }
                                       }
                                       else{
                                           player.sendMessage(ChatColor.RED + "Bu yuvaya sadece 1 adet eşya koyabilirsin!");
                                               }
                                           }
                                   else{
                                       player.sendMessage(ChatColor.RED + "Bu eşyayı kişisel sıkıştırıcıya koyamazsın!");
                                       }
                                   event.setCancelled(true);
                                   }
                               }
                           }
                       }
                   }
               }
           }
       }
   }
}
*/
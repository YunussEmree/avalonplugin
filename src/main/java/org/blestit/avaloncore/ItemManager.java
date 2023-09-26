package org.blestit.avaloncore;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    AvalonCore plugin;


    public static ItemStack GrapplingHook;
    public static ItemStack SaklambacTool;

    public static void init(){
        createGrapplingHook();
        createSaklambacTool();
    }

    private static void createGrapplingHook(){
        ItemStack item = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Kanca");
        List<String> lore = new ArrayList<>();
        lore.add("§7Kancayı kullanarak etrafta gezebilirsiniz.");
        lore.add("");
        lore.add("§5§lEPIK ESYA");
        meta.setLore(lore);
        item.setItemMeta(meta);
        GrapplingHook = item;
    }
    private static void createSaklambacTool() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§bEbelendin!");
        lore.add("§4§lETKINLIK ESYASI");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        SaklambacTool = item;
    }
}


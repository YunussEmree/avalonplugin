package com.civciv.avalon;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    GrapplingHook plugin;

    public ItemManager(GrapplingHook plugin) {
        this.plugin = plugin;
    }


    public static ItemStack GrapplingHook;
    public static ItemStack HomingBow;
    public static ItemStack ExplosiveBow;
    public static ItemStack ASPickaxe;
    public static ItemStack TripleBow;
    public static ItemStack testHomingBow;
    /*
    public static ItemStack PersonalCompactor;
    public static ItemStack EnchantedCobblestone;
    public static ItemStack EnchantedSand;
    public static ItemStack CompactorBg;
    public static ItemStack CompactorSlot;
    public static ItemStack CompactorItem;
     */
    public static ItemStack UndeadSword;
    public static ItemStack TestUndeadSword;
    public static ItemStack DragonKatili;
    public static ItemStack TestDragonKatili;
    public static ItemStack KubizmSword;
    public static ItemStack TestKubizmSword;
    public static ItemStack SaklambacTool;
    public static ItemStack prismarinebow;

    public static void init(){
        createGrapplingHook();
        createHomingBow();
        createExplosiveBow();
        createASPickaxe();
        createTripleBow();
        createtestHomingBow();
        /*
        createPersonalCompactor();
        createEnchantedCobblestone();
        createEnchantedSand();
        createCompactorBg();
        createCompactorItem();
        createCompactorSlot();
         */
        createUndeadSword();
        createTestUndeadSword();
        createDragonKatili();
        createTestDragonKatili();
        createKubizmSword();
        createTestKubizmSword();
        createSaklambacTool();
        createprismarinebow();
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
    private static void createHomingBow(){
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aKartal Yayı");
        List<String> lore = new ArrayList<>();
        lore.add("§6Takip III");
        lore.add("§7Oklar 5 bloğa kadar vurmak");
        lore.add("§7istediğiniz canlıyı takip eder.");
        lore.add("");
        lore.add("§a§lSIRADISI ESYA");
        meta.setLore(lore);
        item.setItemMeta(meta);
        HomingBow = item;
    }
    private static void createExplosiveBow() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5Creeper Yayı");
        List<String> lore = new ArrayList<>();
        lore.add("§5Patlayıcı ok");
        lore.add("§7Oklar isabet ettiği yeri");
        lore.add("§7patlatarak alan hasarı verir");
        lore.add("");
        lore.add("§5§lEPIK ESYA");
        meta.setLore(lore);
        item.setItemMeta(meta);
        ExplosiveBow = item;
    }
    private static void createASPickaxe() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aAteş Kazması");
        List<String> lore = new ArrayList<>();
        lore.add("§6Isıtan Dokunuş");
        meta.setLore(lore);
        item.setItemMeta(meta);
        ASPickaxe = item;
    }
    private static void createTripleBow() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Runaan Yayı");
        List<String> lore = new ArrayList<>();
        lore.add("§6Eşya Özelliği: §6Üçlü ok");
        lore.add("§7Aynı anda 3 ok atarsınız.");
        lore.add("");
        lore.add("§6§lEFSANEVI ESYA");
        meta.setLore(lore);
        item.setItemMeta(meta);
        TripleBow = item;
    }
    private static void createtestHomingBow(){
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aAvalon Test Yayı");
        List<String> lore = new ArrayList<>();
        lore.add("§6Avalon Test Yayı I");
        lore.add("");
        lore.add("§cBu yay test amaçlıdır.");
        lore.add("§fTest yapıldıktan sonra bu yayı");
        lore.add("§fhatıra olarak saklayabilirsiniz");
        lore.add("");
        lore.add("§c§lOZEL ESYA");
        meta.setLore(lore);
        item.setItemMeta(meta);
        testHomingBow = item;
    }
/*
    private static void createPersonalCompactor(){
        ItemStack item = new ItemStack(Material.DROPPER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§9Personal Compactor 5000");
        List<String> lore = new ArrayList<>();
        lore.add("§7Eşyaları otomatik olarak");
        lore.add("§7büyülüye çevirir.");
        lore.add("");
        lore.add("§7Ayarlamak için: Sağ Tık");
        lore.add("");
        lore.add("§9§lNADIR ESYA");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        PersonalCompactor = item;
    }
    private static void createEnchantedCobblestone(){
        ItemStack item = new ItemStack(Material.COBBLESTONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aBüyülü Kırıktaş");
        List<String> lore = new ArrayList<>();
        lore.add("§a§lSIRADISI ESYA");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        EnchantedCobblestone = item;
    }
    private static void createEnchantedSand(){
        ItemStack item = new ItemStack(Material.SAND, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aBüyülü Kum");
        List<String> lore = new ArrayList<>();
        lore.add("§a§lSIRADISI ESYA");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        EnchantedSand = item;
    }
    private static void createCompactorBg(){
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("");
        item.setItemMeta(meta);
        CompactorBg = item;
    }
    private static void createCompactorSlot(){
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("");
        List<String> lore = new ArrayList<>();
        lore.add("Otomatik olarak büyülü eşya");
        lore.add("üretmesi için buraya bir");
        lore.add("büyülü eşya koy.");
        meta.setLore(lore);
        item.setItemMeta(meta);
        CompactorSlot = item;
    }
    private static void createCompactorItem(){
        ItemStack item = new ItemStack(Material.COBBLESTONE, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("Bu eşya, üzerinde yeterince malzeme");
        lore.add("oldukça üretilecek");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
    }
    */
    private static void createUndeadSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§5Undead Sword");
        lore.add("§6Eşya özelliği: §eHasar Çarpanı");
        lore.add("§cZombi Hasarı: +%100");
        lore.add("§cİskelet Hasarı: +%100");
        lore.add("");
        lore.add("§5§lEPIK ESYA");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        UndeadSword = item;
    }
    private static void createTestUndeadSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§bUndead Sword");
        lore.add("§6Eşya özelliği: §eHasar Çarpanı");
        lore.add("§cZombi Hasarı: +%100");
        lore.add("§cİskelet Hasarı: +%100");
        lore.add("");
        lore.add("§b§lDEBUG MODE");
        lore.add("§4§lADMIN ESYASI");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        TestUndeadSword = item;
    }
    private static void createDragonKatili() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§6Ejderha Kılıcı");
        lore.add("§6Eşya özelliği: §eEjderha Katili");
        lore.add("§cEjderha Hasarı: +%100");
        lore.add("");
        lore.add("§6§lEFSANEVI ESYA");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        DragonKatili = item;
    }
    private static void createTestDragonKatili() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§bEjderha Kılıcı");
        lore.add("§6Eşya özelliği: §eEjderha Katili");
        lore.add("§cEjderha Hasarı: +%100");
        lore.add("");
        lore.add("§b§lDEBUG MODE");
        lore.add("§4§lADMIN ESYASI");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        TestDragonKatili = item;
    }
    private static void createKubizmSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§5Küp Katili");
        lore.add("§6Eşya özelliği: §eKübizm");
        lore.add("§cBalçık hasarı: +%100");
        lore.add("§cMagma Kübü hasarı: +%100");
        lore.add("");
        lore.add("§5§lEPIK ESYA");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        KubizmSword = item;
    }
    private static void createTestKubizmSword() {
        ItemStack item = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§bKüp katili");
        lore.add("§6Eşya özelliği: §eKübizm");
        lore.add("§cBalçık hasarı: +%100");
        lore.add("§cMagma Kübü hasarı: +%100");
        lore.add("");
        lore.add("§b§lDEBUG MODE");
        lore.add("§4§lADMIN ESYASI");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        TestKubizmSword = item;
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
    private static void createprismarinebow() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName("§9Prizmarin Yay");
        lore.add("§cEnvanterinizde prizmarin varsa prizmarin");
        lore.add("§catarsınız ve 3 katı hasar verirsiniz.");
        lore.add("");
        lore.add("§9§lNADIR ESYA");
        meta.setLore(lore);
        item.setItemMeta(meta);
        prismarinebow = item;
    }
}

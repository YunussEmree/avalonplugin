
package com.civciv.avalon.dragon;

import com.civciv.avalon.GrapplingHook;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.boss.*;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

 //import static com.civciv.avalon.dragon.rewards.ödüleLayıkMı;
import static com.civciv.avalon.dragon.skills.*;
import static com.civciv.avalon.dragon.type.Bilgedragon;
import static com.civciv.avalon.dragon.type.Gencdragon;
import static com.civciv.avalon.dragon.type.Gucludragon;
import static com.civciv.avalon.dragon.type.Guvenilmezdragon;
import static com.civciv.avalon.dragon.type.Kilgharrahdragon;
import static com.civciv.avalon.dragon.type.Koruyucudragon;
import static com.civciv.avalon.dragon.type.Yaslidragon;
import static com.civciv.avalon.dragon.type.Kutsaldragon;



    public class spawn implements Listener {
        public static EnderDragon bizimDragon;
    public static BossBar bizimBossBar;
    public static EndPortalFrame bizimAltar;
    public static Block bizimEnderPortal;
    public static ArrayList<Location> bizimAltarlar;

    public static String bizimDragonAd;
    public static double bizimDragonAreaDamage;
    public static double bizimDragonFireballDamage;
    public static int bizimDragonHealth;
    public static double bizimDragonDamage;
    public static double bizimDragonSpeed;
    public static int bizimDragonArmor;
    public static int bizimDragonThunderAttack;
    public static int bizimDragonThunderCooldown;
    public static HashMap<String, Double> hasarMapi = new HashMap<>();
    public static HashMap<String, Integer> gozmapi = new HashMap<String, Integer>();
    public static HashMap<String, Integer> weightMapi = new HashMap<String, Integer>();
    public static String bizimPlayer;
    public static Player DragonDamager;
    public static String DragonLastDamager;
    public static double dragonAnlikCan;

    public static Location bizimDragonLocation;

    GrapplingHook plugin;

    public spawn(GrapplingHook plugin) {
        this.plugin = plugin;
    }

   ////EJDERİN ARMORUNU AYARLAMA
   //@EventHandler
   //public void armor(EntityDamageByEntityEvent event){
   //    if (event.getEntity() instanceof EnderDragon){
   //        if (event.getEntity().getCustomName().equals(bizimDragonAd)){
   //            event.setDamage(event.getDamage() * (100 - bizimDragonArmor) / 100);
   //        }
   //    }
   //}

    public static void ejdertp(){
        bizimDragon.teleport(bizimDragonLocation);
    }

    public static void createstatictik(Player player) {

        Set<Map.Entry<String, Double>> set = hasarMapi.entrySet();

        int i = 1;
        String sıralamaMesajı = "";
        Iterator<Map.Entry<String, Double>> sıralıArray = (set.stream().sorted((newEl, oldEl) -> (int) Math.round((oldEl.getValue()-newEl.getValue()))).iterator());

        for (Iterator<Map.Entry<String, Double>> it = sıralıArray; it.hasNext(); ) {
            Map.Entry<String, Double> element = it.next();
            sıralamaMesajı += ("          §6§l" + i + ". §c§l" + element.getKey() + ": " + Math.round(element.getValue()) + "\n");
            i++;
            if(i == 4 ) break;
        }
        i = 0;

                Bukkit.broadcastMessage("§a▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n§5  "+ "        " + bizimDragonAd + " mağlup edildi." + "\n \n  " + "            §e§lHASAR SIRALAMASI\n§c§l \n" + sıralamaMesajı + "\n ");
                for(String hasarVeren : hasarMapi.keySet()){
                        Player p = Bukkit.getPlayer(hasarVeren);

                }

                Bukkit.broadcastMessage("§5 \n§a§a▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");

    }

    //EJDERHA OLUŞTURMA FONKSİYONU
    public BossBar createdragon(Location location, Player player, World world){
        EnderDragon dragon = location.getWorld().spawn(location,EnderDragon.class);
        bizimDragon = dragon;

        //EJDER ÇEŞİDİNİ BELİRLEME
        ArrayList<String> randomdragon = new ArrayList<>();
        for(int i = 0; i<8; i++){
            randomdragon.add("§5Koruyucu Ejderha");
            randomdragon.add("§5Yaşlı Ejderha");
            randomdragon.add("§5Genç Ejderha");
            randomdragon.add("§5Bilge Ejderha");
            randomdragon.add("§5Güvenilmez Ejderha");
            randomdragon.add("§5Güçlü Ejderha");
        }
        randomdragon.add("§6Kilgharrah Ejderhası");
        randomdragon.add("§6Kutsal Ejderhası");

        String sonuc = randomdragon.get((int) Math.round(Math.floor((Math.random()*randomdragon.size()))));

        if (sonuc.equals("§5Koruyucu Ejderha")){
            Koruyucudragon();
        }
        else if(sonuc.equals("§5Yaşlı Ejderha")) {
            Yaslidragon();
        }
        else if(sonuc.equals("§5Genç Ejderha")){
            Gencdragon();
        }
        else if(sonuc.equals("§5Bilge Ejderha")){
            Bilgedragon();
        }
        else if(sonuc.equals("§5Güvenilmez Ejderha")){
            Guvenilmezdragon();
        }
        else if(sonuc.equals("§5Güçlü Ejderha")){
            Gucludragon();
        }
        else if(sonuc.equals("§6Kilgharrah Ejderhası")){
            Kilgharrahdragon();
        }
        else if(sonuc.equals("§6Kutsal Ejderhası")){
            Kutsaldragon();
        }
        else{
            System.out.println(ChatColor.RED + "[AVALON] Ejderha seçimi ile ilgili bilinmeyen bir hata meydana geldi.");
        }

        //EJDER ÖZELLİKLERİ BELİRLEME
        dragon.setMaxHealth(bizimDragonHealth);
        dragon.setHealth(bizimDragonHealth);
        dragon.setCustomName(bizimDragonAd);
        dragon.setCustomNameVisible(true);
        dragon.setLastDamage(bizimDragonDamage);
        Location dragonloc = dragon.getLocation();
        dragon.setVelocity(dragonloc.getDirection().multiply(bizimDragonSpeed));
        String isim = dragon.getCustomName();

        //BOSSBAR AYARLARI
        BossBar bar = Bukkit.getServer().createBossBar(isim , BarColor.PURPLE, BarStyle.SOLID, BarFlag.PLAY_BOSS_MUSIC);
        bar.setVisible(true);
        bar.setProgress(1);

        dragon.setGlowing(true);
        dragon.setPhase(EnderDragon.Phase.CHARGE_PLAYER);
        bizimBossBar = bar;
        bizimDragonAd = isim;

        //SKİLLS
                new BukkitRunnable() {
                    public void run() {
                        if (bizimDragon.isDead()) {
                            cancel();
                        } else {
                            thunder(bizimDragon);
                        }
                    }
                }.runTaskTimer(plugin, 1L, bizimDragonThunderCooldown);

                //ejdertp
        new BukkitRunnable() {
            public void run() {
                if (bizimDragon.isDead()) {
                    cancel();
                } else {
                    if(bizimDragon.getLocation().getX() >= 60 || bizimDragon.getLocation().getX() <= -60 || bizimDragon.getLocation().getZ() >= 60 || bizimDragon.getLocation().getZ() <= -60) ejdertp();
                }
            }
        }.runTaskTimer(plugin, 500, 500);

       return bar;
    }

        public static void killthedragon(){
            if(bizimDragon.getHealth() > 0) {
                ConsoleCommandSender cs = Bukkit.getConsoleSender();
                Bukkit.dispatchCommand(cs, "minecraft:kill @e[type=minecraft:ender_dragon]");

                dragonAnlikCan = 0;

                bizimBossBar.removeAll();

                for (Location bizimAltar : bizimAltarlar) {
                    EndPortalFrame çerçeve = (EndPortalFrame) bizimAltar.getBlock().getBlockData();
                    çerçeve.setEye(false);
                    bizimAltar.getBlock().setBlockData(çerçeve);
                }

                createstatictik(DragonDamager);

                Bukkit.broadcastMessage("§8[§6Avalon§8] §cEjderha düzgün bir şekilde öldürülemediği için ödüller verilmiyor...");
            }
        }

    //ALTARLARA GÖZ KOYMA
    @EventHandler
    public void onplaceeye(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().name().equals(Material.END_PORTAL_FRAME.name())) {
            if (event.getHand() == EquipmentSlot.HAND) {
                World world = event.getPlayer().getWorld();
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore() != null && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§5Ejderha Çağırma Gözü") && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§7Bunu Ejderhanın Yuvasındaki Ender Altarı'nda") && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().contains("§7Ender Ejderhalarını çağırmak için kullanın!")) {
                    Double locx = event.getClickedBlock().getLocation().getBlock().getLocation().getX();
                    Double locy = event.getClickedBlock().getLocation().getBlock().getLocation().getY();
                    Double locz = event.getClickedBlock().getLocation().getBlock().getLocation().getZ();
                    ArrayList<String> altarlar = new ArrayList<>(plugin.getConfig().getStringList("dragon.altarlar"));
                    ArrayList<Location> altarBloklar = new ArrayList<>();
                    for (String altar : altarlar) {
                        String[] kordinatlar = altar.split(" ");
                        Location lokasyon = new Location(world, Double.parseDouble(kordinatlar[0]), Double.parseDouble(kordinatlar[1]), Double.parseDouble(kordinatlar[2]));
                        altarBloklar.add(lokasyon);
                    }
                    if (altarBloklar.stream().anyMatch(altarBlok -> Double.compare(altarBlok.getX(), locx) == 0 && Double.compare(altarBlok.getY(), locy) == 0 && Double.compare(altarBlok.getZ(), locz) == 0 && !(((EndPortalFrame) altarBlok.getBlock().getBlockData()).hasEye()))) {
                        Block enderPortalBlock = new Location(world, locx, locy, locz).getBlock();
                        EndPortalFrame enderPortal = (EndPortalFrame) enderPortalBlock.getBlockData();
                        enderPortal.setEye(true);
                        enderPortalBlock.setBlockData(enderPortal);
                        Player player = event.getPlayer();
                        String playername = event.getPlayer().getName();
                        int tamOlanlar = altarBloklar.stream().filter(altarBlok -> ((EndPortalFrame) altarBlok.getBlock().getBlockData()).hasEye()).toArray().length;
                        Bukkit.broadcastMessage("§d" + playername + " §5bir ejderha çağırma gözü yerleştirdi." + "§d (" + tamOlanlar + "/8)");
                        ConsoleCommandSender cs = Bukkit.getConsoleSender();
                        Bukkit.dispatchCommand(cs, "q point "+playername+" add default.ecgkoyma 1");
                        event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
                        world.playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDER_DRAGON, 5, 5);
                        bizimAltarlar = altarBloklar;
                        Set<Map.Entry<String, Integer>> set = gozmapi.entrySet();
                        if(!gozmapi.containsKey(event.getPlayer().getName())){
                            gozmapi.put(event.getPlayer().getName(), 1);

                        }else{
                            gozmapi.put(event.getPlayer().getName(), gozmapi.get(event.getPlayer().getName()) + 1);

                        }
                        Set<Map.Entry<String, Integer>> set1 = weightMapi.entrySet();
                        if(!weightMapi.containsKey(event.getPlayer().getName())){
                            weightMapi.put(event.getPlayer().getName(), 100);
                        } else {
                            weightMapi.put(event.getPlayer().getName(), weightMapi.get(event.getPlayer().getName()) + 100);
                        }



                        if (altarBloklar.stream().allMatch(altarBlok -> ((EndPortalFrame) altarBlok.getBlock().getBlockData()).hasEye())) {
                            Double dragonlocx = plugin.getConfig().getDouble("dragon.dragonloc.x");
                            Double dragonlocy = plugin.getConfig().getDouble("dragon.dragonloc.y");
                            Double dragonlocz = plugin.getConfig().getDouble("dragon.dragonloc.z");


                            Location dragonloc = new Location(world, dragonlocx, dragonlocy, dragonlocz);
                            bizimDragonLocation = dragonloc;


                            //EJDERHA ÇAĞIRMA YAZILARI
                            Bukkit.broadcastMessage("§5Bir Ejderha Ortaya Çıkıyor...");
                            System.out.println(event.getPlayer().getName() + " adlı oyuncu bir ejderha çağırdı.");

                            new BukkitRunnable() {
                                int i = 5;
                                public void run() {
                                    if (i < 0) cancel();
                                    if (i == 0) {
                                        createdragon(dragonloc, player, world);
                                        Bukkit.broadcastMessage("§dEjderha türü: " + bizimDragonAd);
                                    } else if (i != -1) {
                                        Bukkit.broadcastMessage("§5Ejderha Doğuyor... §d" + i);
                                        world.playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDER_DRAGON, 50, 50);
                                    }
                                    i--;
                                }
                            }.runTaskTimer(plugin, 10, 20);
                        }
                    }
                }
            }
        }
    }





    //EJDERİN PORTALDA KALMASINI ENGELLEME (YERDE ASILI KALMASINI ENGELLEME)
    @EventHandler
    public void dragonbreak(EnderDragonChangePhaseEvent event){
        if(event.getNewPhase().equals(EnderDragon.Phase.FLY_TO_PORTAL)){
            event.setCancelled(true);
        }
        if(event.getNewPhase().equals(EnderDragon.Phase.LAND_ON_PORTAL)){
            event.setCancelled(true);
        }
    }




    @EventHandler
    public void dragonfireball(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof AreaEffectCloud){
            event.setDamage(bizimDragonAreaDamage);
        }
        if (event.getDamager() instanceof LargeFireball){
            Projectile proj = (Projectile) event.getDamager();
            Entity shooter = (Entity) proj.getShooter();

            if(shooter.equals(bizimDragon)){
                event.setDamage(bizimDragonFireballDamage);
            }
        }
    }




    //EJDERİN BLOKLARI KIRMASINI KAPATMA
    @EventHandler
    public void blokkirma(EntityChangeBlockEvent event){
        if (event.getEntity() instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) event.getEntity();

            if (Objects.requireNonNull(dragon.getCustomName()).contains(bizimDragonAd)) {
                event.setCancelled(true);
            }
        }
    }

    //DRAGONA HASAR VERİLDİĞİNDE YAPILACAK ŞEYLER
    @EventHandler
    public void onDragonDamaged(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            EnderDragon dragon = (EnderDragon) event.getEntity();


          //yayla hasar
            if (event.getDamager() instanceof Arrow) {
                Projectile proj = (Projectile) event.getDamager();
                DragonDamager = (Player) proj.getShooter();

                if (bizimDragon != null && !bizimDragon.isDead()) {
                    if (Objects.requireNonNull(dragon.getCustomName()).contains(bizimDragonAd)) {

                        dragon.setCustomName(bizimDragonAd);

                        bizimBossBar.setTitle(bizimDragonAd + " - " + Math.round(dragon.getHealth()));
                        bizimBossBar.setProgress(dragon.getHealth() / bizimDragonHealth);

                        dragonAnlikCan = dragon.getHealth();

                        bizimPlayer = DragonDamager.getName();

                        if (hasarMapi.get(DragonDamager.getName()) == null) {
                            hasarMapi.put(DragonDamager.getName(), event.getFinalDamage());
                        } else {
                            hasarMapi.put(DragonDamager.getName(), hasarMapi.get(DragonDamager.getName()) + event.getFinalDamage());
                        }

                    }


                    List<Entity> entityler = dragon.getNearbyEntities(50, 50, 50);
                    for (Entity entity : entityler) {
                        if (!(entity instanceof Player)) continue;
                        Player p = (Player) entity;
                        if (!bizimBossBar.getPlayers().contains(p)) {
                            bizimBossBar.addPlayer(p);
                        }
                    }
                    for (Player p : bizimBossBar.getPlayers()) {
                        Entity pEntity = (Entity) p;
                        if (!entityler.contains(pEntity)) bizimBossBar.removePlayer(p);

                    }
                }
            }

                //kılıçla hasar
            if (event.getDamager() instanceof Player) {
                DragonDamager = (Player) event.getDamager();


                if (bizimDragon != null && !bizimDragon.isDead()) {
                    if (Objects.requireNonNull(dragon.getCustomName()).contains(bizimDragonAd)) {

                        dragon.setCustomName(bizimDragonAd);

                        bizimBossBar.setTitle(bizimDragonAd + " - " + Math.round(dragon.getHealth()));
                        bizimBossBar.setProgress(dragon.getHealth() / bizimDragonHealth);

                        dragonAnlikCan = dragon.getHealth();

                        if (event.getDamager() instanceof Player) {
                            Player p = (Player) event.getDamager();

                            bizimPlayer = p.getName();

                            if (hasarMapi.get(p.getName()) == null) {
                                hasarMapi.put(p.getName(), event.getFinalDamage());
                            } else {
                                hasarMapi.put(p.getName(), hasarMapi.get(p.getName()) + event.getFinalDamage());
                            }

                        }


                        List<Entity> entityler = dragon.getNearbyEntities(50, 50, 50);
                        for (Entity entity : entityler) {
                            if (!(entity instanceof Player)) continue;
                            Player p = (Player) entity;
                            if (!bizimBossBar.getPlayers().contains(p)) {
                                bizimBossBar.addPlayer(p);
                            }
                        }
                        for (Player p : bizimBossBar.getPlayers()) {
                            Entity pEntity = (Entity) p;
                            if (!entityler.contains(pEntity)) bizimBossBar.removePlayer(p);

                        }
                    }
                }
            }
        }
    }





    //EJDERHA ÖLDÜĞÜNDE YAPILACAK ŞEYLER
    @EventHandler
    public void onkill(EntityDeathEvent event){
    if(event.getEntity() instanceof EnderDragon && bizimDragon != null && bizimDragon.isDead()) {
        if (bizimDragonAd != null && event.getEntity().getCustomName().contains(bizimDragonAd)) {

            dragonAnlikCan = 0;

            bizimBossBar.removeAll();

            for(Location bizimAltar : bizimAltarlar){
                EndPortalFrame çerçeve = (EndPortalFrame)bizimAltar.getBlock().getBlockData();
                çerçeve.setEye(false);
                bizimAltar.getBlock().setBlockData(çerçeve);
            }

            createstatictik(DragonDamager);

            List<Map.Entry<String, Double>> hasarMapiEntries = hasarMapi.entrySet().stream().collect(Collectors.toList());
            hasarMapiEntries.sort((newEnt, oldEnt) -> {
                return Double.compare(oldEnt.getValue(), newEnt.getValue());
            });
            int sira = 1;
            for(Map.Entry<String, Double> el : hasarMapiEntries) {

                String isim = el.getKey();
                Player player = Bukkit.getPlayer(isim);

                assert player != null;
                if (player.hasPermission("avalon.dragon.extraweight.50")) {
                    if (!weightMapi.containsKey(isim)) {
                        weightMapi.put(isim, 50);
                    } else {
                        weightMapi.put(isim, weightMapi.get(isim) + 50);
                    }
                }


                if(sira == 1){


                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 300);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 300);
                    }

                } else if (sira == 2){


                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 250);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 250);
                    }

                } else if (sira == 3){


                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 200);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 200);
                    }

                } else if (sira == 4){


                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 125);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 125);
                    }


                } else if (sira == 5){


                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 110);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 110);
                    }

                } else if (sira == 6 || sira == 7 || sira == 8){


                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 100);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 100);
                    }
                    
                } else if (sira == 9 || sira == 10){


                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 90);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 90);
                    }

                } else if (sira >= 10){

                    if(!weightMapi.containsKey(isim)){
                        weightMapi.put(isim, 80);
                    }else{
                        weightMapi.put(isim, weightMapi.get(isim) + 80);
                    }

                }
                sira++;
            }

            System.out.println("§c§l[AVALON] Weightmap log: " + weightMapi);
            System.out.println("§c§l[AVALON] Damagemap log: " + hasarMapi);
            System.out.println("§c§l[AVALON] Gözmap log: " + gozmapi);
            AtomicInteger siralama = new AtomicInteger(1);
            hasarMapiEntries.forEach((entry) -> {
                    String key = entry.getKey();
                    double db = entry.getValue();

                    Player player = Bukkit.getPlayer(key);

                    CommandSender sender = Bukkit.getConsoleSender();

                        int gozsayi = 0;
                    if(gozmapi.get(key) != null){
                        gozsayi = (gozmapi.get(key));
                    }

                    //koruyucudragon
                    if (bizimDragonAd.equals("§5§lKoruyucu Ejderha")) {

                        if(siralama.get() == 1){
                            Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU "+key+" 7");
                            Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                            Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejderkoruyucu 1");
                        }
                        if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU "+key+" 6");
                        if(siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU "+key+" 5");
                        if(siralama.get() == 4 || siralama.get() == 5)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU "+key+" 4");
                        if(siralama.get() == 6 || siralama.get() == 7 )  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU "+key+" 3");
                        //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KORUYUCU "+key+" 3");

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 295) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Kask&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset helmet");
                                weightMapi.put(key, weightMapi.get(key) - 295);
                            }
                        }

                        if (weightMapi.get(key) >= 410) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Göğüslük&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset chestplate");
                                weightMapi.put(key, weightMapi.get(key) - 410);
                            }
                        }

                        if (weightMapi.get(key) >= 360) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Pantolon&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset leggings");
                                weightMapi.put(key, weightMapi.get(key) - 360);
                            }
                        }

                        if (weightMapi.get(key) >= 290) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cKoruyucu Ejder Bot&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:koruyucuset boots");
                                weightMapi.put(key, weightMapi.get(key) - 290);
                            }
                        }

                        if (weightMapi.get(key) >= 500) {
                            if ((Math.random() * 100) < 25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 500);
                            }
                        }

                        if (weightMapi.get(key) >= 800) {
                            if ((Math.random() * 100) < 5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 800);
                            }
                        }

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 5) {
                            int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                            Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                            weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                        }
                    }

                    //yaşlıdragon
                    if (bizimDragonAd.equals("§5§lYaşlı Ejderha")) {

                        if(siralama.get() == 1){
                            Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI "+key+" 7");
                            Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                            Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejderyasli 1");
                        }
                        if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI "+key+" 6");
                        if(siralama.get() == 3 ) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI "+key+" 5");
                        if(siralama.get() == 4 || siralama.get() == 5) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI "+key+" 4");
                        if(siralama.get() == 6 || siralama.get() == 7)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI "+key+" 3");
                        //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:YASLI "+key+" 3");


                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoitems give " + key +" ejderha_avcisi");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 295) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Kask&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset helmet");
                                weightMapi.put(key, weightMapi.get(key) - 295);
                            }
                        }

                        if (weightMapi.get(key) >= 410) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Göğüslük&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset chestplate");
                                weightMapi.put(key, weightMapi.get(key) - 410);
                            }
                        }

                        if (weightMapi.get(key) >= 360) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Pantolon&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset leggings");
                                weightMapi.put(key, weightMapi.get(key) - 360);
                            }
                        }

                        if (weightMapi.get(key) >= 290) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cYaşlı Ejder Bot&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:yasliset boots ");
                                weightMapi.put(key, weightMapi.get(key) - 290);
                            }
                        }

                        if (weightMapi.get(key) >= 500) {
                            if ((Math.random() * 100) < 25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 500);
                            }
                        }

                        if (weightMapi.get(key) >= 800) {
                            if ((Math.random() * 100) < 5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 800);
                            }
                        }



                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lYaşlı Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }




                        if (weightMapi.get(key) >= 5) {
                            int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                            Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                            weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                        }
                    }

                    //gençdragon
                    if (bizimDragonAd.equals("§5§lGenç Ejderha")) {

                        if(siralama.get() == 1){
                            Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC "+key+" 7");
                            Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                            Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejdergenc 1");
                        }
                        if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC "+key+" 6");
                        if(siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC "+key+" 5");
                        if(siralama.get() == 4 || siralama.get() == 5)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC "+key+" 4");
                        if(siralama.get() == 6 || siralama.get() == 7)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC "+key+" 3");
                        //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GENC "+key+" 3");

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 295) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Kask&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key +" set:gencset helmet");
                                weightMapi.put(key, weightMapi.get(key) - 295);
                            }
                        }

                        if (weightMapi.get(key) >= 410) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Göğüslük&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key +" set:gencset chestplate");
                                weightMapi.put(key, weightMapi.get(key) - 410);
                            }
                        }

                        if (weightMapi.get(key) >= 360) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Pantolon&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gencset leggings");
                                weightMapi.put(key, weightMapi.get(key) - 360);
                            }
                        }

                        if (weightMapi.get(key) >= 290) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cGenç Ejder Bot&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key +" set:gencset boots ");
                                weightMapi.put(key, weightMapi.get(key) - 290);
                            }
                        }

                        if (weightMapi.get(key) >= 500) {
                            if ((Math.random() * 100) < 25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 500);
                            }
                        }

                        if (weightMapi.get(key) >= 800) {
                            if ((Math.random() * 100) < 5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 800);
                            }
                        }



                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGenç Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }



                        if (weightMapi.get(key) >= 5) {
                            int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                            Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                            weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                        }
                    }

                    //bilgedragon
                    if (bizimDragonAd.equals("§5§lBilge Ejderha")) {

                        if(siralama.get() == 1){
                            Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE "+key+" 7");
                            Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                            Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejderbilge 1");
                        }
                        if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE "+key+" 6");
                        if(siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE "+key+" 5");
                        if(siralama.get() == 4 || siralama.get() == 5)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE "+key+" 4");
                        if(siralama.get() == 6 || siralama.get() == 7)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE "+key+" 3");
                        //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:BILGE "+key+" 3");

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoitems give " + key +" ejderha_avcisi");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 295) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Kask&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset helmet");
                                weightMapi.put(key, weightMapi.get(key) - 295);
                            }
                        }

                        if (weightMapi.get(key) >= 410) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Göğüslük&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset chestplate");
                                weightMapi.put(key, weightMapi.get(key) - 410);
                            }
                        }

                        if (weightMapi.get(key) >= 360) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Pantolon&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset leggings");
                                weightMapi.put(key, weightMapi.get(key) - 360);
                            }
                        }

                        if (weightMapi.get(key) >= 290) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cBilge Ejder Bot&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:bilgeset boots");
                                weightMapi.put(key, weightMapi.get(key) - 290);
                            }
                        }

                        if (weightMapi.get(key) >= 500) {
                            if ((Math.random() * 100) < 25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 500);
                            }
                        }

                        if (weightMapi.get(key) >= 800) {
                            if ((Math.random() * 100) < 5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 800);
                            }
                        }



                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lBilge Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }



                        if (weightMapi.get(key) >= 5) {
                            int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                            Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                            weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                        }
                    }


                    //güvenilmezdragon
                    if (bizimDragonAd.equals("§5§lGüvenilmez Ejderha")) {

                        if(siralama.get() == 1){
                            Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ "+key+" 7");
                            Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                            Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejderguvenilmez 1");
                        }
                        if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ "+key+" 6");
                        if(siralama.get() == 3) Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ "+key+" 5");
                        if(siralama.get() == 4 || siralama.get() == 5)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ "+key+" 4");
                        if(siralama.get() == 6 || siralama.get() == 7)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ "+key+" 3");
                        //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUVENILMEZ "+key+" 3");

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 295) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Kask&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset helmet");
                                weightMapi.put(key, weightMapi.get(key) - 295);
                            }
                        }

                        if (weightMapi.get(key) >= 410) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Göğüslük&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset chestplate");
                                weightMapi.put(key, weightMapi.get(key) - 410);
                            }
                        }

                        if (weightMapi.get(key) >= 360) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Pantolon&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset leggings");
                                weightMapi.put(key, weightMapi.get(key) - 360);
                            }
                        }

                        if (weightMapi.get(key) >= 290) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cGüvenilmez Ejder Bot&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:guvenilmezset boots ");
                                weightMapi.put(key, weightMapi.get(key) - 290);
                            }
                        }

                        if (weightMapi.get(key) >= 500) {
                            if ((Math.random() * 100) < 25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 500);
                            }
                        }

                        if (weightMapi.get(key) >= 800) {
                            if ((Math.random() * 100) < 5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 800);
                            }
                        }



                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüvenilmez Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }



                        if (weightMapi.get(key) >= 5) {
                            int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                            Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                            weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                        }
                    }

                    //güçlüdragon
                    if (bizimDragonAd.equals("§5§lGüçlü Ejderha")) {

                        if(siralama.get() == 1){
                            Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU "+key+" 7");
                            Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                            Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejderguclu 1");
                        }
                        if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU "+key+" 6");
                        if(siralama.get() == 3)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU "+key+" 5");
                        if (siralama.get() == 4 || siralama.get() == 5)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU "+key+" 4");
                        if(siralama.get() == 6 || siralama.get() == 7)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU "+key+" 3");
                        //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:GUCLU "+key+" 3");

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 295) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Kask&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset helmet");
                                weightMapi.put(key, weightMapi.get(key) - 295);
                            }
                        }

                        if (weightMapi.get(key) >= 410) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Göğüslük&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset chestplate");
                                weightMapi.put(key, weightMapi.get(key) - 410);
                            }
                        }

                        if (weightMapi.get(key) >= 360) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Pantolon&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset leggings");
                                weightMapi.put(key, weightMapi.get(key) - 360);
                            }
                        }

                        if (weightMapi.get(key) >= 290) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cGüçlü Ejder Bot&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:gucluset boots  1");
                                weightMapi.put(key, weightMapi.get(key) - 290);
                            }
                        }

                        if (weightMapi.get(key) >= 500) {
                            if ((Math.random() * 100) < 25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 500);
                            }
                        }

                        if (weightMapi.get(key) >= 800) {
                            if ((Math.random() * 100) < 5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lGüçlü Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 800);
                            }
                        }



                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &5&lKoruyucu Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }



                        if (weightMapi.get(key) >= 5) {
                            int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                            Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                            weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                        }
                    }

                //kutsaldragon
                if (bizimDragonAd.equals("§6§lKutsal Ejderha")) {

                    if(siralama.get() == 1)  {
                        Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL "+key+" 7");
                        Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                        Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejderkutsal 1");
                    }
                    if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL "+key+" 6");
                    if(siralama.get() == 3)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL "+key+" 5");
                    if(siralama.get() == 4 || siralama.get() == 5)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL "+key+" 4");
                    if(siralama.get() == 6 || siralama.get() == 7)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KUTSAL "+key+" 3");
                    //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 3");

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.25) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 295) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Kask&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset helmet");
                            weightMapi.put(key, weightMapi.get(key) - 295);
                        }
                    }

                    if (weightMapi.get(key) >= 410) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Göğüslük&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset chestplate");
                            weightMapi.put(key, weightMapi.get(key) - 410);
                        }
                    }

                    if (weightMapi.get(key) >= 360) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Pantolon&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset leggings");
                            weightMapi.put(key, weightMapi.get(key) - 360);
                        }
                    }

                    if (weightMapi.get(key) >= 290) {
                        if ((Math.random() * 100) < 2.5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cKutsal Ejder Bot&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kutsalset boots");
                            weightMapi.put(key, weightMapi.get(key) - 290);
                        }
                    }

                    if (weightMapi.get(key) >= 500) {
                        if ((Math.random() * 100) < 50) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 500);
                        }
                    }

                    if (weightMapi.get(key) >= 800) {
                        if ((Math.random() * 100) < 5) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                            Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                            weightMapi.put(key, weightMapi.get(key) - 800);
                        }
                    }


                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 450) {
                        if ((Math.random() * 100) < gozsayi * 0.1) {
                            Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKutsal Ejderha&b'yı keserek &5Ender Ejderhası Evcil Hayvanı&b kazandı.");
                            Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonepic");
                            weightMapi.put(key, weightMapi.get(key) - 450);
                        }
                    }

                    if (weightMapi.get(key) >= 5) {
                        int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                        Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                        weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                    }
                }


                    //kilgharrahdragon
                    if (bizimDragonAd.equals("§6§lKilgharrah Ejderhası")) {

                        if(siralama.get() == 1)  {
                            Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 7");
                            Bukkit.dispatchCommand(sender, "q point "+key+" add default.ejderkesme 1");
                            Bukkit.dispatchCommand(sender, "q point AvalonGuard add default.ejderkilgharrah 1");
                        }
                        if(siralama.get() == 2)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 6");
                        if(siralama.get() == 3)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 5");
                        if(siralama.get() == 4 || siralama.get() == 5)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 4");
                        if(siralama.get() == 6 || siralama.get() == 7)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 3");
                        //if(siralama.get() == 9 || siralama.get() == 10 || siralama.get() == 8)  Bukkit.dispatchCommand(sender, "mi give TOOL DRAGON:KILGHARRAH "+key+" 3");

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.25) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cEjderha Kılıcı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoitems give " + key + " ejderha_avcisi");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 295) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Kask&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset helmet");
                                weightMapi.put(key, weightMapi.get(key) - 295);
                            }
                        }

                        if (weightMapi.get(key) >= 410) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Göğüslük&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset chestplate");
                                weightMapi.put(key, weightMapi.get(key) - 410);
                            }
                        }

                        if (weightMapi.get(key) >= 360) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Pantolon&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset leggings");
                                weightMapi.put(key, weightMapi.get(key) - 360);
                            }
                        }

                        if (weightMapi.get(key) >= 290) {
                            if ((Math.random() * 100) < 2.5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cKilgharrah Ejder Bot&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecoarmor give " + key + " set:kilgharrahset boots ");
                                weightMapi.put(key, weightMapi.get(key) - 290);
                            }
                        }

                        if (weightMapi.get(key) >= 500) {
                            if ((Math.random() * 100) < 50) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cEjderha Yumurtası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_egg" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 500);
                            }
                        }

                        if (weightMapi.get(key) >= 800) {
                            if ((Math.random() * 100) < 5) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderha&b'yı keserek &cEjderha Kafası&b kazandı.");
                                Bukkit.dispatchCommand(sender, "give " + key + " dragon_head" + " 1");
                                weightMapi.put(key, weightMapi.get(key) - 800);
                            }
                        }


                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderhası&b'nı keserek &9Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonrare");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }

                        if (weightMapi.get(key) >= 450) {
                            if ((Math.random() * 100) < gozsayi * 0.1) {
                                Bukkit.dispatchCommand(sender, "broadcast &6" + key + " &badlı oyuncu &6&lKilgharrah Ejderhası&b'nı keserek &5Ender Ejderhası Evcil Hayvanı&b kazandı.");
                                Bukkit.dispatchCommand(sender, "ecopets giveegg " + key + " enderdragonepic");
                                weightMapi.put(key, weightMapi.get(key) - 450);
                            }
                        }



                        if (weightMapi.get(key) >= 5) {
                            int endgozsayi = (int) Math.floor(weightMapi.get(key) / 5);
                            Bukkit.dispatchCommand(sender, "give " + key + " ender_pearl " + endgozsayi);
                            weightMapi.put(key, weightMapi.get(key) - (endgozsayi * 5));
                        }
                    }
                    siralama.getAndIncrement();
                    // Selam
            });

            hasarMapi.clear();
            gozmapi.clear();
            weightMapi.clear();
        }
    }

    }


}



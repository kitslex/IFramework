package me.ashydev.iframework.hypixel.components.abilities;

import de.tr7zw.nbtapi.NBTItem;
import me.ashydev.iframework.IFramework;
import me.ashydev.iframework.framework.item.builder.ItemBuilder;
import me.ashydev.iframework.framework.util.Color;
import me.ashydev.iframework.hypixel.HypixelItem;
import me.ashydev.iframework.hypixel.components.AbilityComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class InstantTransmissionAbility extends AbilityComponent {
    private int distance;

    public InstantTransmissionAbility(int distance) {
        super("Instant Transmission", "INSTANT_TRANSMISSION", "Right Click", List.of("Teleport &a" + distance + " blocks&7 ahead of", "you and gain &aSpeed II&7 for &a3 seconds&7."), 0);

        this.distance = distance;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) return;

        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.getCompound("Item-Framework") == null) return;
        ItemBuilder<HypixelItem> builder = IFramework.getInstance().getBuilder();

        HypixelItem hypixelItem = builder.from(item);

        if (hypixelItem == null) return;

        if (hypixelItem.hasAbility(getId())) {
            teleport(player, distance);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1, true, false, false));
        }
    }

    public void teleport(Player player, int distance)
    {
        try
        {
            int temp = distance;

            for(int range = 0; range < distance - 1; range++) {
                Location location = player.getTargetBlock(null, range).getLocation();

                if (location.getBlock().getType() != Material.AIR)
                    temp = range +1;
                    break;
            }
            Location location = player.getTargetBlock(null, temp - 1).getLocation();

            location.setYaw(player.getLocation().getYaw());
            location.setPitch(player.getLocation().getPitch());

            location.add(0.5, 0, 0.5);

            if (temp != distance)
                player.sendMessage(Color.colorize("&cYou can't teleport through blocks!"));

            if (temp > 1) player.teleport(location);
            else player.teleport(player.getLocation());
        }
        catch (IllegalStateException ex) {/*ignored*/}
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 3f, 1f);
    }
}

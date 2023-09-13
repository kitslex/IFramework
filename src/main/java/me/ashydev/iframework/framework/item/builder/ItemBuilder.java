package me.ashydev.iframework.framework.item.builder;

import me.ashydev.iframework.framework.item.CustomItem;
import me.ashydev.iframework.framework.types.Builder;
import org.bukkit.inventory.ItemStack;

public interface ItemBuilder<T extends CustomItem> extends Builder<T, ItemStack> { }

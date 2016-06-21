package com.cricketcraft.chisel.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemCarvablePumpkin extends ItemBlock {
    public ItemCarvablePumpkin(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return true;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName().replace("chisel.", "");
    }

    @Override
    public int getMetadata(int i) {
        return i;
    }
}

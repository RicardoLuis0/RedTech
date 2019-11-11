package com.ricardoredstone.redtech.base;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class ModDirectionalBlockHorizontal extends ModDirectionalBlock { //horizontal only directional block (ex. machines)

    public ModDirectionalBlockHorizontal(String name, Properties block_properties) {
        super(name, block_properties);
    }

    public ModDirectionalBlockHorizontal(String name, Properties block_properties, boolean reverse) {
        super(name, block_properties, reverse);
    }

    public ModDirectionalBlockHorizontal(String name, Properties block_properties, Item.Properties item_properties) {
        super(name, block_properties, item_properties);
    }

    public ModDirectionalBlockHorizontal(String name, Properties block_properties, Item.Properties item_properties, boolean reverse) {
        super(name, block_properties, item_properties, reverse);
    }

    @Override
    protected Direction getFacing(BlockPos pos, LivingEntity entity) {
        return Direction.getFacingFromVector((float) (entity.posX - pos.getX()), 0, (float) (entity.posZ - pos.getZ()));
    }

}

package com.ricardoredstone.redtech.base;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;

@SuppressWarnings("unused")
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
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(BlockStateProperties.FACING, reverse?context.getPlacementHorizontalFacing().getOpposite():context.getPlacementHorizontalFacing());
    }

}

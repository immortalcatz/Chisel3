package com.cricketcraft.chisel.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChiselTabs {
	private static class CustomCreativeTab extends CreativeTabs {

		private boolean search;

		private ItemStack stack;

		public CustomCreativeTab(String label, boolean searchbar) {
			super(label);
			this.search = searchbar;
		}

		@Override
		public Item getTabIconItem() {
			return stack.getItem();
		}

		public void setTabIconItemStack(ItemStack stack) {
			this.stack = stack;
		}

		@Override
		public ItemStack getIconItemStack() {
			return stack;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public String getBackgroundImageName() {

			return search ? "item_search.png" : super.getBackgroundImageName();
		}

		@Override
		public int getSearchbarWidth() {

			return 89;
		}

		@Override
		public boolean hasSearchBar() {

			return search;
		}
	}

	private static boolean atLeastOneModIsLoaded = false;

	public static final CustomCreativeTab tabChisel = new CustomCreativeTab("tabChisel", false);

	public static final CustomCreativeTab tabStoneChiselBlocks = new CustomCreativeTab("tabStoneChiselBlocks", true);

	public static final CustomCreativeTab tabWoodChiselBlocks = new CustomCreativeTab("tabWoodChiselBlocks", true);

	public static final CustomCreativeTab tabMetalChiselBlocks = new CustomCreativeTab("tabMetalChiselBlocks", true);

	public static final CustomCreativeTab tabOtherChiselBlocks = new CustomCreativeTab("tabOtherChiselBlocks", true);

	public static final CustomCreativeTab tabModdedChiselBlocks = new CustomCreativeTab("tabModdedChiselBlocks", true);

	// this serves mostly just to load the static initializers
	public static void preInit() {
		tabStoneChiselBlocks.setTabIconItemStack(new ItemStack(ChiselBlocks.holystone, 1));
		tabWoodChiselBlocks.setTabIconItemStack(new ItemStack(ChiselBlocks.oak, 1));
		tabMetalChiselBlocks.setTabIconItemStack(new ItemStack(ChiselBlocks.technical, 1));
		tabOtherChiselBlocks.setTabIconItemStack(new ItemStack(ChiselBlocks.litpumpkin, 1));
		tabModdedChiselBlocks.setTabIconItemStack(new ItemStack(ChiselBlocks.voidstone, 1));
		tabChisel.setTabIconItemStack(new ItemStack(ChiselItems.obsidianChisel, 1));
	}
}

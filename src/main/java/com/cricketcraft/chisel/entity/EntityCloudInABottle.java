package com.cricketcraft.chisel.entity;

import com.cricketcraft.chisel.init.ChiselBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class EntityCloudInABottle extends EntityThrowable {
	private Random random = new Random();

	public EntityCloudInABottle(World world) {
		super(world);
	}

	public EntityCloudInABottle(World world, EntityLivingBase entityLivingBase) {
		super(world, entityLivingBase);
	}

	public EntityCloudInABottle(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult mop) {
		if (worldObj.isRemote) {
			return;
		}

		BlockPos pos = mop.getBlockPos();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		switch (mop.sideHit.getIndex()) {
		case 0:
			y--;
			break;
		case 1:
			y++;
			break;
		case 2:
			z--;
			break;
		case 3:
			z++;
			break;
		case 4:
			x--;
			break;
		case 5:
			x++;
			break;
		}

		generate(worldObj, random, x, y, z, 40);
		worldObj.playSound(posX, posY, posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 2002, 2, false);
		setDead();
	}

	public boolean generate(World world, Random random, int x, int y, int z, int numberOfBlocks) {
		int X[] = new int[9];
		int Y[] = new int[9];
		int Z[] = new int[9];

		for (int dir = 0; dir < 9; dir++) {
			X[dir] = x;
			Y[dir] = y;
			Z[dir] = z;
		}

		int count = 0;

		while (count < numberOfBlocks) {
			for (int dir = 0; dir < 9; dir++) {
				if (count >= numberOfBlocks) {
					break;
				}

				int dx = dir % 3 - 1;
				int dz = dir / 3 - 1;

				if (dx == 0 && dz == 0) {
					continue;
				}

				X[dir] += random.nextInt(3) - 1 + dx;
				Y[dir] += random.nextInt(2) * (random.nextInt(3) - 1);
				Z[dir] += random.nextInt(3) - 1 + dz;

				int nx = X[dir];
				int ny = Y[dir];
				int nz = Z[dir];

				for (int c = nx; c < nx + random.nextInt(4) + 1; c++) {
					for (int d = ny; d < ny + random.nextInt(1) + 2; d++) {
						for (int e = nz; e < nz + random.nextInt(4) + 1; e++) {
							BlockPos positionToLook = new BlockPos(c, d, e);

							if (world.getBlockState(positionToLook).getBlock().isAir(world.getBlockState(positionToLook), world, positionToLook) && Math.abs(c - nx) + Math.abs(d - ny) + Math.abs(e - nz) < 4 * 1 + random.nextInt(2)) {
								world.setBlockState(positionToLook, ChiselBlocks.cloud.getDefaultState());
								count++;
							}
						}
					}
				}
			}
		}
		return true;
	}
}

package WayofTime.bloodmagic.livingArmour.upgrade;

import WayofTime.bloodmagic.BloodMagic;
import WayofTime.bloodmagic.livingArmour.ILivingArmour;
import WayofTime.bloodmagic.livingArmour.LivingArmourUpgrade;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class LivingArmourUpgradeSpeed extends LivingArmourUpgrade {
    public static final int[] costs = new int[]{3, 7, 13, 26, 42, 60, 90, 130, 180, 250};
    public static final double[] speedModifier = new double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.7, 0.9, 1.1, 1.3, 1.5};
    public static final int[] sprintSpeedTime = new int[]{0, 0, 0, 0, 0, 20, 60, 60, 100, 200};
    public static final int[] sprintSpeedLevel = new int[]{0, 0, 0, 0, 0, 0, 0, 1, 1, 2};
    public static final int[] healthModifier = new int[]{0, 0, 0, 0, 0, 0, 0, 4, 10, 20};
    public static final int[] sprintRegenTime = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 25};

    public LivingArmourUpgradeSpeed(int level) {
        super(level);
    }

    public double getSpeedModifier() {
        return speedModifier[this.level];
    }

    @Override
    public void onTick(World world, PlayerEntity player, ILivingArmour livingArmour) {
        if (player.isSprinting()) {
            if (sprintSpeedTime[this.level] > 0) {
                player.addPotionEffect(new EffectInstance(Effects.SPEED, sprintSpeedTime[this.level], sprintSpeedLevel[this.level], false, false));
            }

            if (sprintRegenTime[this.level] > 0 && !player.isPotionActive(Effects.REGENERATION)) {
                player.addPotionEffect(new EffectInstance(Effects.REGENERATION, sprintRegenTime[this.level], 0, false, false));
            }
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers() {
        Multimap<String, AttributeModifier> modifierMap = HashMultimap.create();

//        modifierMap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(new UUID(895132, 1), "Speed modifier" + 1, speedModifier[this.level], 1));

//        if (healthModifier[this.level] > 0) {
//            String name = getUniqueIdentifier() + "-HealthModifier1";
//            modifierMap.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(UUID.nameUUIDFromBytes(StringUtils.getBytesUtf8(name)), "HealthModifier1", healthModifier[this.level], 0));
//        }

        return modifierMap;
    }

    @Override
    public String getUniqueIdentifier() {
        return BloodMagic.MODID + ".upgrade.movement";
    }

    @Override
    public int getMaxTier() {
        return 10;
    }

    @Override
    public int getCostOfUpgrade() {
        return costs[this.level];
    }

    @Override
    public void writeToNBT(CompoundNBT tag) {
        // EMPTY
    }

    @Override
    public void readFromNBT(CompoundNBT tag) {
        // EMPTY
    }

    @Override
    public String getTranslationKey() {
        return tooltipBase + "speed";
    }
}

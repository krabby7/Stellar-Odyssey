package net.krabby7.stellarodyssey.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class OverseerEntityBackUp extends Monster {

    public static final AnimationState idleAnimationState = new AnimationState();
    public static AnimationState attackAnimState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public static final float FLAP_DEGREES_PER_TICK = 45.836624F;
    public static final int TICKS_PER_FLAP = Mth.ceil(3.9269907F);
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    private static final int FLAG_IS_CHARGING = 1;

    @Nullable
    private BlockPos boundOrigin;


    public OverseerEntityBackUp(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new OverseerMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new OverseerChargeAttackGoal());
        this.goalSelector.addGoal(8, new OverseerRandomMoveGoal());
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 25.0).add(Attributes.MOVEMENT_SPEED, 0.20000001192092896).add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.FOLLOW_RANGE, 24.0).add(Attributes.STEP_HEIGHT, 1.0);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 20;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    static class RandomFloatAroundGoal extends Goal {
        private final OverseerEntityBackUp overseer;

        public RandomFloatAroundGoal(OverseerEntityBackUp overseer) {
            this.overseer = overseer;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl movecontrol = this.overseer.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.overseer.getX();
                double d1 = movecontrol.getWantedY() - this.overseer.getY();
                double d2 = movecontrol.getWantedZ() - this.overseer.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0 || d3 > 3600.0;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource randomsource = this.overseer.getRandom();
            double d0 = this.overseer.getX() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.overseer.getY() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.overseer.getZ() + (double)((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.overseer.getMoveControl().setWantedPosition(d0, d1, d2, 1.0);
        }
    }


    class OverseerRandomMoveGoal extends Goal {
        public OverseerRandomMoveGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return !OverseerEntityBackUp.this.getMoveControl().hasWanted() && OverseerEntityBackUp.this.random.nextInt(reducedTickDelay(7)) == 0;
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void tick() {
            BlockPos blockpos = OverseerEntityBackUp.this.getBoundOrigin();
            if (blockpos == null) {
                blockpos = OverseerEntityBackUp.this.blockPosition();
            }

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.offset(OverseerEntityBackUp.this.random.nextInt(15) - 7, OverseerEntityBackUp.this.random.nextInt(11) - 5, OverseerEntityBackUp.this.random.nextInt(15) - 7);
                if (OverseerEntityBackUp.this.level().isEmptyBlock(blockpos1)) {
                    OverseerEntityBackUp.this.moveControl.setWantedPosition((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 0.25);
                    if (OverseerEntityBackUp.this.getTarget() == null) {
                        OverseerEntityBackUp.this.getLookControl().setLookAt((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }
    
    class OverseerChargeAttackGoal extends Goal {
        public OverseerChargeAttackGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            LivingEntity livingentity = OverseerEntityBackUp.this.getTarget();
            return livingentity != null && livingentity.isAlive() && !OverseerEntityBackUp.this.getMoveControl().hasWanted() && OverseerEntityBackUp.this.random.nextInt(reducedTickDelay(7)) == 0 ? OverseerEntityBackUp.this.distanceToSqr(livingentity) > 4.0 : false;
        }

        public boolean canContinueToUse() {
            return OverseerEntityBackUp.this.getMoveControl().hasWanted() && OverseerEntityBackUp.this.isCharging() && OverseerEntityBackUp.this.getTarget() != null && OverseerEntityBackUp.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = OverseerEntityBackUp.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                OverseerEntityBackUp.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0);
            }

            OverseerEntityBackUp.this.setIsCharging(true);
            OverseerEntityBackUp.this.playSound(SoundEvents.ENDER_EYE_LAUNCH, 1.0F, 1.0F);
        }

        public void stop() {
            OverseerEntityBackUp.this.setIsCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = OverseerEntityBackUp.this.getTarget();
            if (livingentity != null) {
                if (OverseerEntityBackUp.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                    OverseerEntityBackUp.this.doHurtTarget(livingentity);
                    OverseerEntityBackUp.this.setIsCharging(false);
                } else {
                    double d0 = OverseerEntityBackUp.this.distanceToSqr(livingentity);
                    if (d0 < 9.0) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        OverseerEntityBackUp.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0);
                    }
                    if (isCharging()){
                        attackAnimState.start(OverseerEntityBackUp.this.tickCount);
                    }
                }
            }

        }
    }


    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
    }
    private boolean getVexFlag(int mask) {
        int i = (Byte)this.entityData.get(DATA_FLAGS_ID);
        return (i & mask) != 0;
    }

    private void setVexFlag(int mask, boolean value) {
        int i = (Byte)this.entityData.get(DATA_FLAGS_ID);
        if (value) {
            i |= mask;
        } else {
            i &= ~mask;
        }

        this.entityData.set(DATA_FLAGS_ID, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    public void setIsCharging(boolean charging) {
        this.setVexFlag(1, charging);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.END_PORTAL_FRAME_FILL;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDER_EYE_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENDER_EYE_DEATH;
    }

    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        RandomSource randomsource = level.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, difficulty);
        this.populateDefaultEquipmentEnchantments(level, randomsource, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
    }

    static {
        DATA_FLAGS_ID = SynchedEntityData.defineId(OverseerEntityBackUp.class, EntityDataSerializers.BYTE);
    }

    class OverseerMoveControl extends MoveControl {
        public OverseerMoveControl(OverseerEntityBackUp vex) {
            super(vex);
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - OverseerEntityBackUp.this.getX(), this.wantedY - OverseerEntityBackUp.this.getY(), this.wantedZ - OverseerEntityBackUp.this.getZ());
                double d0 = vec3.length();
                if (d0 < OverseerEntityBackUp.this.getBoundingBox().getSize()) {
                    this.operation = Operation.WAIT;
                    OverseerEntityBackUp.this.setDeltaMovement(OverseerEntityBackUp.this.getDeltaMovement().scale(0.5));
                } else {
                    OverseerEntityBackUp.this.setDeltaMovement(OverseerEntityBackUp.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05 / d0)));
                    if (OverseerEntityBackUp.this.getTarget() == null) {
                        Vec3 vec31 = OverseerEntityBackUp.this.getDeltaMovement();
                        OverseerEntityBackUp.this.setYRot(-((float) Mth.atan2(vec31.x, vec31.z)) * 57.295776F);
                        OverseerEntityBackUp.this.yBodyRot = OverseerEntityBackUp.this.getYRot();
                    } else {
                        double d2 = OverseerEntityBackUp.this.getTarget().getX() - OverseerEntityBackUp.this.getX();
                        double d1 = OverseerEntityBackUp.this.getTarget().getZ() - OverseerEntityBackUp.this.getZ();
                        OverseerEntityBackUp.this.setYRot(-((float)Mth.atan2(d2, d1)) * 57.295776F);
                        OverseerEntityBackUp.this.yBodyRot = OverseerEntityBackUp.this.getYRot();
                    }
                }
            }

        }
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos boundOrigin) {
        this.boundOrigin = null;
    }


}

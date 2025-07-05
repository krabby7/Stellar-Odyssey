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
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class OverseerEntity extends Monster {

    public static final AnimationState idleAnimationState = new AnimationState();
    public static final AnimationState attackAnimState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private int attackAnimationTimeout = 0;

    private int chargeCooldown = 0;


    public static final float FLAP_DEGREES_PER_TICK = 45.836624F;
    public static final int TICKS_PER_FLAP = Mth.ceil(3.9269907F);
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    private static final int FLAG_IS_CHARGING = 1;

    @Nullable
    private BlockPos boundOrigin;


    public OverseerEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new OverseerMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new OverseerChargeAttackGoal());
        this.goalSelector.addGoal(5, new OverseerRandomMoveGoal());
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 24.0F, 1.0F));
        this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Player.class, 3.0F, 1.0, 1.2));
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

        if(this.attackAnimationTimeout > 0) {
            --this.attackAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        //this.noPhysics = true;
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if (isCharging() & attackAnimationTimeout == 0){
            attackAnimState.start(OverseerEntity.this.tickCount);
            attackAnimationTimeout = 20;
        }

        if (chargeCooldown > 0){
            chargeCooldown--;
        }
        else if (chargeCooldown < 0){
            chargeCooldown = 0;
        }


        this.noPhysics = false;
        this.fallDistance = 0;
        this.setNoGravity(true);
    }

    @Override
    public void move(MoverType type, Vec3 pos) {
        super.move(type, pos);
        //this.checkInsideBlocks();
    }

    class OverseerRandomMoveGoal extends Goal {
        public OverseerRandomMoveGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return !OverseerEntity.this.getMoveControl().hasWanted() && OverseerEntity.this.random.nextInt(reducedTickDelay(7)) == 0;
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void tick() {
            BlockPos blockpos = OverseerEntity.this.getBoundOrigin();
            if (blockpos == null) {
                blockpos = OverseerEntity.this.blockPosition();
            }

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.offset(OverseerEntity.this.random.nextInt(15) - 7, OverseerEntity.this.random.nextInt(11) - 5, OverseerEntity.this.random.nextInt(15) - 7);
                if (OverseerEntity.this.level().isEmptyBlock(blockpos1)) {
                    OverseerEntity.this.moveControl.setWantedPosition((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 0.25);
                    if (OverseerEntity.this.getTarget() == null) {
                        OverseerEntity.this.getLookControl().setLookAt((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.5, (double)blockpos1.getZ() + 0.5, 180.0F, 20.0F);
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
            LivingEntity livingentity = OverseerEntity.this.getTarget();
            return livingentity != null && livingentity.isAlive() && !OverseerEntity.this.getMoveControl().hasWanted() && chargeCooldown == 0 && OverseerEntity.this.random.nextInt(reducedTickDelay(7)) == 0 ? OverseerEntity.this.distanceToSqr(livingentity) > 4.0 : false;
        }

        public boolean canContinueToUse() {
            return OverseerEntity.this.getMoveControl().hasWanted() && OverseerEntity.this.isCharging() && OverseerEntity.this.getTarget() != null && OverseerEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = OverseerEntity.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                OverseerEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 2.3);
                chargeCooldown = 100;
            }

            OverseerEntity.this.setIsCharging(true);
            OverseerEntity.this.playSound(SoundEvents.ENDER_EYE_LAUNCH, 1.0F, 1.0F);
        }

        public void stop() {
            OverseerEntity.this.setIsCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = OverseerEntity.this.getTarget();
            if (livingentity != null) {
                if (OverseerEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                    OverseerEntity.this.doHurtTarget(livingentity);
                    OverseerEntity.this.setIsCharging(false);
                } else {
                    double d0 = OverseerEntity.this.distanceToSqr(livingentity);
                    if (d0 < 9.0) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        OverseerEntity.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 2.0);
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
        DATA_FLAGS_ID = SynchedEntityData.defineId(OverseerEntity.class, EntityDataSerializers.BYTE);
    }

    class OverseerMoveControl extends MoveControl {
        public OverseerMoveControl(OverseerEntity vex) {
            super(vex);
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - OverseerEntity.this.getX(), this.wantedY - OverseerEntity.this.getY(), this.wantedZ - OverseerEntity.this.getZ());
                double d0 = vec3.length();
                if (d0 < OverseerEntity.this.getBoundingBox().getSize()) {
                    this.operation = Operation.WAIT;
                    OverseerEntity.this.setDeltaMovement(OverseerEntity.this.getDeltaMovement().scale(0.5));
                } else {
                    OverseerEntity.this.setDeltaMovement(OverseerEntity.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05 / d0)));
                    if (OverseerEntity.this.getTarget() == null) {
                        Vec3 vec31 = OverseerEntity.this.getDeltaMovement();
                        //OverseerEntity.this.setYRot(-((float) Mth.atan2(vec31.x, vec31.z)) * 57.295776F);
                        //OverseerEntity.this.yBodyRot = OverseerEntity.this.getYRot();
                    } else {
                        double d2 = OverseerEntity.this.getTarget().getX() - OverseerEntity.this.getX();
                        double d1 = OverseerEntity.this.getTarget().getZ() - OverseerEntity.this.getZ();
                        //OverseerEntity.this.setYRot(-((float)Mth.atan2(d2, d1)) * 57.295776F);
                        //OverseerEntity.this.yBodyRot = OverseerEntity.this.getYRot();
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

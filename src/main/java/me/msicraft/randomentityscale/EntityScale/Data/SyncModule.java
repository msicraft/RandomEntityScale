package me.msicraft.randomentityscale.EntityScale.Data;

import me.msicraft.randomentityscale.RandomEntityScale;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;

public class SyncModule {

    private boolean isHealthEnabled;
    private double healthMultiplier;

    private boolean isDamageEnabled;
    private double damageMultiplier;

    private boolean isArmorEnabled;
    private double armorMultiplier;

    private boolean isArmorToughnessEnabled;
    private double armorToughnessMultiplier;

    private boolean isMovementSpeedEnabled;
    private double movementSpeedMultiplier;

    public SyncModule(RandomEntityScale plugin) {
        updateVariables(plugin);
    }

    public void updateVariables(RandomEntityScale plugin) {
        FileConfiguration config = plugin.getConfig();

        isHealthEnabled = config.getBoolean("SyncModule.Health.Enabled", false);
        healthMultiplier = config.getDouble("SyncModule.Health.Multiplier", 1);

        isDamageEnabled = config.getBoolean("SyncModule.Damage.Enabled", false);
        damageMultiplier = config.getDouble("SyncModule.Damage.Multiplier", 1);

        isArmorEnabled = config.getBoolean("SyncModule.Armor.Enabled", false);
        armorMultiplier = config.getDouble("SyncModule.Armor.Multiplier", 1);

        isArmorToughnessEnabled = config.getBoolean("SyncModule.ArmorToughness.Enabled", false);
        armorToughnessMultiplier = config.getDouble("SyncModule.ArmorToughness.Multiplier", 1);

        isMovementSpeedEnabled = config.getBoolean("SyncModule.MovementSpeed.Enabled", false);
        movementSpeedMultiplier = config.getDouble("SyncModule.MovementSpeed.Multiplier", 1);
    }

    public boolean apply(LivingEntity livingEntity) {
        AttributeInstance scaleInstance = livingEntity.getAttribute(Attribute.GENERIC_SCALE);
        if (scaleInstance != null) {
            double value = scaleInstance.getValue();
            double defaultValue = scaleInstance.getDefaultValue();

            double scaleM = value / defaultValue;

            if (isHealthEnabled) {
                AttributeInstance healthI = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                if (healthI != null) {
                    double cal = healthI.getBaseValue() * (scaleM * getHealthMultiplier());

                    healthI.setBaseValue(cal);
                }
            }

            if (isDamageEnabled) {
                AttributeInstance damageI = livingEntity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                if (damageI!= null) {
                    double cal = damageI.getBaseValue() * (scaleM * getDamageMultiplier());

                    damageI.setBaseValue(cal);
                }
            }

            if (isArmorEnabled) {
                AttributeInstance armorI = livingEntity.getAttribute(Attribute.GENERIC_ARMOR);
                if (armorI!= null) {
                    double cal = armorI.getBaseValue() * (scaleM * getArmorMultiplier());

                    armorI.setBaseValue(cal);
                }
            }

            if (isArmorToughnessEnabled) {
                AttributeInstance armorToughnessI = livingEntity.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS);
                if (armorToughnessI!= null) {
                    double cal = armorToughnessI.getBaseValue() * (scaleM * getArmorToughnessMultiplier());

                    armorToughnessI.setBaseValue(cal);
                }
            }

            if (isMovementSpeedEnabled) {
                AttributeInstance movementSpeedI = livingEntity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                if (movementSpeedI!= null) {
                    double cal = movementSpeedI.getBaseValue() * (scaleM * getMovementSpeedMultiplier());

                    movementSpeedI.setBaseValue(cal);
                }
            }
        }
        return false;
    }

    public boolean isHealthEnabled() {
        return isHealthEnabled;
    }

    public void setHealthEnabled(boolean healthEnabled) {
        isHealthEnabled = healthEnabled;
    }

    public double getHealthMultiplier() {
        return healthMultiplier;
    }

    public void setHealthMultiplier(double healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    public boolean isDamageEnabled() {
        return isDamageEnabled;
    }

    public void setDamageEnabled(boolean damageEnabled) {
        isDamageEnabled = damageEnabled;
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public boolean isArmorEnabled() {
        return isArmorEnabled;
    }

    public void setArmorEnabled(boolean armorEnabled) {
        isArmorEnabled = armorEnabled;
    }

    public double getArmorMultiplier() {
        return armorMultiplier;
    }

    public void setArmorMultiplier(double armorMultiplier) {
        this.armorMultiplier = armorMultiplier;
    }

    public boolean isArmorToughnessEnabled() {
        return isArmorToughnessEnabled;
    }

    public void setArmorToughnessEnabled(boolean armorToughnessEnabled) {
        isArmorToughnessEnabled = armorToughnessEnabled;
    }

    public double getArmorToughnessMultiplier() {
        return armorToughnessMultiplier;
    }

    public void setArmorToughnessMultiplier(double armorToughnessMultiplier) {
        this.armorToughnessMultiplier = armorToughnessMultiplier;
    }

    public boolean isMovementSpeedEnabled() {
        return isMovementSpeedEnabled;
    }

    public void setMovementSpeedEnabled(boolean movementSpeedEnabled) {
        isMovementSpeedEnabled = movementSpeedEnabled;
    }

    public double getMovementSpeedMultiplier() {
        return movementSpeedMultiplier;
    }

    public void setMovementSpeedMultiplier(double movementSpeedMultiplier) {
        this.movementSpeedMultiplier = movementSpeedMultiplier;
    }
}

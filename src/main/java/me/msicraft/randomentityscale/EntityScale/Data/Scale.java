package me.msicraft.randomentityscale.EntityScale.Data;

import me.msicraft.randomentityscale.RandomEntityScale;
import me.msicraft.randomentityscale.Util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;

public class Scale {

    private boolean isEnabled;
    private double max;
    private double min;

    public Scale(RandomEntityScale plugin) {
        updateVariables(plugin);
    }

    public void updateVariables(RandomEntityScale plugin) {
        FileConfiguration config = plugin.getConfig();

        isEnabled = config.getBoolean("Scale.Enabled", false);
        max = config.getDouble("Scale.Max", 1.0);
        min = config.getDouble("Scale.Min", 1.0);

        if (min > max) {
            min = max;
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + RandomEntityScale.PREFIX + " The min value of Scale cannot be greater than the max value.");
        }
    }

    public boolean apply(LivingEntity livingEntity) {
        AttributeInstance scaleInstance = livingEntity.getAttribute(Attribute.GENERIC_SCALE);
        if (scaleInstance != null) {
            double scaleMultiplier = MathUtil.getRangeRandomDouble(getMax(), getMin());

            double baseValue = scaleInstance.getBaseValue();
            double cal = baseValue * scaleMultiplier;

            scaleInstance.setBaseValue(cal);
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}

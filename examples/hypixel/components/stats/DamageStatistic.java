package me.ashydev.iframework.hypixel.components.stats;

import me.ashydev.iframework.framework.util.Color;
import me.ashydev.iframework.hypixel.components.StatisticComponent;

public class DamageStatistic extends StatisticComponent<Double> {
    public DamageStatistic(Double value) {
        super("Damage", "DAMAGE", value, new Color("#ff2e4d"), "+", OperatorPosition.PREFIX);
    }
}

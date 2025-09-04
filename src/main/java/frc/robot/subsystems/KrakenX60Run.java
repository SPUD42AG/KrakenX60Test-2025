package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class KrakenX60Run {
    private TalonFX krakenX60Motor;

    public static final int motorID = 2;

    public static final CurrentLimitsConfigs currentLimits = new CurrentLimitsConfigs()
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(10)
            .withSupplyCurrentLowerLimit(10)
            .withSupplyCurrentLowerTime(1.0);

    public KrakenX60Run() {
        initMotor();
    }

    private void initMotor() {
        krakenX60Motor = new TalonFX(motorID);

        krakenX60Motor.setNeutralMode(NeutralModeValue.Brake);
        TalonFXConfigurator configurator = krakenX60Motor.getConfigurator();
        configurator.apply(currentLimits);
        
    }

    public void runKrakenX60() {
        krakenX60Motor.setVoltage(1);
    }
}

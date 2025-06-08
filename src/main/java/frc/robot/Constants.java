// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.SlotConfigs;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class OperatorConstants {

    public static final int kDriverControllerPort = 0;

    public static final Rotation2d kStartingAngle = Rotation2d.fromDegrees(270);

  }

  public static final class ArmConstants {
        //I dont know the numbers yet so 0 is a place holder
        public enum ArmSubsystemState {

            EH(Rotation2d.fromDegrees(270)),
            INTAKE(Rotation2d.fromDegrees(225)),
            SCORE(Rotation2d.fromDegrees(180)),
            STOW(Rotation2d.fromDegrees(90));

            public Rotation2d angle;

            private ArmSubsystemState(Rotation2d angle) {
                this.angle = angle;
            }

        }
        
        public static final int kArmMotorID = 0;
        public static final double kPositionConversionFactor = 0;
        public static final double kVelocityConversionFactor = 0;
        
        public static final double kDt = 0.02;
        public static final int kPeriodMs = 0;

        public static final double kS = 0.0;
        public static final double kG = 0.0;
        public static final double kV = 0.0;
        public static final double kA = 0.0;

        public static final double kP = 0.001;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        
        //The values set here are placeholders for sim

        public static final Rotation2d kStartingAngle = Rotation2d.fromDegrees(0);

        public static final SlotConfigs kPIDConfigs = new SlotConfigs()
            .withKP(kP)
            .withKI(kI)
            .withKD(kD);

        public static final CurrentLimitsConfigs kCurrentLimits = new CurrentLimitsConfigs()
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(70)
            .withSupplyCurrentLowerLimit(40)
            .withSupplyCurrentLowerTime(1.0);

        public static final FeedbackConfigs kFeedbackConfig = new FeedbackConfigs()
            .withSensorToMechanismRatio(25.7143)
        ;

    }
}

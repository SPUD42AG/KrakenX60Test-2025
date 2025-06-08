package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.ArmConstants.ArmSubsystemState;

public class KrakenX60Subsystem extends SubsystemBase {
    
    private TalonFX mArmMotor;
    private ArmFeedforward mFFCalculator;

    private Rotation2d mCurrentSetPoint = ArmConstants.kStartingAngle;
    private State mCurrentState;

    public KrakenX60Subsystem() {

        initArmMotor();
        
        SmartDashboard.putData("preset1Arm", presetCommand(ArmSubsystemState.EH));

    }

    private void initArmMotor() {
        
        mArmMotor = new TalonFX(ArmConstants.kArmMotorID);
        mArmMotor.setNeutralMode(NeutralModeValue.Brake);
        var mConfigurator = mArmMotor.getConfigurator();
        mConfigurator.apply(ArmConstants.kPIDConfigs);
        mConfigurator.apply(ArmConstants.kCurrentLimits);
        mConfigurator.apply(ArmConstants.kFeedbackConfig);
    }

    public void resetMechanism(Rotation2d angle){
        mCurrentSetPoint = angle;
        mCurrentState = new State(angleToRaw(angle), 0.0);
    }

    private Rotation2d rawToAngle(double rotation) {
        Rotation2d angle = Rotation2d.fromRotations(rotation);
        return angle;
    }

    private double angleToRaw(Rotation2d angle) {
        double rotation = angle.getRotations();
        return rotation;
    }

    public Rotation2d getSetpoint() {
        return mCurrentSetPoint;
    }

    public Rotation2d getPosition() {
        var position = mArmMotor.getPosition().getValue().in(Rotations);

        return rawToAngle(position);
    }

    public Rotation2d getTargetPosition(){
        return rawToAngle(mCurrentState.position);
    }

    @Override
    public void periodic() {

        //need set points as a imput

        mCurrentState = new State((angleToRaw(mCurrentSetPoint)), 0.0);

        final PositionVoltage m_request = new PositionVoltage(mCurrentState.position).withFeedForward(mFFCalculator.calculate(mCurrentState.position, mCurrentState.velocity));
        
        mArmMotor.setControl(m_request);

    }
    
    
    private void setMechanismAngle(Rotation2d angle){
        mArmMotor.setPosition(angleToRaw(angle));
    }

    public void setSetpoint(Rotation2d newSetpoint){
        mCurrentSetPoint = newSetpoint;
    }

    public void setSetpoint(ArmSubsystemState newState) {
        setSetpoint(newState.angle);
    }

    public Command setSetpointCommand(Rotation2d newSetpoint){
        return this.runOnce(() -> setSetpoint(newSetpoint));
    }

    public Command presetCommand(ArmSubsystemState preset){
        return setSetpointCommand(preset.angle);
    }


}
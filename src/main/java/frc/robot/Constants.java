package frc.robot;

public final class Constants {
    public static class OperatorConstants {
        public static final int xboxControllerPort = 1;
        public static final int joystickPort = 0;

        public static final double TURNING_RATE = 0.15;
        public static final double SLOW_MODE_RATE = 0.1;
    }

    public static class JoystickIDs {
        public static final int JOYSTICK_TRIGGER_ID = 1;
        public static final int ARM_UP_JOYSTICK = 6;
        public static final int ARM_DOWN_JOYSTICK = 7;
        public static final int ELEVATOR_UP_JOYSTICK = 3;
        public static final int ELEVATOR_DOWN_JOYSTICK = 2;
    }

    public static class CANIDs {
        public static final int MOTOR_LEFT0_ID = 4;
        public static final int MOTOR_LEFT1_ID = 5;
        public static final int MOTOR_RIGHT0_ID = 2;
        public static final int MOTOR_RIGHT1_ID = 3;

        public static final int ARM = 7;
        public static final int DRAWER = 8;
    }
}

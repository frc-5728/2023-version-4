package frc.robot;

public final class Constants {
    public static class OperatorConstants {
        public static final int xboxControllerPort = 1;
        public static final int joystickPort = 0;

        public static final double TURNING_RATE = 0.2;
        public static final double SLOW_MODE_RATE = 0.1;
    }

    public static class JoystickIDs {
        public static final int JOYSTICK_TRIGGER_ID = 1;
        public static final int ARM_UP_JOYSTICK_ID = 6;
        public static final int ARM_DOWN_JOYSTICK_ID = 7;
        public static final int ELEVATOR_UP_JOYSTICK_ID = 3;
        public static final int ELEVATOR_DOWN_JOYSTICK_ID = 2;
        public static final int DRAWER_UP_JOYSTICK_ID = 5;
        public static final int DRAWER_DOWN_JOYSTICK_ID = 4;
    }

    public static class CANIDs {
        public static final int MOTOR_LEFT0_ID = 4;
        public static final int MOTOR_LEFT1_ID = 5;
        public static final int MOTOR_RIGHT0_ID = 2;
        public static final int MOTOR_RIGHT1_ID = 3;

        public static final int ELEVATOR = 6;
        public static final int ARM = 7;
        public static final int DRAWER = 8;
    }

    public static class LimitSwitches {
        public static final int ELEVATOR_TOP_LIMIT = 0;
        public static final int ELEVATOR_BOTTOM_LIMIT = 1;
        public static final int DRAWER_TOP_LIMIT = 4;
        public static final int DRAWER_BOTTOM_LIMIT = 5;
        public static final int ARM_TOP_LIMIT = 2;
        public static final int ARM_BOTTOM_LIMIT = 3;
    }

    public static class OtherConstants {
        public static final int SOLENOID_CHANNEL = 15;
        public static final int NUMBER_OF_POSITIONS_ELEVATOR = 5;
        public static final int NUMBER_OF_POSITIONS_DRAWER = 3;
    }

    public static class Store {
        // this is a special one as it controls the application state management
        public static double ELEVATOR_ENCODER_START_POSITION = -16;
        public static double ELEVATOR_ENCODER_END_POSITION = -720;
        
        public static double ARM_ENCODER_START_POSITION = 0;
        public static double DRAWER_ENCODER_START_POSITION = 0;
        public static double DRAWER_ENCODER_END_POSITION = 0;
        public static double ARM_ENCODER_END_POSITION = 0;
    }
}

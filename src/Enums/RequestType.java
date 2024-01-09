package Enums;

public enum RequestType {
    LAUNCH_MISSILE("Launch Missile"),
    MOVEMENT_INSTRUCTION("Movement Instruction"),
    TACTICAL_INSTRUCTION("Tactical Instruction"),
    OTHER("Other");

    private final String displayName;

    RequestType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


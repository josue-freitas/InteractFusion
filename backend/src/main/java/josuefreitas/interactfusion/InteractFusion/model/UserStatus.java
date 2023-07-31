package josuefreitas.interactfusion.InteractFusion.model;

import java.util.Arrays;
import java.util.Optional;

public enum UserStatus {
    EMAIL_NOT_CONFIRMED("Email not confirmed"),
    ACTIVE("Active"),
    SUSPENDED("Suspended"),
    DELETED("Deleted");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return status;
    }

    public static Optional<UserStatus> get(String status) {
        return Arrays.stream(UserStatus.values())
                .filter(env -> env.status.equals(status))
                .findFirst();
    }
}

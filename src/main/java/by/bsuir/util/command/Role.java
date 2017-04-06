package by.bsuir.util.command;

import by.bsuir.command.constant.Attribute;

public enum Role {
    USER(Attribute.USER_ROLE),
    ADMIN(Attribute.ADMIN_ROLE),
    GUEST(Attribute.GUEST_ROLE),
    UNREGISTERED(Attribute.UNREGISTERED_ROLE),
    ALL(Attribute.ALL),
    NULL(null);

    private String role;

    private Role(String role){
        this.role = role;
    }


    @Override
    public String toString() {
        return role;
    }
}

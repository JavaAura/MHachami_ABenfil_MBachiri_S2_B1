package enums;

public enum Role {
	PROJECTMANAGER,
	DEVELOPER,
	DESIGNER;

	@Override
    public String toString() {
        return name(); 
    }
}

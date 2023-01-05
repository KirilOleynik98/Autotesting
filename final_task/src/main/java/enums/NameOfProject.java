package enums;

public enum NameOfProject {

    NEXAGE("Nexage"),
    ALOHA("Aloha");

    private final String projectName;

    NameOfProject(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }
}

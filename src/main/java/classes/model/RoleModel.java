package classes.model;

public class RoleModel {

    private Long id;
    private RoleNameModel name;

    public RoleModel() {
    }

    public RoleModel(RoleNameModel name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleNameModel getName() {
        return name;
    }

    public void setName(RoleNameModel name) {
        this.name = name;
    }
}

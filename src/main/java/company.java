

public class company {
    private int id;
    private String name;
    private String code;
    
    public company(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

// Suggested code may be subject to a license. Learn more: ~LicenseLog:2055676898.
    public int getId() {
        return id;
    }
// Suggested code may be subject to a license. Learn more: ~LicenseLog:2848089347.
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}

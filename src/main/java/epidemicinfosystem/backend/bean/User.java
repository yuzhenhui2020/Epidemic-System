package epidemicinfosystem.backend.bean;

public class User {
    private int usr_id;
    private String usr_name;
    private String password;
    private int type;
    private String secure_question;
    private String secure_answer;
    private int permission;

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsr_name() {
        return usr_name;
    }

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSecure_question() {
        return secure_question;
    }

    public void setSecure_question(String secure_question) {
        this.secure_question = secure_question;
    }

    public String getSecure_answer() {
        return secure_answer;
    }

    public void setSecure_answer(String secure_answer) {
        this.secure_answer = secure_answer;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}

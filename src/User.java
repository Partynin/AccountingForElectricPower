public class User {
    private int idUser;
    private String login;
    private String password;
    private String surname;
    private String name;
    private String middleName;
    private String position;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, String surname, String name, String middleName, String position) {
        this.login = login;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
        this.position = position;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

package piette.dylan.examenandroidpiette;

/**
 * Created by Dylan on 15/12/2017.
 */

//Classe qui définit un user
public class UserData {
    private int idUser;
    private String name;
    private String password;

    public UserData(int idUser, String name, String password) {
        this.idUser = idUser;
        this.name = name;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

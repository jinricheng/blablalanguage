package Models;

import java.util.Date;

public class User {

    private int type;
    private int id;
    private String name;
    private String login;
    private String pass;
    private String facebookProfile;
    private String sex;
    private String birthday;
    private String photo;
    private Date dateInclude;
    

    public User(){}

    /**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public User(String name, String login, String password, String facebookProfile, String sex, String birthday){
  
        this.setUserType(1);
        this.setName(name);
        this.setLogin(login);
        this.setPass(password);
        this.setFacebookProfile(facebookProfile);
        this.setSex(sex);
        this.setBirthday(birthday);
    }

    public Date getDateInclude() {
        return dateInclude;
    }

    public void setDateInclude(Date dateInclude) {
        this.dateInclude = dateInclude;
    }
    
    public int getUserType() {
        return type;
    }

    public void setUserType(int userType) {
        this.type = userType;
    }

    public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * Find user at database
     * @param login user login
     * @param pass user pass
     * @return The user founded or null
     */
    public User findUser(String login, String pass){
        //Todo: Implement logic to search in the database
        return  null;
    }

    public String FormatString(){
        return "{"
                +"login:".concat(this.login.trim()).concat(";")
                +"pass:".concat(this.pass.trim()).concat(";")
                +"name:".concat(this.name.trim()).concat(";")
                +"email:".concat(this.login.trim()).concat(";")
                +"facebookProfile:".concat(this.facebookProfile.trim()).concat(";")
                +"photo:".concat(this.login.trim()).concat(";")
                +"sex:".concat(this.sex.trim()).concat(";")
                +"birthday:".concat(this.birthday.trim()).concat("}");
    }

 

    public User parseJson(String json){

        User u = new User();

        String[] jsStrings =  json
                                .replace("{", "").replace("}", "")
                                .replace("\"", "")
                                .replace("\\", "")
                                .split(";");


        for (String s: jsStrings){
            String[] tmp = s.split(":");

            if(tmp.length>1) {

                if (tmp[1].length() > 0 && tmp[0].contains("name")) u.setName(tmp[1]);
                if (tmp[1].length() > 0 && tmp[0].contains("login")) u.setLogin(tmp[1].replace("\\",""));
                if (tmp[1].length() > 0 && tmp[0].contains("pass")) u.setPass(tmp[1].replace("\\",""));
                if (tmp[1].length() > 0 && tmp[0].contains("facebookProfile")) u.setFacebookProfile(tmp[1]);
                if (tmp[1].length() > 0 && tmp[0].contains("sex")) u.setSex(tmp[1]);
                if (tmp[1].length() > 0 && tmp[0].contains("birthday")) u.setBirthday(tmp[1]);
                if (tmp[1].length() > 0 && tmp[0].contains("photo")) u.setPhoto(tmp[1]);
            }
          }

        return u;
    }

  
}
package Models;

/**
 * Created by vitor on 21/11/15.
 * Establishment
 */
public class Establishment{

	public Integer id;
    public String name;
    private User owner;
    public String ownerName;
    public String address;
    private Integer longitude;
    private Integer latitude;
    public Integer placesAvailable;
    public String  telephone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getPlacesAvailable() {
        return placesAvailable;
    }

    public void setPlacesAvailable(Integer placesAvailable) {
        this.placesAvailable = placesAvailable;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

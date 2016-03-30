package entity;

public class User {

	public String id;
	public String firstName;
	public String nickName;
	public String lastName;

	@Override
	public String toString() {
		return nickName + " <" + id + "> " + lastName + " " + firstName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
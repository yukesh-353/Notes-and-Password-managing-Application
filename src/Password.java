public class Password {
    private String website;
    private String username;
    private String password;

    public Password(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
    }

    public String getWebsite() { return website; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "Website: " + website + "\nUsername: " + username + "\nPassword: " + password;
    }
}

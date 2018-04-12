package UserService571;




public class UserServiceImpl {
    private UserDAO userDAO;
    private SecurityService securityService;

    public UserServiceImpl(UserDAO userDAO, SecurityService securityService) {
        this.userDAO = userDAO;
        this.securityService = securityService;
    }

    public void assignPassword(User user) throws Exception {
        String passwordMd5 = securityService.md5(user.getPassword());
        user.setPassword(passwordMd5);
        userDAO.updateUser(user);
    }
// constructor and/or setters omitted for the sake of brevity
}

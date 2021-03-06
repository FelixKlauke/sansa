package de.felix_klauke.sansa.server.user;


// TODO: SO MUCH IMPROVEMENTS NEEDED!
public interface IUserManager {

  /**
   * Authenticate user or return null.
   *
   * @param userName The user name.
   * @param password The password.
   * @return The user or null.
   */
  IUser authenticateUser(String userName, String password);

  /**
   * Check if a user with the given name exists.
   *
   * @param userName The user name.
   * @return If the user exists.
   */
  boolean userExists(String userName);

  /**
   * Add a user to authenticate with.
   *
   * @param user The user.
   */
  void registerUser(IUser user);

  /**
   * Authenticate a user that doesn't need authentication.
   *
   * @param userName The user name.
   * @return The user or null.
   */
  IUser authenticateUser(String userName);

  /**
   * Check if a user with the given name exists and needs authentication.
   *
   * @param userName The user name.
   * @return If the user needs authentication.
   */
  boolean userNeedsAuthentication(String userName);
}

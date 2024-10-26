package org.airtribe.assignment.taskmaster.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.airtribe.assignment.taskmaster.auth.entity.User;
import org.airtribe.assignment.taskmaster.auth.model.LoginDto;
import org.airtribe.assignment.taskmaster.auth.model.UserModel;
import org.airtribe.assignment.taskmaster.auth.exception.TokenExpiredException;
import org.airtribe.assignment.taskmaster.auth.model.UserProfile;

import java.net.http.HttpRequest;


public interface UserService {
  User registerUser(UserModel userModel, HttpServletRequest request) throws TokenExpiredException;

  User autheticateUser(LoginDto loginDto) throws TokenExpiredException;

  void createVerificationToken(User user, String token) throws TokenExpiredException;

  boolean validateTokenAndEnableUser(String token) throws TokenExpiredException;

  User getUserById(Long userId) throws TokenExpiredException ;

  UserProfile getUserProfile(String email);

   String updateUserProfile(UserModel updatedUser);

  User getCurrentUser() throws Exception;

  User getUserByEmail(String assignedTo);
}

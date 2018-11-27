package com.javaproject.utilcollect.bean;

public class User {
private Gender _gender;
private Name _name;
private boolean _isVerified;
private byte[] _userImage;

public Name getName() {
  return _name;
}

public boolean isVerified() {
  return _isVerified;
}

public Gender getGender() {
  return _gender;
}

public byte[] getUserImage() {
  return _userImage;
}

public void setName(final Name n) {
  _name = n;
}

public void setVerified(final boolean b) {
  _isVerified = b;
}

public void setGender(final Gender g) {
  _gender = g;
}

public void setUserImage(final byte[] b) {
  _userImage = b;
}}
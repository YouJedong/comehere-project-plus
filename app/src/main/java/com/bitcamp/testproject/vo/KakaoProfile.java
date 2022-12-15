package com.bitcamp.testproject.vo;

import lombok.Data;

@Data
public class KakaoProfile {

  private long id;
  private String connected_at;
  private Properties properties;
  private KakaoAccount kakao_account;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getConnected_at() {
    return connected_at;
  }

  public void setConnected_at(String connected_at) {
    this.connected_at = connected_at;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public KakaoAccount getKakao_account() {
    return kakao_account;
  }

  public void setKakao_account(KakaoAccount kakao_account) {
    this.kakao_account = kakao_account;
  }

  @Data
  public class Properties {

    private String nickname;
    private String profile_image;
    private String thumbnail_image;

    @Override
    public String toString() {
      return "Properties [nickname=" + nickname + ", profile_image=" + profile_image
          + ", thumbnail_image=" + thumbnail_image + "]";
    }

    public String getNickname() {
      return nickname;
    }

    public void setNickname(String nickname) {
      this.nickname = nickname;
    }

    public String getProfile_image() {
      return profile_image;
    }

    public void setProfile_image(String profile_image) {
      this.profile_image = profile_image;
    }

    public String getThumbnail_image() {
      return thumbnail_image;
    }

    public void setThumbnail_image(String thumbnail_image) {
      this.thumbnail_image = thumbnail_image;
    }

  }

  @Data
  public class KakaoAccount {

    private Boolean profile_needs_agreement;
    private Boolean profile_nickname_needs_agreement;
    private Profile profile;
    private Boolean has_email;
    private Boolean email_needs_agreement;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private String email;
    private Boolean has_age_range;
    private Boolean age_range_needs_agreement;
    private String birthday;
    private String birthday_type;
    private Boolean has_birthday;
    private Boolean birthday_needs_agreement;
    private Boolean has_gender;
    private Boolean gender_needs_agreement;
    private Boolean profile_image_needs_agreement;
    private String gender;

    public Boolean getProfile_needs_agreement() {
      return profile_needs_agreement;
    }

    public void setProfile_needs_agreement(Boolean profile_needs_agreement) {
      this.profile_needs_agreement = profile_needs_agreement;
    }

    public Boolean getProfile_nickname_needs_agreement() {
      return profile_nickname_needs_agreement;
    }

    public void setProfile_nickname_needs_agreement(Boolean profile_nickname_needs_agreement) {
      this.profile_nickname_needs_agreement = profile_nickname_needs_agreement;
    }

    public Profile getProfile() {
      return profile;
    }

    public void setProfile(Profile profile) {
      this.profile = profile;
    }

    public Boolean getHas_email() {
      return has_email;
    }

    public void setHas_email(Boolean has_email) {
      this.has_email = has_email;
    }

    public Boolean getEmail_needs_agreement() {
      return email_needs_agreement;
    }

    public void setEmail_needs_agreement(Boolean email_needs_agreement) {
      this.email_needs_agreement = email_needs_agreement;
    }

    public Boolean getIs_email_valid() {
      return is_email_valid;
    }

    public void setIs_email_valid(Boolean is_email_valid) {
      this.is_email_valid = is_email_valid;
    }

    public Boolean getIs_email_verified() {
      return is_email_verified;
    }

    public void setIs_email_verified(Boolean is_email_verified) {
      this.is_email_verified = is_email_verified;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public Boolean getHas_age_range() {
      return has_age_range;
    }

    public void setHas_age_range(Boolean has_age_range) {
      this.has_age_range = has_age_range;
    }

    public Boolean getAge_range_needs_agreement() {
      return age_range_needs_agreement;
    }

    public void setAge_range_needs_agreement(Boolean age_range_needs_agreement) {
      this.age_range_needs_agreement = age_range_needs_agreement;
    }

    public String getBirthday() {
      return birthday;
    }

    public void setBirthday(String birthday) {
      this.birthday = birthday;
    }

    public String getBirthday_type() {
      return birthday_type;
    }

    public void setBirthday_type(String birthday_type) {
      this.birthday_type = birthday_type;
    }

    public Boolean getHas_birthday() {
      return has_birthday;
    }

    public void setHas_birthday(Boolean has_birthday) {
      this.has_birthday = has_birthday;
    }

    public Boolean getBirthday_needs_agreement() {
      return birthday_needs_agreement;
    }

    public void setBirthday_needs_agreement(Boolean birthday_needs_agreement) {
      this.birthday_needs_agreement = birthday_needs_agreement;
    }

    public Boolean getHas_gender() {
      return has_gender;
    }

    public void setHas_gender(Boolean has_gender) {
      this.has_gender = has_gender;
    }

    public Boolean getGender_needs_agreement() {
      return gender_needs_agreement;
    }

    public void setGender_needs_agreement(Boolean gender_needs_agreement) {
      this.gender_needs_agreement = gender_needs_agreement;
    }

    public Boolean getProfile_image_needs_agreement() {
      return profile_image_needs_agreement;
    }

    public void setProfile_image_needs_agreement(Boolean profile_image_needs_agreement) {
      this.profile_image_needs_agreement = profile_image_needs_agreement;
    }

    public String getGender() {
      return gender;
    }

    public void setGender(String gender) {
      this.gender = gender;
    }

    @Data
    public class Profile {

      private String nickname;
      private String thumbnail_image_url;
      private String profile_image_url;
      private Boolean is_default_image;

      @Override
      public String toString() {
        return "Profile [nickname=" + nickname + ", thumbnail_image_url=" + thumbnail_image_url
            + ", profile_image_url=" + profile_image_url + ", is_default_image=" + is_default_image
            + "]";
      }

      public String getNickname() {
        return nickname;
      }

      public void setNickname(String nickname) {
        this.nickname = nickname;
      }

      public String getThumbnail_image_url() {
        return thumbnail_image_url;
      }

      public void setThumbnail_image_url(String thumbnail_image_url) {
        this.thumbnail_image_url = thumbnail_image_url;
      }

      public String getProfile_image_url() {
        return profile_image_url;
      }

      public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
      }

      public Boolean getIs_default_image() {
        return is_default_image;
      }

      public void setIs_default_image(Boolean is_default_image) {
        this.is_default_image = is_default_image;
      }


    }

    @Override
    public String toString() {
      return "KakaoAccount [profile_needs_agreement=" + profile_needs_agreement
          + ", profile_nickname_needs_agreement=" + profile_nickname_needs_agreement + ", profile="
          + profile + ", has_email=" + has_email + ", email_needs_agreement="
          + email_needs_agreement + ", is_email_valid=" + is_email_valid + ", is_email_verified="
          + is_email_verified + ", email=" + email + ", has_age_range=" + has_age_range
          + ", age_range_needs_agreement=" + age_range_needs_agreement + ", birthday=" + birthday
          + ", birthday_type=" + birthday_type + ", has_birthday=" + has_birthday
          + ", birthday_needs_agreement=" + birthday_needs_agreement + ", has_gender=" + has_gender
          + ", gender_needs_agreement=" + gender_needs_agreement
          + ", profile_image_needs_agreement=" + profile_image_needs_agreement + ", gender="
          + gender + "]";
    }

  }

  @Override
  public String toString() {
    return "KakaoProfile [id=" + id + ", connected_at=" + connected_at + ", properties="
        + properties + ", kakao_account=" + kakao_account + "]";
  }



}


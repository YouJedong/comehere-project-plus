package com.bitcamp.testproject.vo;

import lombok.Data;

@Data
public class KakaoProfile {

  public long id;
  public String connected_at;
  public Properties properties;
  public KakaoAccount kakao_account;


  @Data
  public class Properties {

    public String nickname;
    public String profile_image;
    public String thumbnail_image;

    @Override
    public String toString() {
      return "Properties [nickname=" + nickname + ", profile_image=" + profile_image
          + ", thumbnail_image=" + thumbnail_image + "]";
    }

  }

  @Data
  public class KakaoAccount {

    public Boolean profile_needs_agreement;
    public Boolean profile_nickname_needs_agreement;
    public Profile profile;
    public Boolean has_email;
    public Boolean email_needs_agreement;
    public Boolean is_email_valid;
    public Boolean is_email_verified;
    public String email;
    public Boolean has_age_range;
    public Boolean age_range_needs_agreement;
    public String birthday;
    public String birthday_type;
    public Boolean has_birthday;
    public Boolean birthday_needs_agreement;
    public Boolean has_gender;
    public Boolean gender_needs_agreement;
    public Boolean profile_image_needs_agreement;
    public String gender;

    @Data
    public class Profile {

      public String nickname;
      public String thumbnail_image_url;
      public String profile_image_url;
      public Boolean is_default_image;

      @Override
      public String toString() {
        return "Profile [nickname=" + nickname + ", thumbnail_image_url=" + thumbnail_image_url
            + ", profile_image_url=" + profile_image_url + ", is_default_image=" + is_default_image
            + "]";
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


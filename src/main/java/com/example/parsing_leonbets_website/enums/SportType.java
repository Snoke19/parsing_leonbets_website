package com.example.parsing_leonbets_website.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum SportType {

  FOOTBALL("Soccer"),
  TENNIS("Tennis"),
  ICE_HOCKEY("IceHockey"),
  BASKETBALL("Basketball");

  private final String family;
  private static final EnumSet<SportType> ALL_TYPES = EnumSet.allOf(SportType.class);

  SportType(String family) {
    this.family = family;
  }

  public static EnumSet<SportType> getAllTypes() {
    return ALL_TYPES;
  }

  @JsonCreator
  public static SportType getSportByFamily(String family) {
    for (SportType dep : SportType.values()) {
      if (dep.getFamily().equals(family)) {
        return dep;
      }
    }
    return null;
  }
}

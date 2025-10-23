package com.florian.aos.battlescrollservice.dto.unity;

import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.entity.charter.Weapon;
import lombok.Data;

import java.util.List;

@Data
public class WeaponDtoGet {
    private Long id;
    private String name;
    private Boolean isShootingWeapon;
    private Integer ranged;
    private String attacks;
    private Integer touch;
    private Integer wound;
    private Integer perforation;
    private String damage;

    private List<String> keywords;

    public WeaponDtoGet(Weapon weapon) {
        this.id = weapon.getId();
        this.name = weapon.getName();
        this.isShootingWeapon = weapon.isShootingWeapon();
        if (weapon.getRanged() > 0) {
            this.ranged = weapon.getRanged();
        }
        this.attacks = weapon.getAttacks();
        this.touch = weapon.getTouch();
        this.wound = weapon.getWound();
        this.perforation = weapon.getPerforation();
        this.damage = weapon.getDamage();
        if (weapon.getKeywords() != null && !weapon.getKeywords().isEmpty()){
            this.keywords = weapon.getKeywords().stream().map(Keyword::getName).toList();
        }
    }
}

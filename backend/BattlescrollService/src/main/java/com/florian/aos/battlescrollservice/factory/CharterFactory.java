package com.florian.aos.battlescrollservice.factory;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoPost;
import com.florian.aos.battlescrollservice.dto.unity.UnityDtoPost;
import com.florian.aos.battlescrollservice.dto.unity.WeaponDtoPost;
import com.florian.aos.battlescrollservice.entity.Version;
import com.florian.aos.battlescrollservice.entity.charter.Faction;
import com.florian.aos.battlescrollservice.entity.charter.Unity;
import com.florian.aos.battlescrollservice.entity.charter.Weapon;
import com.florian.aos.battlescrollservice.utils.enums.AllianceType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharterFactory {

    public Faction fromDto(FactionDtoPost dto, Version version){

        Faction faction = Faction
                .builder()
                .name(dto.getName())
                .version(version)
                .build();

        if (dto.getImagePath() != null && !dto.getImagePath().isBlank()){
            faction.setImagePath(dto.getImagePath());
        }

        try {
            faction.setAlliance(AllianceType.valueOf(dto.getAlliance().toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Illegal alliance type " + dto.getAlliance());
        }
        return faction;
    }

    public Unity fromDto(UnityDtoPost dto, Version version){
          Unity unity = Unity.builder()
                    .name(dto.getName())
                    .version(version)
                    .movement(dto.getMovement())
                    .save(dto.getSave())
                    .control(dto.getControl())
                    .health(dto.getHealth())
                    .points(dto.getPoints())
                    .build();

        if (dto.getImagePath() != null && !dto.getImagePath().isBlank()){
            unity.setImagePath(dto.getImagePath());
        }

        return unity;
    }

    public Weapon weaponFromDto(WeaponDtoPost dto){
            Weapon weapon = Weapon.builder()
                    .name(dto.getName())
                    .isShootingWeapon(dto.getIsShootingWeapon())
                    .attacks(dto.getAttacks())
                    .touch(dto.getTouch())
                    .wound(dto.getWound())
                    .perforation(dto.getPerforation())
                    .damage(dto.getDamage())
                    .build();
            if (weapon.isShootingWeapon()){
                if (dto.getRanged() == null || dto.getRanged() < 1){
                    throw new IllegalArgumentException("If " + dto.getName() + " is shooting weapon, it need ranged");
                }
                weapon.setRanged(dto.getRanged());
            }

        return weapon;
    }
}

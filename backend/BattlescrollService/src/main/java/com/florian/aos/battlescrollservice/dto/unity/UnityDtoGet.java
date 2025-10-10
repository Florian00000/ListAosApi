package com.florian.aos.battlescrollservice.dto.unity;

import com.florian.aos.battlescrollservice.dto.faction.FactionDtoGet;
import com.florian.aos.battlescrollservice.entity.Keyword;
import com.florian.aos.battlescrollservice.entity.battleAptitude.AptitudeContext;
import com.florian.aos.battlescrollservice.entity.charter.Unity;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class UnityDtoGet {

    private Long id;
    private String name;
    private String imagePath;
    private String version;

    private int movement;
    private int save;
    private int control;
    private int health;
    private int points;

    private List<String> keywords;

    private FactionDtoGet faction;
    private List<WeaponDtoGet> weapons;
    private List<BattleAptitudeDtoGetUnity> battleAptitudes;

    public UnityDtoGet(Unity unity) {
        this.id = unity.getId();
        this.name = unity.getName();
        if (unity.getImagePath() != null && !unity.getImagePath().isBlank()){
            this.imagePath = unity.getImagePath();
        }
        this.version = unity.getVersion().getName();
        if (unity.getKeywords() != null && !unity.getKeywords().isEmpty()){
            this.keywords = unity.getKeywords().stream().map(Keyword::getName).toList();
        }
        this.movement = unity.getMovement();
        this.save = unity.getSave();
        this.control = unity.getControl();
        this.health = unity.getHealth();
        this.points = unity.getPoints();
        this.faction = new FactionDtoGet(unity.getFaction());
        if (unity.getWeapons() != null && !unity.getWeapons().isEmpty()){
            this.weapons = unity.getWeapons().stream().map(WeaponDtoGet::new).toList();
        }
        if (unity.getAptitudeContextList() != null && !unity.getAptitudeContextList().isEmpty()){
            this.battleAptitudes = unity.getAptitudeContextList().stream()
                    .map(aptitudeContext -> {
                        //TODO faire des logs
                        if (aptitudeContext.getBattleAptitude() == null){
                            System.out.println("AptitudeContext n'est pas lié à battleAptitude" + aptitudeContext.getId());
                            if (aptitudeContext.getDomain() != null){
                                System.out.println("domain de l'aptitude context"+ aptitudeContext.getDomain().getName());
                            } else {
                                System.out.println("Aptitude context n'est pas lié à un domaine");
                            }
                        }
                        return aptitudeContext.getBattleAptitude();
                    })
                    .filter(Objects::nonNull)
                    .map(BattleAptitudeDtoGetUnity::new)
                    .toList();
        }

    }
}

package tomato.backend.data;

import assets.IdToAsset;
import util.RNG;

import java.io.Serializable;

public class Projectile implements Serializable {

    private int damage;
    private int summonerId;
    private boolean armorPiercing;

    public Projectile(int damage) {
        this.damage = damage;
    }

    public Projectile(int damage, boolean armorPiercing) {
        this.damage = damage;
        this.armorPiercing = armorPiercing;
    }

    public Projectile(short damage, int id, int type, int summonerId) {
        this.damage = damage;
        this.summonerId = summonerId;
        try {
            armorPiercing = IdToAsset.getIdProjectileArmorPierces(id, type);
        } catch (Exception e) {
        }
    }

    /**
     * Calculates the projectiles damage.
     *
     * @param rng          Seed used with randomizer to find the exact value of used weapon from min to max range of weapon dmg.
     * @param player       The player entity.
     * @param weaponId     Weapon ID used (retrieved from packet being sent).
     * @param projectileId Projectile ID used (retrieved from packet being sent).
     */
    public Projectile(RNG rng, Entity player, int weaponId, int projectileId) {
        if (player == null || rng == null) return;

        if (projectileId == -1) {
            projectileId = 0;
        }
        int min = IdToAsset.getIdProjectileMinDmg(weaponId, projectileId);
        int max = IdToAsset.getIdProjectileMaxDmg(weaponId, projectileId);
        boolean ap = IdToAsset.getIdProjectileArmorPierces(weaponId, projectileId);
        int slot = IdToAsset.getIdProjectileSlotType(weaponId);
        boolean mainWeapon = isMainWeapon(slot);
        int dmg;
        if (min != max) {
            long r = rng.next();
            dmg = (int) (min + r % (max - min));
        } else {
            dmg = min;
        }
        float f = 1f;
        if (mainWeapon) f = player.playerStatsMultiplier();
        damage = (int) (dmg * f);
        armorPiercing = ap;
    }

    private boolean isMainWeapon(int slot) {
        switch (slot) {
            case 1:
            case 2:
            case 3:
            case 8:
            case 17:
            case 24:
                return true;
        }
        return false;
    }

    /**
     * Used when an entity takes damage taking defence and other effects into account for final damage to entity.
     *
     * @param damage        the base damage the bullet can do.
     * @param armorPiercing if the bullet ignores defence.
     * @param defence       defence of the entity being hit.
     * @param conditions    condition effects the entity being shot can have.
     * @return final damage applied to the entity.
     */
    public static int damageWithDefense(int damage, boolean armorPiercing, int defence, int[] conditions) {
        if (damage == 0) return 0;

        if (armorPiercing || (conditions[0] & 0x4000000) != 0) {
            defence = 0;
        } else if ((conditions[0] & 0x2000000) != 0) {
            defence = (int) (defence * 1.5);
        }
        if ((conditions[1] & 0x20000) != 0) {
            defence = defence - 20;
        }
        int minDmg = damage * 2 / 20;
        int dmg = Math.max(minDmg, damage - defence);

        if ((conditions[0] & 0x1000000) != 0) {
            dmg = 0;
        }
        if ((conditions[1] & 0x8) != 0) {
            dmg = (int) (dmg * 0.9);
        }
        if ((conditions[1] & 0x40) != 0) {
            dmg = (int) (dmg * 1.25);
        }
        return dmg;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isArmorPiercing() {
        return armorPiercing;
    }

    public int getSummonerId() {
        return summonerId;
    }

    public void clear() {
        damage = 0;
        armorPiercing = false;
    }
}

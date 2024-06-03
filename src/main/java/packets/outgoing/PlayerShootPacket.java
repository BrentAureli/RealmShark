package packets.outgoing;

import packets.Packet;
import packets.reader.BufferReader;
import packets.data.WorldPosData;

import java.util.Arrays;

/**
 * Sent when the player shoots a projectile.
 */
public class PlayerShootPacket extends Packet {
    /**
     * The current client time.
     */
    public int time;
    /**
     * Counts the number of bullets sense entering dungeon.
     */
    public short bulletId;
    /**
     * The item id of the weapon used to fire the projectile.
     */
    public int weaponId;
    /**
     * ID of the projectile for weapons with multiple types of projectiles. (i.e. bows)
     * Warning: some projectileIDs can be -1 and should be treated as 0!
     */
    public byte projectileId;
    /**
     * The position of the starting point where the projectile was fired.
     */
    public WorldPosData startingPos;
    /**
     * The angle at which the projectile was fired.
     */
    public float angle;
    /**
     * If the projectile is related to a burst weapon projectile.
     */
    public boolean isBurst;
    /**
     * Unknown
     */
    public byte patternIdx;
    /**
     * Unknown
     */
    public byte attackType;
    /**
     * Position of the player shooting
     */
    public WorldPosData playerPosition;

    @Override
    public void deserialize(BufferReader buffer) throws Exception {
        time = buffer.readInt();
        bulletId = buffer.readShort();
        weaponId = buffer.readUnsignedShort();
        projectileId = buffer.readByte();
        startingPos = new WorldPosData().deserialize(buffer);
        angle = buffer.readFloat();
        isBurst = buffer.readBoolean();
        patternIdx = buffer.readByte();
        attackType = buffer.readByte();
        playerPosition = new WorldPosData().deserialize(buffer);
    }

    @Override
    public String toString() {
        return "PlayerShootPacket{" +
                "\n   time=" + time +
                "\n   bulletId=" + bulletId +
                "\n   weaponId=" + weaponId +
                "\n   projectileId=" + projectileId +
                "\n   startingPos=" + startingPos +
                "\n   angle=" + angle +
                "\n   isBurst=" + isBurst +
                "\n   patternIdx=" + patternIdx +
                "\n   attackType=" + attackType +
                "\n   playerPosition=" + playerPosition;
    }
}

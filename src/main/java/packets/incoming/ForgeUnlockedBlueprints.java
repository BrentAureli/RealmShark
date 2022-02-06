package packets.incoming;

import packets.Packet;
import packets.buffer.PBuffer;
import packets.buffer.data.CompressedInt;

/**
 * Received when the player enters the nexus
 */
public class ForgeUnlockedBlueprints extends Packet {
    /**
     * The itemIds of unlocked blueprints in an array
     */
    public int[] unlockedBlueprints;

    // TODO: currently bugged, fix this
    @Override
    public void deserialize(PBuffer buffer) {
//        unlockedBlueprints = new int[buffer.readByte()];
//        for (int i = 0; i < unlockedBlueprints.length; i++) {
//            unlockedBlueprints[i] = new CompressedInt().deserialize(buffer);
//        }
    }
}
package packets.outgoing;


import packets.Packet;
import packets.data.WorldPosData;
import packets.reader.BufferReader;

/**
 * Sent when playing the Summoner class and a spawned creep minion has to move position.
 */
public class CreepMovePacket extends Packet {
    /**
     * The object ID of the Summoner's creep to move.
     */
    public int objectId;
    /**
     * Time of the server
     */
    public int serverTime;
    /**
     * The position to move the creep to.
     */
    public WorldPosData position;
    /**
     * Whether the Summoner ability key is held down.
     */
    public boolean hold;

    @Override
    public void deserialize(BufferReader buffer) throws Exception {
        objectId = buffer.readInt();
        serverTime = buffer.readInt();
        position = new WorldPosData().deserialize(buffer);
        hold = buffer.readBoolean();
    }

    @Override
    public String toString() {
        return "CreepMovePacket{" +
                "\n   objectId=" + objectId +
                "\n   serverTime=" + serverTime +
                "\n   position=" + position +
                "\n   hold=" + hold;
    }
}
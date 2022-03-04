/*_##########################################################################
  _##
  _##  Copyright (C) 2012-2016  Pcap4J.org
  _##
  _##########################################################################
*/

package packets.packetcapture.networktap.pcap4j;

/**
 * @author Kaito Yamada
 * @since pcap4j 0.9.14
 */
public final class StaticUnknownPacketFactory implements PacketFactory<Packet, NamedNumber<?, ?>> {

  private static final StaticUnknownPacketFactory INSTANCE = new StaticUnknownPacketFactory();

  private StaticUnknownPacketFactory() {}

  /** @return the singleton instance of StaticUnknownPacketFactory. */
  public static StaticUnknownPacketFactory getInstance() {
    return INSTANCE;
  }

  @Override
  public Packet newInstance(byte[] rawData, int offset, int length, NamedNumber<?, ?>... numbers) {
    return UnknownPacket.newPacket(rawData, offset, length);
  }
}

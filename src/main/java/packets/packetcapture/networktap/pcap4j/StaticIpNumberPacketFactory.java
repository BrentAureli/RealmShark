/*_##########################################################################
  _##
  _##  Copyright (C) 2012-2019 Pcap4J.org
  _##
  _##########################################################################
*/

package packets.packetcapture.networktap.pcap4j;

/**
 * @author Kaito Yamada
 * @since pcap4j 0.9.14
 */
public final class StaticIpNumberPacketFactory implements PacketFactory<Packet, IpNumber> {

  private static final StaticIpNumberPacketFactory INSTANCE = new StaticIpNumberPacketFactory();

  private StaticIpNumberPacketFactory() {}

  /** @return the singleton instance of StaticIpNumberPacketFactory. */
  public static StaticIpNumberPacketFactory getInstance() {
    return INSTANCE;
  }

  /**
   * This method is a variant of {@link #newInstance(byte[], int, int, IpNumber...)} and exists only
   * for performance reason.
   *
   * @param rawData see {@link PacketFactory#newInstance}.
   * @param offset see {@link PacketFactory#newInstance}.
   * @param length see {@link PacketFactory#newInstance}.
   * @return see {@link PacketFactory#newInstance}.
   */
  public Packet newInstance(byte[] rawData, int offset, int length) {
    return UnknownPacket.newPacket(rawData, offset, length);
  }

  /**
   * This method is a variant of {@link #newInstance(byte[], int, int, IpNumber...)} and exists only
   * for performance reason.
   *
   * @param rawData see {@link PacketFactory#newInstance}.
   * @param offset see {@link PacketFactory#newInstance}.
   * @param length see {@link PacketFactory#newInstance}.
   * @param number see {@link PacketFactory#newInstance}.
   * @return see {@link PacketFactory#newInstance}.
   */
  public Packet newInstance(byte[] rawData, int offset, int length, IpNumber number) {
    try {
      switch (number.value() & 0xff) {
//        case 0:
//          return IpV6ExtHopByHopOptionsPacket.newPacket(rawData, offset, length);
//        case 1:
//          return IcmpV4CommonPacket.newPacket(rawData, offset, length);
        case 6:
          return TcpPacket.newPacket(rawData, offset, length);
//        case 17:
//          return UdpPacket.newPacket(rawData, offset, length);
//        case 43:
//          return IpV6ExtRoutingPacket.newPacket(rawData, offset, length);
//        case 44:
//          return IpV6ExtFragmentPacket.newPacket(rawData, offset, length);
//        case 58:
//          return IcmpV6CommonPacket.newPacket(rawData, offset, length);
        case 59:
          return UnknownPacket.newPacket(rawData, offset, length);
//        case 60:
//          return IpV6ExtDestinationOptionsPacket.newPacket(rawData, offset, length);
//        case 132:
//          return SctpPacket.newPacket(rawData, offset, length);
          //          case 255:
          //            255 conflicts with UnknownIpV6Extension
          //            break;
      }
//      if (number == UnknownIpV6Extension.getInstance()) {
//        return IpV6ExtUnknownPacket.newPacket(rawData, offset, length);
//      }
      return UnknownPacket.newPacket(rawData, offset, length);
    } catch (IllegalRawDataException e) {
//      return IllegalPacket.newPacket(rawData, offset, length, e);
      return UnknownPacket.newPacket(rawData, offset, length);
    }
  }

  /**
   * This method is a variant of {@link #newInstance(byte[], int, int, IpNumber...)} and exists only
   * for performance reason.
   *
   * @param rawData see {@link PacketFactory#newInstance}.
   * @param offset see {@link PacketFactory#newInstance}.
   * @param length see {@link PacketFactory#newInstance}.
   * @param number1 see {@link PacketFactory#newInstance}.
   * @param number2 see {@link PacketFactory#newInstance}.
   * @return see {@link PacketFactory#newInstance}.
   */
  public Packet newInstance(
      byte[] rawData, int offset, int length, IpNumber number1, IpNumber number2) {
    try {
      switch (number1.value() & 0xff) {
//        case 0:
//          return IpV6ExtHopByHopOptionsPacket.newPacket(rawData, offset, length);
//        case 1:
//          return IcmpV4CommonPacket.newPacket(rawData, offset, length);
        case 6:
          return TcpPacket.newPacket(rawData, offset, length);
        case 17:
//          return UdpPacket.newPacket(rawData, offset, length);
//        case 43:
//          return IpV6ExtRoutingPacket.newPacket(rawData, offset, length);
//        case 44:
//          return IpV6ExtFragmentPacket.newPacket(rawData, offset, length);
//        case 58:
//          return IcmpV6CommonPacket.newPacket(rawData, offset, length);
        case 59:
          return UnknownPacket.newPacket(rawData, offset, length);
//        case 60:
//          return IpV6ExtDestinationOptionsPacket.newPacket(rawData, offset, length);
//        case 132:
//          return SctpPacket.newPacket(rawData, offset, length);
          //          case 255:
          //            255 conflicts with UnknownIpV6Extension
          //            break;
      }
//      if (number1 == UnknownIpV6Extension.getInstance()) {
//        return IpV6ExtUnknownPacket.newPacket(rawData, offset, length);
//      }

      switch (number2.value() & 0xff) {
//        case 0:
//          return IpV6ExtHopByHopOptionsPacket.newPacket(rawData, offset, length);
//        case 1:
//          return IcmpV4CommonPacket.newPacket(rawData, offset, length);
        case 6:
          return TcpPacket.newPacket(rawData, offset, length);
//        case 17:
//          return UdpPacket.newPacket(rawData, offset, length);
//        case 43:
//          return IpV6ExtRoutingPacket.newPacket(rawData, offset, length);
//        case 44:
//          return IpV6ExtFragmentPacket.newPacket(rawData, offset, length);
//        case 58:
//          return IcmpV6CommonPacket.newPacket(rawData, offset, length);
        case 59:
          return UnknownPacket.newPacket(rawData, offset, length);
//        case 60:
//          return IpV6ExtDestinationOptionsPacket.newPacket(rawData, offset, length);
//        case 132:
//          return SctpPacket.newPacket(rawData, offset, length);
          //          case 255:
          //            255 conflicts with UnknownIpV6Extension
          //            break;
      }
//      if (number2 == UnknownIpV6Extension.getInstance()) {
//        return IpV6ExtUnknownPacket.newPacket(rawData, offset, length);
//      }
      return UnknownPacket.newPacket(rawData, offset, length);
    } catch (IllegalRawDataException e) {
//      return IllegalPacket.newPacket(rawData, offset, length, e);
      return UnknownPacket.newPacket(rawData, offset, length);
    }
  }

  @Override
  public Packet newInstance(byte[] rawData, int offset, int length, IpNumber... numbers) {
    try {
      for (IpNumber num : numbers) {
        switch (num.value() & 0xff) {
//          case 0:
//            return IpV6ExtHopByHopOptionsPacket.newPacket(rawData, offset, length);
//          case 1:
//            return IcmpV4CommonPacket.newPacket(rawData, offset, length);
          case 6:
            return TcpPacket.newPacket(rawData, offset, length);
//          case 17:
//            return UdpPacket.newPacket(rawData, offset, length);
//          case 43:
//            return IpV6ExtRoutingPacket.newPacket(rawData, offset, length);
//          case 44:
//            return IpV6ExtFragmentPacket.newPacket(rawData, offset, length);
//          case 58:
//            return IcmpV6CommonPacket.newPacket(rawData, offset, length);
          case 59:
            return UnknownPacket.newPacket(rawData, offset, length);
//          case 60:
//            return IpV6ExtDestinationOptionsPacket.newPacket(rawData, offset, length);
//          case 132:
//            return SctpPacket.newPacket(rawData, offset, length);
            //          case 255:
            //            255 conflicts with UnknownIpV6Extension
            //            break;
        }
//        if (num == UnknownIpV6Extension.getInstance()) {
//          return IpV6ExtUnknownPacket.newPacket(rawData, offset, length);
//        }
      }
      return UnknownPacket.newPacket(rawData, offset, length);
    } catch (IllegalRawDataException e) {
      return UnknownPacket.newPacket(rawData, offset, length);
//      return IllegalPacket.newPacket(rawData, offset, length, e);
    }
  }
}

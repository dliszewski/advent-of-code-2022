class Day13 {

    fun part1(input: String): Int {
        return input.split("\n\n").map { it.lines() }
            .map { (packet, other) -> packet.mapToPacketData() to other.mapToPacketData() }
            .mapIndexed { index, pair -> if (pair.first.compareTo(pair.second) < 1) index + 1 else 0 }
            .sum()
    }

    fun part2(input: String): Int {
        return 2
    }

    private fun String.mapToPacketData(): PacketData {
        val bracketsAndNumbers = split(Regex("((?<=[\\[\\],])|(?=[\\[\\],]))"))
            .filter { it.isNotBlank() }
            .filter { it != "," }
            .iterator()
        return mapIteratorToPacketData(bracketsAndNumbers)
    }

    private fun mapIteratorToPacketData(input: Iterator<String>): PacketData {
        val packets = mutableListOf<PacketData>()
        while (input.hasNext()) {
            when (val symbol = input.next()) {
                "]" -> return ListPacketData(packets)
                "[" -> packets.add(mapIteratorToPacketData(input))
                else -> packets.add(IntPacketData(symbol.toInt()))
            }
        }
        return ListPacketData(packets)
    }

    sealed class PacketData : Comparable<PacketData>
    data class IntPacketData(val data: Int) : PacketData() {
        override fun compareTo(other: PacketData): Int {
            return when (other) {
                is IntPacketData -> data.compareTo(other.data)
                is ListPacketData -> ListPacketData(listOf(this)).compareTo(other)
            }
        }
    }

    data class ListPacketData(val data: List<PacketData>) : PacketData() {
        override fun compareTo(other: PacketData): Int {
            return when (other) {
                is IntPacketData -> compareTo(ListPacketData(listOf(other)))
                is ListPacketData -> data.zip(other.data)
                    .map { (packet1, packet2) -> packet1.compareTo(packet2) }
                    .firstOrNull { it != 0 } ?: data.size.compareTo(other.data.size)
            }
        }
    }

    data class Packet(val data: String) : Comparable<Packet> {

        override fun compareTo(other: Packet): Int {
            TODO("Not yet implemented")
        }
    }

}














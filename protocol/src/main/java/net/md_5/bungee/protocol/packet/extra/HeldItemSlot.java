package net.md_5.bungee.protocol.packet.extra;

import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.DefinedPacket;
import net.md_5.bungee.protocol.ProtocolConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HeldItemSlot extends DefinedPacket
{

    int slot;

    @Override
    public void write(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        if ( direction == ProtocolConstants.Direction.TO_CLIENT )
        {
            buf.writeByte( slot );
        } else
        {
            buf.writeShort( slot );
        }
    }

    @Override
    public void read(ByteBuf buf, ProtocolConstants.Direction direction, int protocolVersion)
    {
        if ( direction == ProtocolConstants.Direction.TO_SERVER )
        {
            slot = buf.readShort();
        } else
        {
            slot = buf.readByte();
        }
    }

    public HeldItemSlot increase()
    {
        slot++;
        return this;
    }

    public HeldItemSlot reset()
    {
        slot = 0;
        return this;
    }

    @Override
    public void handle(AbstractPacketHandler handler) throws Exception
    {
        handler.handle( this );
    }
}

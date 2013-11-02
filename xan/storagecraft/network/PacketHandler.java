package xan.storagecraft.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import xan.storagecraft.lib.Reference;
import xan.storagecraft.tileentity.TileEntitySC;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		
		byte packetID = reader.readByte();
		
		switch (packetID){
		case 0:
			int x, y, z;
			x = reader.readInt();
			y = reader.readInt();
			z = reader.readInt();
			int playersUsing = reader.readInt();
			float angle = reader.readFloat();
			
			System.out.println("Packet Recieved");
			
			if (player instanceof EntityPlayer){
				EntityPlayer entityPlayer = (EntityPlayer)player;
				TileEntity te = entityPlayer.worldObj.getBlockTileEntity(x, y, z);
				if(te instanceof TileEntitySC){
					((TileEntitySC)te).numUsingPlayers = playersUsing;
					((TileEntitySC)te).lidAngle = angle;
				}
			}
			break;
		}
		
		
	}

	public static void sendLidAngle(TileEntitySC tileEntitySC) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		
		try {
			dataStream.writeByte(0);
			dataStream.writeInt(tileEntitySC.xCoord);
			dataStream.writeInt(tileEntitySC.yCoord);
			dataStream.writeInt(tileEntitySC.zCoord);
			dataStream.writeInt(tileEntitySC.numUsingPlayers);
			dataStream.writeFloat(tileEntitySC.lidAngle);
			
			PacketDispatcher.sendPacketToAllAround(tileEntitySC.xCoord, tileEntitySC.yCoord, tileEntitySC.zCoord, 66, tileEntitySC.getWorldObj().provider.dimensionId, PacketDispatcher.getPacket(Reference.CHANNEL, byteStream.toByteArray()));
		}catch (IOException e){
			System.err.append("Error sending Lid Angle Packet");
		}
	}

}

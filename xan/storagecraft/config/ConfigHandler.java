package xan.storagecraft.config;

import java.io.File;

import xan.storagecraft.lib.BlockIDs;
import net.minecraftforge.common.Configuration;

public class ConfigHandler {

	public static void init(File file){
		Configuration config = new Configuration(file);
		
		config.load();
		
		BlockIDs.CHEST_MULTI_ID = config.get("Block IDs", "Chest Meta Block", BlockIDs.DEFAULT_CHEST_MULTI_ID).getInt();
		
		config.save();
	}
	
}

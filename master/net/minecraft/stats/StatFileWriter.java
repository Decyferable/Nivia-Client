package net.minecraft.stats;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;
import nivia.utils.utils.AESUtils;
import nivia.utils.utils.AESUtils.HWID;

public class StatFileWriter
{
    protected final Map field_150875_a = Maps.newConcurrentMap();

    /**
     * Returns true if the achievement has been unlocked.
     */
    public boolean hasAchievementUnlocked(Achievement p_77443_1_)
    {
        return this.writeStat(p_77443_1_) > 0;
    }

    /**
     * Returns true if the parent has been unlocked, or there is no parent
     */
    public boolean canUnlockAchievement(Achievement p_77442_1_)
    {
        return p_77442_1_.parentAchievement == null || this.hasAchievementUnlocked(p_77442_1_.parentAchievement);
    }

    public int func_150874_c(Achievement p_150874_1_)
    {
        if (this.hasAchievementUnlocked(p_150874_1_))
        {
            return 0;
        }
        else
        {
            int var2 = 0;

            for (Achievement var3 = p_150874_1_.parentAchievement; var3 != null && !this.hasAchievementUnlocked(var3); ++var2)
            {
                var3 = var3.parentAchievement;
            }

            return var2;
        }
    }

    public void func_150871_b(EntityPlayer p_150871_1_, StatBase p_150871_2_, int p_150871_3_)
    {
        if (!p_150871_2_.isAchievement() || this.canUnlockAchievement((Achievement)p_150871_2_))
        {
            this.func_150873_a(p_150871_1_, p_150871_2_, this.writeStat(p_150871_2_) + p_150871_3_);
        }
    }

    public void func_150873_a(EntityPlayer p_150873_1_, StatBase p_150873_2_, int p_150873_3_)
    {
        TupleIntJsonSerializable var4 = (TupleIntJsonSerializable)this.field_150875_a.get(p_150873_2_);

        if (var4 == null)
        {
            var4 = new TupleIntJsonSerializable();
            this.field_150875_a.put(p_150873_2_, var4);
        }

        var4.setIntegerValue(p_150873_3_);
    }

    public static boolean AuthSHA512() throws Exception {
	  	  try {
	  		  String declink = AESUtils.AESDecrypt("6nOhr6ND9/YSXw54x2wb", 20);
	  	      URL url = new URL(declink);
	  	      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	  	      String line;
	  	      if ((line = in.readLine().trim()).startsWith(HWID.getSHA512())) {
	  	    	  in.close();
	  	    	  return true;
	  	      } else {
	  	    	  System.exit(0);
	  	    	  in.close();
	  	    	  return false;
	  	      }
	  	    }
	  	    catch (IOException e) {
	  	      e.printStackTrace();
	  	      System.exit(0);
	  	      return false;
	  	    }
	  }
    
    public int writeStat(StatBase p_77444_1_)
    {
        TupleIntJsonSerializable var2 = (TupleIntJsonSerializable)this.field_150875_a.get(p_77444_1_);
        return var2 == null ? 0 : var2.getIntegerValue();
    }

    public IJsonSerializable func_150870_b(StatBase p_150870_1_)
    {
        TupleIntJsonSerializable var2 = (TupleIntJsonSerializable)this.field_150875_a.get(p_150870_1_);
        return var2 != null ? var2.getJsonSerializableValue() : null;
    }

    public IJsonSerializable func_150872_a(StatBase p_150872_1_, IJsonSerializable p_150872_2_)
    {
        TupleIntJsonSerializable var3 = (TupleIntJsonSerializable)this.field_150875_a.get(p_150872_1_);

        if (var3 == null)
        {
            var3 = new TupleIntJsonSerializable();
            this.field_150875_a.put(p_150872_1_, var3);
        }

        var3.setJsonSerializableValue(p_150872_2_);
        return p_150872_2_;
    }
}

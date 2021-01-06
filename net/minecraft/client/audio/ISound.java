package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;

public interface ISound
{
    ResourceLocation getSoundLocation();

    boolean canRepeat();

    int getRepeatDelay();

    float getVolume();

    float getPitch();

    float getXPosF();

    float getYPosF();

    float getZPosF();

    ISound.AttenuationType getAttenuationType();

    public static enum AttenuationType
    {
        NONE("NONE", 0, 0),
        LINEAR("LINEAR", 1, 2);
        private final int type;

        private AttenuationType(String p_i45110_1_, int p_i45110_2_, int p_i45110_3_)
        {
            this.type = p_i45110_3_;
        }

        public int getTypeInt()
        {
            return this.type;
        }
    }
}

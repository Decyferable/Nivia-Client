package net.minecraft.client.resources.data;

import com.google.common.collect.Lists;
import com.google.gson.*;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TextureMetadataSectionSerializer extends BaseMetadataSectionSerializer
{

    public TextureMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_)
    {
        JsonObject var4 = p_deserialize_1_.getAsJsonObject();
        boolean var5 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "blur", false);
        boolean var6 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "clamp", false);
        ArrayList var7 = Lists.newArrayList();

        if (var4.has("mipmaps"))
        {
            try
            {
                JsonArray var8 = var4.getAsJsonArray("mipmaps");

                for (int var9 = 0; var9 < var8.size(); ++var9)
                {
                    JsonElement var10 = var8.get(var9);

                    if (var10.isJsonPrimitive())
                    {
                        try
                        {
                            var7.add(Integer.valueOf(var10.getAsInt()));
                        }
                        catch (NumberFormatException var12)
                        {
                            throw new JsonParseException("Invalid texture->mipmap->" + var9 + ": expected number, was " + var10, var12);
                        }
                    }
                    else if (var10.isJsonObject())
                    {
                        throw new JsonParseException("Invalid texture->mipmap->" + var9 + ": expected number, was " + var10);
                    }
                }
            }
            catch (ClassCastException var13)
            {
                throw new JsonParseException("Invalid texture->mipmaps: expected array, was " + var4.get("mipmaps"), var13);
            }
        }

        return new TextureMetadataSection(var5, var6, var7);
    }

    /**
     * The name of this section type as it appears in JSON.
     */
    public String getSectionName()
    {
        return "texture";
    }
}

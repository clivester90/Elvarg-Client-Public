package com.runescape.cache.textures;

import com.runescape.cache.FileArchive;
import com.runescape.collection.Deque;
import com.runescape.io.Buffer;

public class TextureProvider implements TextureLoader {

    private final Texture[] textures;

    private Deque deque;

    private int capacity;

    private int remaining;

    private double brightness;

    private int textureSize;

    private final FileArchive archive;

    public TextureProvider(FileArchive textureArchive, FileArchive configArchive, int capacity, int textureSize) {
        deque = new Deque();
        archive = textureArchive;
        this.capacity = capacity;
        this.remaining = this.capacity;
        this.textureSize = textureSize;
        int textureCount;

        Buffer stream = new Buffer(configArchive.readFile("textures.dat"));
        textureCount = stream.readUShort();

        textures = new Texture[textureCount + 1];

        for (int var9 = 0; var9 < textureCount; ++var9) {
            Texture text =  new Texture(stream);
            textures[text.id] = text;
        }

    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
        clear();
    }

    public int[] getTexturePixels(int textureID) {
        Texture texture = textures[textureID];
        if (texture != null) {
            if (texture.pixels != null) {
                deque.insertTail(texture);
                texture.isLoaded = true;
                return texture.pixels;
            }

            boolean hasLoaded = texture.load(brightness, textureSize, archive);
            if (hasLoaded) {
                if (remaining == 0) {
                    Texture currentTexture = (Texture)deque.popHead();
                    currentTexture.reset();
                } else {
                    --remaining;
                }

                deque.insertTail(texture);
                texture.isLoaded = true;
                return texture.pixels;
            }
        }

        return null;
    }

    public int getAverageTextureRGB(int textureID) {
        return textures[textureID] != null ? textures[textureID].averageRGB : 0;
    }

    public boolean isTransparent(int textureID) {
        return textures[textureID].isTransparent;
    }

    public void clear() {
        for (Texture texture : textures) {
            if (texture != null) {
                texture.reset();
            }
        }

        deque = new Deque();
        remaining = capacity;
    }

    public void animate(int textureID) {
        for (Texture texture : textures) {
            if (texture != null && texture.animationDirection != 0 && texture.isLoaded) {
                texture.animate(textureID);
                texture.isLoaded = false;
            }
        }
    }

    @Override
    public boolean isLowDetail(int textureID) {
        return textureSize == 64;
    }

}

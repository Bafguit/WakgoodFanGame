package com.fastcat.labyrintale.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.AllArgsConstructor;
import lombok.Data;

/***
 * Simple wrapper of {@link com.badlogic.gdx.graphics.g2d.Animation<com.badlogic.gdx.graphics.g2d.Sprite>}
 */
@AllArgsConstructor
@Data
public class Gif {

    private Animation<Sprite> gif;
}

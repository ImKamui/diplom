/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainPackage;

import java.sql.Blob;
import javax.swing.ImageIcon;

/**
 *
 * @author danil
 */
public class ModuleContent {
        public final String text;
        public final byte[] imageBytes;
        public final boolean isTitle;

        public ModuleContent(String text, byte[] imageBytes, boolean isTitle) {
            this.text = text;
            this.imageBytes = imageBytes;
            this.isTitle = isTitle;
        }

        public ImageIcon getImageIcon() {
            if (imageBytes == null) return null;
            return new ImageIcon(imageBytes);
        }
    }
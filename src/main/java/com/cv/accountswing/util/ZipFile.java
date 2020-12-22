/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author SAI
 */
public class ZipFile {

    public String zipFiles(String filePath, String fileName) {
        String zipFileName = " ";
        try {
            File file = new File(filePath + File.separator + fileName);
            zipFileName = fileName.replace(".json", "") + ".zip";

            FileOutputStream fos = new FileOutputStream(filePath + File.separator + zipFileName);
            try (ZipOutputStream zos = new ZipOutputStream(fos)) {
                zos.putNextEntry(new ZipEntry(file.getName()));
                byte[] bytes = Files.readAllBytes(Paths.get(filePath + File.separator + fileName));
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
                zos.close();
                

            }

        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", filePath);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
        return zipFileName;

    }
}

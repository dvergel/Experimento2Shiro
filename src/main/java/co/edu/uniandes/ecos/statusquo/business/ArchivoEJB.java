/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.ecos.statusquo.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Dev
 */
@Stateless
@LocalBean
public class ArchivoEJB {

    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            // TODO ajustar tamaño de archivos
        }

        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("No se pudo leer el archivo " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static byte[] getBytesFromInput(InputStream file) throws IOException {
        InputStream is = file;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        int next = is.read();
        while (next > -1) {
            bytes.write(next);
            next = is.read();
        }
        bytes.flush();
        return bytes.toByteArray();
    }

    public static void writeFile(byte[] data, String fileName) throws IOException {
        try {
            OutputStream out = new FileOutputStream(fileName);
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean delete(String filePath, boolean recursive) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return true;
        }

        if (!recursive || !file.isDirectory()) {
            return file.delete();
        }

        String[] list = file.list();
        for (int i = 0; i < list.length; i++) {
            if (!delete(filePath + File.separator + list[i], true)) {
                return false;
            }
        }

        return file.delete();
    }

    public void copyFile(String fileName, InputStream in) throws IOException {
        try {
            OutputStream out = new FileOutputStream(new File(fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

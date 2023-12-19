package ru.itis.Services;

import ru.itis.util.JDBCUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageService {
public byte[] setImage(InputStream inputStream){
    try {
        byte[] imageBytes = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        while ((bytesRead = inputStream.read(imageBytes)) != -1) {
            buffer.write(imageBytes, 0, bytesRead);
        }
        byte[] image = buffer.toByteArray();
        return image;
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
    public String getImage(String tableName, String name){
        ImageService imageService = new ImageService();
        try(PreparedStatement statement = JDBCUtil.getConnection().prepareStatement("SELECT image FROM " + tableName +
                " where name = ?")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            InputStream image = null;
            while (resultSet.next()) {
                image = resultSet.getBinaryStream("image");
            }if(image == null){
                return null;
            }
            return imageService.getBase64Image(image);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBase64Image(InputStream image) throws IOException {
        byte[] bytes = new byte[image.available()];
        image.read(bytes);
        image.close();
        return new String(java.util.Base64.getEncoder().encode(bytes));
    }
}

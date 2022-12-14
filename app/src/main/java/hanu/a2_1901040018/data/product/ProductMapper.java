package hanu.a2_1901040018.data.product;

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hanu.a2_1901040018.data.BaseMapper;

public class ProductMapper extends BaseMapper<ProductDto, ProductEntity> {

    @Override
    protected ProductDto jsonToDto(JSONObject json) {
        ProductDto productDto = null;
        try {
            int id = json.getInt("id");
            String thumbnail = json.getString("thumbnail");
            String name = json.getString("name");
            double unitPrice = json.getDouble("unitPrice");
            productDto = new ProductDto(id, thumbnail, name, unitPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productDto;
    }

    @Override
    public List<ProductEntity> cursorToEntities(Cursor cursor) {
        List<ProductEntity> products = new ArrayList<>();
        int idIndex = cursor.getColumnIndex("id");
        int thumbnailIndex = cursor.getColumnIndex("thumbnail");
        int nameIndex = cursor.getColumnIndex("name");
        int unitPriceIndex = cursor.getColumnIndex("unitPrice");
        int quantityIndex = cursor.getColumnIndex("quantity");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            String thumbnail = cursor.getString(thumbnailIndex);
            String name = cursor.getString(nameIndex);
            double unitPrice = cursor.getDouble(unitPriceIndex);
            int quantity = cursor.getInt(quantityIndex);
            products.add(new ProductEntity(id, thumbnail, name, unitPrice, quantity));
        }
        return products;
    }

    @Override
    public ContentValues entitiesToContentValue(ProductEntity productEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", productEntity.getId());
        contentValues.put("thumbnail", productEntity.getThumbnail());
        contentValues.put("name", productEntity.getName());
        contentValues.put("unitPrice", productEntity.getUnitPrice());
        contentValues.put("quantity", productEntity.getQuantity());
        return contentValues;
    }

}

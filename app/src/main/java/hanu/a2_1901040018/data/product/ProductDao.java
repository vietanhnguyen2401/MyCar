package hanu.a2_1901040018.data.product;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.List;

import hanu.a2_1901040018.data.DbHelper;

public class ProductDao {
    private static final String TABLE_PRODUCT = "product";
    private final SQLiteDatabase db;
    private static ProductDao instance;
    private final ProductMapper productMapper;

    private ProductDao(DbHelper dbHelper) {
        this.db = dbHelper.getWritableDatabase();
        productMapper = new ProductMapper();
    }


    public static ProductDao getInstance(DbHelper dbHelper) {
        if (instance == null)
            instance = new ProductDao(dbHelper);
        return instance;
    }

    public ProductEntity getById(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM  product where id = ?",
                new String[]{id + ""}, null);
        List<ProductEntity> products = productMapper.cursorToEntities(cursor);
        cursor.close();
        if (products.size() == 0) return null;
        return products.get(0);
    }

    @Override
    protected void finalize() throws Throwable {
        db.close();
        super.finalize();
    }

    public long insert(ProductEntity productEntity) {
        ContentValues contentValues = productMapper.entitiesToContentValue(productEntity);
        return db.insert(TABLE_PRODUCT, null, contentValues);
    }

    public boolean updateQuantity(int id, int newQuantity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("quantity", newQuantity);
        int affectedRow = db.update(TABLE_PRODUCT, contentValues, "id=?", new String[]{String.valueOf(id)});
        return affectedRow > 0;
    }

    public List<ProductEntity> getAll() {
        Cursor cursor = db.rawQuery("SELECT * FROM  product", null);
        List<ProductEntity> products = productMapper.cursorToEntities(cursor);
        cursor.close();
        return products;
    }

    public boolean deleteProductInCart(int id) {
        int affectedRow = db.delete(TABLE_PRODUCT, "id=?", new String[]{String.valueOf(id)});
        return affectedRow > 0;
    }
}

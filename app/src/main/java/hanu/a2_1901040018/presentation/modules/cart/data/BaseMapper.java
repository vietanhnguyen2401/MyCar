package hanu.a2_1901040018.presentation.modules.cart.data;

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<Dto, Entity> {



    protected abstract Dto jsonToDto(JSONObject json);

    public List<Dto> jsonArrayToDtos(JSONArray jsonArray) {
        int length = jsonArray.length();
        List<Dto> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            try {
                list.add(jsonToDto(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public abstract List<Entity> cursorToEntities(Cursor cursor);

    public abstract ContentValues entitiesToContentValue(Entity productEntity);
}

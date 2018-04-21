package com.demospeachjava.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import com.demospeachjava.model.People;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ContactHelper extends SQLiteOpenHelper {

    //Mensaje
    private static final String LOGTAG = "LOGTAG";
    public static final String DB_NAME = "demo.db";
    public static final int DB_VERSION = 1;

    //Tabla user
    public static final String TABLE_USER = "usuario";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_EMAIL = "email_user";
    public static final String COLUMN_USER_TOKEN = "token";

    public static final String CREATE_TABLE_USER =
            "CREATE TABLE "+ TABLE_USER + " ("+
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_EMAIL + " TEXT, " +
                    COLUMN_USER_TOKEN + " TEXT)";

    //Tabla contactos
    public static final String TABLE_CONTACT = "contactos";
    public static final String COLUMN_CONTACT_ID = "_id";
    public static final String COLUMN_CONTACT_USERID = "user_id";
    public static final String COLUMN_CONTACT_AVATAR = "avatar";
    public static final String COLUMN_CONTACT_FIRSNAME = "nombres";
    public static final String COLUMN_CONTACT_LASTNAME = "lastname";
    public static final String COLUMN_CONTACT_EMAIL = "email";
    public static final String COLUMN_CONTACT_PHONE = "telephone_private";
    public static final String COLUMN_CONTACT_CELL = "celular";
    public static final String COLUMN_CONTACT_ADDRESS = "address";
    public static final String COLUMN_CONTACT_STATE = "state";
    public static final String COLUMN_CONTACT_FECHA = "fecha";
    public static final String COLUMN_CONTACT_CI = "ci";
    public static final String COLUMN_CONTACT_TYPE = "tipo_contacto";
    public static final String COLUMN_CONTACT_ADMIN = "is_admin";
    public static final String COLUMN_CONTACT_PRIORITY = "is_priority";
    public static final String COLUMN_CONTACT_GENDER = "gender";

    public static final String CREATE_TABLA_CONTACT =
            "CREATE TABLE "+ TABLE_CONTACT + " ("+
                    COLUMN_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_CONTACT_USERID + " INTEGER NULL, " +
                    COLUMN_CONTACT_AVATAR + " BLOB NULL, " +
                    COLUMN_CONTACT_FIRSNAME + " TEXT NOT NULL," +
                    COLUMN_CONTACT_LASTNAME + " TEXT, " +
                    COLUMN_CONTACT_EMAIL + " TEXT NOT NULL, " +
                    COLUMN_CONTACT_PHONE + " TEXT NOT NULL, " +
                    COLUMN_CONTACT_CELL + " TEXT NOT NULL, " +
                    COLUMN_CONTACT_ADDRESS + " TEXT NOT NULL, " +
                    COLUMN_CONTACT_STATE + " TEXT, " +
                    COLUMN_CONTACT_FECHA + " TEXT, " +
                    COLUMN_CONTACT_CI + " TEXT, " +
                    COLUMN_CONTACT_TYPE + " TEXT, " +
                    COLUMN_CONTACT_ADMIN + " BOOL DEFAULT 0, " +
                    COLUMN_CONTACT_PRIORITY + " BOOL DEFAULT 0, " +
                    COLUMN_CONTACT_GENDER + " TEXT, "+
                    "FOREIGN KEY ("+ COLUMN_CONTACT_USERID +") REFERENCES "+ TABLE_USER +" ("+ COLUMN_USER_ID +"))";

    public static final String VALUE_ONE_CONTACT =
            "INSERT INTO " + TABLE_CONTACT + " VALUES (null, null, '',"+
                    " 'Proinfem', '', 'proinfem@gmail.com',"+
                    "  4445878, 68795841, 'Calle la trojes',"+
                    " 'ninguno', '', '', 'entidad publica', 0, 1,"+
                    " 'ninguno')";

    public static final String VALUE_TWO_CONTACT =
            "INSERT INTO " + TABLE_CONTACT + " VALUES (null, null, '',"+
                    " 'FELCV', '', 'proinfem@gmail.com',"+
                    "  4445878, 68795841, 'Calle la trojes',"+
                    " 'ninguno', '', '', 'entidad publica', 0, 1,"+
                    " 'ninguno')";
    //Tabla denuncia
    public static final String TABLE_COMPLAINT = "denuncia";
    public static final String COLUMN_COMPLAINT_ID = "_id";
    public static final String COLUMN_COMPLAINT_USERID = "user_id";
    public static final String COLUMN_COMPLAINT_REGISTERID = "register_id";
    public static final String COLUMN_COMPLAINT_COVERAGE = "coverage_health";
    public static final String COLUMN_COMPLAINT_LINK = "link_user";
    public static final String COLUMN_COMPLAINT_STORY = "story";

    public static final String CREATE_TABLE_COMPLAINT =
            "CREATE TABLE "+ TABLE_COMPLAINT + " ("+
                    COLUMN_COMPLAINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_COMPLAINT_USERID + " INTEGER NULL, " +
                    COLUMN_COMPLAINT_REGISTERID + " INTEGER NULL, " +
                    COLUMN_COMPLAINT_COVERAGE + " TEXT NOT NULL, " +
                    COLUMN_COMPLAINT_LINK + " TEXT NOT NULL, " +
                    COLUMN_COMPLAINT_STORY + " TEXT, " +
                    "FOREIGN KEY ("+ COLUMN_COMPLAINT_USERID +") REFERENCES "+ TABLE_USER +" ("+ COLUMN_USER_ID +"), "+
                    "FOREIGN KEY ("+ COLUMN_COMPLAINT_REGISTERID +") REFERENCES "+ TABLE_CONTACT +" ("+ COLUMN_CONTACT_ID +"))";

    public ContactHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLA_CONTACT);
        db.execSQL(VALUE_ONE_CONTACT);
        db.execSQL(VALUE_TWO_CONTACT);
        db.execSQL(CREATE_TABLE_COMPLAINT);
        Log.i(LOGTAG,"Tabla creada correctamente");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPLAINT);
        this.onCreate(db);
    }

    //Crear un nuevo contacto
    public void saveNewPeople(People people){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACT_AVATAR, people.getAvatar());
        values.put(COLUMN_CONTACT_FIRSNAME, people.getFirsname());
        values.put(COLUMN_CONTACT_LASTNAME, people.getLastname());
        values.put(COLUMN_CONTACT_EMAIL, people.getEmail());
        values.put(COLUMN_CONTACT_PHONE,
                people.getTelephone_private());
        values.put(COLUMN_CONTACT_CELL, people.getCell_phone());
        values.put(COLUMN_CONTACT_ADDRESS, people.getAddress());
        values.put(COLUMN_CONTACT_STATE, people.getState());
        values.put(COLUMN_CONTACT_FECHA, people.getFecha());
        values.put(COLUMN_CONTACT_CI, people.getCi());
        values.put(COLUMN_CONTACT_TYPE, people.getType_register());
        values.put(COLUMN_CONTACT_PRIORITY, people.getIs_priority());
        values.put(COLUMN_CONTACT_GENDER, people.getGender());

        //insert
        db.insert(TABLE_CONTACT,null, values);
        db.close();
    }

    //Buscar contacto por su nombre

    public People bucarPeople(String firsname){
        People people = new People();
        SQLiteDatabase db = this.getReadableDatabase();
        String where = COLUMN_CONTACT_FIRSNAME + "= ?";
        String[] whereArgs = {firsname};
        Cursor cursor = db.query(TABLE_CONTACT, null, where, whereArgs, null, null, null);

        if( cursor != null || cursor.getCount() <=0) {
            cursor.moveToFirst();
            people.setAvatar(cursor.getString(2));
            people.setFirsname(cursor.getString(3));
            people.setLastname(cursor.getString(4));
            people.setEmail(cursor.getString(5));
            people.setTelephone_private(cursor.getString(6));
            people.setCell_phone(cursor.getString(7));
            people.setAddress(cursor.getString(8));
            people.setState(cursor.getString(9));
            people.setFecha(cursor.getString(10));
            people.setCi(cursor.getString(11));
            people.setType_register(cursor.getString(12));
            people.setIs_admin(cursor.getShort(13));
            people.setIs_priority(cursor.getShort(14));
            people.setGender(cursor.getString(15));
            cursor.close();
        }
        db.close();
        return people;
    }

    //Lista contacto dando opciones de listarlos por su tipo
    public List<People> peopleList(String filter){
        String query;
        if (filter.equals("")){
            query = "SELECT * FROM "+TABLE_CONTACT;
        }else{
            query = "SELECT * FROM "+ TABLE_CONTACT +
                    " ORDER BY "+filter;
        }
        List<People> personLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        People people;
        if (cursor.moveToFirst()){
            do {
                people = new People();
                people.setId(cursor.getLong(cursor.
                        getColumnIndex(COLUMN_CONTACT_ID)));
                people.setUser_id(cursor.getLong(cursor.
                        getColumnIndex(COLUMN_CONTACT_USERID)));
                people.setAvatar(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_AVATAR)));
                people.setFirsname(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_FIRSNAME)));
                people.setLastname(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_LASTNAME)));
                people.setEmail(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_EMAIL)));
                people.setTelephone_private(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_PHONE)));
                people.setCell_phone(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_CELL)));
                people.setAddress(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_ADDRESS)));
                people.setState(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_STATE)));
                people.setFecha(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_FECHA)));
                people.setCi(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_CI)));
                people.setType_register(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_TYPE)));
                people.setIs_priority(cursor.getShort(cursor.
                        getColumnIndex(COLUMN_CONTACT_PRIORITY)));
                people.setGender(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_GENDER)));
                personLinkedList.add(people);
            }while (cursor.moveToNext());
        }
        return personLinkedList;
    }

    //Buscar a un solo persona
    public People getPeople(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_CONTACT + " WHERE _id="+
                id;
        Cursor cursor = db.rawQuery(query,null);
        People receivedPeople = new People();
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            receivedPeople.setUser_id(cursor.getLong(cursor.
                    getColumnIndex(COLUMN_CONTACT_USERID)));
            receivedPeople.setAvatar(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_AVATAR)));
            receivedPeople.setFirsname(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_FIRSNAME)));
            receivedPeople.setLastname(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_LASTNAME)));
            receivedPeople.setEmail(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_EMAIL)));
            receivedPeople.setTelephone_private(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_PHONE)));
            receivedPeople.setCell_phone(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_CELL)));
            receivedPeople.setAddress(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_ADDRESS)));
            receivedPeople.setState(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_STATE)));
            receivedPeople.setFecha(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_FECHA)));
            receivedPeople.setCi(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_CI)));
            receivedPeople.setType_register(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_TYPE)));
            receivedPeople.setIs_priority(cursor.getShort(cursor.
                    getColumnIndex(COLUMN_CONTACT_PRIORITY)));
            receivedPeople.setGender(cursor.getString(cursor.
                    getColumnIndex(COLUMN_CONTACT_GENDER)));
        }
        return receivedPeople;
    }
    //Eliminar people
    public void deletePeople(long id, Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_CONTACT+" WHERE _id = '"+id+"'");
        Toast.makeText(context, "Se elimino con exito.",
                Toast.LENGTH_SHORT).show();
    }
    //Actualizacion de people
    public void updatePeople(long peopleID, Context context,
                                    People updatedpeople){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE  "+TABLE_CONTACT+" SET avatar = '"+ updatedpeople.getAvatar() +
                "', firsname = '" + updatedpeople.getFirsname()+
                "', lastname = '"+ updatedpeople.getLastname() +
                "', email = '"+ updatedpeople.getEmail() +
                "', telephone_private = '" + updatedpeople.getTelephone_private() +
                "', cell_phone = '" + updatedpeople.getCell_phone() +
                "', address = '" + updatedpeople.getAddress() +
                "', state = '" + updatedpeople.getState() +
                "', fecha = '" + updatedpeople.getFecha() +
                "', ci= '" + updatedpeople.getCi() +
                "', type_register = '"+ updatedpeople.getType_register() +
                "', is_priority = '"+ updatedpeople.getIs_priority() +
                "', gender = '" + updatedpeople.getGender() +
                "'  WHERE _id= '" + peopleID + "'");

        Toast.makeText(context, "Se actualizo con exito.",
                Toast.LENGTH_SHORT).show();
    }
    //Listar numero de telefono de contactos con permisos
    public List<People> listPeoplePhone(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<People> personPriority = new ArrayList<People>();
        String query = "SELECT  * FROM " + TABLE_CONTACT + " WHERE is_priority="+
                1;
        Cursor cursor = db.rawQuery(query, null);
        People people;
        if (cursor.moveToFirst()){
            do {
                people = new People();
                people.setId(cursor.getLong(cursor.
                        getColumnIndex(COLUMN_CONTACT_ID)));
                people.setUser_id(cursor.getLong(cursor.
                        getColumnIndex(COLUMN_CONTACT_USERID)));
                people.setAvatar(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_AVATAR)));
                people.setFirsname(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_FIRSNAME)));
                people.setLastname(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_LASTNAME)));
                people.setEmail(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_EMAIL)));
                people.setTelephone_private(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_PHONE)));
                people.setCell_phone(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_CELL)));
                people.setAddress(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_ADDRESS)));
                people.setState(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_STATE)));
                people.setFecha(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_FECHA)));
                people.setCi(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_CI)));
                people.setType_register(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_TYPE)));
                people.setIs_priority(cursor.getShort(cursor.
                        getColumnIndex(COLUMN_CONTACT_PRIORITY)));
                people.setGender(cursor.getString(cursor.
                        getColumnIndex(COLUMN_CONTACT_GENDER)));
                personPriority.add(people);
            }while (cursor.moveToNext());
        }
        return personPriority;
    }
}

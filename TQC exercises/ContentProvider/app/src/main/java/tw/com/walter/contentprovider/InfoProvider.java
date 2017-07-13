package tw.com.walter.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class InfoProvider extends ContentProvider {
	public static final String PROVIDER_NAME = "COM.TQC.GAD01";

	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ PROVIDER_NAME + "/Info");

	public static final String _ID = "_id";
	public static final String NAME = "name";
	public static final String VALUE = "value";

	private static final int INFOS = 1;
	private static final int INFOS_ID = 2;

	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(PROVIDER_NAME, "Info", INFOS);
		uriMatcher.addURI(PROVIDER_NAME, "Info/#", INFOS_ID);
	}

	// ---for database use---
	private SQLiteDatabase InfoDB;
	private static final String DATABASE_NAME = "Info";
	private static final String DATABASE_TABLE = "Infos";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_CREATE = "create table Infos (_id text not null , name text not null, value text not null);";
	//private static final String DATABASE_CREATE = "create table Infos (_id integer primary key autoincrement , name text not null, value text not null);";
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			//create database use db.execSQL(
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("Content provider db" ,
					"Upgrading database from version " + oldVersion + " to "
							+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS Infos");
			onCreate(db);
		}
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// arg0 = uri
		// arg1 = selection
		// arg2 = selectionArgs
		int count = 0;
		switch (uriMatcher.match(arg0)) {
		case INFOS:
			count = InfoDB.delete(DATABASE_TABLE,arg1,arg2);
			break;
		case INFOS_ID:
			String id = arg0.getPathSegments().get(1);
			count = InfoDB.delete(DATABASE_TABLE,_ID + " = " + id +
					(!TextUtils.isEmpty(arg1)?"AND (" + arg1 + '+' :""),arg2);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + arg0);
		}
		getContext().getContentResolver().notifyChange(arg0,null);
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		// ---get all books---
		case INFOS:
			return "vnd.android.cursor.dir/COM.TQC.GAD01";
			// ---get a particular book---
		case INFOS_ID:
			return "vnd.android.cursor.item/COM.TQC.GAD01";
		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		long rowID = InfoDB.insert(DATABASE_TABLE,"",values);

		if (rowID > 0) {
			Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
			getContext().getContentResolver().notifyChange(_uri, null);
			return _uri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public boolean onCreate() {
		Context context = getContext();
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		InfoDB = dbHelper.getWritableDatabase();
		return (InfoDB == null) ? false : true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder sqlBuilder = new SQLiteQueryBuilder();
		sqlBuilder.setTables(DATABASE_TABLE);

		if (uriMatcher.match(uri) == INFOS_ID)

			sqlBuilder.appendWhere(_ID + " = " + uri.getPathSegments().get(1));

		if (sortOrder == null || sortOrder == "")
			sortOrder = NAME;

		Cursor c = sqlBuilder.query(InfoDB,projection,selection,selectionArgs,null,null,sortOrder);

		c.setNotificationUri(getContext().getContentResolver(),uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int count = 0;
		switch (uriMatcher.match(uri)) {
		case INFOS:
			count=InfoDB.update(DATABASE_TABLE,values,selection,selectionArgs);
			break;
		case INFOS_ID:
			count = InfoDB.update(DATABASE_TABLE,values,_ID
														+ " = "
														+ uri.getPathSegments().get(1)
														+ (!TextUtils.isEmpty(selection)? "AND (" + selection + ')':""),selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri,null);
		return count;
	}
}
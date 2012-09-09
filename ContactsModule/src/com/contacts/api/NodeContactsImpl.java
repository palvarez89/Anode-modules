package com.contacts.api;


import java.util.ArrayList;

import org.meshpoint.anode.AndroidContext;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.util.Log;

public class NodeContactsImpl extends NodeContacts implements IModule {

	private Context androidContext;
	int beepTimes=0;
	Thread t;
	Uri soundUri;
	Ringtone r;
	int duracion;
	
	ArrayList<Contacto> datos = new ArrayList<Contacto>();

	class Contacto {
		String Name = null;;
		ArrayList<String> Numbers = null;

		public Contacto() {
			Name = new String();
			Numbers = new ArrayList<String>();
		}

		public Contacto(String name, String number) {
			Name = new String();
			Numbers = new ArrayList<String>();
			Name = name;
			Numbers.add(number);
		}

		public void setContact(String name, String number) {
			Name = name;
			Numbers.add(number);
		}

		public void addNumber(String number) {
			Numbers.add(number);
		}

		public ArrayList<String> getNumbers() {
			return Numbers;
		}

		public String getName() {
			return Name;
		}

	}




	/**************************
	 * Module lifecycle methods
	 **************************/
	@Override
	public Object startModule(IModuleContext ctx) {
		androidContext = ((AndroidContext)ctx).getAndroidContext();
		Thread contacts = new Thread(new Runnable() {
			public void run() {

				ContentResolver contentresolver = androidContext.getContentResolver();

				Cursor mCursor = contentresolver.query(Data.CONTENT_URI,
						new String[] { Data._ID, Data.DISPLAY_NAME,
								Phone.NUMBER, Phone.TYPE }, Data.MIMETYPE
								+ "='" + Phone.CONTENT_ITEM_TYPE + "' AND "
								+ Phone.NUMBER + " IS NOT NULL", null,
						Data.DISPLAY_NAME + " ASC");

//				startManagingCursor(mCursor);

				// Iteramos sobre la lista de resultados a través del cursor.

				String lastName = new String();
				lastName = "";
				int nameIndex = mCursor
						.getColumnIndexOrThrow(Data.DISPLAY_NAME);
				int numberIndex = mCursor.getColumnIndexOrThrow(Phone.NUMBER);
				String name = null;
				String number = null;
				Contacto CurrentContact = null;
				if (mCursor.moveToFirst()) {
					do {
						name = mCursor.getString(nameIndex);
						number = mCursor.getString(numberIndex);

						// Log.w("myapp",name);

						if (lastName.equals(name)) {
//							Log.w("myapp", name + " = " + lastName);
							CurrentContact.addNumber(number);
						} else {
							if (CurrentContact != null)
								datos.add(CurrentContact);
							CurrentContact = new Contacto(name, number);

						}
						// datos.add(new Contacto(name, number));

						lastName = name;
					} while (mCursor.moveToNext());
					if (CurrentContact != null)
						datos.add(CurrentContact);
				}

				mCursor.close();
				Log.w("ANODEEEE","Carga de contactos finalizada");
			}

		});
		contacts.start();
		/*
		 * perform any module initialisation here ...
		 */

		return this;
	}


	/**************************
	 * NodeContacts methods
	 **************************/
	
	@Override
	public void stopModule() {
		/*
		 * perform any module shutdown here ...
		 */
	}


	@Override
	public int size() {
		return datos.size();
	}


	@Override
	public String getName(int index) {
		return datos.get(index).getName();
	}


	@Override
	public String[] getNumbers(int index) {
		ArrayList<String> con = datos.get(index).getNumbers();
		String[] nums = new String[con.size()];
		for(int i=0;i<con.size();i++){
			nums[i]=con.get(i);			
		}
		return nums;
	}

}

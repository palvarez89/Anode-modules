package com.camera.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.meshpoint.anode.AndroidContext;
import org.meshpoint.anode.module.IModule;
import org.meshpoint.anode.module.IModuleContext;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.util.Log;

public class NodeCameraImpl extends NodeCamera implements IModule {
	String TAG = "ANODEEEE";
	boolean saved,error;
	String path,name;
	Camera camera;
	private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
	// private String mCurrentPhotoPath;

	// private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
	private Context androidContext;

	/**************************
	 * Module lifecycle methods
	 **************************/
	@Override
	public Object startModule(IModuleContext ctx) {
		path=null;
		name = null;
		androidContext = ((AndroidContext) ctx).getAndroidContext();
		mAlbumStorageDirFactory = new BaseAlbumDirFactory();

		/*
		 * perform any module initialisation here ...
		 */

		return this;
	}

	@Override
	public void stopModule() {
		/*
		 * perform any module shutdown here ...
		 */
	}

	/**************************
	 * NodeSMS methods
	 **************************/

	@Override
	public void makePhoto() {
		path=null;
		name = null;

		Log.w(TAG, "Cogiendo camara");
		saved = false;
		error = false;

		try
	     {
	        camera  = Camera.open();
	     }
	     catch (Exception e)
	     {
	        Log.e(TAG,"Problem opening camera! " + e);
	        return;
	     }
		Camera.Parameters parameters = camera.getParameters();
		parameters.setPictureFormat(PixelFormat.JPEG);
		List<Size> sizes = parameters.getSupportedPictureSizes();
		Size mSize = sizes.get(0);
		parameters.setPictureSize(mSize.width, mSize.height);
		camera.setParameters(parameters);
		Log.w(TAG, mSize.width + " x " + mSize.height);

		camera.startPreview();

		/** Handles data for jpeg picture */
		PictureCallback jpegCallback = new PictureCallback() {
			File storageDir = mAlbumStorageDirFactory
					.getAlbumStorageDir("Camera");
			public void onPictureTaken(byte[] data, Camera camera) {
				FileOutputStream outStream = null;
				try {
					long time = System.currentTimeMillis();
					name = String.format("%d.jpg",time);
					Log.w(TAG, "onPictureTaken1 - jpeg");
					// outStream = new FileOutputStream(storageDir);
					path = storageDir.getPath() + "/" + name;
					outStream = new FileOutputStream(path);
					outStream.write(data);
					outStream.close();
					Log.w(TAG, "onPictureTaken - wrote bytes: " + data.length
							+ " in: " + path);
				} catch (FileNotFoundException e) {
					error=true;
					e.printStackTrace();
				} catch (IOException e) {
					error=true;
					e.printStackTrace();
				} finally {
				}
				Log.w(TAG, "onPictureTaken2 - jpeg");
				saved = true;
			}
		};

		camera.takePicture(null, null, jpegCallback);

		//Thread that wait for at end of Camera use.
		Thread t = new Thread() {
			public void run() {
				while (!saved && !error) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				camera.release();
				Log.w(TAG, "Camara liberada");
			}

		};
		t.start();

	}

	@Override
	public String getPath() {
		while (!saved && !error) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return path;
	}

	@Override
	public String getName() {
		while (!saved && !error) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return name;
	}

}

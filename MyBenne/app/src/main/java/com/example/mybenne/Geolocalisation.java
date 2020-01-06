package com.example.mybenne;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

public class Geolocalisation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location mLocation;
    Button BetaMairie;
    DatabaseManager db = new DatabaseManager(this);
    SharedPreferences permissionStatus;
    private boolean sentToSettings = false;
    private static final int SMS_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BetaMairie = findViewById(R.id.BetamoveBenne);
        BetaMairie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 *  NUM DESTINATAIRE
                 */
                Cursor colStrArr1 = db.getDataNom("destinataire");
                colStrArr1.moveToFirst();
                Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(colStrArr1));
                String destnumber = colStrArr1.getString(2);
                Toast.makeText(Geolocalisation.this, destnumber , Toast.LENGTH_LONG).show();


                /**
                 *  INFO BENNE
                 */
                String idBenne = "1";
                String posbenne = "49.847177, 3.287543";

                /**
                 *  INFO UTILISATEUR
                 */
                Cursor colStrArr2 = db.getDataNom("utilisateur");
                colStrArr1.moveToFirst();
                Log.v("Cursor Object", DatabaseUtils.dumpCursorToString(colStrArr1));
                String userinfo = "utilisateur :" +
                        colStrArr1.getString(0) +
                        colStrArr1.getString(1) +
                        colStrArr1.getString(2);



                String message = "Benne n°" + idBenne + " Aux coordonnées " + posbenne + "mise à jour par : " + userinfo;
                SendMessage(destnumber, message);


                if (ActivityCompat.checkSelfPermission(Geolocalisation.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Geolocalisation.this, Manifest.permission.SEND_SMS)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Geolocalisation.this);
                        builder.setTitle("Need SMS Permission");
                        builder.setMessage("This app needs SMS permission to send Messages.");
                        builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                ActivityCompat.requestPermissions(Geolocalisation.this,
                                        new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CONSTANT);
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    } else {
                        ActivityCompat.requestPermissions(Geolocalisation.this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CONSTANT);
                    }

//                    SharedPreferences.Editor editor = permissionStatus.edit();
//                    editor.putBoolean(Manifest.permission.SEND_SMS, true);
//                    editor.commit();

                }
                /**
                 * SMS
                 */

//                SmsManager.getDefault().sendTextMessage(destnumber,null,
//                        "Benne n°" + idBenne +
//                        " Aux coordonnées " + posbenne +
//                        "mise à jour par : " + userinfo,null,null);
                getCoord();
            }
        });
    }
//    boolean isInserted=db.insertDataBenne(BENNE_QR_CODE.getText().toString(), BENNE_POSITION.getText().toString());
//    boolean B1=db.insertDataBenne("1","", "49.847177, 3.287543");
//    boolean B2=db.insertDataBenne("2","", "49.846054, 3.286342");
//    boolean B3=db.insertDataBenne("3","", "49.847342, 3.287045");
//    boolean B4=db.insertDataBenne("4","", "49.845640, 3.290009");
//    boolean B5=db.insertDataBenne("5","", "49.845280, 3.284628");
    public void SendMessage(String strMobileNo, String strMessage) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(strMobileNo, null, strMessage, null, null);
            Toast.makeText(getApplicationContext(), "Your Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(Geolocalisation.this, "if you agreed permissions , repress the send button" , Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng BenneMairie = new LatLng(49.847177, 3.287543);
//        LatLng BenneMairie = new LatLng(Double.parseDouble(db.getDataBenne("1")[0]),Double.parseDouble(db.getDataBenne("1")[1]));
        LatLng BenneHotelMemorial = new LatLng(49.846054, 3.286342);
        LatLng BenneGoldenPub = new LatLng(49.847342, 3.287045);
        LatLng BenneBoulanderiePaul = new LatLng(49.845640, 3.290009);
        LatLng BenneEcoleLyon = new LatLng(49.845280, 3.284628);
        mMap.addMarker(new MarkerOptions().position(BenneMairie).title("Benne de la Mairie"));
        mMap.addMarker(new MarkerOptions().position(BenneHotelMemorial).title("Benne de l'Hotel Memorial"));
        mMap.addMarker(new MarkerOptions().position(BenneGoldenPub).title("Benne du Golden Pub"));
        mMap.addMarker(new MarkerOptions().position(BenneBoulanderiePaul).title("Benne de la boulangerie Paul"));
        mMap.addMarker(new MarkerOptions().position(BenneEcoleLyon).title("Benne de l'Ecole Lyon"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(BenneMairie));
    }
    public void getCoord(){

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10,this);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10,this);

        }else if(locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)){
            if(Build.VERSION.SDK_INT >= 23) {
                if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //Permission refusée je ne fais rien
                }else{
                    mLocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                }
            }else{
                mLocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            }
        }else{
            Toast.makeText(Geolocalisation.this, "Impossible de récupérer vos coordonnées", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        if(location != null){
            mLocation = location;
            Log.d("APP", "Lat = " + mLocation.getLatitude() + " Longitude = " + mLocation.getLongitude());

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}



